package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;
import com.service.setebit.gestao.domain.repository.RelatorioAtividadeRepository;
import com.service.setebit.gestao.infrastructure.entity.RelatorioAtividadeEntity;
import com.service.setebit.gestao.infrastructure.mapper.RelatorioAtividadeMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class RelatorioAtividadeRepositoryImpl implements RelatorioAtividadeRepository {
    private final RelatorioAtividadeJpaRepository jpaRepository;


    @Override
    public RelatorioAtividadeDomain salvar(RelatorioAtividadeDomain relatorio) {

        RelatorioAtividadeEntity entity = RelatorioAtividadeMapper.toEntity(relatorio);
        entity = jpaRepository.save(entity);
        return relatorio;
    }

    @Override
    public List<RelatorioAtividadeDomain> listarTodos() {
        return jpaRepository.findAll().stream().toList().stream()
            .map(this::toDomain)
            .collect(toList());
    }

    private RelatorioAtividadeDomain toDomain(RelatorioAtividadeEntity entity) {
        return RelatorioAtividadeMapper.toDomain(entity);
    }
} 