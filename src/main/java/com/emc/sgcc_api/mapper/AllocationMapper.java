package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.Allocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AllocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice.id", source = "invoiceId")
    @Mapping(target = "tenant.id", source = "tenantId")
    @Mapping(target = "subMeter.id", source = "subMeterId")
    @Mapping(target = "percentage", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Allocation toEntity(AllocationCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invoice.id", source = "invoiceId")
    @Mapping(target = "tenant.id", source = "tenantId")
    @Mapping(target = "subMeter.id", source = "subMeterId")
    @Mapping(target = "percentage", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Allocation entity, AllocationUpdateDto dto);

    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "subMeterId", source = "subMeter.id")
    AllocationResponseDto toResponse(Allocation entity);
}

