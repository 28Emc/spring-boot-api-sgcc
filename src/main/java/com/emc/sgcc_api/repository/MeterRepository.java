package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Meter;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<@NotNull Meter, @NotNull Long> {

    Page<@NotNull Meter> findByLocationId(Pageable pageable, Long locationId);

    Page<@NotNull Meter> findByServiceId(Pageable pageable, Long serviceId);

    boolean existsByCode(String code);
}
