package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.domain.FaturamentoDomain;
import com.breth.otimizando_relatorios.domain.repository.FaturamentoRepository;
import com.breth.otimizando_relatorios.infrastructure.entity.FaturamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FaturamentoRepositoryImpl implements FaturamentoRepository {
    private final FaturamentoJpaRepository jpaRepository;

    @Autowired
    public FaturamentoRepositoryImpl(FaturamentoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public FaturamentoDomain salvar(FaturamentoDomain faturamento) {
        FaturamentoEntity entity = toEntity(faturamento);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<FaturamentoDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<FaturamentoDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private FaturamentoDomain toDomain(FaturamentoEntity entity) {
        return FaturamentoDomain.builder()
                .id(entity.getId())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigoContrato() : null)
                .objetivo(entity.getObjetivo())
                .valor(entity.getValor())
                .numeroMedicao(entity.getNumeroMedicao())
                .build();
    }

    private FaturamentoEntity toEntity(FaturamentoDomain domain) {
        FaturamentoEntity entity = new FaturamentoEntity();
        entity.setId(domain.getId());
        // Contrato deve ser setado via service, se necessário buscar por código
        entity.setObjetivo(domain.getObjetivo());
        entity.setValor(domain.getValor());
        entity.setNumeroMedicao(domain.getNumeroMedicao());
        return entity;
    }
}
