package com.service.setebit.gestao.domain.repository;

import com.service.setebit.gestao.domain.FeriadoDomain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FeriadoRepository {
    FeriadoDomain salvar(FeriadoDomain feriado);
    Optional<FeriadoDomain> findByData(LocalDate data);
    List<FeriadoDomain> listarTodos();
    void deletarByData(LocalDate data);
} 