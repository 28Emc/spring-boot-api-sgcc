package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.SubMeterReadingCreateDto;
import com.emc.sgcc_api.dto.SubMeterReadingUpdateDto;
import com.emc.sgcc_api.dto.SubMeterReadingResponseDto;
import com.emc.sgcc_api.entity.SubMeterReading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubMeterReadingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "readingValue", source = "value")
    @Mapping(target = "subMeter.id", source = "subMeterId")
    @Mapping(target = "createdAt", ignore = true)
    SubMeterReading toEntity(SubMeterReadingCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "readingValue", source = "value")
    @Mapping(target = "subMeter.id", source = "subMeterId")
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget SubMeterReading entity, SubMeterReadingUpdateDto dto);

    @Mapping(target = "value", source = "readingValue")
    @Mapping(target = "subMeterId", source = "subMeter.id")
    SubMeterReadingResponseDto toResponse(SubMeterReading entity);

    List<SubMeterReadingResponseDto> toResponseList(List<SubMeterReading> list);
}
