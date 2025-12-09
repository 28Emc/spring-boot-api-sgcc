package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.SubMeterReadingCreateDto;
import com.emc.sgcc_api.dto.SubMeterReadingResponseDto;
import com.emc.sgcc_api.dto.SubMeterReadingUpdateDto;
import com.emc.sgcc_api.entity.SubMeter;
import com.emc.sgcc_api.entity.SubMeterReading;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-05T18:13:33-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class SubMeterReadingMapperImpl implements SubMeterReadingMapper {

    @Override
    public SubMeterReading toEntity(SubMeterReadingCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubMeterReading subMeterReading = new SubMeterReading();

        subMeterReading.setSubMeter( subMeterReadingCreateDtoToSubMeter( dto ) );
        subMeterReading.setReadingValue( dto.getValue() );
        subMeterReading.setReadingDate( dto.getReadingDate() );

        return subMeterReading;
    }

    @Override
    public void updateEntity(SubMeterReading entity, SubMeterReadingUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getSubMeter() == null ) {
            entity.setSubMeter( new SubMeter() );
        }
        subMeterReadingUpdateDtoToSubMeter( dto, entity.getSubMeter() );
        entity.setReadingValue( dto.getValue() );
        entity.setReadingDate( dto.getReadingDate() );
    }

    @Override
    public SubMeterReadingResponseDto toResponse(SubMeterReading entity) {
        if ( entity == null ) {
            return null;
        }

        SubMeterReadingResponseDto subMeterReadingResponseDto = new SubMeterReadingResponseDto();

        subMeterReadingResponseDto.setValue( entity.getReadingValue() );
        subMeterReadingResponseDto.setSubMeterId( entitySubMeterId( entity ) );
        subMeterReadingResponseDto.setId( entity.getId() );
        subMeterReadingResponseDto.setReadingDate( entity.getReadingDate() );
        subMeterReadingResponseDto.setCreatedAt( entity.getCreatedAt() );

        return subMeterReadingResponseDto;
    }

    @Override
    public List<SubMeterReadingResponseDto> toResponseList(List<SubMeterReading> list) {
        if ( list == null ) {
            return null;
        }

        List<SubMeterReadingResponseDto> list1 = new ArrayList<SubMeterReadingResponseDto>( list.size() );
        for ( SubMeterReading subMeterReading : list ) {
            list1.add( toResponse( subMeterReading ) );
        }

        return list1;
    }

    protected SubMeter subMeterReadingCreateDtoToSubMeter(SubMeterReadingCreateDto subMeterReadingCreateDto) {
        if ( subMeterReadingCreateDto == null ) {
            return null;
        }

        SubMeter subMeter = new SubMeter();

        subMeter.setId( subMeterReadingCreateDto.getSubMeterId() );

        return subMeter;
    }

    protected void subMeterReadingUpdateDtoToSubMeter(SubMeterReadingUpdateDto subMeterReadingUpdateDto, SubMeter mappingTarget) {
        if ( subMeterReadingUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( subMeterReadingUpdateDto.getSubMeterId() );
    }

    private Long entitySubMeterId(SubMeterReading subMeterReading) {
        SubMeter subMeter = subMeterReading.getSubMeter();
        if ( subMeter == null ) {
            return null;
        }
        return subMeter.getId();
    }
}
