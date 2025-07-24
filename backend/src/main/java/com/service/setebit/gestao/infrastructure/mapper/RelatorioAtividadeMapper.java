package com.service.setebit.gestao.infrastructure.mapper;

import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;
import com.service.setebit.gestao.domain.RecursoDomain;
import com.service.setebit.gestao.infrastructure.entity.RelatorioAtividadeEntity;
import com.service.setebit.gestao.infrastructure.entity.RecursoEntity;

public class RelatorioAtividadeMapper {
    public static RelatorioAtividadeDomain toDomain(RelatorioAtividadeEntity entity) {
        return RelatorioAtividadeDomain.builder()
                .cliente(entity.getCliente())
                .ano(entity.getAno())
                .mes(entity.getMes())
                .recurso(entity.getRecurso() != null ?
                        RecursoDomain.builder()
                                .nome(entity.getRecurso().getNome())
                                .fatorAjuste(entity.getRecurso().getFatorAjuste())
                                .build() : null)
                .nomeProjeto(entity.getNomeProjeto())
                .horaTotalProjeto(entity.getHoraTotalProjeto())
                .build();
    }

    public static RelatorioAtividadeEntity toEntity(RelatorioAtividadeDomain domain) {
        return RelatorioAtividadeEntity.builder()
                .cliente(domain.getCliente())
                .ano(domain.getAno())
                .mes(domain.getMes())
                .recurso(RecursoMapper.toEntity(domain.getRecurso()))
                .nomeProjeto(domain.getNomeProjeto())
                .horaTotalProjeto(domain.getHoraTotalProjeto())
                .build();
    }
} 