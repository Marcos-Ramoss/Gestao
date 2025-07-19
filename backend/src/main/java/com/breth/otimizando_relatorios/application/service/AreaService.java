package com.breth.otimizando_relatorios.application.service;

import com.breth.otimizando_relatorios.adapter.dto.AreaRequest;
import com.breth.otimizando_relatorios.adapter.dto.AreaResponse;
import com.breth.otimizando_relatorios.domain.AreaDomain;
import com.breth.otimizando_relatorios.domain.repository.AreaRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AreaService {
    
    private final AreaRepository areaRepository;

    public AreaResponse criar(AreaRequest request) {
        AreaDomain domain = AreaDomain.builder()
                .nome(request.nome())
                .build();
        AreaDomain salvo = areaRepository.salvar(domain);
        return toResponse(salvo);
    }

    public List<AreaResponse> listarTodos() {
        return areaRepository.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Optional<AreaResponse> buscarPorId(Long id) {
        return areaRepository.buscarPorId(id).map(this::toResponse);
    }

    public AreaResponse atualizar(Long id, AreaRequest request) {
        AreaDomain domain = AreaDomain.builder()
                .id(id)
                .nome(request.nome())
                .build();
        AreaDomain atualizado = areaRepository.salvar(domain);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        areaRepository.deletarPorId(id);
    }

    private AreaResponse toResponse(AreaDomain domain) {
        return new AreaResponse(
                domain.getId(),
                domain.getNome()
        );
    }
} 