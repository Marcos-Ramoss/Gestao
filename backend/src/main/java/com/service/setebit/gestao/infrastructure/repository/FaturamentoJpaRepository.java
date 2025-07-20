package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.infrastructure.entity.FaturamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturamentoJpaRepository extends JpaRepository<FaturamentoEntity, Long> {
}