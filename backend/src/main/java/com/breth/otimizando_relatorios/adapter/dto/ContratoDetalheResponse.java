package com.breth.otimizando_relatorios.adapter.dto;

public record ContratoDetalheResponse(
    Long id,
    String codigoContrato,
    String preposto,
    String fiscal,
    String gestor,
    String objeto,
    String processoSei
) {} 