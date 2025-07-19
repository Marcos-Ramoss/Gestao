package com.breth.otimizando_relatorios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CONTRATO_DETALHE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoDetalheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTRATO_DETALHE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CONTRATO")
    private ContratoEntity contrato;

    @Column(name = "PREPOSTO")
    private String preposto;

    @Column(name = "FISCAL")
    private String fiscal;

    @Column(name = "GESTOR")
    private String gestor;

    @Column(name = "OBJETO")
    private String objeto;

    @Column(name = "PROCESSO_SEI")
    private String processoSei;
} 