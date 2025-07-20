package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.FaturamentoDetalheDomain;
import java.util.List;
import java.util.Optional;

public interface FaturamentoDetalheRepository {
    FaturamentoDetalheDomain salvar(FaturamentoDetalheDomain detalhe);
    Optional<FaturamentoDetalheDomain> buscarPorId(Long id);
    List<FaturamentoDetalheDomain> listarTodos();
    void deletarPorId(Long id);
} 