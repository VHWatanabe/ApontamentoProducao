package com.apontamentoproducao.apontamentoproducao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shifts")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User operator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShiftStatus status;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;
}