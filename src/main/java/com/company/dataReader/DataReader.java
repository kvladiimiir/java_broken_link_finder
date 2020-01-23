package com.company.dataReader;

import java.util.ArrayList;
import java.util.Collections;

public class DataReader {
    public DataReader(ArrayList<String> inputArgs) {
        this.inputArgs = inputArgs;
    }

    private int getIndexAndCheckLinks() {
        int index = 2;
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
        String firstArg = inputArgs.get(1);
        if (!firstArg.equals(filesWord)) {
            throw new IllegalArgumentException("Input error!");
        }

        int currIndex = getIndexAndCheckLinks();

        if (currIndex + 1 == inputArgs.size()) {
            throw new IllegalArgumentException("Input error!");
        }

        if (links.isEmpty()) {
            throw new IllegalArgumentException("Input error!");
        }

        if (!inputArgs.get(currIndex - 1).equals(outWord) && currIndex == inputArgs.size()) {
            throw new IllegalArgumentException("Input error!");
        }

        outputFileName = inputArgs.get(currIndex + 1);
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    private ArrayList<String> links = new ArrayList<String>();
    private String outputFileName = "";
    private ArrayList<String> inputArgs = new ArrayList<String>();
    private final String filesWord = "--files";
    private final String outWord = "--out";
}
