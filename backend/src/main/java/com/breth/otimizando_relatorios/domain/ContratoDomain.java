package com.breth.otimizando_relatorios.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoDomain {
    private String codigoContrato;
    private Long idArea;
} 