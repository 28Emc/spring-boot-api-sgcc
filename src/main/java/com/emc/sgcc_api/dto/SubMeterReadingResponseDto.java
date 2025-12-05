package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SubMeterReadingResponseDto {
    private Long id;
    private Long subMeterId;
    private BigDecimal value;
    private LocalDate readingDate;
    private LocalDateTime createdAt;
}
