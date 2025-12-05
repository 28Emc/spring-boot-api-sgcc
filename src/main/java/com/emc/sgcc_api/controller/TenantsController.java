package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.TenantCreateDto;
import com.emc.sgcc_api.dto.TenantResponseDto;
import com.emc.sgcc_api.dto.TenantUpdateDto;
import com.emc.sgcc_api.service.TenantService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantsController {

    private final TenantService tenantService;

    @GetMapping
    public Page<@NotNull TenantResponseDto> findAll(Pageable pageable) {
        return tenantService.findAll(pageable);
    }

    @GetMapping("/location/{locationId}")
    public Page<@NotNull TenantResponseDto> findByLocation(Pageable pageable,
                                                           @PathVariable Long locationId) {
        return tenantService.findByLocation(pageable, locationId);
    }

    @GetMapping("/{id}")
    public TenantResponseDto findById(@PathVariable Long id) {
        return tenantService.findById(id);
    }

    @PostMapping
    public TenantResponseDto create(@RequestBody TenantCreateDto request) {
        return tenantService.create(request);
    }

    @PutMapping("/{id}")
    public TenantResponseDto update(@PathVariable Long id,
                                    @RequestBody TenantUpdateDto request) {
        return tenantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tenantService.delete(id);
    }
}

