package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.ServiceCreateDto;
import com.emc.sgcc_api.dto.ServiceResponseDto;
import com.emc.sgcc_api.dto.ServiceUpdateDto;
import com.emc.sgcc_api.service.ServiceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServiceService serviceService;

    @GetMapping
    public Page<@NotNull ServiceResponseDto> findAll(Pageable pageable) {
        return serviceService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ServiceResponseDto findById(@PathVariable Long id) {
        return serviceService.findById(id);
    }

    @PostMapping
    public ServiceResponseDto create(@RequestBody ServiceCreateDto request) {
        return serviceService.create(request);
    }

    @PutMapping("/{id}")
    public ServiceResponseDto update(@PathVariable Long id,
                                     @RequestBody ServiceUpdateDto request) {
        return serviceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceService.delete(id);
    }
}


