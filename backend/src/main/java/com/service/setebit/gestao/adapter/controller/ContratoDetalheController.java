package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.ContratoDetalheRequest;
import com.service.setebit.gestao.adapter.dto.ContratoDetalheResponse;
import com.service.setebit.gestao.application.service.ContratoDetalheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller responsável pela gestão de detalhes de contrato.
 */
@RestController
@RequestMapping("/contrato-detalhes")
@Tag(name = "Contrato Detalhe", description = "API para gestão de detalhes de contrato")
@RequiredArgsConstructor
@Slf4j
public class ContratoDetalheController {
    private final ContratoDetalheService service;

    /**
     * Cria um novo detalhe de contrato.
     * @param request Dados do detalhe
     * @return Detalhe criado
     */
    @PostMapping
    @Operation(summary = "Criar detalhe de contrato", description = "Cria um novo detalhe de contrato no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Detalhe criado com sucesso", content = @Content(schema = @Schema(implementation = ContratoDetalheResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<ContratoDetalheResponse> criar(
            @Parameter(description = "Dados do detalhe de contrato", required = true)
            @Valid @RequestBody ContratoDetalheRequest request) {
        log.info("Criando detalhe de contrato: {}", request);
        ContratoDetalheResponse response = service.criar(request);
        log.info("Detalhe de contrato criado com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os detalhes de contrato cadastrados.
     * @return Lista de detalhes
     */
    @GetMapping
    @Operation(summary = "Listar detalhes de contrato", description = "Lista todos os detalhes de contrato cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de detalhes retornada com sucesso", content = @Content(schema = @Schema(implementation = ContratoDetalheResponse.class)))
    })
    public ResponseEntity<List<ContratoDetalheResponse>> listarTodos() {
        log.info("Listando todos os detalhes de contrato");
        List<ContratoDetalheResponse> detalhes = service.listarTodos();
        log.info("{} detalhes encontrados", detalhes.size());
        return ResponseEntity.ok(detalhes);
    }

    /**
     * Busca um detalhe de contrato pelo ID.
     * @param id ID do detalhe
     * @return Detalhe encontrado ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar detalhe de contrato por ID", description = "Busca um detalhe de contrato pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalhe encontrado", content = @Content(schema = @Schema(implementation = ContratoDetalheResponse.class))),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<ContratoDetalheResponse> buscarPorId(
            @Parameter(description = "ID do detalhe de contrato", required = true)
            @PathVariable Long id) {
        log.info("Buscando detalhe de contrato pelo ID: {}", id);
        return service.buscarPorId(id)
                .map(detalhe -> {
                    log.info("Detalhe encontrado: {}", detalhe.id());
                    return ResponseEntity.ok(detalhe);
                })
                .orElseGet(() -> {
                    log.warn("Detalhe não encontrado: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza um detalhe de contrato existente.
     * @param id ID do detalhe
     * @param request Novos dados do detalhe
     * @return Detalhe atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar detalhe de contrato", description = "Atualiza um detalhe de contrato existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalhe atualizado com sucesso", content = @Content(schema = @Schema(implementation = ContratoDetalheResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<ContratoDetalheResponse> atualizar(
            @Parameter(description = "ID do detalhe de contrato", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados do detalhe", required = true)
            @Valid @RequestBody ContratoDetalheRequest request) {
        log.info("Atualizando detalhe de contrato: {}", id);
        ContratoDetalheResponse response = service.atualizar(id, request);
        log.info("Detalhe de contrato atualizado com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um detalhe de contrato do sistema.
     * @param id ID do detalhe
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar detalhe de contrato", description = "Remove um detalhe de contrato do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Detalhe removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do detalhe de contrato", required = true)
            @PathVariable Long id) {
        log.info("Removendo detalhe de contrato: {}", id);
        service.deletar(id);
        log.info("Detalhe de contrato removido com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 