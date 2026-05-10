package com.apontamentoproducao.apontamentoproducao.dto;

import com.apontamentoproducao.apontamentoproducao.domain.ProductionBoxRecord;

import java.time.LocalDateTime;

public class ProductionBoxRecordResponse {

    private Long id;
    private Long productionRecordId;
    private Integer boxNumber;
    private LocalDateTime recordedAt;
    private Long secondsSincePreviousBox;
    private String notes;

    public ProductionBoxRecordResponse(ProductionBoxRecord record, Long secondsSincePreviousBox) {
        this.id = record.getId();
        this.productionRecordId = record.getProductionRecord().getId();
        this.boxNumber = record.getBoxNumber();
        this.recordedAt = record.getRecordedAt();
        this.secondsSincePreviousBox = secondsSincePreviousBox;
        this.notes = record.getNotes();
    }

    public Long getId() {
        return id;
    }

    public Long getProductionRecordId() {
        return productionRecordId;
    }

    public Integer getBoxNumber() {
        return boxNumber;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public Long getSecondsSincePreviousBox() {
        return secondsSincePreviousBox;
    }

    public String getNotes() {
        return notes;
    }
}