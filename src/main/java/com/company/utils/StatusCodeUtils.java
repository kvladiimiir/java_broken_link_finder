package com.company.utils;

import org.apache.http.impl.EnglishReasonPhraseCatalog;

public class StatusCodeUtils {
    public String getStatusCodeText(Integer statusCode)
    {
        try {
            return EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, null);
        } catch (IllegalArgumentException ex) {
            return "Invalid Status Code";
        }
    }
}
