package com.breth.otimizando_relatorios.infrastructure.repository;

import com.breth.otimizando_relatorios.infrastructure.entity.FeriadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeriadoEntityRepository extends JpaRepository<FeriadoEntity, Long> {
} 