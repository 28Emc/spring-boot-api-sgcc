package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InvoiceResponseDto {
    private Long id;
    private BigDecimal amount;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private LocalDateTime createdAt;
}




