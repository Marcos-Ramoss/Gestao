package com.breth.otimizando_relatorios.adapter.controller;

import com.breth.otimizando_relatorios.adapter.dto.ContratoDetalheRequest;
import com.breth.otimizando_relatorios.adapter.dto.ContratoDetalheResponse;
import com.breth.otimizando_relatorios.application.service.ContratoDetalheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contrato-detalhes")
public class ContratoDetalheController {
    private final ContratoDetalheService service;

    public ContratoDetalheController(ContratoDetalheService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContratoDetalheResponse> criar(@RequestBody ContratoDetalheRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ContratoDetalheResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDetalheResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDetalheResponse> atualizar(@PathVariable Long id, @RequestBody ContratoDetalheRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 