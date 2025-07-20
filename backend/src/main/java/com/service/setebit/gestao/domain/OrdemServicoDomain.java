package com.service.setebit.gestao.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoDomain {
    private Long id;
    private String codigoContrato;
    private String numeroOs;
} 