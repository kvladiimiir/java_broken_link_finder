package com.company;

import com.company.dataReader.DataReader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DataReaderTest {
    @Test
    public void CheckInputData_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
        /* assert */
        assertTrue( dataReader.getOutputFileName() == "report.csv" );
    }

    @Test
    public void CheckLinks_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
        /* assert */
        assertTrue( dataReader.getLinks().get(0) == "page1.html" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_ErrorWordFilesArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "-files", "page1.html", "page2.html", "--out", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_ErrorWordOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--ot", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_TwoOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--ot", "report.csv", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_EmptyOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_EmptyFilesArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "--out", "report.csv"};
        App app = new App();
        ArrayList<String> exampleArguments = app.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }
}
