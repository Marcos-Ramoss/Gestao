package com.service.setebit.gestao.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaDomain {
    private Long id;
    private String nome;
    private String codigoContrato;
}