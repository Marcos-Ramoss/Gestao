package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.OrdemServicoDomain;
import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository {
    OrdemServicoDomain salvar(OrdemServicoDomain ordemServico);
    Optional<OrdemServicoDomain> buscarPorId(Long id);
    List<OrdemServicoDomain> listarTodos();
    void deletarPorId(Long id);
} 