package com.company;

import com.company.dataReader.DataReader;

import java.util.ArrayList;
import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
        ArrayList<String> inputArgs = new ArrayList<String>();
        Collections.addAll(inputArgs, args);

        DataReader dataReader = new DataReader(inputArgs);
        try {
            dataReader.IsValidInputData();
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
