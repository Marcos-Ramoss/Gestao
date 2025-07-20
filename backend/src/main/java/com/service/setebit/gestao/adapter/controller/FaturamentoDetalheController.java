package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheRequest;
import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheResponse;
import com.service.setebit.gestao.application.service.FaturamentoDetalheService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/faturamento-detalhes")
public class FaturamentoDetalheController {
    
    private final FaturamentoDetalheService service;

    @PostMapping
    public ResponseEntity<FaturamentoDetalheResponse> criar(@RequestBody FaturamentoDetalheRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<FaturamentoDetalheResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturamentoDetalheResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturamentoDetalheResponse> atualizar(@PathVariable Long id, @RequestBody FaturamentoDetalheRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 