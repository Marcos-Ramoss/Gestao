package com.service.setebit.gestao.adapter.dto;

public record AuthRegisterRequest(
     String username,
     String password,
     String nome,
     String cpf
) {

} 