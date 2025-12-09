package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.LocationCreateDto;
import com.emc.sgcc_api.dto.LocationResponseDto;
import com.emc.sgcc_api.dto.LocationUpdateDto;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.Location;
import com.emc.sgcc_api.mapper.LocationMapper;
import com.emc.sgcc_api.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper mapper;

    @Auditable(entity = "Location", action = AuditAction.CREATE)
    public LocationResponseDto create(LocationCreateDto dto) {
        Location entity = mapper.toEntity(dto);
        Location saved = locationRepository.save(entity);
        return mapper.toResponse(saved);
    }

    public Page<@NotNull LocationResponseDto> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public LocationResponseDto findById(Long id) {
        Location entity = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        return mapper.toResponse(entity);
    }

    @Auditable(entity = "Location", action = AuditAction.UPDATE)
    public LocationResponseDto update(Long id, LocationUpdateDto dto) {
        Location entity = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        mapper.updateEntity(entity, dto);
        Location updated = locationRepository.save(entity);

        return mapper.toResponse(updated);
    }

    @Auditable(entity = "Location", action = AuditAction.DELETE)
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
