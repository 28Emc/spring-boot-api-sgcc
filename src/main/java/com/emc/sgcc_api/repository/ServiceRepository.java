package com.emc.sgcc_api.repository;

import com.emc.sgcc_api.entity.ServiceEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<@NotNull ServiceEntity, @NotNull Long> {
}
