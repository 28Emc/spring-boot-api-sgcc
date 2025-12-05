package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.SubMeterReadingCreateDto;
import com.emc.sgcc_api.dto.SubMeterReadingUpdateDto;
import com.emc.sgcc_api.dto.SubMeterReadingResponseDto;
import com.emc.sgcc_api.entity.SubMeterReading;
import com.emc.sgcc_api.service.SubMeterReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-meter-readings")
@RequiredArgsConstructor
public class SubMeterReadingController {

    private final SubMeterReadingService service;

    @GetMapping
    public List<SubMeterReadingResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SubMeterReadingResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public SubMeterReadingResponseDto create(@RequestBody SubMeterReadingCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SubMeterReadingResponseDto update(@PathVariable Long id,
                                             @RequestBody SubMeterReadingUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
