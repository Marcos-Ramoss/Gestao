package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;
import com.service.setebit.gestao.domain.repository.RelatorioAtividadeRepository;
import com.service.setebit.gestao.infrastructure.entity.RecursoEntity;
import com.service.setebit.gestao.infrastructure.entity.RelatorioAtividadeEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RelatorioAtividadeRepositoryImpl implements RelatorioAtividadeRepository {
    private final RelatorioAtividadeJpaRepository jpaRepository;


    @Override
    public RelatorioAtividadeDomain salvar(RelatorioAtividadeDomain relatorio) {
        RelatorioAtividadeEntity entity = RelatorioAtividadeEntity.builder()
                .cliente(relatorio.getCliente())
                .ano(relatorio.getAno())
                .mes(relatorio.getMes())
                .recurso(RecursoEntity.builder().id(relatorio.getRecurso().getId()).build())
                .nomeProjeto(relatorio.getNomeProjeto())
                .horaTotalProjeto(relatorio.getHoraTotalProjeto())
                .build();
        entity = jpaRepository.save(entity);
        // Retorna o domain preenchido (pode incluir o id se desejar)
        return relatorio;
    }

    @Override
    public List<RelatorioAtividadeDomain> listarTodos() {
        return jpaRepository.findAll().stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    private RelatorioAtividadeDomain toDomain(RelatorioAtividadeEntity entity) {
        return RelatorioAtividadeDomain.builder()
                .cliente(entity.getCliente())
                .ano(entity.getAno())
                .mes(entity.getMes())
                //.colaborador(entity.getColaborador())
                .nomeProjeto(entity.getNomeProjeto())
                .horaTotalProjeto(entity.getHoraTotalProjeto())
                .build();
    }
} 