package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.domain.ContratoDomain;
import com.breth.otimizando_relatorios.domain.repository.ContratoRepository;
import com.breth.otimizando_relatorios.infrastructure.entity.AreaEntity;
import com.breth.otimizando_relatorios.infrastructure.entity.ContratoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ContratoRepositoryImpl implements ContratoRepository {
    private final ContratoEntityRepository jpaRepository;
    private final AreaEntityRepository areaRepository;

    @Autowired
    public ContratoRepositoryImpl(ContratoEntityRepository jpaRepository, AreaEntityRepository areaRepository) {
        this.jpaRepository = jpaRepository;
        this.areaRepository = areaRepository;
    }

    @Override
    public ContratoDomain salvar(ContratoDomain contrato) {
        AreaEntity area = areaRepository.findById(contrato.getIdArea())
                .orElseThrow(() -> new IllegalArgumentException("Área não encontrada"));
        ContratoEntity entity = new ContratoEntity();
        entity.setCodigoContrato(contrato.getCodigoContrato());
        entity.setArea(area);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<ContratoDomain> buscarPorId(String codigoContrato) {
        return jpaRepository.findById(codigoContrato).map(this::toDomain);
    }

    @Override
    public List<ContratoDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(String codigoContrato) {
        jpaRepository.deleteById(codigoContrato);
    }

    private ContratoDomain toDomain(ContratoEntity entity) {
        return ContratoDomain.builder()
                .codigoContrato(entity.getCodigoContrato())
                .idArea(entity.getArea() != null ? entity.getArea().getId() : null)
                .build();
    }
} 