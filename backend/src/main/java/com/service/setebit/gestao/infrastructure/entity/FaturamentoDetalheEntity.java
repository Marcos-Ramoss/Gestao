package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faturamento_detalhe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoDetalheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faturamento_detalhe")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_faturamento")
    private FaturamentoEntity faturamento;

    @Column(name = "DESCRICAO")
    private String descricao;
} 