package com.company.linkResponseInfo;

import org.apache.http.impl.EnglishReasonPhraseCatalog;

public class LinkResponseInfo {
    private final String uri;
    private final Integer statusCode;

    public LinkResponseInfo(String uri, Integer statusCode) {
        this.statusCode = statusCode;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Integer getNumberStatusCode() {
        return statusCode;
    }

    public String getTextStatusCode() {
        try {
            return EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, null);
        } catch (IllegalArgumentException ex) {
            return "Invalid Status Code";
        }
    }

    @Override
    public boolean equals(Object obj) {
        LinkResponseInfo linkResponseInfo = (LinkResponseInfo) obj;
        boolean equals = false;
        if (linkResponseInfo.statusCode.equals(statusCode) && linkResponseInfo.uri.equals(uri)) {
            equals = true;
        }
        return equals;
    }
}
