package com.emc.sgcc_api.mapper;

import com.emc.sgcc_api.dto.AllocationCreateDto;
import com.emc.sgcc_api.dto.AllocationResponseDto;
import com.emc.sgcc_api.dto.AllocationUpdateDto;
import com.emc.sgcc_api.entity.Allocation;
import com.emc.sgcc_api.entity.Invoice;
import com.emc.sgcc_api.entity.SubMeter;
import com.emc.sgcc_api.entity.Tenant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T21:33:03-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25 (Oracle Corporation)"
)
@Component
public class AllocationMapperImpl implements AllocationMapper {

    @Override
    public Allocation toEntity(AllocationCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Allocation allocation = new Allocation();

        allocation.setInvoice( allocationCreateDtoToInvoice( dto ) );
        allocation.setTenant( allocationCreateDtoToTenant( dto ) );
        allocation.setSubMeter( allocationCreateDtoToSubMeter( dto ) );
        allocation.setAmount( dto.getAmount() );

        return allocation;
    }

    @Override
    public void updateEntity(Allocation entity, AllocationUpdateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( entity.getInvoice() == null ) {
            entity.setInvoice( new Invoice() );
        }
        allocationUpdateDtoToInvoice( dto, entity.getInvoice() );
        if ( entity.getTenant() == null ) {
            entity.setTenant( new Tenant() );
        }
        allocationUpdateDtoToTenant( dto, entity.getTenant() );
        if ( entity.getSubMeter() == null ) {
            entity.setSubMeter( new SubMeter() );
        }
        allocationUpdateDtoToSubMeter( dto, entity.getSubMeter() );
        entity.setAmount( dto.getAmount() );
    }

    @Override
    public AllocationResponseDto toResponse(Allocation entity) {
        if ( entity == null ) {
            return null;
        }

        AllocationResponseDto allocationResponseDto = new AllocationResponseDto();

        allocationResponseDto.setInvoiceId( entityInvoiceId( entity ) );
        allocationResponseDto.setTenantId( entityTenantId( entity ) );
        allocationResponseDto.setSubMeterId( entitySubMeterId( entity ) );
        allocationResponseDto.setId( entity.getId() );
        allocationResponseDto.setAmount( entity.getAmount() );
        allocationResponseDto.setPercentage( entity.getPercentage() );
        allocationResponseDto.setCreatedAt( entity.getCreatedAt() );

        return allocationResponseDto;
    }

    protected Invoice allocationCreateDtoToInvoice(AllocationCreateDto allocationCreateDto) {
        if ( allocationCreateDto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( allocationCreateDto.getInvoiceId() );

        return invoice;
    }

    protected Tenant allocationCreateDtoToTenant(AllocationCreateDto allocationCreateDto) {
        if ( allocationCreateDto == null ) {
            return null;
        }

        Tenant tenant = new Tenant();

        tenant.setId( allocationCreateDto.getTenantId() );

        return tenant;
    }

    protected SubMeter allocationCreateDtoToSubMeter(AllocationCreateDto allocationCreateDto) {
        if ( allocationCreateDto == null ) {
            return null;
        }

        SubMeter subMeter = new SubMeter();

        subMeter.setId( allocationCreateDto.getSubMeterId() );

        return subMeter;
    }

    protected void allocationUpdateDtoToInvoice(AllocationUpdateDto allocationUpdateDto, Invoice mappingTarget) {
        if ( allocationUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( allocationUpdateDto.getInvoiceId() );
    }

    protected void allocationUpdateDtoToTenant(AllocationUpdateDto allocationUpdateDto, Tenant mappingTarget) {
        if ( allocationUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( allocationUpdateDto.getTenantId() );
    }

    protected void allocationUpdateDtoToSubMeter(AllocationUpdateDto allocationUpdateDto, SubMeter mappingTarget) {
        if ( allocationUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( allocationUpdateDto.getSubMeterId() );
    }

    private Long entityInvoiceId(Allocation allocation) {
        Invoice invoice = allocation.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        return invoice.getId();
    }

    private Long entityTenantId(Allocation allocation) {
        Tenant tenant = allocation.getTenant();
        if ( tenant == null ) {
            return null;
        }
        return tenant.getId();
    }

    private Long entitySubMeterId(Allocation allocation) {
        SubMeter subMeter = allocation.getSubMeter();
        if ( subMeter == null ) {
            return null;
        }
        return subMeter.getId();
    }
}
