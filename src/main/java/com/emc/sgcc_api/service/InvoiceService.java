package com.emc.sgcc_api.service;

import com.emc.sgcc_api.dto.InvoiceCreateDto;
import com.emc.sgcc_api.dto.InvoiceResponseDto;
import com.emc.sgcc_api.dto.InvoiceUpdateDto;
import com.emc.sgcc_api.entity.Invoice;
import com.emc.sgcc_api.entity.Meter;
import com.emc.sgcc_api.mapper.InvoiceMapper;
import com.emc.sgcc_api.repository.InvoiceRepository;
import com.emc.sgcc_api.repository.MeterRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MeterRepository meterRepository;
    private final InvoiceMapper mapper;

    public Page<@NotNull InvoiceResponseDto> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(mapper::toResponse);
    }

    public InvoiceResponseDto findById(Long id) {
        return mapper.toResponse(invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found")));
    }

    public InvoiceResponseDto create(InvoiceCreateDto dto) {
        Meter meter = meterRepository.findById(dto.getMeterId())
                .orElseThrow(() -> new EntityNotFoundException("Meter not found"));

        Invoice invoice = new Invoice();
        invoice.setMeter(meter);
        invoice.setPeriodStart(dto.getPeriodStart());
        invoice.setPeriodEnd(dto.getPeriodEnd());
        invoice.setTotalAmount(dto.getTotalAmount());

        return mapper.toResponse(invoiceRepository.save(invoice));
    }

    public InvoiceResponseDto update(Long id, InvoiceUpdateDto dto) {
        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        Meter meter = meterRepository.findById(dto.getMeterId())
                .orElseThrow(() -> new EntityNotFoundException("Meter not found"));

        existing.setMeter(meter);
        existing.setPeriodStart(dto.getPeriodStart());
        existing.setPeriodEnd(dto.getPeriodEnd());
        existing.setTotalAmount(dto.getTotalAmount());

        return mapper.toResponse(invoiceRepository.save(existing));
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}
