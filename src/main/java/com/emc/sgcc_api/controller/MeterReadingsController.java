package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.MeterReadingCreateDto;
import com.emc.sgcc_api.dto.MeterReadingResponseDto;
import com.emc.sgcc_api.dto.MeterReadingUpdateDto;
import com.emc.sgcc_api.service.MeterReadingService;
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
@RequestMapping("/meter-readings")
@RequiredArgsConstructor
@Tag(name = "Lecturas de Medidores", description = "Gestión de lecturas y registros de consumo")
public class MeterReadingsController {

    private final MeterReadingService meterReadingService;

    @GetMapping
    @Operation(summary = "Obtener todas las lecturas", description = "Retorna una lista paginada de todas las lecturas de medidores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lecturas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull MeterReadingResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return meterReadingService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener lectura por ID", description = "Retorna los detalles de una lectura específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura encontrada"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada")
    })
    public MeterReadingResponseDto findById(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id) {
        return meterReadingService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva lectura", description = "Registra una nueva lectura de medidor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public MeterReadingResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva lectura", required = true)
            @RequestBody MeterReadingCreateDto request) {
        return meterReadingService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar lectura", description = "Modifica los datos de una lectura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public MeterReadingResponseDto update(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody MeterReadingUpdateDto request) {
        return meterReadingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar lectura", description = "Elimina una lectura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada")
    })
    public void delete(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id) {
        meterReadingService.delete(id);
    }
}
