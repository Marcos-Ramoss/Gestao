package com.breth.otimizando_relatorios.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.breth.otimizando_relatorios.domain.RecursoDomain;
import com.breth.otimizando_relatorios.domain.repository.RecursoRepository;
import com.breth.otimizando_relatorios.infrastructure.entity.ContratoEntity;
import com.breth.otimizando_relatorios.infrastructure.entity.RecursoEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class RecursoRepositoryImpl implements RecursoRepository {
    
    private final RecursoEntityRepository jpaRepository;
    private final ContratoEntityRepository contratoRepository;

    @Override
    public RecursoDomain salvar(RecursoDomain recurso) {
        ContratoEntity contrato = contratoRepository.findById(recurso.getCodigoContrato())
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        RecursoEntity entity = new RecursoEntity();
        entity.setId(recurso.getId());
        entity.setContrato(contrato);
        entity.setNome(recurso.getNome());
        entity.setFatorAjuste(recurso.getFatorAjuste());
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<RecursoDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<RecursoDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private RecursoDomain toDomain(RecursoEntity entity) {
        return RecursoDomain.builder()
                .id(entity.getId())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigoContrato() : null)
                .nome(entity.getNome())
                .fatorAjuste(entity.getFatorAjuste())
                .build();
    }
} 