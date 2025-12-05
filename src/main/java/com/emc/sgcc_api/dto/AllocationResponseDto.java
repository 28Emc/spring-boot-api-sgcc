package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AllocationResponseDto {

    private Long id;

    private Long invoiceId;

    private Long tenantId;

    private Long subMeterId;

    private BigDecimal amount;

    private BigDecimal percentage;

    private LocalDateTime createdAt;
}





