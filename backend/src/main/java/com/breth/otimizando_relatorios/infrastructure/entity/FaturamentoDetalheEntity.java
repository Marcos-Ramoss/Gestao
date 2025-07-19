package com.breth.otimizando_relatorios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FATURAMENTO_DETALHE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoDetalheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FATURAMENTO_DETALHE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_FATURAMENTO")
    private FaturamentoEntity faturamento;

    @Column(name = "DESCRICAO")
    private String descricao;
} 