package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.AllocationCreateDto;
import com.emc.sgcc_api.dto.AllocationResponseDto;
import com.emc.sgcc_api.dto.AllocationUpdateDto;
import com.emc.sgcc_api.entity.Allocation;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.mapper.AllocationMapper;
import com.emc.sgcc_api.repository.AllocationRepository;
import com.emc.sgcc_api.repository.InvoiceRepository;
import com.emc.sgcc_api.repository.SubMeterRepository;
import com.emc.sgcc_api.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final InvoiceRepository invoiceRepository;
    private final TenantRepository tenantRepository;
    private final SubMeterRepository subMeterRepository;
    private final AllocationMapper mapper;

    public Page<@NotNull AllocationResponseDto> findAll(Pageable pageable) {
        return allocationRepository.findAll(pageable).map(mapper::toResponse);
    }

    public AllocationResponseDto findById(Long id) {
        return mapper.toResponse(allocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Allocation not found")));
    }

    @Auditable(entity = "Allocation", action = AuditAction.CREATE)
    public AllocationResponseDto create(AllocationCreateDto dto) {
        var invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        var tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        var subMeter = subMeterRepository.findById(dto.getSubMeterId())
                .orElseThrow(() -> new EntityNotFoundException("SubMeter not found"));

        Allocation allocation = new Allocation();
        allocation.setInvoice(invoice);
        allocation.setTenant(tenant);
        allocation.setSubMeter(subMeter);
        allocation.setAmount(dto.getAmount());
        var percentage = dto.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .divide(invoice.getTotalAmount(), 2, java.math.RoundingMode.HALF_UP);
        allocation.setPercentage(percentage);

        return mapper.toResponse(allocationRepository.save(allocation));
    }

    @Auditable(entity = "Allocation", action = AuditAction.UPDATE)
    public AllocationResponseDto update(Long id, AllocationUpdateDto dto) {
        Allocation existing = allocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Allocation not found"));

        var invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        var tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        var subMeter = subMeterRepository.findById(dto.getSubMeterId())
                .orElseThrow(() -> new EntityNotFoundException("SubMeter not found"));

        existing.setInvoice(invoice);
        existing.setTenant(tenant);
        existing.setSubMeter(subMeter);
        existing.setAmount(dto.getAmount());

        return mapper.toResponse(allocationRepository.save(existing));
    }

    @Auditable(entity = "Allocation", action = AuditAction.DELETE)
    public void delete(Long id) {
        allocationRepository.deleteById(id);
    }
}

