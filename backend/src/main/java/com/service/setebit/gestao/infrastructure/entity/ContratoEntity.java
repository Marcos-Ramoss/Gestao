package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "CONTRATO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoEntity {
    @Id
    @Column(name = "CODIGO_CONTRATO")
    private String codigoContrato;

    @ManyToOne
    @JoinColumn(name = "ID_AREA")
    private AreaEntity area;

    @OneToMany(mappedBy = "contrato")
    private List<OrdemServicoEntity> ordensServico;

    @OneToMany(mappedBy = "contrato")
    private List<RecursoEntity> recursos;

    @OneToMany(mappedBy = "contrato")
    private List<ContratoDetalheEntity> detalhes;

    @OneToMany(mappedBy = "contrato")
    private List<FaturamentoEntity> faturamentos;
} 