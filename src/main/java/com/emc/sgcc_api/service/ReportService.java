package com.emc.sgcc_api.service;

import com.emc.sgcc_api.dto.AllocationDetailDto;
import com.emc.sgcc_api.dto.ServiceTotalDto;
import com.emc.sgcc_api.dto.TenantReportDto;
import com.emc.sgcc_api.entity.Allocation;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.entity.Tenant;
import com.emc.sgcc_api.repository.AllocationRepository;
import com.emc.sgcc_api.repository.ServiceRepository;
import com.emc.sgcc_api.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TenantRepository tenantRepository;
    private final AllocationRepository allocationRepository;
    private final ServiceRepository serviceRepository;

    /**
     * Genera un reporte mensual dinámico para un inquilino específico
     * La estructura se adapta automáticamente a los servicios disponibles
     */
    public TenantReportDto generateTenantReport(Long tenantId, Integer month, Integer year) {
        // Verificar que el tenant existe
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        // Obtener todas las allocations del tenant para el mes/año especificado
        List<Allocation> allocations = allocationRepository.findByTenantIdAndPeriod(tenantId, month, year);

        // Crear el reporte base
        TenantReportDto report = createReportHeader(tenant, month, year);

        // Procesar allocations y calcular totales por servicio
        Map<Long, ServiceTotalDto> serviceTotalsMap = processAllocationsByService(allocations);

        // Calcular total general
        BigDecimal totalGeneral = calculateTotalGeneral(serviceTotalsMap);

        // Preparar lista ordenada de servicios
        List<ServiceTotalDto> serviceTotals = serviceTotalsMap.values().stream()
                .sorted(Comparator.comparing(ServiceTotalDto::getServiceName))
                .collect(Collectors.toList());

        // Completar reporte
        report.setServiceTotals(serviceTotals);
        report.setTotalGeneral(totalGeneral);

        return report;
    }

    /**
     * Crea la cabecera básica del reporte
     */
    private TenantReportDto createReportHeader(Tenant tenant, Integer month, Integer year) {
        TenantReportDto report = new TenantReportDto();
        report.setTenantId(tenant.getId());
        report.setTenantName(tenant.getName());
        report.setMonth(month);
        report.setYear(year);
        report.setReportDate(LocalDate.now());
        return report;
    }

    /**
     * Procesa las allocations agrupándolas por servicio
     */
    private Map<Long, ServiceTotalDto> processAllocationsByService(List<Allocation> allocations) {
        Map<Long, ServiceTotalDto> serviceTotalsMap = new HashMap<>();

        for (Allocation allocation : allocations) {
            Long serviceId = allocation.getSubMeter().getService().getId();
            String serviceName = allocation.getSubMeter().getService().getName();
            BigDecimal amount = allocation.getAmount();
            String currency = allocation.getInvoice().getCurrency();

            // Obtener o crear el ServiceTotalDto para este servicio
            ServiceTotalDto serviceTotal = serviceTotalsMap.computeIfAbsent(serviceId, k -> {
                ServiceTotalDto dto = new ServiceTotalDto();
                dto.setServiceId(serviceId);
                dto.setServiceName(serviceName);
                dto.setTotalAmount(BigDecimal.ZERO);
                dto.setCurrency(currency);
                dto.setAllocations(new ArrayList<>());
                return dto;
            });

            // Agregar el allocation al servicio correspondiente
            AllocationDetailDto detail = convertToAllocationDetail(allocation);
            serviceTotal.getAllocations().add(detail);

            // Sumar al total del servicio
            serviceTotal.setTotalAmount(serviceTotal.getTotalAmount().add(amount));
        }

        return serviceTotalsMap;
    }

    /**
     * Calcula el total general sumando todos los totales de servicios
     */
    private BigDecimal calculateTotalGeneral(Map<Long, ServiceTotalDto> serviceTotalsMap) {
        return serviceTotalsMap.values().stream()
                .map(ServiceTotalDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Convierte una Allocation a AllocationDetailDto
     */
    private AllocationDetailDto convertToAllocationDetail(Allocation allocation) {
        AllocationDetailDto detail = new AllocationDetailDto();
        detail.setAllocationId(allocation.getId());
        detail.setInvoiceId(allocation.getInvoice().getId());
        detail.setServiceName(allocation.getSubMeter().getService().getName());
        detail.setPeriodStart(allocation.getInvoice().getPeriodStart());
        detail.setPeriodEnd(allocation.getInvoice().getPeriodEnd());
        detail.setAmount(allocation.getAmount());
        detail.setPercentage(allocation.getPercentage());
        detail.setCurrency(allocation.getInvoice().getCurrency());
        return detail;
    }

    /**
     * Obtiene todos los servicios activos del sistema
     * Útil para interfaces que necesitan mostrar opciones de servicios
     */
    public List<ServiceEntity> getActiveServices() {
        return serviceRepository.findAll().stream()
                .filter(service -> service.getTarifaActual() != null &&
                                 service.getTarifaActual().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }
}
