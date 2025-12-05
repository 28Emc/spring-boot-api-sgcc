package com.emc.sgcc_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sub_meter_readings")
@Data
public class SubMeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_meter_id")
    private SubMeter subMeter;

    @Column(name = "reading_value", nullable = false, precision = 18, scale = 3)
    private BigDecimal readingValue;

    @Column(name = "reading_date", nullable = false)
    private LocalDate readingDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

