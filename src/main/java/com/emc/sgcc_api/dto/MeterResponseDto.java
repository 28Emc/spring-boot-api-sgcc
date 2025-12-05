package com.emc.sgcc_api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MeterResponseDto {
    private Long id;
    private Long locationId;
    private Long serviceId;
    private String code;
    private String type;
    private String brand;
    private String model;
    private LocalDate installationDate;
    private LocalDateTime createdAt;

}



