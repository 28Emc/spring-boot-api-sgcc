package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceCreateDto {
    @NotBlank
    private String name;
    private String description;
}

