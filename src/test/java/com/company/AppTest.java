package com.company;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class AppTest
{
    @Test
    public void ConvertInArrayList_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        ArrayList<String> expected = new ArrayList<>();
        /* act */
        expected = App.convertStringArrayToArrayList(exampleArgumentsArray);
        /* assert */
        assertSame(expected.get(0), exampleArgumentsArray[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertInArrayList_EmptyArguments_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {};
        /* act */
        App.convertStringArrayToArrayList(exampleArgumentsArray);
    }
}
