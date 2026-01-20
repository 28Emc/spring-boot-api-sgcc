package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.MeterReadingCreateDto;
import com.emc.sgcc_api.dto.MeterReadingResponseDto;
import com.emc.sgcc_api.dto.MeterReadingUpdateDto;
import com.emc.sgcc_api.entity.Meter;
import com.emc.sgcc_api.entity.MeterReading;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T21:33:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class MeterReadingMapperImpl implements MeterReadingMapper {

    @Override
    public MeterReading toEntity(MeterReadingCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        MeterReading meterReading = new MeterReading();

        meterReading.setMeter( meterReadingCreateDtoToMeter( dto ) );
        meterReading.setReadingValue( dto.getValue() );
        meterReading.setReadingDate( dto.getReadingDate() );

        return meterReading;
    }

    @Override
    public void updateEntity(MeterReading entity, MeterReadingUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setReadingValue( dto.getValue() );
        entity.setReadingDate( dto.getReadingDate() );
        if ( entity.getMeter() == null ) {
            entity.setMeter( new Meter() );
        }
        meterReadingUpdateDtoToMeter( dto, entity.getMeter() );
    }

    @Override
    public MeterReadingResponseDto toResponse(MeterReading entity) {
        if ( entity == null ) {
            return null;
        }

        MeterReadingResponseDto meterReadingResponseDto = new MeterReadingResponseDto();

        meterReadingResponseDto.setMeterId( entityMeterId( entity ) );
        meterReadingResponseDto.setValue( entity.getReadingValue() );
        meterReadingResponseDto.setId( entity.getId() );
        meterReadingResponseDto.setReadingDate( entity.getReadingDate() );
        meterReadingResponseDto.setCreatedAt( entity.getCreatedAt() );

        return meterReadingResponseDto;
    }

    protected Meter meterReadingCreateDtoToMeter(MeterReadingCreateDto meterReadingCreateDto) {
        if ( meterReadingCreateDto == null ) {
            return null;
        }

        Meter meter = new Meter();

        meter.setId( meterReadingCreateDto.getMeterId() );

        return meter;
    }

    protected void meterReadingUpdateDtoToMeter(MeterReadingUpdateDto meterReadingUpdateDto, Meter mappingTarget) {
        if ( meterReadingUpdateDto == null ) {
            return;
        }
    }

    private Long entityMeterId(MeterReading meterReading) {
        Meter meter = meterReading.getMeter();
        if ( meter == null ) {
            return null;
        }
        return meter.getId();
    }
}
