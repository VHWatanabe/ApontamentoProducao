package com.apontamentoproducao.apontamentoproducao.dto;

import jakarta.validation.constraints.NotNull;

public class CloseProductionRecordRequest {

    @NotNull
    private Integer goodQuantity;

    @NotNull
    private Integer scrapQuantity;

    private String notes;

    public Integer getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(Integer goodQuantity) {
        this.goodQuantity = goodQuantity;
    }

    public Integer getScrapQuantity() {
        return scrapQuantity;
    }

    public void setScrapQuantity(Integer scrapQuantity) {
        this.scrapQuantity = scrapQuantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}