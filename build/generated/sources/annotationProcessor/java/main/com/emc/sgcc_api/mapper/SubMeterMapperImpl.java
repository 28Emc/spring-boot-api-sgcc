package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.SubMeterCreateDto;
import com.emc.sgcc_api.dto.SubMeterResponseDto;
import com.emc.sgcc_api.dto.SubMeterUpdateDto;
import com.emc.sgcc_api.entity.ServiceEntity;
import com.emc.sgcc_api.entity.SubMeter;
import com.emc.sgcc_api.entity.Tenant;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T21:33:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class SubMeterMapperImpl implements SubMeterMapper {

    @Override
    public SubMeter toEntity(SubMeterCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubMeter subMeter = new SubMeter();

        subMeter.setCode( dto.getCode() );
        subMeter.setBrand( dto.getBrand() );
        subMeter.setModel( dto.getModel() );
        subMeter.setInstallationDate( dto.getInstallationDate() );

        return subMeter;
    }

    @Override
    public void updateEntity(SubMeter entity, SubMeterUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setCode( dto.getCode() );
        entity.setBrand( dto.getBrand() );
        entity.setModel( dto.getModel() );
        entity.setInstallationDate( dto.getInstallationDate() );
    }

    @Override
    public SubMeterResponseDto toResponse(SubMeter entity) {
        if ( entity == null ) {
            return null;
        }

        SubMeterResponseDto subMeterResponseDto = new SubMeterResponseDto();

        Long id = entityTenantId( entity );
        if ( id != null ) {
            subMeterResponseDto.setTenantId( id );
        }
        else {
            subMeterResponseDto.setTenantId( 0L );
        }
        Long id1 = entityServiceId( entity );
        if ( id1 != null ) {
            subMeterResponseDto.setServiceId( id1 );
        }
        else {
            subMeterResponseDto.setServiceId( 0L );
        }
        subMeterResponseDto.setId( entity.getId() );
        subMeterResponseDto.setCode( entity.getCode() );
        subMeterResponseDto.setBrand( entity.getBrand() );
        subMeterResponseDto.setModel( entity.getModel() );
        if ( entity.getInstallationDate() != null ) {
            subMeterResponseDto.setInstallationDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getInstallationDate() ) );
        }
        if ( entity.getCreatedAt() != null ) {
            subMeterResponseDto.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getCreatedAt() ) );
        }

        return subMeterResponseDto;
    }

    private Long entityTenantId(SubMeter subMeter) {
        Tenant tenant = subMeter.getTenant();
        if ( tenant == null ) {
            return null;
        }
        return tenant.getId();
    }

    private Long entityServiceId(SubMeter subMeter) {
        ServiceEntity service = subMeter.getService();
        if ( service == null ) {
            return null;
        }
        return service.getId();
    }
}
