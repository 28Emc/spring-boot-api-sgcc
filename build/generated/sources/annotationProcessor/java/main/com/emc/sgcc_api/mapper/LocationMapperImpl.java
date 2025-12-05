package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.LocationCreateDto;
import com.emc.sgcc_api.dto.LocationResponseDto;
import com.emc.sgcc_api.dto.LocationUpdateDto;
import com.emc.sgcc_api.entity.Location;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T18:48:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location toEntity(LocationCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Location location = new Location();

        location.setName( dto.getName() );
        location.setDescription( dto.getDescription() );

        return location;
    }

    @Override
    public void updateEntity(Location entity, LocationUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
    }

    @Override
    public LocationResponseDto toResponse(Location entity) {
        if ( entity == null ) {
            return null;
        }

        LocationResponseDto locationResponseDto = new LocationResponseDto();

        locationResponseDto.setId( entity.getId() );
        locationResponseDto.setName( entity.getName() );
        locationResponseDto.setDescription( entity.getDescription() );
        locationResponseDto.setCreatedAt( entity.getCreatedAt() );

        return locationResponseDto;
    }

    @Override
    public List<LocationResponseDto> toResponseList(List<Location> list) {
        if ( list == null ) {
            return null;
        }

        List<LocationResponseDto> list1 = new ArrayList<LocationResponseDto>( list.size() );
        for ( Location location : list ) {
            list1.add( toResponse( location ) );
        }

        return list1;
    }
}
