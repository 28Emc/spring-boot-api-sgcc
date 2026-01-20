package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.SubMeterReadingCreateDto;
import com.emc.sgcc_api.dto.SubMeterReadingUpdateDto;
import com.emc.sgcc_api.dto.SubMeterReadingResponseDto;
import com.emc.sgcc_api.service.SubMeterReadingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-meter-readings")
@RequiredArgsConstructor
@Tag(name = "Lecturas de Submedidores", description = "Gestión de lecturas de submedidores para inquilinos")
public class SubMeterReadingController {

    private final SubMeterReadingService service;

    @GetMapping
    @Operation(summary = "Obtener todas las lecturas de submedidores", description = "Retorna la lista de todas las lecturas de submedidores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de lecturas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public List<SubMeterReadingResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener lectura de submedidor por ID", description = "Retorna los detalles de una lectura específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura encontrada"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada")
    })
    public SubMeterReadingResponseDto findById(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nueva lectura de submedidor", description = "Registra una nueva lectura de submedidor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public SubMeterReadingResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva lectura", required = true)
            @RequestBody SubMeterReadingCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar lectura de submedidor", description = "Modifica los datos de una lectura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public SubMeterReadingResponseDto update(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody SubMeterReadingUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar lectura de submedidor", description = "Elimina una lectura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lectura eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Lectura no encontrada")
    })
    public void delete(
            @Parameter(description = "ID de la lectura", required = true)
            @PathVariable Long id) {
        service.delete(id);
    }
}
