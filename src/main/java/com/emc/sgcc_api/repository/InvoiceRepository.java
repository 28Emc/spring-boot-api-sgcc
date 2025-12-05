package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Invoice;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InvoiceRepository extends JpaRepository<@NotNull Invoice, @NotNull Long> {

    Page<@NotNull Invoice> findByMeterId(Pageable pageable, Long meterId);

    Page<@NotNull Invoice> findByPeriodStartBetween(Pageable pageable, LocalDate start, LocalDate end);
}
