package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recurso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_contrato")
    private ContratoEntity contrato;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "FATOR_AJUSTE")
    private Long fatorAjuste;
} 