package com.company.dataReader;

import com.company.enums.InputDataType;

import java.util.ArrayList;
import java.util.Collections;

public class DataReader {
    public DataReader(ArrayList<String> inputArgs) {
        this.inputArgs = inputArgs;
    }

    private int getIndexAndCheckLinks() {
        int index = 1;
        for (; index < inputArgs.size(); index++) {
            String argument = inputArgs.get(index);
            if (argument.equals(outWord)) {
                break;
            }
            links.add(inputArgs.get(index));
        }

        return index;
    }

    public void checkInputData() {
        String firstArg = inputArgs.get(0);

        if (firstArg.equals(linksWord)) {
            this.dataType = InputDataType.LINKS;
        } else if (firstArg.equals(filesWord)) {
            this.dataType = InputDataType.FILES;
        } else {
            throw new IllegalArgumentException("Error! Input arguments contain --files or --links");
        }

        int currIndex = getIndexAndCheckLinks();

        if (currIndex + 1 == inputArgs.size()) {
            throw new IllegalArgumentException("Error! Input arguments contain --out");
        }

        if (links.isEmpty()) {
            throw new IllegalArgumentException("Error! Number links = 0!");
        }

        for(String link : links) {
            if(Collections.frequency(links, link) > 1) {
                throw new IllegalArgumentException("Error! Duplicate links found.");
            }
        }

        if (!inputArgs.get(currIndex - 1).equals(outWord) && currIndex == inputArgs.size()) {
            throw new IllegalArgumentException("Error! Input arguments contain --out file");
        }

        outputFileName = inputArgs.get(currIndex + 1);
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public InputDataType getDataType() {
        return dataType;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    private ArrayList<String> links = new ArrayList<>();
    private String outputFileName = "";
    private ArrayList<String> inputArgs = new ArrayList<>();
    private InputDataType dataType = InputDataType.NONE;
    private final String filesWord = "--files";
    private final String linksWord = "--links";
    private final String outWord = "--out";
}
