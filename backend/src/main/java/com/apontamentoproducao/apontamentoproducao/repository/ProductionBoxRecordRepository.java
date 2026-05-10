package com.apontamentoproducao.apontamentoproducao.repository;

import com.apontamentoproducao.apontamentoproducao.domain.ProductionBoxRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionBoxRecordRepository extends JpaRepository<ProductionBoxRecord, Long> {

    List<ProductionBoxRecord> findByProductionRecordOrderByBoxNumberAsc(ProductionRecord productionRecord);

    long countByProductionRecord(ProductionRecord productionRecord);
}