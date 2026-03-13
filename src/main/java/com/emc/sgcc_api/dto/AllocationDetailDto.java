package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AllocationDetailDto {
    private Long allocationId;
    private Long invoiceId;
    private String serviceName;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private BigDecimal amount;
    private BigDecimal percentage;
    private String currency;
}
