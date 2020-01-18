package com.company;

import java.util.ArrayList;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
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

