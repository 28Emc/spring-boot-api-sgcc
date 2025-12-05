package com.emc.sgcc_api.dto;

import lombok.Data;

@Data
public class TenantUpdateDto {
    private String name;
    private Long locationId;
    private Boolean active;
}


