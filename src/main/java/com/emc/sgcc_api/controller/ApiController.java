package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.HealthResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Health Check", description = "Endpoints para verificar el estado de la API")
public class ApiController {

    @GetMapping("health-check")
    @Operation(summary = "Verificar estado de la API", description = "Retorna el estado actual de la API y su disponibilidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API está operativa"),
    })
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
