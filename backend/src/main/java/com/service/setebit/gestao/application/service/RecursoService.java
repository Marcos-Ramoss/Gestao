package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.RecursoRequest;
import com.service.setebit.gestao.adapter.dto.RecursoResponse;
import com.service.setebit.gestao.domain.RecursoDomain;
import com.service.setebit.gestao.domain.repository.RecursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecursoService {
    private final RecursoRepository repository;

    public RecursoService(RecursoRepository repository) {
        this.repository = repository;
    }

    public RecursoResponse criar(RecursoRequest request) {
        RecursoDomain domain = RecursoDomain.builder()
                .codigoContrato(request.codigoContrato())
                .nome(request.nome())
                .fatorAjuste(request.fatorAjuste())
                .build();
        RecursoDomain salvo = repository.salvar(domain);
        return toResponse(salvo);
    }

    public List<RecursoResponse> listarTodos() {
        return repository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<RecursoResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(this::toResponse);
    }

    public RecursoResponse atualizar(Long id, RecursoRequest request) {
        RecursoDomain domain = RecursoDomain.builder()
                .id(id)
                .codigoContrato(request.codigoContrato())
                .nome(request.nome())
                .fatorAjuste(request.fatorAjuste())
                .build();
        RecursoDomain atualizado = repository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deletarPorId(id);
    }

    private RecursoResponse toResponse(RecursoDomain domain) {
        return new RecursoResponse(
                domain.getId(),
                domain.getCodigoContrato(),
                domain.getNome(),
                domain.getFatorAjuste()
        );
    }
} 