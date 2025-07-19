package com.breth.otimizando_relatorios.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.breth.otimizando_relatorios.domain.AreaDomain;
import com.breth.otimizando_relatorios.domain.repository.AreaRepository;
import com.breth.otimizando_relatorios.infrastructure.entity.AreaEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AreaRepositoryImpl implements AreaRepository {

    private final AreaEntityRepository jpaRepository;

    @Override
    public AreaDomain salvar(AreaDomain area) {
        AreaEntity entity = new AreaEntity();
        entity.setId(area.getId());
        entity.setNome(area.getNome());
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
                .build();
    }
} 