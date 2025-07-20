package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.ContratoDomain;
import java.util.List;
import java.util.Optional;

public interface ContratoRepository {
    ContratoDomain salvar(ContratoDomain contrato);
    Optional<ContratoDomain> buscarPorId(String codigoContrato);
    List<ContratoDomain> listarTodos();
    void deletarPorId(String codigoContrato);
} 