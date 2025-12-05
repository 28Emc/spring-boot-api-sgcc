package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.MeterCreateDto;
import com.emc.sgcc_api.dto.MeterResponseDto;
import com.emc.sgcc_api.dto.MeterUpdateDto;
import com.emc.sgcc_api.entity.Meter;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MeterMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),

            @Mapping(target = "location", ignore = true),
            @Mapping(target = "service", ignore = true)
    })
    Meter toEntity(MeterCreateDto dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),

            @Mapping(target = "location", ignore = true),
            @Mapping(target = "service", ignore = true)
    })
    void updateEntity(@MappingTarget Meter entity, MeterUpdateDto dto);

    @Mappings({
            @Mapping(target = "locationId", source = "location.id", defaultValue = "0L"),
            @Mapping(target = "serviceId", source = "service.id", defaultValue = "0L")
    })
    MeterResponseDto toResponse(Meter entity);
}
