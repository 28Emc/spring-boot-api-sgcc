package com.emc.sgcc_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MeterUpdateDto {
    private Long locationId;
    private Long serviceId;
    private String code;
    private String type;
    private String brand;
    private String model;
    private LocalDate installationDate;
}


