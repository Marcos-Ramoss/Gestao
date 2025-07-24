package com.service.setebit.gestao.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.setebit.gestao.adapter.dto.ContratoRequest;
import com.service.setebit.gestao.adapter.dto.ContratoResponse;
import com.service.setebit.gestao.domain.ContratoDomain;
import com.service.setebit.gestao.domain.repository.ContratoRepository;
import com.service.setebit.gestao.infrastructure.repository.ContratoDetalheEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final ContratoDetalheEntityRepository contratoDetalheEntityRepository;
    private final RecursoService recursoService;

    public ContratoResponse criar(ContratoRequest request) {
        ContratoDomain domain = ContratoDomain.builder()
                .codigoContrato(request.codigoContrato())
                .build();
        ContratoDomain salvo = contratoRepository.salvar(domain);
        return toResponse(salvo);
    }

    public List<ContratoResponse> listarTodos() {
        return contratoRepository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<ContratoResponse> buscarPorId(String codigoContrato) {
        return contratoRepository.buscarPorId(codigoContrato).map(this::toResponse);
    }

    @Transactional
    public ContratoResponse atualizar(String codigoContrato, ContratoRequest request) {
        ContratoDomain domain = ContratoDomain.builder()
                .codigoContrato(codigoContrato)
                .build();
        ContratoDomain atualizado = contratoRepository.salvar(domain);
        return toResponse(atualizado);
    }

    @Transactional
    public void deletar(String codigoContrato) {
        // Exclui todos os recursos vinculados ao contrato (e seus relatórios)
        var recursos = recursoService.buscarPorCodigoContrato(codigoContrato);
        for (var recurso : recursos) {
            recursoService.deletar(recurso.getId());
        }
        // Exclui todos os detalhes de contrato relacionados ao contrato
        contratoDetalheEntityRepository.deleteByContrato_Codigo(codigoContrato);
        // Agora exclui o contrato
        contratoRepository.deletarPorId(codigoContrato);
    }

    private ContratoResponse toResponse(ContratoDomain domain) {
        return new ContratoResponse(
                domain.getCodigoContrato()
        );
    }
} 