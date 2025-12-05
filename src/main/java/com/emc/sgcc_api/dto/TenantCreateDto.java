package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TenantCreateDto {
    @NotBlank
    private String name;

    @NotNull
    private Long locationId;
}

