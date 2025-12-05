package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.MeterReading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MeterReadingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meter.id", source = "meterId")
    @Mapping(target = "readingValue", source = "value")
    @Mapping(target = "readingDate", source = "readingDate")
    @Mapping(target = "createdAt", ignore = true)
    MeterReading toEntity(MeterReadingCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meter.id", ignore = true)
    @Mapping(target = "readingValue", source = "value")
    @Mapping(target = "readingDate", source = "readingDate")
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget MeterReading entity, MeterReadingUpdateDto dto);

    @Mapping(target = "meterId", source = "meter.id")
    @Mapping(target = "value", source = "readingValue")
    MeterReadingResponseDto toResponse(MeterReading entity);
}
