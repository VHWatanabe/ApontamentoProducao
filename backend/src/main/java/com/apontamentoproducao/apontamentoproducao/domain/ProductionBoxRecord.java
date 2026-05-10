package com.apontamentoproducao.apontamentoproducao.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "production_box_records")
@Getter
@Setter
public class ProductionBoxRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "production_record_id")
    private ProductionRecord productionRecord;

    @Column(nullable = false)
    private Integer boxNumber;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    private String notes;
}