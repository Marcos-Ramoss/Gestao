package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.ContratoDetalheDomain;
import java.util.List;
import java.util.Optional;

public interface ContratoDetalheRepository {
    ContratoDetalheDomain salvar(ContratoDetalheDomain detalhe);
    Optional<ContratoDetalheDomain> buscarPorId(Long id);
    List<ContratoDetalheDomain> listarTodos();
    void deletarPorId(Long id);
} 