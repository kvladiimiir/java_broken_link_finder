package com.company;

import com.company.dataReader.DataReader;
        import org.junit.Test;

        import java.util.ArrayList;

    import static org.junit.Assert.assertTrue;

public class AppTest
{
    @Test
    public void ConvertInArrayList_ExampleArguments_ExpectedTrue()
    {
        /* arrange */
        String[] exampleArgumentsArray = {"brokenLinkFinder", "--files", "page1.html", "page2.html", "--out", "report.csv"};
        App app = new App();
        ArrayList<String> expected = new ArrayList<String>();
        /* act */
        expected = app.ConvertStringArrayToArrayList(exampleArgumentsArray);
        /* assert */
        assertTrue(expected.get(0) == exampleArgumentsArray[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConvertInArrayList_EmptyArguments_ExpectedThrow()
    {
        /* arrange */
        String[] exampleArgumentsArray = {};
        App app = new App();
        ArrayList<String> expected = new ArrayList<String>();
        /* act */
        expected = app.ConvertStringArrayToArrayList(exampleArgumentsArray);
    }
}