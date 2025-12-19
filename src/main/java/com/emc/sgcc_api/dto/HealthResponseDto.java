package com.emc.sgcc_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class HealthResponseDto {
    private boolean success;
    private int statusCode;
    private String message;
    private Map<String, String> headers;
    private LocalDateTime timestamp;
}
