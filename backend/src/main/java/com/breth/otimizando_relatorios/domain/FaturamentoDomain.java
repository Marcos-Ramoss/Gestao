package com.breth.otimizando_relatorios.domain;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaturamentoDomain {
    private Long id;
    private String codigoContrato;
    private String objetivo;
    private BigDecimal valor;
    private String numeroMedicao;
}
