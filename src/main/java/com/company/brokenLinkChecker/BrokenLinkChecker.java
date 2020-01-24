package com.company.brokenLinkChecker;

import com.company.statusCodeRecipient.StatusCodeRecipient;
import com.company.linkResponseInfo.LinkResponseInfo;
import com.company.utils.ConfigUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BrokenLinkChecker {
    public ArrayList<LinkResponseInfo> getBrokenLinks(ArrayList<String> links) throws IOException, InterruptedException, ExecutionException {
        ArrayList<LinkResponseInfo> brokenLinks = new ArrayList<>();
        ArrayList<StatusCodeRecipient> tasks = new ArrayList<>();

        for (String link: links) {
            tasks.add(new StatusCodeRecipient(link));
        }

        ExecutorService executor = Executors.newFixedThreadPool(ConfigUtils.getNumberOfThreads());
        List<Future<LinkResponseInfo>> resultResponses = executor.invokeAll(tasks);

        for (var response: resultResponses) {
            LinkResponseInfo responseInfo = response.get();

            if (responseInfo.getNumberStatusCode() >= 300) {
                brokenLinks.add(new LinkResponseInfo(responseInfo.getUri(), responseInfo.getNumberStatusCode()));
            }
        }

        executor.shutdown();

        return brokenLinks;
    }
}
