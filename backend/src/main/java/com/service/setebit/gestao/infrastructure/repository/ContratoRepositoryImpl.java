package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.domain.ContratoDomain;
import com.service.setebit.gestao.domain.repository.ContratoRepository;
import com.service.setebit.gestao.infrastructure.entity.AreaEntity;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;
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
        ContratoEntity entity = new ContratoEntity();
        entity.setCodigo(contrato.getCodigoContrato());
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
                .codigoContrato(entity.getCodigo())
                .build();
    }
} 