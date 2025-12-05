package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.InvoiceCreateDto;
import com.emc.sgcc_api.dto.InvoiceResponseDto;
import com.emc.sgcc_api.dto.InvoiceUpdateDto;
import com.emc.sgcc_api.service.InvoiceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoicesController {

    private final InvoiceService service;

    @GetMapping
    public Page<@NotNull InvoiceResponseDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public InvoiceResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public InvoiceResponseDto create(@RequestBody InvoiceCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public InvoiceResponseDto update(@PathVariable Long id,
                                     @RequestBody InvoiceUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}



