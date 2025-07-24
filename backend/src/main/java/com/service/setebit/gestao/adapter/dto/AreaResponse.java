package com.service.setebit.gestao.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de dados de área.
 */
@Schema(description = "Dados de resposta de área")
public record AreaResponse(
    @Schema(description = "ID único da área", example = "1")
    Long id,

    @Schema(description = "Nome da área", example = "Tecnologia da Informação")
    String nome,

    @Schema(description = "Código do contrato relacionado à área", example = "CT-2025-001")
    String codigoContrato
) {}