package com.apontamentoproducao.apontamentoproducao.controller;

import com.apontamentoproducao.apontamentoproducao.domain.Machine;
import com.apontamentoproducao.apontamentoproducao.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
public class MachineController {

    private final MachineRepository machineRepository;

    @GetMapping
    public List<Machine> listAll() {
        return machineRepository.findAll();
    }

    @PostMapping
    public Machine create(@RequestBody Machine machine) {
        return machineRepository.save(machine);
    }
}