package com.apontamentoproducao.apontamentoproducao.controller;

import com.apontamentoproducao.apontamentoproducao.domain.Shift;
import com.apontamentoproducao.apontamentoproducao.dto.OpenShiftRequest;
import com.apontamentoproducao.apontamentoproducao.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping("/open")
    public Shift openShift(@RequestBody OpenShiftRequest request) {
        return shiftService.openShift(request.getOperatorId(), request.getMachineId());
    }

    @PostMapping("/{id}/close")
    public Shift closeShift(@PathVariable Long id) {
        return shiftService.closeShift(id);
    }
}