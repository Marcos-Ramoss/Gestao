package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.AreaDomain;
import java.util.List;
import java.util.Optional;

public interface AreaRepository {
    AreaDomain salvar(AreaDomain area);
    Optional<AreaDomain> buscarPorId(Long id);
    List<AreaDomain> listarTodos();
    void deletarPorId(Long id);
} 