package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MeterReadingResponseDto {
    private Long id;
    private Long meterId;
    private BigDecimal value;
    private LocalDate readingDate;
    private LocalDateTime createdAt;
}
