package com.emc.sgcc_api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiError {
    private boolean success;
    private Instant timestamp;
    private ErrorBody error;

    @Data
    @Builder
    public static class ErrorBody {
        private int statusCode;
        private Object message;
    }
}

