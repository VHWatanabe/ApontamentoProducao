package com.apontamentoproducao.apontamentoproducao.domain;

import com.apontamentoproducao.apontamentoproducao.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "production_records")
@Getter
@Setter
public class ProductionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private Integer goodQuantity = 0;

    @Column(nullable = false)
    private Integer scrapQuantity = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductionRecordStatus status;

    private String notes;
}