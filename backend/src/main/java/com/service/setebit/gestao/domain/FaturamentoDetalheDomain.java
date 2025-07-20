package com.service.setebit.gestao.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoDetalheDomain {
    private Long id;
    private Long idFaturamento;
    private String descricao;
} 