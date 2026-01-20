package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.MeterCreateDto;
import com.emc.sgcc_api.dto.MeterResponseDto;
import com.emc.sgcc_api.dto.MeterUpdateDto;
import com.emc.sgcc_api.entity.Location;
import com.emc.sgcc_api.entity.Meter;
import com.emc.sgcc_api.entity.ServiceEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T21:33:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class MeterMapperImpl implements MeterMapper {

    @Override
    public Meter toEntity(MeterCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Meter meter = new Meter();

        meter.setCode( dto.getCode() );
        meter.setType( dto.getType() );
        meter.setBrand( dto.getBrand() );
        meter.setModel( dto.getModel() );
        meter.setInstallationDate( dto.getInstallationDate() );

        return meter;
    }

    @Override
    public void updateEntity(Meter entity, MeterUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setCode( dto.getCode() );
        entity.setType( dto.getType() );
        entity.setBrand( dto.getBrand() );
        entity.setModel( dto.getModel() );
        entity.setInstallationDate( dto.getInstallationDate() );
    }

    @Override
    public MeterResponseDto toResponse(Meter entity) {
        if ( entity == null ) {
            return null;
        }

        MeterResponseDto meterResponseDto = new MeterResponseDto();

        Long id = entityLocationId( entity );
        if ( id != null ) {
            meterResponseDto.setLocationId( id );
        }
        else {
            meterResponseDto.setLocationId( 0L );
        }
        Long id1 = entityServiceId( entity );
        if ( id1 != null ) {
            meterResponseDto.setServiceId( id1 );
        }
        else {
            meterResponseDto.setServiceId( 0L );
        }
        meterResponseDto.setId( entity.getId() );
        meterResponseDto.setCode( entity.getCode() );
        meterResponseDto.setType( entity.getType() );
        meterResponseDto.setBrand( entity.getBrand() );
        meterResponseDto.setModel( entity.getModel() );
        meterResponseDto.setInstallationDate( entity.getInstallationDate() );
        meterResponseDto.setCreatedAt( entity.getCreatedAt() );

        return meterResponseDto;
    }

    private Long entityLocationId(Meter meter) {
        Location location = meter.getLocation();
        if ( location == null ) {
            return null;
        }
        return location.getId();
    }

    private Long entityServiceId(Meter meter) {
        ServiceEntity service = meter.getService();
        if ( service == null ) {
            return null;
        }
        return service.getId();
    }
}
