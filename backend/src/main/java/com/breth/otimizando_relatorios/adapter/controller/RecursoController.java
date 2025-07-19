package com.breth.otimizando_relatorios.adapter.controller;

import com.breth.otimizando_relatorios.adapter.dto.RecursoRequest;
import com.breth.otimizando_relatorios.adapter.dto.RecursoResponse;
import com.breth.otimizando_relatorios.application.service.RecursoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recursos")
public class RecursoController {

    private final RecursoService service;

    @PostMapping
    public ResponseEntity<RecursoResponse> criar(@RequestBody RecursoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<RecursoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponse> atualizar(@PathVariable Long id, @RequestBody RecursoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 