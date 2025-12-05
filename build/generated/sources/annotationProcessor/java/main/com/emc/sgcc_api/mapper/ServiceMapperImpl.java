package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.ServiceCreateDto;
import com.emc.sgcc_api.dto.ServiceResponseDto;
import com.emc.sgcc_api.dto.ServiceUpdateDto;
import com.emc.sgcc_api.entity.ServiceEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T18:38:26-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceEntity toEntity(ServiceCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setName( dto.getName() );
        serviceEntity.setDescription( dto.getDescription() );

        serviceEntity.setCreatedAt( java.time.LocalDateTime.now() );

        return serviceEntity;
    }

    @Override
    public void updateEntity(ServiceEntity entity, ServiceUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
    }

    @Override
    public ServiceResponseDto toResponse(ServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        serviceResponseDto.setId( entity.getId() );
        serviceResponseDto.setName( entity.getName() );
        serviceResponseDto.setDescription( entity.getDescription() );
        serviceResponseDto.setCreatedAt( entity.getCreatedAt() );

        return serviceResponseDto;
    }
}
