package com.emc.sgcc_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllocationCreateDto {

    @NotNull
    private Long invoiceId;

    @NotNull
    private Long tenantId;

    @NotNull
    private Long subMeterId;

    @NotNull
    private BigDecimal amount;
}



