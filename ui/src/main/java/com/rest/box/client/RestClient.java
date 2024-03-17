package com.rest.box.client;

import com.rest.box.core.HttpClient;
import com.rest.box.model.enums.HttpMethod;
import com.rest.box.model.Request;
import com.rest.box.model.Response;

import java.util.Map;

public class RestClient {

    private final HttpClient httpClient = new HttpClient();

    public Response invoke(
            final String httpMethod,
            final String fullUrl,
            String requestBody,
            final Map<String, String> queryParams,
            final Map<String, String> requestHeaders
    ) {
        Request request = new Request(
                HttpMethod.valueOf(httpMethod),
                fullUrl,
                requestBody,
                queryParams,
                requestHeaders
        );

        return httpClient.get(request);
    }

}
