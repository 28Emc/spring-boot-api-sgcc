package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ServiceResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal tarifaActual;
    private LocalDateTime createdAt;
}
