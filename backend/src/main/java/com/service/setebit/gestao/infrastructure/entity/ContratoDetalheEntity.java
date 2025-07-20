package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrato_detalhe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoDetalheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cont_det")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_contrato")
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