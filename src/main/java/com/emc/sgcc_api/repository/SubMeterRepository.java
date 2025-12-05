package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.SubMeter;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubMeterRepository extends JpaRepository<@NotNull SubMeter, @NotNull Long> {

    Page<@NotNull SubMeter> findByTenantId(Pageable pageable, Long tenantId);

    Page<@NotNull SubMeter> findByServiceId(Pageable pageable, Long serviceId);

    boolean existsByCode(String code);
}
