package com.rest.box.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlUtils {

    public static String addQueryParamToUrl(final String fullUrl, final Map<String, String> queryParamMap) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(fullUrl);
        if (queryParamMap != null && !queryParamMap.isEmpty()) {
            uriBuilder.clearParameters();
            for (final String key : queryParamMap.keySet()) {
                if (key != null && !key.trim().isEmpty()) {
                    uriBuilder.addParameter(key, queryParamMap.getOrDefault(key, ""));
                }
            }
        }
        return uriBuilder.build().toASCIIString();
    }

    public static Map<String, String> getQueryParamsFromUrl(final String fullUrl) {
        String queryParamsString = getQueryParamsString(fullUrl);
        Map<String, String> queryParams = new HashMap<>();
        List<NameValuePair> queryParamsNameValuePairs = URLEncodedUtils.parse(queryParamsString, StandardCharsets.UTF_8);
        if (queryParamsNameValuePairs != null) {
            for (NameValuePair param : queryParamsNameValuePairs) {
                if (param != null) {
                    queryParams.put(param.getName(), param.getValue());
                }
            }
        }
        return queryParams;
    }

    public static String getQueryParamsString(final String fullUrl) {
        if (fullUrl == null || fullUrl.isEmpty()) {
            return "";
        }
        int startingIndexQuestionMark = fullUrl.indexOf("?");
        if (startingIndexQuestionMark >= 0) {
            return fullUrl.substring(startingIndexQuestionMark + 1, fullUrl.length() - 1);
        }
        return "";
    }

    public static String getBaseUrl(final String fullUrl) {
        if (fullUrl == null || fullUrl.isEmpty()) {
            return "";
        }
        int startingIndexQuestionMark = fullUrl.indexOf("?");
        if (startingIndexQuestionMark >= 0) {
            return fullUrl.substring(startingIndexQuestionMark);
        }
        return fullUrl;
    }

}
