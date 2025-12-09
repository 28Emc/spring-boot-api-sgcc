package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.MeterReadingCreateDto;
import com.emc.sgcc_api.dto.MeterReadingResponseDto;
import com.emc.sgcc_api.dto.MeterReadingUpdateDto;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.MeterReading;
import com.emc.sgcc_api.mapper.MeterReadingMapper;
import com.emc.sgcc_api.repository.MeterReadingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MeterReadingService {

    private final MeterReadingRepository meterReadingRepository;
    private final MeterReadingMapper mapper;

    @Auditable(entity = "MeterReading", action = AuditAction.CREATE)
    public MeterReadingResponseDto create(MeterReadingCreateDto dto) {
        MeterReading entity = mapper.toEntity(dto);
        return mapper.toResponse(meterReadingRepository.save(entity));
    }

    public Page<@NotNull MeterReadingResponseDto> findAll(Pageable pageable) {
        return meterReadingRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public MeterReadingResponseDto findById(Long id) {
        MeterReading entity = meterReadingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meter reading not found"));

        return mapper.toResponse(entity);
    }

    public Page<@NotNull MeterReadingResponseDto> findByMeter(Pageable pageable, Long meterId) {
        return meterReadingRepository.findByMeterId(pageable, meterId)
                .map(mapper::toResponse);
    }

    public Page<@NotNull MeterReadingResponseDto> findByRange(
            Pageable pageable,
            Long meterId,
            LocalDate start,
            LocalDate end
    ) {
        return meterReadingRepository.findByMeterIdAndReadingDateBetween(pageable, meterId, start, end)
                .map(mapper::toResponse);
    }

    @Auditable(entity = "MeterReading", action = AuditAction.UPDATE)
    public MeterReadingResponseDto update(Long id, MeterReadingUpdateDto dto) {
        MeterReading entity = meterReadingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meter reading not found"));

        mapper.updateEntity(entity, dto);

        return mapper.toResponse(meterReadingRepository.save(entity));
    }

    @Auditable(entity = "MeterReading", action = AuditAction.DELETE)
    public void delete(Long id) {
        meterReadingRepository.deleteById(id);
    }
}