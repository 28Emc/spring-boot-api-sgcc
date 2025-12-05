package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllocationUpdateDto {

    private Long invoiceId;

    private Long tenantId;

    private Long subMeterId;

    private BigDecimal amount;
}



