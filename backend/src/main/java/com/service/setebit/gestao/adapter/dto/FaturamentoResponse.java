package com.service.setebit.gestao.adapter.dto;

import java.math.BigDecimal;

public record FaturamentoResponse(
    Long id,
    String codigoContrato,
    String objetivo,
    BigDecimal valor,
    String numeroMedicao
) {} 