package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.ContratoRequest;
import com.service.setebit.gestao.adapter.dto.ContratoResponse;
import com.service.setebit.gestao.application.service.ContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoService service;

    public ContratoController(ContratoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContratoResponse> criar(@RequestBody ContratoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{codigoContrato}")
    public ResponseEntity<ContratoResponse> buscarPorId(@PathVariable String codigoContrato) {
        return service.buscarPorId(codigoContrato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{codigoContrato}")
    public ResponseEntity<ContratoResponse> atualizar(@PathVariable String codigoContrato, @RequestBody ContratoRequest request) {
        return ResponseEntity.ok(service.atualizar(codigoContrato, request));
    }

    @DeleteMapping("/{codigoContrato}")
    public ResponseEntity<Void> deletar(@PathVariable String codigoContrato) {
        service.deletar(codigoContrato);
        return ResponseEntity.noContent().build();
    }
} 