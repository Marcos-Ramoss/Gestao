package com.breth.otimizando_relatorios.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoDomain {
    private Long id;
    private String codigoContrato;
    private String nome;
    private Long fatorAjuste;
} 