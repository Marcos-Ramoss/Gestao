package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.AreaDomain;
import java.util.List;
import java.util.Optional;

public interface AreaRepository {
    AreaDomain salvar(AreaDomain area);
    Optional<AreaDomain> buscarPorId(Long id);
    List<AreaDomain> listarTodos();
    void deletarPorId(Long id);
} 