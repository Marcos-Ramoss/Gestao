package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.OrdemServicoRequest;
import com.service.setebit.gestao.adapter.dto.OrdemServicoResponse;
import com.service.setebit.gestao.domain.OrdemServicoDomain;
import com.service.setebit.gestao.domain.repository.OrdemServicoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {
    
    private final OrdemServicoRepository repository;

    public OrdemServicoResponse criar(OrdemServicoRequest request) {
        OrdemServicoDomain domain = OrdemServicoDomain.builder()
                .codigoContrato(request.codigoContrato())
                .numeroOs(request.numeroOs())
                .build();
        OrdemServicoDomain salvo = repository.salvar(domain);
        return toResponse(salvo);
    }

    public List<OrdemServicoResponse> listarTodos() {
        return repository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<OrdemServicoResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(this::toResponse);
    }

    public OrdemServicoResponse atualizar(Long id, OrdemServicoRequest request) {
        OrdemServicoDomain domain = OrdemServicoDomain.builder()
                .id(id)
                .codigoContrato(request.codigoContrato())
                .numeroOs(request.numeroOs())
                .build();
        OrdemServicoDomain atualizado = repository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deletarPorId(id);
    }

    private OrdemServicoResponse toResponse(OrdemServicoDomain domain) {
        return new OrdemServicoResponse(
                domain.getId(),
                domain.getCodigoContrato(),
                domain.getNumeroOs()
        );
    }
} 