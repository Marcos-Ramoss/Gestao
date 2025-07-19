package com.breth.otimizando_relatorios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "FATURAMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FATURAMENTO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CONTRATO")
    private ContratoEntity contrato;

    @Column(name = "OBJETIVO")
    private String objetivo;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "NUMERO_MEDICAO")
    private String numeroMedicao;

    @OneToMany(mappedBy = "faturamento")
    private List<FaturamentoDetalheEntity> detalhes;
} 