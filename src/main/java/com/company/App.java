package com.company;

import com.company.dataReader.DataReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class App
{
    /*brokenLinkFinder --files page1.html page2.html --out report.csv*/
    public static ArrayList<String> ConvertStringArrayToArrayList(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Input error!");
        }
        return new ArrayList<>(Arrays.asList(arguments));
    }

    public static void main( String[] args )
    {
        ArrayList<String> inputArgs = ConvertStringArrayToArrayList(args);

        DataReader dataReader = new DataReader(inputArgs);
        dataReader.CheckInputData();
        ArrayList<String> links = dataReader.GetLinks();

        String outputFileName = dataReader.GetOutputFileName();
        System.out.println("OUTPUT FILE NAME: " + outputFileName);

        for (var link: links) {
            HtmlParser htmlParser = new HtmlParser();
            ArrayList<String> parsedLinks =
                    htmlParser.getUrlsFromPage(link);

            BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

            Map<String, Integer> brokenLinksMap = brokenLinkChecker.getBrokenLinksMap(parsedLinks);

            for (var item: brokenLinksMap.entrySet()) {
                System.out.println(item.getKey() + " " + item.getValue() + " " + brokenLinkChecker.GetStatusCodeText(item.getValue()));
            }
        }
    }
}

