package com.service.setebit.gestao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioAtividadeDomain {
    private String cliente;
    private Integer ano;
    private Integer mes;
    private String colaborador;
    private String nomeProjeto;
    private Double horaTotalProjeto;
} 