package com.service.setebit.gestao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "feriado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeriadoEntity {
    @Id
    @Column(name = "data", nullable = false)
    private LocalDate data;
} 