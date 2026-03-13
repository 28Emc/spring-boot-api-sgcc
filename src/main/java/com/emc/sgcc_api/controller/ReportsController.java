package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.TenantReportDto;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Reportes de consumos y pagos para inquilinos")
public class ReportsController {

    private final ReportService reportService;

    @GetMapping("/tenants/{tenantId}/{month}/{year}")
    @Operation(summary = "Obtener reporte mensual de inquilino",
               description = "Genera un reporte completo de lo que debe pagar un inquilino en un mes específico, incluyendo totales por servicio y total general")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte generado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado o sin allocations para el período")
    })
    public TenantReportDto getTenantMonthlyReport(
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long tenantId,
            @Parameter(description = "Mes del reporte (1-12)", required = true)
            @PathVariable Integer month,
            @Parameter(description = "Año del reporte", required = true)
            @PathVariable Integer year) {

        return reportService.generateTenantReport(tenantId, month, year);
    }

    @GetMapping("/tenants/{tenantId}/current")
    @Operation(summary = "Obtener reporte del mes actual",
               description = "Genera el reporte del mes actual para un inquilino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte del mes actual generado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado o sin allocations")
    })
    public TenantReportDto getTenantCurrentMonthReport(
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long tenantId) {

        // Obtener fecha actual
        java.time.LocalDate now = java.time.LocalDate.now();
        return reportService.generateTenantReport(tenantId, now.getMonthValue(), now.getYear());
    }

    @GetMapping("/services/active")
    @Operation(summary = "Obtener servicios activos",
               description = "Retorna la lista de servicios activos con tarifa configurada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de servicios activos obtenida exitosamente")
    })
    public List<ServiceEntity> getActiveServices() {
        return reportService.getActiveServices();
    }
}
