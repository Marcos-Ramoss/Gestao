package com.breth.otimizando_relatorios.application.service;

import com.breth.otimizando_relatorios.adapter.dto.ContratoDetalheRequest;
import com.breth.otimizando_relatorios.adapter.dto.ContratoDetalheResponse;
import com.breth.otimizando_relatorios.domain.ContratoDetalheDomain;
import com.breth.otimizando_relatorios.domain.repository.ContratoDetalheRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoDetalheService {
    private final ContratoDetalheRepository repository;

    public ContratoDetalheService(ContratoDetalheRepository repository) {
        this.repository = repository;
    }

    public ContratoDetalheResponse criar(ContratoDetalheRequest request) {
        ContratoDetalheDomain domain = ContratoDetalheDomain.builder()
                .codigoContrato(request.codigoContrato())
                .preposto(request.preposto())
                .fiscal(request.fiscal())
                .gestor(request.gestor())
                .objeto(request.objeto())
                .processoSei(request.processoSei())
                .build();
        ContratoDetalheDomain salvo = repository.salvar(domain);
        return toResponse(salvo);
    }

    public List<ContratoDetalheResponse> listarTodos() {
        return repository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<ContratoDetalheResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(this::toResponse);
    }

    public ContratoDetalheResponse atualizar(Long id, ContratoDetalheRequest request) {
        ContratoDetalheDomain domain = ContratoDetalheDomain.builder()
                .id(id)
                .codigoContrato(request.codigoContrato())
                .preposto(request.preposto())
                .fiscal(request.fiscal())
                .gestor(request.gestor())
                .objeto(request.objeto())
                .processoSei(request.processoSei())
                .build();
        ContratoDetalheDomain atualizado = repository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deletarPorId(id);
    }

    private ContratoDetalheResponse toResponse(ContratoDetalheDomain domain) {
        return new ContratoDetalheResponse(
                domain.getId(),
                domain.getCodigoContrato(),
                domain.getPreposto(),
                domain.getFiscal(),
                domain.getGestor(),
                domain.getObjeto(),
                domain.getProcessoSei()
        );
    }
} 