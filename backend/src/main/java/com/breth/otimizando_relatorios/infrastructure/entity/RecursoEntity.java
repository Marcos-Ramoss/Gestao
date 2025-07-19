package com.breth.otimizando_relatorios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RECURSO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECURSO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CONTRATO")
    private ContratoEntity contrato;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "FATOR_AJUSTE")
    private Long fatorAjuste;
} 