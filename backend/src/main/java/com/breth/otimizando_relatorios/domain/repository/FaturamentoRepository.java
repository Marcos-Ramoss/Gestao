package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.FaturamentoDomain;
import java.util.List;
import java.util.Optional;

public interface FaturamentoRepository {
    FaturamentoDomain salvar(FaturamentoDomain faturamento);
    Optional<FaturamentoDomain> buscarPorId(Long id);
    List<FaturamentoDomain> listarTodos();
    void deletarPorId(Long id);
}
