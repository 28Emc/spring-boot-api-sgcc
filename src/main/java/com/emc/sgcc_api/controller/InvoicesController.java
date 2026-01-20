package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.InvoiceCreateDto;
import com.emc.sgcc_api.dto.InvoiceResponseDto;
import com.emc.sgcc_api.dto.InvoiceUpdateDto;
import com.emc.sgcc_api.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Tag(name = "Facturas", description = "Gestión de facturas de consumo")
public class InvoicesController {

    private final InvoiceService service;

    @GetMapping
    @Operation(summary = "Obtener todas las facturas", description = "Retorna una lista paginada de todas las facturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull InvoiceResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener factura por ID", description = "Retorna los detalles de una factura específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public InvoiceResponseDto findById(
            @Parameter(description = "ID de la factura", required = true)
            @PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva factura", description = "Registra una nueva factura en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public InvoiceResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva factura", required = true)
            @RequestBody InvoiceCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar factura", description = "Modifica los datos de una factura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public InvoiceResponseDto update(
            @Parameter(description = "ID de la factura", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody InvoiceUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura", description = "Elimina una factura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public void delete(
            @Parameter(description = "ID de la factura", required = true)
            @PathVariable Long id) {
        service.delete(id);
    }
}



