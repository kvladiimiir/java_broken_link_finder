package com.company.brokenLinkChecker;

import com.company.StatusCodeRecipient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class BrokenLinkChecker {
    public Map<String, Integer> getBrokenLinksMap(ArrayList<String> links) {
        Map<String, Integer> brokenLinksMap = new HashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        links.forEach(link -> {
            StatusCodeRecipient statusCodeRecipient = new StatusCodeRecipient(link);
            Future<Integer> result = executor.submit(statusCodeRecipient);

            try {
                Integer statusCode = result.get();

                if (statusCode >= 300 || statusCode == -1) {
                    brokenLinksMap.put(link, statusCode);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();

        return brokenLinksMap;
    }
}
