package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.SubMeterReading;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubMeterReadingRepository extends JpaRepository<@NotNull SubMeterReading, @NotNull Long> {

    List<SubMeterReading> findBySubMeterId(Long subMeterId);

    List<SubMeterReading> findBySubMeterIdAndReadingDateBetween(Long subMeterId, LocalDate start, LocalDate end);
}
