package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.FeriadoRequest;
import com.service.setebit.gestao.adapter.dto.FeriadoResponse;
import com.service.setebit.gestao.application.service.FeriadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feriados")
public class FeriadoController {
    private final FeriadoService service;

    public FeriadoController(FeriadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FeriadoResponse> criar(@RequestBody FeriadoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<FeriadoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeriadoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeriadoResponse> atualizar(@PathVariable Long id, @RequestBody FeriadoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 