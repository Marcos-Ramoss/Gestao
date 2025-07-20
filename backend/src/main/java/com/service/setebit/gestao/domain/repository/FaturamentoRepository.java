package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.FaturamentoDomain;
import java.util.List;
import java.util.Optional;

public interface FaturamentoRepository {
    FaturamentoDomain salvar(FaturamentoDomain faturamento);
    Optional<FaturamentoDomain> buscarPorId(Long id);
    List<FaturamentoDomain> listarTodos();
    void deletarPorId(Long id);
}
