package com.apontamentoproducao.apontamentoproducao.repository;

import com.apontamentoproducao.apontamentoproducao.domain.Machine;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecordStatus;
import com.apontamentoproducao.apontamentoproducao.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductionRecordRepository extends JpaRepository<ProductionRecord, Long> {

    Optional<ProductionRecord> findByShiftAndStatus(Shift shift, ProductionRecordStatus status);

    Optional<ProductionRecord> findByMachineAndStatus(Machine machine, ProductionRecordStatus status);

    List<ProductionRecord> findByShift(Shift shift);

    List<ProductionRecord> findByMachine(Machine machine);
}