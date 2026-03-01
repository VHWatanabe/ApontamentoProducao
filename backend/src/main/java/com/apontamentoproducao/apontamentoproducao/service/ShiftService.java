package com.apontamentoproducao.apontamentoproducao.service;

import com.apontamentoproducao.apontamentoproducao.domain.*;
import com.apontamentoproducao.apontamentoproducao.domain.user.User;
import com.apontamentoproducao.apontamentoproducao.domain.user.UserRepository;
import com.apontamentoproducao.apontamentoproducao.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;
    private final MachineRepository machineRepository;

    public Shift openShift(Long operatorId, Long machineId) {

        User operator = userRepository.findById(operatorId)
                .orElseThrow(() -> new RuntimeException("Operador não encontrado."));

        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Máquina não encontrada."));

        shiftRepository.findByMachineIdAndStatus(machineId, ShiftStatus.ABERTO)
                .ifPresent(s -> {
                    throw new RuntimeException("Já existe turno aberto para esta máquina.");
                });

        shiftRepository.findByOperatorIdAndStatus(operatorId, ShiftStatus.ABERTO)
                .ifPresent(s -> {
                    throw new RuntimeException("Operador já possui turno aberto.");
                });

        Shift shift = new Shift();
        shift.setOperator(operator);
        shift.setMachine(machine);
        shift.setStatus(ShiftStatus.ABERTO);
        shift.setStartTime(LocalDateTime.now());

        return shiftRepository.save(shift);
    }

    public Shift closeShift(Long shiftId) {

        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Turno não encontrado."));

        if (shift.getStatus() == ShiftStatus.FINALIZADO) {
            throw new RuntimeException("Turno já está finalizado.");
        }

        shift.setStatus(ShiftStatus.FINALIZADO);
        shift.setEndTime(LocalDateTime.now());

        return shiftRepository.save(shift);
    }
}