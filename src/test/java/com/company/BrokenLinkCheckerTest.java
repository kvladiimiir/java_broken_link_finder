package com.company;

import com.company.brokenLinkChecker.BrokenLinkChecker;
import com.company.utils.StatusCodeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BrokenLinkCheckerTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 200, "OK" }, { 404, "Not Found" }, { -1, "Invalid Status Code" }
        });
    }

    @Parameterized.Parameter(0)
    public int statusCode;

    @Parameterized.Parameter(1)
    public String textStatusCode;

    //вынести в отдельный класс
    @Test
    public void GetStatusCodeText_IntegerStatusCode_StringStatusCode() {
        StatusCodeUtils statusCodeUtils = new StatusCodeUtils();
        String textCode = statusCodeUtils.getStatusCodeText(statusCode);

        assertEquals(textCode, textStatusCode);
    }

    @Test
    public void getBrokenLinksMap_ListOfLinks_BrokenLinksMap() throws ExecutionException, InterruptedException {
        //arrange
        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

        String[] linksArray = new String[] {
                "https://httpstat.us/200",
                "https://httpstat.us/202",
                "https://httpstat.us/301",
                "https://httpstat.us/404",
                "https://httpstat.us/503",
                "invalid link"
        };

        Map<String, Integer> expectedBrokenLinksMap = new HashMap<String, Integer>();
        expectedBrokenLinksMap.put("https://httpstat.us/301", 301);
        expectedBrokenLinksMap.put("https://httpstat.us/404", 404);
        expectedBrokenLinksMap.put("https://httpstat.us/503", 503);
        expectedBrokenLinksMap.put("invalid link", -1);

        ArrayList<String> links = new ArrayList<>(Arrays.asList(linksArray));
        //act
        Map<String, Integer> brokenLinksMap = brokenLinkChecker.getBrokenLinksMap(links);
        //assert
        assertEquals(expectedBrokenLinksMap, brokenLinksMap);
    }
}
