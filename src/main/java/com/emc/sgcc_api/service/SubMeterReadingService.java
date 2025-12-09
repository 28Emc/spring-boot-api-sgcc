package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.SubMeterReadingCreateDto;
import com.emc.sgcc_api.dto.SubMeterReadingResponseDto;
import com.emc.sgcc_api.dto.SubMeterReadingUpdateDto;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.SubMeter;
import com.emc.sgcc_api.entity.SubMeterReading;
import com.emc.sgcc_api.mapper.SubMeterReadingMapper;
import com.emc.sgcc_api.repository.SubMeterReadingRepository;
import com.emc.sgcc_api.repository.SubMeterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubMeterReadingService {

    private final SubMeterReadingRepository repository;
    private final SubMeterRepository subMeterRepository;
    private final SubMeterReadingMapper mapper;

    @Auditable(entity = "SubMeterReading", action = AuditAction.CREATE)
    public SubMeterReadingResponseDto create(SubMeterReadingCreateDto reading) {
        SubMeterReading entity = mapper.toEntity(reading);
        SubMeterReading savedReading = repository.save(entity);
        return mapper.toResponse(savedReading);
    }

    public List<SubMeterReadingResponseDto> findAll() {
        List<SubMeterReading> readings = repository.findAll();
        return mapper.toResponseList(readings);
    }

    public List<SubMeterReadingResponseDto> getBySubMeter(Long id) {
        List<SubMeterReading> readings = repository.findBySubMeterId(id);
        return mapper.toResponseList(readings);
    }

    public List<SubMeterReadingResponseDto> getByRange(Long id, LocalDate start, LocalDate end) {
        List<SubMeterReading> readings = repository.findBySubMeterIdAndReadingDateBetween(id, start, end);
        return mapper.toResponseList(readings);
    }

    public SubMeterReadingResponseDto findById(Long id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub-meter reading not found")));
    }

    @Auditable(entity = "SubMeterReading", action = AuditAction.UPDATE)
    public SubMeterReadingResponseDto update(Long id, SubMeterReadingUpdateDto readingDetails) {
        SubMeterReading reading = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub-meter reading not found"));

        SubMeter subMeter = subMeterRepository.findById(readingDetails.getSubMeterId())
                .orElseThrow(() -> new EntityNotFoundException("Sub-meter not found"));

        reading.setReadingValue(readingDetails.getValue());
        reading.setReadingDate(readingDetails.getReadingDate());
        reading.setSubMeter(subMeter);
        return mapper.toResponse(repository.save(reading));
    }

    @Auditable(entity = "SubMeterReading", action = AuditAction.DELETE)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}