package com.service.setebit.gestao.adapter.dto;

public record ContratoDetalheResponse(
    Long id,
    String codigoContrato,
    String preposto,
    String fiscal,
    String gestor,
    String objeto,
    String processoSei
) {} 