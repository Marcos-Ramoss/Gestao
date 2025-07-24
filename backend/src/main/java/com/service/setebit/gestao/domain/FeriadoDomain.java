package com.service.setebit.gestao.domain;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeriadoDomain {
    private LocalDate data;
} 