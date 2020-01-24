package com.company;

import com.company.brokenLinkChecker.BrokenLinkChecker;
import com.company.linkResponseInfo.LinkResponseInfo;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class BrokenLinkCheckerTest {
    @Test
    public void getBrokenLinks_ListOfLinks_BrokenLinksList() throws IOException, ExecutionException, InterruptedException {
        //arrange
        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

        String[] linksArray = new String[] {
                "https://httpstat.us/200",
                "https://httpstat.us/301",
                "https://httpstat.us/404",
                "https://httpstat.us/503",
                "invalid_link"
        };

        ArrayList<LinkResponseInfo> expectedBrokenLinks = new ArrayList<>();
        expectedBrokenLinks.add(new LinkResponseInfo("https://httpstat.us/404", 404));
        expectedBrokenLinks.add(new LinkResponseInfo("https://httpstat.us/503", 503));
        expectedBrokenLinks.add(new LinkResponseInfo("invalid_link", 400));

        ArrayList<String> links = new ArrayList<>(Arrays.asList(linksArray));
        //act
        ArrayList<LinkResponseInfo> brokenLinks = brokenLinkChecker.getBrokenLinks(links);
        //assert
        assertEquals(expectedBrokenLinks, brokenLinks);
    }
}
