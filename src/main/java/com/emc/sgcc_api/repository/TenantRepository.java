package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Tenant;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<@NotNull Tenant, @NotNull Long> {

    Page<@NotNull Tenant> findByLocationId(Pageable pageable, Long locationId);

    Page<@NotNull Tenant> findByLocationIdAndActiveTrue(Pageable pageable, Long locationId);
}
