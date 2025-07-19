package com.breth.otimizando_relatorios.adapter.controller;

import com.breth.otimizando_relatorios.adapter.dto.FaturamentoRequest;
import com.breth.otimizando_relatorios.adapter.dto.FaturamentoResponse;
import com.breth.otimizando_relatorios.application.service.FaturamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/faturamentos")
public class FaturamentoController {
    private final FaturamentoService service;

    public FaturamentoController(FaturamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FaturamentoResponse> criar(@RequestBody FaturamentoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<FaturamentoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturamentoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturamentoResponse> atualizar(@PathVariable Long id, @RequestBody FaturamentoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 