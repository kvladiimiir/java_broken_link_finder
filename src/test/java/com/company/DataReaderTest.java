package com.company;

import com.company.dataReader.DataReader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class DataReaderTest {
    @Test
    public void CheckInputData_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
        /* assert */
        assertSame("report.csv", dataReader.getOutputFileName());
    }

    @Test
    public void CheckLinks_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
        /* assert */
        assertSame("page1.html", dataReader.getLinks().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_ErrorWordFilesArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "-files", "page1.html", "page2.html", "--out", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_ErrorWordOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--ot", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_TwoOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--ot", "report.csv", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_EmptyOutArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }

    @Test(expected = IllegalArgumentException.class)
    public void CheckInputData_EmptyFilesArgument_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "--out", "report.csv"};
        ArrayList<String> exampleArguments = App.convertStringArrayToArrayList(exampleArgumentsArray);
        DataReader dataReader = new DataReader(exampleArguments);
        /* act */
        dataReader.checkInputData();
    }
}
