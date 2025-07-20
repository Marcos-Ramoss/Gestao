package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.ContratoRequest;
import com.service.setebit.gestao.adapter.dto.ContratoResponse;
import com.service.setebit.gestao.domain.ContratoDomain;
import com.service.setebit.gestao.domain.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoService {
    private final ContratoRepository contratoRepository;

    public ContratoResponse criar(ContratoRequest request) {
        ContratoDomain domain = ContratoDomain.builder()
                .codigoContrato(request.codigoContrato())
                .idArea(request.idArea())
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

    public ContratoResponse atualizar(String codigoContrato, ContratoRequest request) {
        ContratoDomain domain = ContratoDomain.builder()
                .codigoContrato(codigoContrato)
                .idArea(request.idArea())
                .build();
        ContratoDomain atualizado = contratoRepository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(String codigoContrato) {
        contratoRepository.deletarPorId(codigoContrato);
    }

    private ContratoResponse toResponse(ContratoDomain domain) {
        return new ContratoResponse(
                domain.getCodigoContrato(),
                domain.getIdArea()
        );
    }
} 