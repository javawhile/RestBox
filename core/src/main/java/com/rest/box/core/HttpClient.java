package com.rest.box.core;

import com.rest.box.model.Request;
import com.rest.box.model.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClient {

    private RestTemplate restTemplate = new RestTemplate();

    public HttpClient() {
    }

    public HttpClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Response get(final Request request) {
        if (this.restTemplate == null) {
            this.restTemplate = new RestTemplate();
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(
                request.getRequestBody(),
                convertToMultiValueMap(request.getRequestHeaders())
        );

        ResponseEntity<String> responseString = restTemplate.exchange(
                request.getFullUrl(),
                getHttpMethod(request),
                httpEntity,
                String.class
        );

        String body = responseString.getBody();
        int statusCode = responseString.getStatusCodeValue();
        Map<String, String> responseHeaders = responseString.getHeaders().toSingleValueMap();

        return new Response(body, statusCode, responseHeaders);
    }

    private HttpMethod getHttpMethod(final Request request) {
        return switch (request.getHttpMethod()) {
            case GET -> HttpMethod.GET;
            case POST -> HttpMethod.POST;
            case PUT -> HttpMethod.PUT;
            case DELETE -> HttpMethod.DELETE;
        };
    }

    private MultiValueMap<String, String> convertToMultiValueMap(Map<String, String> headersMap) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> headerEntry : headersMap.entrySet()) {
            List<String> values = headers.getOrDefault(headerEntry.getKey(), null);
            if (values == null) {
                values = new ArrayList<>();
            }
            headers.put(headerEntry.getKey(), values);
        }
        return headers;
    }
}
