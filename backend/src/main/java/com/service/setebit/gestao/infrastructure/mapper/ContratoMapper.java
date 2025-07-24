package com.service.setebit.gestao.infrastructure.mapper;

import com.service.setebit.gestao.domain.ContratoDomain;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;
import com.service.setebit.gestao.infrastructure.entity.AreaEntity;

public class ContratoMapper {
    public static ContratoDomain toDomain(ContratoEntity entity) {
        return ContratoDomain.builder()
                .codigoContrato(entity.getCodigo())
                .build();
    }

    public static ContratoEntity toEntity(ContratoDomain domain, AreaEntity areaEntity) {
        return ContratoEntity.builder()
                .codigo(domain.getCodigoContrato())
                .build();
    }
} 