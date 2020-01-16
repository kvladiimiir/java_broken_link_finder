package com.company;

import com.company.dataReader.DataReader;

import java.util.ArrayList;
import java.util.Collections;

public class App 
{
    /*brokenLinkFinder --files page1.html page2.html --out report.csv*/
    public static ArrayList<String> ConvertStringArrayToArrayList(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException("Input error!");
        }
        ArrayList<String> inputArgs = new ArrayList<String>();
        Collections.addAll(inputArgs, arguments);
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

    }
}
