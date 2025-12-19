package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.HealthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("health-check")
    public HealthResponseDto healthCheck() {
        return HealthResponseDto.builder()
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .headers(Collections.emptyMap())
                .timestamp(LocalDateTime.now())
                .message("Api SGCC is running")
                .build();
    }
}
