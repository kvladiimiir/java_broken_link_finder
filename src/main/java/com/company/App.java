package com.company;

import com.company.brokenLinkChecker.BrokenLinkChecker;
import com.company.dataReader.DataReader;
import com.company.enums.InputDataType;
import com.company.htmlParser.HtmlParser;
import com.company.linkResponseInfo.LinkResponseInfo;
import com.company.reportExporter.ReportExporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

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

    public static void main( String[] args ) throws IOException, ExecutionException, InterruptedException {
        ArrayList<String> inputArgs = convertStringArrayToArrayList(args);

        DataReader dataReader = new DataReader(inputArgs);
        dataReader.checkInputData();
        ArrayList<String> links = dataReader.getLinks();
        InputDataType dataType = dataReader.getDataType();

        String outputFileName = dataReader.getOutputFileName();

        ArrayList<LinkResponseInfo> brokenLinks = new ArrayList<>();

        HtmlParser htmlParser = new HtmlParser();
        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

        for (String link: links) {
            ArrayList<String> parsedLinks = htmlParser.getUrlsFromPage(dataType, link);
            brokenLinks.addAll(brokenLinkChecker.getBrokenLinks(parsedLinks));
        }

        ReportExporter reportExporter = new ReportExporter();
        reportExporter.execute(outputFileName, brokenLinks);
        System.out.println(String.format("Found %d broken links, for details check file \"%s\"", links.size(), outputFileName));

        /*for (var i: brokenLinks) {
            System.out.println(i.getUri() + " " + i.getNumberStatusCode() + " " + i.getTextStatusCode());
        }*/
    }
}