package com.apontamentoproducao.apontamentoproducao.repository;

import com.apontamentoproducao.apontamentoproducao.domain.Shift;
import com.apontamentoproducao.apontamentoproducao.domain.ShiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    Optional<Shift> findByMachineIdAndStatus(Long machineId, ShiftStatus status);

    Optional<Shift> findByOperatorIdAndStatus(Long operatorId, ShiftStatus status);
}