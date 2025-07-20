package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.RecursoDomain;
import java.util.List;
import java.util.Optional;

public interface RecursoRepository {
    RecursoDomain salvar(RecursoDomain recurso);
    Optional<RecursoDomain> buscarPorId(Long id);
    RecursoDomain buscarRecursoPorNome(String nome);
    List<RecursoDomain> listarTodos();
    void deletarPorId(Long id);
} 