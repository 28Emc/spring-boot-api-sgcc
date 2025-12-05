package com.emc.sgcc_api.service;

import com.emc.sgcc_api.dto.TenantCreateDto;
import com.emc.sgcc_api.dto.TenantResponseDto;
import com.emc.sgcc_api.dto.TenantUpdateDto;
import com.emc.sgcc_api.entity.Location;
import com.emc.sgcc_api.entity.Tenant;
import com.emc.sgcc_api.mapper.TenantMapper;
import com.emc.sgcc_api.repository.LocationRepository;
import com.emc.sgcc_api.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final LocationRepository locationRepository;
    private final TenantMapper mapper;

    public TenantResponseDto create(TenantCreateDto dto) {
        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        Tenant entity = mapper.toEntity(dto);
        entity.setLocation(location);
        Tenant saved = tenantRepository.save(entity);
        return mapper.toResponse(saved);
    }

    public Page<@NotNull TenantResponseDto> findAll(Pageable pageable) {
        return tenantRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public Page<@NotNull TenantResponseDto> findByLocation(Pageable pageable, Long locationId) {
        return tenantRepository.findByLocationId(pageable, locationId)
                .map(mapper::toResponse);
    }

    public TenantResponseDto findById(Long id) {
        Tenant entity = tenantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        return mapper.toResponse(entity);
    }

    public TenantResponseDto update(Long id, TenantUpdateDto dto) {
        Tenant entity = tenantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        mapper.updateEntity(entity, dto);
        Tenant updated = tenantRepository.save(entity);

        return mapper.toResponse(updated);
    }

    public void delete(Long id) {
        tenantRepository.deleteById(id);
    }
}
