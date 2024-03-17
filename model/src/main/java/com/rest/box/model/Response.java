package com.rest.box.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private String body;
    private int statusCode = -1;
    private Map<String, String> responseHeaders;
}
