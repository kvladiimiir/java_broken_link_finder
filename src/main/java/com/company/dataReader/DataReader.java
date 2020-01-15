package com.company.dataReader;

import java.util.ArrayList;

public class DataReader {
    public DataReader(ArrayList<String> inputArgs) {
        this.inputArgs = inputArgs;
    }

    private int GetIndexAndCheckLinks() {
        int i = 2;
        for (; i < inputArgs.size(); i++) {
            String argument = inputArgs.get(i);
            if (argument.equals(outWord)) {
                break;
            }
            links.add(inputArgs.get(i));
        }

        return i;
    }

    public void IsValidInputData() {
        String firstArg = inputArgs.get(1);
        if (!firstArg.equals(filesWord)) {
            throw new IllegalArgumentException("Input error!");
        }

        int currIndex = GetIndexAndCheckLinks();
        if (inputArgs.get(currIndex).equals(outWord)) {
            outputFileName = inputArgs.get(currIndex + 1);
        } else {
            throw new IllegalArgumentException("Input error!");
        }
    }

    public ArrayList<String> GetLinks() {
        return links;
    }

    public String GetOutputFileName() {
        return outputFileName;
    }

    private ArrayList<String> links = new ArrayList<String>();
    private String filesWord = "--files";
    private String outWord = "--out";
    private String outputFileName = "";
    private ArrayList<String> inputArgs = new ArrayList<String>();
}
