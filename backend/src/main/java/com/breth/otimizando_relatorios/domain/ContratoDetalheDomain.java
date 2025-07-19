package com.breth.otimizando_relatorios.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoDetalheDomain {
    private Long id;
    private String codigoContrato;
    private String preposto;
    private String fiscal;
    private String gestor;
    private String objeto;
    private String processoSei;
} 