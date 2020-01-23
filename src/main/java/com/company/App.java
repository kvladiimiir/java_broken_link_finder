package com.company;

import com.company.brokenLinkChecker.BrokenLinkChecker;
import com.company.dataReader.DataReader;
import com.company.htmlParser.HtmlParser;
import com.company.utils.StatusCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App
{
    /*brokenLinkFinder --files page1.html page2.html --out report.csv*/
    public static ArrayList<String> convertStringArrayToArrayList(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Input error!");
        }
        return new ArrayList<>(Arrays.asList(arguments));
    }
    //надо вынести наверн тоже

    public static void main( String[] args ) {
        ArrayList<String> inputArgs = convertStringArrayToArrayList(args);

        DataReader dataReader = new DataReader(inputArgs);
        dataReader.checkInputData();
        ArrayList<String> links = dataReader.getLinks();

        String outputFileName = dataReader.getOutputFileName();
        //System.out.println("OUTPUT FILE NAME: " + outputFileName);

        Map<String, Integer> brokenLinksMap = new HashMap<>();
        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();
        StatusCodeUtils statusCodeUtils = new StatusCodeUtils();
        HtmlParser htmlParser = new HtmlParser();

        for (String link: links) {
            ArrayList<String> parsedLinks = htmlParser.getUrlsFromPage(link);

            brokenLinksMap.putAll(brokenLinkChecker.getBrokenLinksMap(parsedLinks));
        }

        for (Map.Entry<String, Integer> item: brokenLinksMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue() + " " + statusCodeUtils.getStatusCodeText(item.getValue()));
        }
    }
}

