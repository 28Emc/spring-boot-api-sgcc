package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.MeterCreateDto;
import com.emc.sgcc_api.dto.MeterResponseDto;
import com.emc.sgcc_api.dto.MeterUpdateDto;
import com.emc.sgcc_api.service.MeterService;
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
@RequestMapping("/meters")
@RequiredArgsConstructor
@Tag(name = "Medidores", description = "Gestión de medidores de consumo")
public class MetersController {

    private final MeterService meterService;

    @GetMapping
    @Operation(summary = "Obtener todos los medidores", description = "Retorna una lista paginada de todos los medidores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de medidores obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull MeterResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return meterService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener medidor por ID", description = "Retorna los detalles de un medidor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidor encontrado"),
            @ApiResponse(responseCode = "404", description = "Medidor no encontrado")
    })
    public MeterResponseDto findById(
            @Parameter(description = "ID del medidor", required = true)
            @PathVariable Long id) {
        return meterService.findById(id);
    }

    @GetMapping("/by-location/{locationId}")
    @Operation(summary = "Obtener medidores por ubicación", description = "Retorna todos los medidores asociados a una ubicación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidores de la ubicación obtenidos"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public Page<@NotNull MeterResponseDto> findByLocation(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable,
            @Parameter(description = "ID de la ubicación", required = true)
            @PathVariable Long locationId) {
        return meterService.findByLocation(pageable, locationId);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo medidor", description = "Registra un nuevo medidor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Medidor duplicado")
    })
    public MeterResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo medidor", required = true)
            @RequestBody MeterCreateDto request) {
        return meterService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar medidor", description = "Modifica los datos de un medidor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Medidor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public MeterResponseDto update(
            @Parameter(description = "ID del medidor", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody MeterUpdateDto request) {
        return meterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar medidor", description = "Elimina un medidor del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Medidor no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del medidor", required = true)
            @PathVariable Long id) {
        meterService.delete(id);
    }
}


