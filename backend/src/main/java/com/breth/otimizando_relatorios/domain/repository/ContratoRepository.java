package com.breth.otimizando_relatorios.domain.repository;

import com.breth.otimizando_relatorios.domain.ContratoDomain;
import java.util.List;
import java.util.Optional;

public interface ContratoRepository {
    ContratoDomain salvar(ContratoDomain contrato);
    Optional<ContratoDomain> buscarPorId(String codigoContrato);
    List<ContratoDomain> listarTodos();
    void deletarPorId(String codigoContrato);
} 