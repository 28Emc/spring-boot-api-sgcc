package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocationCreateDto {
    @NotBlank
    private String name;

    private String description;
}
