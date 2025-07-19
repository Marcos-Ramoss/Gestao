package com.breth.otimizando_relatorios.adapter.dto;

public record AuthRegisterRequest(
     String username,
     String password,
     String nome,
     String cpf
) {

} 