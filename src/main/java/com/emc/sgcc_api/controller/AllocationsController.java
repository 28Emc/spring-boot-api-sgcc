package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.AllocationCreateDto;
import com.emc.sgcc_api.dto.AllocationResponseDto;
import com.emc.sgcc_api.dto.AllocationUpdateDto;
import com.emc.sgcc_api.service.AllocationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocations")
@RequiredArgsConstructor
public class AllocationsController {

    private final AllocationService allocationService;

    @GetMapping
    public Page<@NotNull AllocationResponseDto> findAll(Pageable pageable) {
        return allocationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public AllocationResponseDto findById(@PathVariable Long id) {
        return allocationService.findById(id);
    }

    @PostMapping
    public AllocationResponseDto create(@RequestBody AllocationCreateDto request) {
        return allocationService.create(request);
    }

    @PutMapping("/{id}")
    public AllocationResponseDto update(@PathVariable Long id,
                                        @RequestBody AllocationUpdateDto request) {
        return allocationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        allocationService.delete(id);
    }
}


