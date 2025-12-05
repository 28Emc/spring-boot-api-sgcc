package com.emc.sgcc_api.service;

import com.emc.sgcc_api.dto.ServiceCreateDto;
import com.emc.sgcc_api.dto.ServiceResponseDto;
import com.emc.sgcc_api.dto.ServiceUpdateDto;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.mapper.ServiceMapper;
import com.emc.sgcc_api.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper mapper;

    public ServiceResponseDto create(ServiceCreateDto dto) {
        ServiceEntity entity = mapper.toEntity(dto);
        return mapper.toResponse(serviceRepository.save(entity));
    }

    public Page<@NotNull ServiceResponseDto> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public ServiceResponseDto findById(Long id) {
        return mapper.toResponse(serviceRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Service not found")));
    }

    public ServiceResponseDto update(Long id, ServiceUpdateDto dto) {
        ServiceEntity existing = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        mapper.updateEntity(existing, dto);

        return mapper.toResponse(serviceRepository.save(existing));
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }
}

