package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MeterCreateDto {
    @NotNull
    private Long serviceId;

    @NotNull
    private Long locationId;

    @NotNull
    private String code;

    @NotNull
    private String type;

    private String brand;

    private String model;

    private LocalDate installationDate;
}

