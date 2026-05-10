package com.apontamentoproducao.apontamentoproducao.controller;

import com.apontamentoproducao.apontamentoproducao.domain.ProductionBoxRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecord;
import com.apontamentoproducao.apontamentoproducao.domain.user.User;
import com.apontamentoproducao.apontamentoproducao.dto.CloseProductionRecordRequest;
import com.apontamentoproducao.apontamentoproducao.dto.OpenProductionRecordRequest;
import com.apontamentoproducao.apontamentoproducao.dto.ProductionBoxRecordRequest;
import com.apontamentoproducao.apontamentoproducao.dto.ProductionBoxRecordResponse;
import com.apontamentoproducao.apontamentoproducao.dto.ProductionRecordResponse;
import com.apontamentoproducao.apontamentoproducao.service.ProductionRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production-records")
public class ProductionRecordController {

    private final ProductionRecordService productionRecordService;

    public ProductionRecordController(ProductionRecordService productionRecordService) {
        this.productionRecordService = productionRecordService;
    }

    @PostMapping("/open")
    public ResponseEntity<ProductionRecordResponse> open(
            @RequestBody @Valid OpenProductionRecordRequest request,
            @AuthenticationPrincipal User user
    ) {
        ProductionRecord record = productionRecordService.open(request, user);
        return ResponseEntity.ok(new ProductionRecordResponse(record));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ProductionRecordResponse> close(
            @PathVariable Long id,
            @RequestBody @Valid CloseProductionRecordRequest request
    ) {
        ProductionRecord record = productionRecordService.close(id, request);
        return ResponseEntity.ok(new ProductionRecordResponse(record));
    }

    @PostMapping("/{id}/boxes")
    public ResponseEntity<ProductionBoxRecordResponse> recordBox(
            @PathVariable Long id,
            @RequestBody ProductionBoxRecordRequest request
    ) {
        ProductionBoxRecord boxRecord = productionRecordService.recordBox(id, request);
        Long secondsSincePreviousBox = productionRecordService.calculateSecondsSincePreviousBox(boxRecord);
        return ResponseEntity.ok(new ProductionBoxRecordResponse(boxRecord, secondsSincePreviousBox));
    }

    @GetMapping("/open/machine/{machineId}")
    public ResponseEntity<ProductionRecordResponse> findOpenByMachine(
            @PathVariable Long machineId
    ) {
        ProductionRecord record = productionRecordService.findOpenByMachine(machineId);
        return ResponseEntity.ok(new ProductionRecordResponse(record));
    }
}