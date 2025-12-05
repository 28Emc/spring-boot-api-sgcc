package com.emc.sgcc_api.service;

import com.emc.sgcc_api.dto.MeterCreateDto;
import com.emc.sgcc_api.dto.MeterResponseDto;
import com.emc.sgcc_api.dto.MeterUpdateDto;
import com.emc.sgcc_api.entity.Location;
import com.emc.sgcc_api.entity.Meter;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.mapper.MeterMapper;
import com.emc.sgcc_api.repository.LocationRepository;
import com.emc.sgcc_api.repository.MeterRepository;
import com.emc.sgcc_api.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeterService {

    private final MeterRepository meterRepository;
    private final ServiceRepository serviceRepository;
    private final LocationRepository locationRepository;
    private final MeterMapper mapper;

    public MeterResponseDto create(MeterCreateDto dto) {
        if (meterRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Meter code already exists");
        }

        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        Meter entity = mapper.toEntity(dto);
        entity.setService(service);
        entity.setLocation(location);
        return mapper.toResponse(meterRepository.save(entity));
    }

    public Page<@NotNull MeterResponseDto> findAll(Pageable pageable) {
        return meterRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public MeterResponseDto findById(Long id) {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meter not found"));

        return mapper.toResponse(meter);
    }

    public Page<@NotNull MeterResponseDto> findByLocation(Pageable pageable, Long locationId) {
        return meterRepository.findByLocationId(pageable, locationId)
                .map(mapper::toResponse);
    }

    public MeterResponseDto update(Long id, MeterUpdateDto dto) {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meter not found"));

        mapper.updateEntity(meter, dto);

        return mapper.toResponse(meterRepository.save(meter));
    }

    public void delete(Long id) {
        meterRepository.deleteById(id);
    }
}

