package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceUpdateDto {
    private String name;
    private String description;
    private BigDecimal tarifaActual;
}
