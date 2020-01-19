package com.company;


import com.company.dataReader.DataReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class App
{
    /*brokenLinkFinder --files page1.html page2.html --out report.csv*/
    public static ArrayList<String> ConvertStringArrayToArrayList(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Input error!");
        }
        ArrayList<String> inputArgs = new ArrayList<>(Arrays.asList(arguments));
        return inputArgs;
    }

    public static void main( String[] args )
    {
        try {
            ArrayList<String> inputArgs = ConvertStringArrayToArrayList(args);

            DataReader dataReader = new DataReader(inputArgs);
            dataReader.CheckInputData();
            ArrayList<String> links = dataReader.GetLinks();
            System.out.print("LINKS: ");
            for (String link: links) {
                System.out.print(link + " ");
            }
            System.out.println();
            String outputFileName = dataReader.GetOutputFileName();
            System.out.println("OUTPUT FILE NAME: " + outputFileName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        HtmlParser htmlParser = new HtmlParser();
        ArrayList<String> links =
                htmlParser.getUrlsFromPage("http://google.com");

        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

        Map<String, Integer> brokenLinksMap = brokenLinkChecker.getBrokenLinksMap(links);

        for (var item: brokenLinksMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue() + " " + brokenLinkChecker.GetStatusCodeText(item.getValue()));
        }
    }
}

