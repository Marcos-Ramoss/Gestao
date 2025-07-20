package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.FaturamentoRequest;
import com.service.setebit.gestao.adapter.dto.FaturamentoResponse;
import com.service.setebit.gestao.domain.FaturamentoDomain;
import com.service.setebit.gestao.domain.repository.FaturamentoRepository;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;
import com.service.setebit.gestao.infrastructure.repository.ContratoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaturamentoService {
    private final FaturamentoRepository faturamentoRepository;
    private final ContratoEntityRepository contratoEntityRepository;

    public FaturamentoResponse criar(FaturamentoRequest request) {
        ContratoEntity contrato = contratoEntityRepository.findById(request.codigoContrato())
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        FaturamentoDomain domain = FaturamentoDomain.builder()
                .codigoContrato(request.codigoContrato())
                .objetivo(request.objetivo())
                .valor(request.valor())
                .numeroMedicao(request.numeroMedicao())
                .build();
        FaturamentoDomain salvo = faturamentoRepository.salvar(domain);
        return toResponse(salvo);
    }

    public List<FaturamentoResponse> listarTodos() {
        return faturamentoRepository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<FaturamentoResponse> buscarPorId(Long id) {
        return faturamentoRepository.buscarPorId(id).map(this::toResponse);
    }

    public FaturamentoResponse atualizar(Long id, FaturamentoRequest request) {
        FaturamentoDomain domain = FaturamentoDomain.builder()
                .id(id)
                .codigoContrato(request.codigoContrato())
                .objetivo(request.objetivo())
                .valor(request.valor())
                .numeroMedicao(request.numeroMedicao())
                .build();
        FaturamentoDomain atualizado = faturamentoRepository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        faturamentoRepository.deletarPorId(id);
    }

    private FaturamentoResponse toResponse(FaturamentoDomain domain) {
        return new FaturamentoResponse(
                domain.getId(),
                domain.getCodigoContrato(),
                domain.getObjetivo(),
                domain.getValor(),
                domain.getNumeroMedicao()
        );
    }
}
