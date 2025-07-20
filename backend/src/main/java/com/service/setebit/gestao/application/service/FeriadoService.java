package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.FeriadoRequest;
import com.service.setebit.gestao.adapter.dto.FeriadoResponse;
import com.service.setebit.gestao.domain.FeriadoDomain;
import com.service.setebit.gestao.domain.repository.FeriadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeriadoService {
    private final FeriadoRepository repository;

    public FeriadoService(FeriadoRepository repository) {
        this.repository = repository;
    }

    public FeriadoResponse criar(FeriadoRequest request) {
        FeriadoDomain domain = FeriadoDomain.builder()
                .data(request.data())
                .build();
        FeriadoDomain salvo = repository.salvar(domain);
        return toResponse(salvo);
    }

    public List<FeriadoResponse> listarTodos() {
        return repository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<FeriadoResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(this::toResponse);
    }

    public FeriadoResponse atualizar(Long id, FeriadoRequest request) {
        FeriadoDomain domain = FeriadoDomain.builder()
                .id(id)
                .data(request.data())
                .build();
        FeriadoDomain atualizado = repository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deletarPorId(id);
    }

    private FeriadoResponse toResponse(FeriadoDomain domain) {
        return new FeriadoResponse(
                domain.getId(),
                domain.getData()
        );
    }
} 