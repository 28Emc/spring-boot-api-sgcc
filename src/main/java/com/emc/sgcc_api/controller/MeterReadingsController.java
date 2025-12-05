package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.MeterReadingCreateDto;
import com.emc.sgcc_api.dto.MeterReadingResponseDto;
import com.emc.sgcc_api.dto.MeterReadingUpdateDto;
import com.emc.sgcc_api.service.MeterReadingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meter-readings")
@RequiredArgsConstructor
public class MeterReadingsController {

    private final MeterReadingService meterReadingService;

    @GetMapping
    public Page<@NotNull MeterReadingResponseDto> findAll(Pageable pageable) {
        return meterReadingService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MeterReadingResponseDto findById(@PathVariable Long id) {
        return meterReadingService.findById(id);
    }

    @PostMapping
    public MeterReadingResponseDto create(@RequestBody MeterReadingCreateDto request) {
        return meterReadingService.create(request);
    }

    @PutMapping("/{id}")
    public MeterReadingResponseDto update(@PathVariable Long id,
                                          @RequestBody MeterReadingUpdateDto request) {
        return meterReadingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        meterReadingService.delete(id);
    }
}
