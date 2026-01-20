package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.InvoiceCreateDto;
import com.emc.sgcc_api.dto.InvoiceResponseDto;
import com.emc.sgcc_api.dto.InvoiceUpdateDto;
import com.emc.sgcc_api.entity.Invoice;
import com.emc.sgcc_api.entity.Meter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T21:33:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice toEntity(InvoiceCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setMeter( invoiceCreateDtoToMeter( dto ) );
        invoice.setPeriodStart( dto.getPeriodStart() );
        invoice.setPeriodEnd( dto.getPeriodEnd() );
        invoice.setTotalAmount( dto.getTotalAmount() );

        return invoice;
    }

    @Override
    public void updateEntity(Invoice entity, InvoiceUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getMeter() == null ) {
            entity.setMeter( new Meter() );
        }
        invoiceUpdateDtoToMeter( dto, entity.getMeter() );
        entity.setPeriodStart( dto.getPeriodStart() );
        entity.setPeriodEnd( dto.getPeriodEnd() );
        entity.setTotalAmount( dto.getTotalAmount() );
    }

    @Override
    public InvoiceResponseDto toResponse(Invoice entity) {
        if ( entity == null ) {
            return null;
        }

        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();

        invoiceResponseDto.setAmount( entity.getTotalAmount() );
        invoiceResponseDto.setPeriodStart( entity.getPeriodStart() );
        invoiceResponseDto.setPeriodEnd( entity.getPeriodEnd() );
        invoiceResponseDto.setId( entity.getId() );
        invoiceResponseDto.setCreatedAt( entity.getCreatedAt() );

        return invoiceResponseDto;
    }

    protected Meter invoiceCreateDtoToMeter(InvoiceCreateDto invoiceCreateDto) {
        if ( invoiceCreateDto == null ) {
            return null;
        }

        Meter meter = new Meter();

        meter.setId( invoiceCreateDto.getMeterId() );

        return meter;
    }

    protected void invoiceUpdateDtoToMeter(InvoiceUpdateDto invoiceUpdateDto, Meter mappingTarget) {
        if ( invoiceUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( invoiceUpdateDto.getMeterId() );
    }
}
