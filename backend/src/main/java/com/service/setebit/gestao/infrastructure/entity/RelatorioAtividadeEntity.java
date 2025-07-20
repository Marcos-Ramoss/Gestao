package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "relatorio_atividade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioAtividadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rel_ativ")
    private Long id;

    @Column(name = "cliente", nullable = false, length = 100)
    private String cliente;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @ManyToOne
    @JoinColumn(name = "id_recurso")
    private RecursoEntity recurso;

    @Column(name = "nome_projeto", nullable = false, length = 100)
    private String nomeProjeto;

    @Column(name = "hora_total_projeto", nullable = false)
    private Double horaTotalProjeto;
} 