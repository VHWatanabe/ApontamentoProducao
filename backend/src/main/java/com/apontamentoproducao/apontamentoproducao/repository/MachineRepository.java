package com.apontamentoproducao.apontamentoproducao.repository;

import com.apontamentoproducao.apontamentoproducao.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}