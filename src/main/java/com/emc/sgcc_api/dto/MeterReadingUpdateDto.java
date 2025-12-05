package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MeterReadingUpdateDto {
    private BigDecimal value;
    private LocalDate readingDate;
}



