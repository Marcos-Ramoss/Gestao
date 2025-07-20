package com.service.setebit.gestao.domain;

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