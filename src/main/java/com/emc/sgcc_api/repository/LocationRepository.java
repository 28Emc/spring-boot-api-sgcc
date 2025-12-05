package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.Location;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<@NotNull Location, @NotNull Long> {
}