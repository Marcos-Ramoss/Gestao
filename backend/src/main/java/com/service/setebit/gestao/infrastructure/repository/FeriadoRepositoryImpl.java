package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.domain.FeriadoDomain;
import com.service.setebit.gestao.domain.repository.FeriadoRepository;
import com.service.setebit.gestao.infrastructure.entity.FeriadoEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FeriadoRepositoryImpl implements FeriadoRepository {
    private final FeriadoEntityRepository jpaRepository;

    public FeriadoRepositoryImpl(FeriadoEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public FeriadoDomain salvar(FeriadoDomain feriado) {
        FeriadoEntity entity = new FeriadoEntity();
        entity.setId(feriado.getId());
        entity.setData(feriado.getData());
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<FeriadoDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<FeriadoDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private FeriadoDomain toDomain(FeriadoEntity entity) {
        return FeriadoDomain.builder()
                .id(entity.getId())
                .data(entity.getData())
                .build();
    }
} 