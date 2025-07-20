package com.service.setebit.gestao.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação e atualização de áreas.
 */
@Schema(description = "Dados para criação/atualização de área")
public record AreaRequest(
    @Schema(description = "Nome da área", example = "Tecnologia da Informação")
    @NotBlank(message = "O nome da área é obrigatório")
    @Size(min = 2, max = 255, message = "O nome da área deve ter entre 2 e 255 caracteres")
    String nome
) {} 