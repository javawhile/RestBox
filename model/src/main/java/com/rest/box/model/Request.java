package com.rest.box.model;

import com.rest.box.model.enums.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {
    private HttpMethod httpMethod;
    private String fullUrl;
    private String requestBody;
    private Map<String, String> queryParams;
    private Map<String, String> requestHeaders;
}
