package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.infrastructure.entity.RelatorioAtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatorioAtividadeJpaRepository extends JpaRepository<RelatorioAtividadeEntity, Long> {
    List<RelatorioAtividadeEntity> findByAnoAndMes(Integer ano, Integer mes);
} 