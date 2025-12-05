package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.SubMeter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubMeterMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tenant", ignore = true),
            @Mapping(target = "service", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    SubMeter toEntity(SubMeterCreateDto dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tenant", ignore = true),
            @Mapping(target = "service", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    void updateEntity(@MappingTarget SubMeter entity, SubMeterUpdateDto dto);

    @Mappings({
            @Mapping(target = "tenantId", source = "tenant.id", defaultValue = "0L"),
            @Mapping(target = "serviceId", source = "service.id", defaultValue = "0L")
    })
    SubMeterResponseDto toResponse(SubMeter entity);
}



