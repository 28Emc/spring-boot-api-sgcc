package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.MeterReading;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MeterReadingRepository extends JpaRepository<@NotNull MeterReading, @NotNull Long> {

    Page<@NotNull MeterReading> findByMeterId(Pageable pageable, Long meterId);

    Page<@NotNull MeterReading> findByMeterIdAndReadingDateBetween(Pageable pageable, Long meterId, LocalDate start, LocalDate end);

    Optional<MeterReading> findFirstByMeterIdAndReadingDateLessThanEqualOrderByReadingDateDesc(Long meterId, LocalDate readingDate);

    Optional<MeterReading> findFirstByMeterIdAndReadingDateLessThanOrderByReadingDateDesc(Long meterId, LocalDate readingDate);

    @Query("SELECT COALESCE(SUM(mr.readingValue), 0) FROM MeterReading mr WHERE mr.meter.id = :meterId AND mr.readingDate BETWEEN :startDate AND :endDate")
    BigDecimal sumConsumptionByMeterIdAndPeriod(@Param("meterId") Long meterId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
