package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.LocationCreateDto;
import com.emc.sgcc_api.dto.LocationResponseDto;
import com.emc.sgcc_api.dto.LocationUpdateDto;
import com.emc.sgcc_api.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Location toEntity(LocationCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Location entity, LocationUpdateDto dto);

    LocationResponseDto toResponse(Location entity);

    List<LocationResponseDto> toResponseList(List<Location> list);
}
