package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Allocation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AllocationRepository extends JpaRepository<@NotNull Allocation, @NotNull Long> {

    Page<@NotNull Allocation> findByInvoiceId(Pageable pageable, Long invoiceId);

    Page<@NotNull Allocation> findByTenantId(Pageable pageable, Long tenantId);

    @Query("SELECT a FROM Allocation a WHERE a.tenant.id = :tenantId AND YEAR(a.invoice.periodStart) = :year AND MONTH(a.invoice.periodStart) = :month")
    List<Allocation> findByTenantIdAndPeriod(@Param("tenantId") Long tenantId, @Param("month") Integer month, @Param("year") Integer year);
}
