package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ServiceTotalDto {
    private Long serviceId;
    private String serviceName;
    private BigDecimal totalAmount;
    private String currency;

    // Detalles de allocations para este servicio
    private List<AllocationDetailDto> allocations;
}
