package com.apontamentoproducao.apontamentoproducao.dto;

import jakarta.validation.constraints.NotNull;

public class OpenProductionRecordRequest {

    @NotNull
    private Long machineId;

    @NotNull
    private Long shiftId;

    @NotNull
    private Long productId;

    private String notes;

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}