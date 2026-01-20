package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.SubMeterCreateDto;
import com.emc.sgcc_api.dto.SubMeterResponseDto;
import com.emc.sgcc_api.dto.SubMeterUpdateDto;
import com.emc.sgcc_api.service.SubMeterService;
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
@RequestMapping("/sub-meters")
@RequiredArgsConstructor
@Tag(name = "Submedidores", description = "Gestión de medidores secundarios para inquilinos")
public class SubMetersController {

    private final SubMeterService subMeterService;

    @GetMapping
    @Operation(summary = "Obtener todos los submedidores", description = "Retorna una lista paginada de todos los submedidores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de submedidores obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull SubMeterResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return subMeterService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener submedidor por ID", description = "Retorna los detalles de un submedidor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Submedidor encontrado"),
            @ApiResponse(responseCode = "404", description = "Submedidor no encontrado")
    })
    public SubMeterResponseDto findById(
            @Parameter(description = "ID del submedidor", required = true)
            @PathVariable Long id) {
        return subMeterService.findById(id);
    }

    @GetMapping("/by-tenant/{tenantId}")
    @Operation(summary = "Obtener submedidores por inquilino", description = "Retorna todos los submedidores asociados a un inquilino específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Submedidores obtenidos"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado")
    })
    public Page<@NotNull SubMeterResponseDto> findByTenant(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable,
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long tenantId) {
        return subMeterService.findByTenant(pageable, tenantId);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo submedidor", description = "Registra un nuevo submedidor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Submedidor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public SubMeterResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo submedidor", required = true)
            @RequestBody SubMeterCreateDto request) {
        return subMeterService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar submedidor", description = "Modifica los datos de un submedidor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Submedidor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Submedidor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public SubMeterResponseDto update(
            @Parameter(description = "ID del submedidor", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody SubMeterUpdateDto request) {
        return subMeterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar submedidor", description = "Elimina un submedidor del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Submedidor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Submedidor no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del submedidor", required = true)
            @PathVariable Long id) {
        subMeterService.delete(id);
    }
}


