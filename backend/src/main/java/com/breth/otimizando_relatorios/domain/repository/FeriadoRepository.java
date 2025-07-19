package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.FeriadoDomain;
import java.util.List;
import java.util.Optional;

public interface FeriadoRepository {
    FeriadoDomain salvar(FeriadoDomain feriado);
    Optional<FeriadoDomain> buscarPorId(Long id);
    List<FeriadoDomain> listarTodos();
    void deletarPorId(Long id);
} 