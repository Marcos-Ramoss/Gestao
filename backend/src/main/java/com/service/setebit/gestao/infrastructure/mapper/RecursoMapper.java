package com.service.setebit.gestao.infrastructure.mapper;

import com.service.setebit.gestao.domain.RecursoDomain;
import com.service.setebit.gestao.infrastructure.entity.RecursoEntity;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;

public class RecursoMapper {
    public static RecursoDomain toDomain(RecursoEntity entity) {
        return RecursoDomain.builder()
                .id(entity.getId())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigo() : null)
                .nome(entity.getNome())
                .fatorAjuste(entity.getFatorAjuste())
                .build();
    }

    public static RecursoEntity toEntity(RecursoDomain domain) {
        return RecursoEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .fatorAjuste(domain.getFatorAjuste())
                .build();
    }
} 