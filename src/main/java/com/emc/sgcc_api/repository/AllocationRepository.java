package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Allocation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<@NotNull Allocation, @NotNull Long> {

    Page<@NotNull Allocation> findByInvoiceId(Pageable pageable, Long invoiceId);

    Page<@NotNull Allocation> findByTenantId(Pageable pageable, Long tenantId);
}
