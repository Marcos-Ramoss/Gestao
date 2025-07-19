package com.breth.otimizando_relatorios.adapter.dto;

import java.time.LocalDate;

public record FeriadoResponse(
    Long id,
    LocalDate data
) {} 