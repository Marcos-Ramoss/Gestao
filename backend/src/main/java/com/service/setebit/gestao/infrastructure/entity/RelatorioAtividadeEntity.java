package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RELATORIO_ATIVIDADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioAtividadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private Integer ano;
    private Integer mes;
    private String colaborador;
    private String nomeProjeto;
    private Double horaTotalProjeto;
} 