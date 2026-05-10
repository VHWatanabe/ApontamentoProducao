package com.apontamentoproducao.apontamentoproducao.service;

import com.apontamentoproducao.apontamentoproducao.domain.Machine;
import com.apontamentoproducao.apontamentoproducao.domain.Product;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionBoxRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecord;
import com.apontamentoproducao.apontamentoproducao.domain.ProductionRecordStatus;
import com.apontamentoproducao.apontamentoproducao.domain.Shift;
import com.apontamentoproducao.apontamentoproducao.domain.user.User;
import com.apontamentoproducao.apontamentoproducao.dto.CloseProductionRecordRequest;
import com.apontamentoproducao.apontamentoproducao.dto.OpenProductionRecordRequest;
import com.apontamentoproducao.apontamentoproducao.dto.ProductionBoxRecordRequest;
import com.apontamentoproducao.apontamentoproducao.exception.BusinessException;
import com.apontamentoproducao.apontamentoproducao.repository.MachineRepository;
import com.apontamentoproducao.apontamentoproducao.repository.ProductRepository;
import com.apontamentoproducao.apontamentoproducao.repository.ProductionBoxRecordRepository;
import com.apontamentoproducao.apontamentoproducao.repository.ProductionRecordRepository;
import com.apontamentoproducao.apontamentoproducao.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionRecordService {

    private final ProductionRecordRepository productionRecordRepository;
    private final ProductionBoxRecordRepository productionBoxRecordRepository;
    private final MachineRepository machineRepository;
    private final ShiftRepository shiftRepository;
    private final ProductRepository productRepository;

    public ProductionRecordService(
            ProductionRecordRepository productionRecordRepository,
            ProductionBoxRecordRepository productionBoxRecordRepository,
            MachineRepository machineRepository,
            ShiftRepository shiftRepository,
            ProductRepository productRepository
    ) {
        this.productionRecordRepository = productionRecordRepository;
        this.productionBoxRecordRepository = productionBoxRecordRepository;
        this.machineRepository = machineRepository;
        this.shiftRepository = shiftRepository;
        this.productRepository = productRepository;
    }

    public ProductionRecord open(OpenProductionRecordRequest request, User user) {
        Machine machine = machineRepository.findById(request.getMachineId())
                .orElseThrow(() -> new BusinessException("Máquina não encontrada"));

        Shift shift = shiftRepository.findById(request.getShiftId())
                .orElseThrow(() -> new BusinessException("Turno não encontrado"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));

        productionRecordRepository
                .findByMachineAndStatus(machine, ProductionRecordStatus.OPEN)
                .ifPresent(record -> {
                    throw new BusinessException("Já existe apontamento aberto para esta máquina");
                });

        ProductionRecord record = new ProductionRecord();

        record.setUser(user);
        record.setMachine(machine);
        record.setShift(shift);
        record.setProduct(product);
        record.setStartTime(LocalDateTime.now());
        record.setStatus(ProductionRecordStatus.OPEN);
        record.setNotes(request.getNotes());

        return productionRecordRepository.save(record);
    }

    public ProductionRecord close(Long id, CloseProductionRecordRequest request) {
        ProductionRecord record = productionRecordRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Apontamento não encontrado"));

        if (record.getStatus() == ProductionRecordStatus.CLOSED) {
            throw new BusinessException("Apontamento já está fechado");
        }

        record.setEndTime(LocalDateTime.now());
        record.setGoodQuantity(request.getGoodQuantity());
        record.setScrapQuantity(request.getScrapQuantity());
        record.setNotes(request.getNotes());
        record.setStatus(ProductionRecordStatus.CLOSED);

        return productionRecordRepository.save(record);
    }

    public ProductionBoxRecord recordBox(Long productionRecordId, ProductionBoxRecordRequest request) {
        ProductionRecord productionRecord = productionRecordRepository.findById(productionRecordId)
                .orElseThrow(() -> new BusinessException("Apontamento de produção não encontrado"));

        if (productionRecord.getStatus() == ProductionRecordStatus.CLOSED) {
            throw new BusinessException("Não é possível apontar caixa em uma produção fechada");
        }

        long currentCount = productionBoxRecordRepository.countByProductionRecord(productionRecord);

        ProductionBoxRecord boxRecord = new ProductionBoxRecord();
        boxRecord.setProductionRecord(productionRecord);
        boxRecord.setBoxNumber((int) currentCount + 1);
        boxRecord.setRecordedAt(LocalDateTime.now());
        boxRecord.setNotes(request.getNotes());

        productionRecord.setGoodQuantity((int) currentCount + 1);
        productionRecordRepository.save(productionRecord);

        return productionBoxRecordRepository.save(boxRecord);
    }

    public Long calculateSecondsSincePreviousBox(ProductionBoxRecord boxRecord) {
        List<ProductionBoxRecord> records = productionBoxRecordRepository
                .findByProductionRecordOrderByBoxNumberAsc(boxRecord.getProductionRecord());

        if (records.size() <= 1) {
            return null;
        }

        ProductionBoxRecord previous = records.get(records.size() - 2);

        return Duration.between(previous.getRecordedAt(), boxRecord.getRecordedAt()).getSeconds();
    }

    public ProductionRecord findOpenByMachine(Long machineId) {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new BusinessException("Máquina não encontrada"));

        return productionRecordRepository
                .findByMachineAndStatus(machine, ProductionRecordStatus.OPEN)
                .orElseThrow(() -> new BusinessException("Não existe produção aberta para esta máquina"));
    }
}