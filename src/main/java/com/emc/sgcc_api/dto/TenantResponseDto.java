package com.emc.sgcc_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TenantResponseDto {
    private Long id;
    private String name;
    private Long locationId;
    private Boolean active;
    private LocalDateTime createdAt;

}



