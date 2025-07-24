package com.service.setebit.gestao.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.service.setebit.gestao.domain.AreaDomain;
import com.service.setebit.gestao.domain.repository.AreaRepository;
import com.service.setebit.gestao.infrastructure.entity.AreaEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor

public class AreaRepositoryImpl implements AreaRepository {

    private final AreaEntityRepository jpaRepository;
    private final ContratoEntityRepository contratoRepository;

    @Override
    public AreaDomain salvar(AreaDomain area) {
        AreaEntity entity = new AreaEntity();
        entity.setId(area.getId());
        entity.setNome(area.getNome());
        if (area.getCodigoContrato() != null) {
            contratoRepository.findById(area.getCodigoContrato())
                .ifPresent(entity::setContrato);
        }
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<AreaDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<AreaDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private AreaDomain toDomain(AreaEntity entity) {
        return AreaDomain.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigo() : null)
                .build();
    }
} 