package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long id;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @OneToMany(mappedBy = "area")
    private List<ContratoEntity> contratos;
} 