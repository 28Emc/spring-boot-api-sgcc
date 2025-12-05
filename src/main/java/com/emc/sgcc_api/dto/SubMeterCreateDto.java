package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubMeterCreateDto {
    @NotNull
    private Long tenantId;

    @NotNull
    private Long serviceId;

    @NotBlank
    private String code;

    private String brand;

    private String model;

    private LocalDate installationDate;
}
