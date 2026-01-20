package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.LocationCreateDto;
import com.emc.sgcc_api.dto.LocationResponseDto;
import com.emc.sgcc_api.dto.LocationUpdateDto;
import com.emc.sgcc_api.service.LocationService;
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
@RequestMapping("/locations")
@RequiredArgsConstructor
@Tag(name = "Ubicaciones", description = "Gestión de ubicaciones y propiedades")
public class LocationsController {

    private final LocationService locationService;

    @GetMapping
    @Operation(summary = "Obtener todas las ubicaciones", description = "Retorna una lista paginada de todas las ubicaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ubicaciones obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull LocationResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return locationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ubicación por ID", description = "Retorna los detalles de una ubicación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación encontrada"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public LocationResponseDto findById(
            @Parameter(description = "ID de la ubicación", required = true)
            @PathVariable Long id) {
        return locationService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva ubicación", description = "Registra una nueva ubicación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public LocationResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva ubicación", required = true)
            @RequestBody LocationCreateDto request) {
        return locationService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ubicación", description = "Modifica los datos de una ubicación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public LocationResponseDto update(
            @Parameter(description = "ID de la ubicación", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody LocationUpdateDto request) {
        return locationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ubicación", description = "Elimina una ubicación del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public void delete(
            @Parameter(description = "ID de la ubicación", required = true)
            @PathVariable Long id) {
        locationService.delete(id);
    }
}

