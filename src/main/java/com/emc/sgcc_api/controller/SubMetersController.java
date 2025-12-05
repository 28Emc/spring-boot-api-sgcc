package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.SubMeterCreateDto;
import com.emc.sgcc_api.dto.SubMeterResponseDto;
import com.emc.sgcc_api.dto.SubMeterUpdateDto;
import com.emc.sgcc_api.service.SubMeterService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sub-meters")
@RequiredArgsConstructor
public class SubMetersController {

    private final SubMeterService subMeterService;

    @GetMapping
    public Page<@NotNull SubMeterResponseDto> findAll(Pageable pageable) {
        return subMeterService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public SubMeterResponseDto findById(@PathVariable Long id) {
        return subMeterService.findById(id);
    }

    @GetMapping("/by-tenant/{tenantId}")
    public Page<@NotNull SubMeterResponseDto> findByTenant(
            Pageable pageable,
            @PathVariable Long tenantId
    ) {
        return subMeterService.findByTenant(pageable, tenantId);
    }

    @PostMapping
    public SubMeterResponseDto create(@RequestBody SubMeterCreateDto request) {
        return subMeterService.create(request);
    }

    @PutMapping("/{id}")
    public SubMeterResponseDto update(
            @PathVariable Long id,
            @RequestBody SubMeterUpdateDto request
    ) {
        return subMeterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subMeterService.delete(id);
    }
}


