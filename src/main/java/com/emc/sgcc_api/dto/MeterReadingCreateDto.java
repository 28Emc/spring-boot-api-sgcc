package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MeterReadingCreateDto {
    @NotNull
    private Long meterId;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDate readingDate;
}


