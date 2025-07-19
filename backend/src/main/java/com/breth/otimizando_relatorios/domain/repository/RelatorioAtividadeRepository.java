package com.breth.otimizando_relatorios.domain.repository;

import java.util.List;

import com.breth.otimizando_relatorios.domain.RelatorioAtividadeDomain;

public interface RelatorioAtividadeRepository {
    RelatorioAtividadeDomain salvar(RelatorioAtividadeDomain relatorio);
    List<RelatorioAtividadeDomain> listarTodos();
    // Outros métodos podem ser adicionados conforme necessário
} 