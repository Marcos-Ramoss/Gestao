package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ordem_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordem_servico")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private AreaEntity area;

    @Column(name = "NUMERO_OS", nullable = false, length = 20)
    private String numeroOs;
} 