package com.emc.sgcc_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sub_meters")
@Data
public class SubMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Column(nullable = false, unique = true, length = 100)
    private String code;

    private String brand;
    private String model;

    @Column(name = "installation_date")
    private LocalDate installationDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

