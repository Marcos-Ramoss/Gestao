package com.service.setebit.gestao.infrastructure.repository;

import com.service.setebit.gestao.infrastructure.entity.RelatorioAtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatorioAtividadeJpaRepository extends JpaRepository<RelatorioAtividadeEntity, Long> {
    List<RelatorioAtividadeEntity> findByAnoAndMes(Integer ano, Integer mes);
    void deleteByRecurso_Id(Long idRecurso);
} 