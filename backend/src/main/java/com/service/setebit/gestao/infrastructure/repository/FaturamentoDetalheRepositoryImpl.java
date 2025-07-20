package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.domain.FaturamentoDetalheDomain;
import com.service.setebit.gestao.domain.repository.FaturamentoDetalheRepository;
import com.service.setebit.gestao.infrastructure.entity.FaturamentoDetalheEntity;
import com.service.setebit.gestao.infrastructure.entity.FaturamentoEntity;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class FaturamentoDetalheRepositoryImpl implements FaturamentoDetalheRepository {
    
    private final FaturamentoDetalheEntityRepository jpaRepository;
    private final FaturamentoJpaRepository faturamentoRepository;

    @Override
    public FaturamentoDetalheDomain salvar(FaturamentoDetalheDomain detalhe) {
        FaturamentoEntity faturamento = faturamentoRepository.findById(detalhe.getIdFaturamento())
                .orElseThrow(() -> new IllegalArgumentException("Faturamento não encontrado"));
        FaturamentoDetalheEntity entity = new FaturamentoDetalheEntity();
        entity.setId(detalhe.getId());
        entity.setFaturamento(faturamento);
        entity.setDescricao(detalhe.getDescricao());
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<FaturamentoDetalheDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<FaturamentoDetalheDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private FaturamentoDetalheDomain toDomain(FaturamentoDetalheEntity entity) {
        return FaturamentoDetalheDomain.builder()
                .id(entity.getId())
                .idFaturamento(entity.getFaturamento() != null ? entity.getFaturamento().getId() : null)
                .descricao(entity.getDescricao())
                .build();
    }
} 