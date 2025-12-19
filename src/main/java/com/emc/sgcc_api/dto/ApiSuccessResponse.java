package com.emc.sgcc_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ApiSuccessResponse<T> {
    private boolean success;
    private int statusCode;
    private Map<String, String> headers;
    private LocalDateTime timestamp;
    private T data;
}
