package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.AllocationCreateDto;
import com.emc.sgcc_api.dto.AllocationResponseDto;
import com.emc.sgcc_api.dto.AllocationUpdateDto;
import com.emc.sgcc_api.service.AllocationService;
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
@RequestMapping("/allocations")
@RequiredArgsConstructor
@Tag(name = "Asignaciones", description = "Gestión de asignaciones de consumo entre inquilinos")
public class AllocationsController {

    private final AllocationService allocationService;

    @GetMapping
    @Operation(summary = "Obtener todas las asignaciones", description = "Retorna una lista paginada de todas las asignaciones de consumo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de asignaciones obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull AllocationResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return allocationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener asignación por ID", description = "Retorna los detalles de una asignación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignación encontrada"),
            @ApiResponse(responseCode = "404", description = "Asignación no encontrada")
    })
    public AllocationResponseDto findById(
            @Parameter(description = "ID de la asignación", required = true)
            @PathVariable Long id) {
        return allocationService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva asignación", description = "Registra una nueva asignación de consumo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public AllocationResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva asignación", required = true)
            @RequestBody AllocationCreateDto request) {
        return allocationService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asignación", description = "Modifica los datos de una asignación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asignación no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public AllocationResponseDto update(
            @Parameter(description = "ID de la asignación", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody AllocationUpdateDto request) {
        return allocationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asignación", description = "Elimina una asignación del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asignación no encontrada")
    })
    public void delete(
            @Parameter(description = "ID de la asignación", required = true)
            @PathVariable Long id) {
        allocationService.delete(id);
    }
}


