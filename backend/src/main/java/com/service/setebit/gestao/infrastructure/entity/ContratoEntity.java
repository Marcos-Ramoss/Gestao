package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "contrato")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoEntity {
    @Id
    @Column(name = "codigo_contrato")
    private String codigo;

    @OneToMany(mappedBy = "contrato")
    private List<OrdemServicoEntity> ordensServico;

    @OneToMany(mappedBy = "contrato")
    private List<RecursoEntity> recursos;

    @OneToMany(mappedBy = "contrato")
    private List<ContratoDetalheEntity> detalhes;

    @OneToMany(mappedBy = "contrato")
    private List<FaturamentoEntity> faturamentos;
} 