package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.TenantCreateDto;
import com.emc.sgcc_api.dto.TenantResponseDto;
import com.emc.sgcc_api.dto.TenantUpdateDto;
import com.emc.sgcc_api.service.TenantService;
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
@RequestMapping("/tenants")
@RequiredArgsConstructor
@Tag(name = "Inquilinos", description = "Gestión de inquilinos y ocupantes de propiedades")
public class TenantsController {

    private final TenantService tenantService;

    @GetMapping
    @Operation(summary = "Obtener todos los inquilinos", description = "Retorna una lista paginada de todos los inquilinos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inquilinos obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull TenantResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return tenantService.findAll(pageable);
    }

    @GetMapping("/location/{locationId}")
    @Operation(summary = "Obtener inquilinos por ubicación", description = "Retorna todos los inquilinos de una ubicación específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquilinos obtenidos"),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada")
    })
    public Page<@NotNull TenantResponseDto> findByLocation(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable,
            @Parameter(description = "ID de la ubicación", required = true)
            @PathVariable Long locationId) {
        return tenantService.findByLocation(pageable, locationId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inquilino por ID", description = "Retorna los detalles de un inquilino específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquilino encontrado"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado")
    })
    public TenantResponseDto findById(
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long id) {
        return tenantService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo inquilino", description = "Registra un nuevo inquilino en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquilino creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public TenantResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo inquilino", required = true)
            @RequestBody TenantCreateDto request) {
        return tenantService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inquilino", description = "Modifica los datos de un inquilino existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquilino actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public TenantResponseDto update(
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody TenantUpdateDto request) {
        return tenantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inquilino", description = "Elimina un inquilino del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inquilino eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Inquilino no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del inquilino", required = true)
            @PathVariable Long id) {
        tenantService.delete(id);
    }
}

