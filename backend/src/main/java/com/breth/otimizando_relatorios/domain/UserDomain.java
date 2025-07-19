package com.breth.otimizando_relatorios.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDomain {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String nome;
    private String cpf;
} 