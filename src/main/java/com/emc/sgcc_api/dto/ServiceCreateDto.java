package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceCreateDto {
    @NotBlank
    private String name;
    private String description;
    private BigDecimal tarifaActual;
}
