package com.emc.sgcc_api.controller;

import com.emc.sgcc_api.dto.ServiceCreateDto;
import com.emc.sgcc_api.dto.ServiceResponseDto;
import com.emc.sgcc_api.dto.ServiceUpdateDto;
import com.emc.sgcc_api.service.ServiceService;
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
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Servicios", description = "Gestión de servicios de suministro (agua, electricidad, gas)")
public class ServicesController {

    private final ServiceService serviceService;

    @GetMapping
    @Operation(summary = "Obtener todos los servicios", description = "Retorna una lista paginada de todos los servicios disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de servicios obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos")
    })
    public Page<@NotNull ServiceResponseDto> findAll(
            @Parameter(description = "Parámetros de paginación")
            Pageable pageable) {
        return serviceService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener servicio por ID", description = "Retorna los detalles de un servicio específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio encontrado"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    public ServiceResponseDto findById(
            @Parameter(description = "ID del servicio", required = true)
            @PathVariable Long id) {
        return serviceService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo servicio", description = "Registra un nuevo servicio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ServiceResponseDto create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo servicio", required = true)
            @RequestBody ServiceCreateDto request) {
        return serviceService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar servicio", description = "Modifica los datos de un servicio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ServiceResponseDto update(
            @Parameter(description = "ID del servicio", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos a actualizar", required = true)
            @RequestBody ServiceUpdateDto request) {
        return serviceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar servicio", description = "Elimina un servicio del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del servicio", required = true)
            @PathVariable Long id) {
        serviceService.delete(id);
    }
}


