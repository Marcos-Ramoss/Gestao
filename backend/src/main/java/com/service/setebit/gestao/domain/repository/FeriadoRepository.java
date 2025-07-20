package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.FeriadoDomain;
import java.util.List;
import java.util.Optional;

public interface FeriadoRepository {
    FeriadoDomain salvar(FeriadoDomain feriado);
    Optional<FeriadoDomain> buscarPorId(Long id);
    List<FeriadoDomain> listarTodos();
    void deletarPorId(Long id);
} 