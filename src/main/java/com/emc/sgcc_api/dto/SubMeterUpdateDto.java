package com.emc.sgcc_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubMeterUpdateDto {
    private Long tenantId;
    private Long serviceId;
    private String code;
    private String brand;
    private String model;
    private LocalDate installationDate;
}

