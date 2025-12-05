package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.MeterCreateDto;
import com.emc.sgcc_api.dto.MeterResponseDto;
import com.emc.sgcc_api.dto.MeterUpdateDto;
import com.emc.sgcc_api.service.MeterService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meters")
@RequiredArgsConstructor
public class MetersController {

    private final MeterService meterService;

    @GetMapping
    public Page<@NotNull MeterResponseDto> findAll(Pageable pageable) {
        return meterService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MeterResponseDto findById(@PathVariable Long id) {
        return meterService.findById(id);
    }

    @GetMapping("/by-location/{locationId}")
    public Page<@NotNull MeterResponseDto> findByLocation(
            Pageable pageable,
            @PathVariable Long locationId
    ) {
        return meterService.findByLocation(pageable, locationId);
    }

    @PostMapping
    public MeterResponseDto create(@RequestBody MeterCreateDto request) {
        return meterService.create(request);
    }

    @PutMapping("/{id}")
    public MeterResponseDto update(@PathVariable Long id,
                                   @RequestBody MeterUpdateDto request) {
        return meterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        meterService.delete(id);
    }
}


