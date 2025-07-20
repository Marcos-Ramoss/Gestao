package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "FERIADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeriadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA", nullable = false)
    private LocalDate data;
} 