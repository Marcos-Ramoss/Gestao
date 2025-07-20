package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDEM_SERVICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CONTRATO")
    private ContratoEntity contrato;

    @Column(name = "NUMERO_OS", nullable = false)
    private String numeroOs;
} 