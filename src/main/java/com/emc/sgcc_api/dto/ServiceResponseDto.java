package com.emc.sgcc_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}



