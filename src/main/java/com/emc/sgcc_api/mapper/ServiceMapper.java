package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ServiceEntity toEntity(ServiceCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget ServiceEntity entity, ServiceUpdateDto dto);

    ServiceResponseDto toResponse(ServiceEntity entity);
}



