package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.domain.RelatorioAtividadeDomain;
import com.breth.otimizando_relatorios.domain.repository.RelatorioAtividadeRepository;
import com.breth.otimizando_relatorios.infrastructure.entity.RelatorioAtividadeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RelatorioAtividadeRepositoryImpl implements RelatorioAtividadeRepository {
    private final RelatorioAtividadeJpaRepository jpaRepository;

    @Autowired
    public RelatorioAtividadeRepositoryImpl(RelatorioAtividadeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public RelatorioAtividadeDomain salvar(RelatorioAtividadeDomain relatorio) {
        RelatorioAtividadeEntity entity = RelatorioAtividadeEntity.builder()
                .cliente(relatorio.getCliente())
                .ano(relatorio.getAno())
                .mes(relatorio.getMes())
                .colaborador(relatorio.getColaborador())
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
                .colaborador(entity.getColaborador())
                .nomeProjeto(entity.getNomeProjeto())
                .horaTotalProjeto(entity.getHoraTotalProjeto())
                .build();
    }
} 