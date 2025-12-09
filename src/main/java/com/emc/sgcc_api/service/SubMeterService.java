package com.emc.sgcc_api.service;

import com.emc.sgcc_api.config.Auditable;
import com.emc.sgcc_api.dto.SubMeterCreateDto;
import com.emc.sgcc_api.dto.SubMeterResponseDto;
import com.emc.sgcc_api.dto.SubMeterUpdateDto;
import com.emc.sgcc_api.entity.AuditAction;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.entity.SubMeter;
import com.emc.sgcc_api.entity.Tenant;
import com.emc.sgcc_api.mapper.SubMeterMapper;
import com.emc.sgcc_api.repository.ServiceRepository;
import com.emc.sgcc_api.repository.SubMeterRepository;
import com.emc.sgcc_api.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubMeterService {

    private final SubMeterRepository subMeterRepository;
    private final TenantRepository tenantRepository;
    private final ServiceRepository serviceRepository;
    private final SubMeterMapper mapper;

    @Auditable(entity = "SubMeter", action = AuditAction.CREATE)
    public SubMeterResponseDto create(SubMeterCreateDto dto) {
        if (subMeterRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Sub-meter code already exists");
        }

        Tenant tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        ServiceEntity service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        SubMeter entity = mapper.toEntity(dto);
        entity.setTenant(tenant);
        entity.setService(service);
        return mapper.toResponse(subMeterRepository.save(entity));
    }

    public Page<@NotNull SubMeterResponseDto> findAll(Pageable pageable) {
        return subMeterRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public SubMeterResponseDto findById(Long id) {
        SubMeter meter = subMeterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub-meter not found"));

        return mapper.toResponse(meter);
    }

    public Page<@NotNull SubMeterResponseDto> findByTenant(Pageable pageable, Long tenantId) {
        return subMeterRepository.findByTenantId(pageable, tenantId)
                .map(mapper::toResponse);
    }

    @Auditable(entity = "SubMeter", action = AuditAction.UPDATE)
    public SubMeterResponseDto update(Long id, SubMeterUpdateDto dto) {
        SubMeter subMeter = subMeterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sub-meter not found"));

        mapper.updateEntity(subMeter, dto);

        return mapper.toResponse(subMeterRepository.save(subMeter));
    }

    @Auditable(entity = "SubMeter", action = AuditAction.DELETE)
    public void delete(Long id) {
        subMeterRepository.deleteById(id);
    }
}
