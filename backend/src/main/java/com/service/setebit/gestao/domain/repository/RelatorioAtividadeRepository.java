package com.service.setebit.gestao.domain.repository;

import java.util.List;

import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;

public interface RelatorioAtividadeRepository {
    RelatorioAtividadeDomain salvar(RelatorioAtividadeDomain relatorio);
    List<RelatorioAtividadeDomain> listarTodos();
    // Outros métodos podem ser adicionados conforme necessário
} 