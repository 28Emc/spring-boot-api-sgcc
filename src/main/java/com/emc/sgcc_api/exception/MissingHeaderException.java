package com.emc.sgcc_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissingHeaderException extends ResponseStatusException {

    public MissingHeaderException(String headerName, String reason) {
        super(HttpStatus.UNAUTHORIZED, reason != null ? reason : "Missing or invalid header: " + headerName);
    }
}