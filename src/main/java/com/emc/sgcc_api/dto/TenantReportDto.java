package com.emc.sgcc_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class TenantReportDto {
    // Cabecera del reporte
    private Long tenantId;
    private String tenantName;
    private Integer month;
    private Integer year;
    private LocalDate reportDate;
    private BigDecimal totalGeneral;

    // Detalles por servicio (dinámico)
    private List<ServiceTotalDto> serviceTotals;
}
