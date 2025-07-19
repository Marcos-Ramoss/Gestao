package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.RecursoDomain;
import java.util.List;
import java.util.Optional;

public interface RecursoRepository {
    RecursoDomain salvar(RecursoDomain recurso);
    Optional<RecursoDomain> buscarPorId(Long id);
    List<RecursoDomain> listarTodos();
    void deletarPorId(Long id);
} 