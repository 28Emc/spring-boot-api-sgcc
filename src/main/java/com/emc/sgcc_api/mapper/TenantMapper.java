package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.Tenant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "location", ignore = true)
    })
    Tenant toEntity(TenantCreateDto dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "location", ignore = true)
    })
    void updateEntity(@MappingTarget Tenant entity, TenantUpdateDto dto);

    @Mappings({
            @Mapping(target = "locationId", source = "location.id", defaultValue = "0L")
    })
    TenantResponseDto toResponse(Tenant entity);
}
