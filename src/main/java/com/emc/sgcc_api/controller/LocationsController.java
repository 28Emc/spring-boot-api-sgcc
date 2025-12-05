package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.LocationCreateDto;
import com.emc.sgcc_api.dto.LocationResponseDto;
import com.emc.sgcc_api.dto.LocationUpdateDto;
import com.emc.sgcc_api.service.LocationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationsController {

    private final LocationService locationService;

    @GetMapping
    public Page<@NotNull LocationResponseDto> findAll(Pageable pageable) {
        return locationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public LocationResponseDto findById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @PostMapping
    public LocationResponseDto create(@RequestBody LocationCreateDto request) {
        return locationService.create(request);
    }

    @PutMapping("/{id}")
    public LocationResponseDto update(@PathVariable Long id,
                                      @RequestBody LocationUpdateDto request) {
        return locationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        locationService.delete(id);
    }
}

