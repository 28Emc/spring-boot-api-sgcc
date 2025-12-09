package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.TenantCreateDto;
import com.emc.sgcc_api.dto.TenantResponseDto;
import com.emc.sgcc_api.dto.TenantUpdateDto;
import com.emc.sgcc_api.entity.Location;
import com.emc.sgcc_api.entity.Tenant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-05T18:13:33-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class TenantMapperImpl implements TenantMapper {

    @Override
    public Tenant toEntity(TenantCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tenant tenant = new Tenant();

        tenant.setName( dto.getName() );

        return tenant;
    }

    @Override
    public void updateEntity(Tenant entity, TenantUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setActive( dto.getActive() );
    }

    @Override
    public TenantResponseDto toResponse(Tenant entity) {
        if ( entity == null ) {
            return null;
        }

        TenantResponseDto tenantResponseDto = new TenantResponseDto();

        Long id = entityLocationId( entity );
        if ( id != null ) {
            tenantResponseDto.setLocationId( id );
        }
        else {
            tenantResponseDto.setLocationId( 0L );
        }
        tenantResponseDto.setId( entity.getId() );
        tenantResponseDto.setName( entity.getName() );
        tenantResponseDto.setActive( entity.getActive() );
        tenantResponseDto.setCreatedAt( entity.getCreatedAt() );

        return tenantResponseDto;
    }

    private Long entityLocationId(Tenant tenant) {
        Location location = tenant.getLocation();
        if ( location == null ) {
            return null;
        }
        return location.getId();
    }
}
