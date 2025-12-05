package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.MeterReading;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MeterReadingRepository extends JpaRepository<@NotNull MeterReading, @NotNull Long> {

    Page<@NotNull MeterReading> findByMeterId(Pageable pageable, Long meterId);

    Page<@NotNull MeterReading> findByMeterIdAndReadingDateBetween(Pageable pageable, Long meterId, LocalDate start, LocalDate end);
}
