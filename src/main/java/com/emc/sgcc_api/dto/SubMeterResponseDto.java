package com.emc.sgcc_api.dto;

import lombok.Data;

@Data
public class SubMeterResponseDto {
    private Long id;
    private Long tenantId;
    private Long serviceId;
    private String code;
    private String brand;
    private String model;
    private String installationDate;
    private String createdAt;
}


