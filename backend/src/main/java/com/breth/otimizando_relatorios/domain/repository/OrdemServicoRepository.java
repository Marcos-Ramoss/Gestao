package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.OrdemServicoDomain;
import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository {
    OrdemServicoDomain salvar(OrdemServicoDomain ordemServico);
    Optional<OrdemServicoDomain> buscarPorId(Long id);
    List<OrdemServicoDomain> listarTodos();
    void deletarPorId(Long id);
} 