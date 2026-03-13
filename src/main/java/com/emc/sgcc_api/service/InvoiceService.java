package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.InvoiceCreateDto;
import com.emc.sgcc_api.dto.InvoiceResponseDto;
import com.emc.sgcc_api.dto.InvoiceUpdateDto;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.Invoice;
import com.emc.sgcc_api.entity.Meter;
import com.emc.sgcc_api.entity.MeterReading;
import com.emc.sgcc_api.mapper.InvoiceMapper;
import com.emc.sgcc_api.repository.InvoiceRepository;
import com.emc.sgcc_api.repository.MeterReadingRepository;
import com.emc.sgcc_api.repository.MeterRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MeterRepository meterRepository;
    private final MeterReadingRepository meterReadingRepository;
    private final InvoiceMapper mapper;

    public Page<@NotNull InvoiceResponseDto> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(mapper::toResponse);
    }

    public InvoiceResponseDto findById(Long id) {
        return mapper.toResponse(invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found")));
    }

    @Auditable(entity = "Invoice", action = AuditAction.CREATE)
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

    @Auditable(entity = "Invoice", action = AuditAction.UPDATE)
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

    @Auditable(entity = "Invoice", action = AuditAction.DELETE)
    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }

    /**
     * Crea una factura con cálculo automático del monto total
     * basado en las lecturas del medidor y la tarifa del servicio
     */
    @Auditable(entity = "Invoice", action = AuditAction.CREATE)
    public InvoiceResponseDto createWithCalculation(InvoiceCreateDto dto) {
        Meter meter = meterRepository.findById(dto.getMeterId())
                .orElseThrow(() -> new EntityNotFoundException("Meter not found"));

        // Obtener lectura actual (más reciente en el período)
        MeterReading lecturaActual = meterReadingRepository
                .findFirstByMeterIdAndReadingDateLessThanEqualOrderByReadingDateDesc(
                    dto.getMeterId(), dto.getPeriodEnd())
                .orElseThrow(() -> new EntityNotFoundException("No reading found for current period"));

        // Obtener lectura anterior (más reciente antes del período)
        MeterReading lecturaAnterior = meterReadingRepository
                .findFirstByMeterIdAndReadingDateLessThanOrderByReadingDateDesc(
                    dto.getMeterId(), dto.getPeriodStart())
                .orElse(null); // Puede ser null si es la primera lectura

        // Calcular consumo
        BigDecimal lecturaActualValor = lecturaActual.getReadingValue();
        BigDecimal lecturaAnteriorValor = lecturaAnterior != null
            ? lecturaAnterior.getReadingValue()
            : BigDecimal.ZERO;

        BigDecimal consumo = lecturaActualValor.subtract(lecturaAnteriorValor);

        // Validar que el consumo no sea negativo
        if (consumo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Consumption cannot be negative. Check meter readings.");
        }

        // Obtener tarifa del servicio
        BigDecimal tarifa = meter.getService().getTarifaActual();
        if (tarifa == null || tarifa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Service rate not configured or invalid");
        }

        // Calcular monto total
        BigDecimal totalAmount = consumo.multiply(tarifa).setScale(2, java.math.RoundingMode.HALF_UP);

        // Crear invoice
        Invoice invoice = new Invoice();
        invoice.setMeter(meter);
        invoice.setPeriodStart(dto.getPeriodStart());
        invoice.setPeriodEnd(dto.getPeriodEnd());
        invoice.setTotalAmount(totalAmount);
        invoice.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "PEN");
        invoice.setIssuedAt(LocalDateTime.now());

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return mapper.toResponse(savedInvoice);
    }
}
