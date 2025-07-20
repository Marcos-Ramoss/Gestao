package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.OrdemServicoRequest;
import com.service.setebit.gestao.adapter.dto.OrdemServicoResponse;
import com.service.setebit.gestao.application.service.OrdemServicoService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    
    private final OrdemServicoService service;

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> criar(@RequestBody OrdemServicoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> atualizar(@PathVariable Long id, @RequestBody OrdemServicoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 