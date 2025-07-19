package com.breth.otimizando_relatorios.application.service;

import com.breth.otimizando_relatorios.adapter.dto.FaturamentoDetalheRequest;
import com.breth.otimizando_relatorios.adapter.dto.FaturamentoDetalheResponse;
import com.breth.otimizando_relatorios.domain.FaturamentoDetalheDomain;
import com.breth.otimizando_relatorios.domain.repository.FaturamentoDetalheRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FaturamentoDetalheService {
    
    private final FaturamentoDetalheRepository repository;

    public FaturamentoDetalheResponse criar(FaturamentoDetalheRequest request) {
        FaturamentoDetalheDomain domain = FaturamentoDetalheDomain.builder()
                .idFaturamento(request.idFaturamento())
                .descricao(request.descricao())
                .build();
        FaturamentoDetalheDomain salvo = repository.salvar(domain);
        return toResponse(salvo);
    }

    public List<FaturamentoDetalheResponse> listarTodos() {
        return repository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<FaturamentoDetalheResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(this::toResponse);
    }

    public FaturamentoDetalheResponse atualizar(Long id, FaturamentoDetalheRequest request) {
        FaturamentoDetalheDomain domain = FaturamentoDetalheDomain.builder()
                .id(id)
                .idFaturamento(request.idFaturamento())
                .descricao(request.descricao())
                .build();
        FaturamentoDetalheDomain atualizado = repository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deletarPorId(id);
    }

    private FaturamentoDetalheResponse toResponse(FaturamentoDetalheDomain domain) {
        return new FaturamentoDetalheResponse(
                domain.getId(),
                domain.getIdFaturamento(),
                domain.getDescricao()
        );
    }
} 