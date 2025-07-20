package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheRequest;
import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheResponse;
import com.service.setebit.gestao.domain.FaturamentoDetalheDomain;
import com.service.setebit.gestao.domain.repository.FaturamentoDetalheRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
        return repository.listarTodos().stream().toList().stream().map(this::toResponse).collect(toList());
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