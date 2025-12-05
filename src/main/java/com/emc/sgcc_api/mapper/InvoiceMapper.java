package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.*;
import com.emc.sgcc_api.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meter.id", source = "meterId")
    @Mapping(target = "periodStart", source = "periodStart")
    @Mapping(target = "periodEnd", source = "periodEnd")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "file", ignore = true)
    @Mapping(target = "issuedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Invoice toEntity(InvoiceCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meter.id", source = "meterId")
    @Mapping(target = "periodStart", source = "periodStart")
    @Mapping(target = "periodEnd", source = "periodEnd")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "file", ignore = true)
    @Mapping(target = "issuedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Invoice entity, InvoiceUpdateDto dto);

    @Mapping(target = "amount", source = "totalAmount")
    @Mapping(target = "periodStart", source = "periodStart")
    @Mapping(target = "periodEnd", source = "periodEnd")
    InvoiceResponseDto toResponse(Invoice entity);
}
