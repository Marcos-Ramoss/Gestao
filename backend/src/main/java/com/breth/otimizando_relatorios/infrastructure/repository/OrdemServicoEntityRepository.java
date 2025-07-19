package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.infrastructure.entity.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoEntityRepository extends JpaRepository<OrdemServicoEntity, Long> {
} 