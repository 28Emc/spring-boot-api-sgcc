package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubMeterReadingUpdateDto {
    private BigDecimal value;
    private LocalDate readingDate;
    private Long subMeterId;
}
