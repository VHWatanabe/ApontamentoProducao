package com.apontamentoproducao.apontamentoproducao.dto;

import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecordStatus;

import java.time.LocalDateTime;

public class ProductionRecordResponse {

    private Long id;
    private Long userId;
    private String username;

    private Long machineId;
    private String machineName;

    private Long shiftId;

    private Long productId;
    private String productCode;
    private String productName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer goodQuantity;
    private Integer scrapQuantity;

    private ProductionRecordStatus status;

    private String notes;

    public ProductionRecordResponse(ProductionRecord productionRecord) {

        this.id = productionRecord.getId();

        this.userId = productionRecord.getUser().getId();
        this.username = productionRecord.getUser().getUsername();

        this.machineId = productionRecord.getMachine().getId();
        this.machineName = productionRecord.getMachine().getName();

        this.shiftId = productionRecord.getShift().getId();

        if (productionRecord.getProduct() != null) {
            this.productId = productionRecord.getProduct().getId();
            this.productCode = productionRecord.getProduct().getCode();
            this.productName = productionRecord.getProduct().getName();
        }

        this.startTime = productionRecord.getStartTime();
        this.endTime = productionRecord.getEndTime();

        this.goodQuantity = productionRecord.getGoodQuantity();
        this.scrapQuantity = productionRecord.getScrapQuantity();

        this.status = productionRecord.getStatus();

        this.notes = productionRecord.getNotes();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Long getMachineId() {
        return machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getGoodQuantity() {
        return goodQuantity;
    }

    public Integer getScrapQuantity() {
        return scrapQuantity;
    }

    public ProductionRecordStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }
}