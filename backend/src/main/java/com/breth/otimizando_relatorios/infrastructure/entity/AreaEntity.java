package com.breth.otimizando_relatorios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "AREA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AREA")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "area")
    private List<ContratoEntity> contratos;
} 