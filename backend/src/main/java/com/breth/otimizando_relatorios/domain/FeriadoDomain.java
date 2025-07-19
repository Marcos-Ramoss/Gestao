package com.breth.otimizando_relatorios.domain;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeriadoDomain {
    private Long id;
    private LocalDate data;
} 