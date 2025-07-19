package com.breth.otimizando_relatorios.application.service;

import com.breth.otimizando_relatorios.adapter.dto.OrdemServicoRequest;
import com.breth.otimizando_relatorios.adapter.dto.OrdemServicoResponse;
import com.breth.otimizando_relatorios.domain.OrdemServicoDomain;
import com.breth.otimizando_relatorios.domain.repository.OrdemServicoRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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