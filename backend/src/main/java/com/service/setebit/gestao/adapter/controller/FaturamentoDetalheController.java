package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheRequest;
import com.service.setebit.gestao.adapter.dto.FaturamentoDetalheResponse;
import com.service.setebit.gestao.application.service.FaturamentoDetalheService;
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
 * Controller responsável pela gestão de detalhes de faturamento.
 */
@RestController
@RequestMapping("/faturamento-detalhes")
@Tag(name = "Faturamento Detalhe", description = "API para gestão de detalhes de faturamento")
@RequiredArgsConstructor
@Slf4j
public class FaturamentoDetalheController {
    private final FaturamentoDetalheService service;

    /**
     * Cria um novo detalhe de faturamento.
     * @param request Dados do detalhe
     * @return Detalhe criado
     */
    @PostMapping
    @Operation(summary = "Criar detalhe de faturamento", description = "Cria um novo detalhe de faturamento no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Detalhe criado com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoDetalheResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<FaturamentoDetalheResponse> criar(
            @Parameter(description = "Dados do detalhe de faturamento", required = true)
            @Valid @RequestBody FaturamentoDetalheRequest request) {
        log.info("Criando detalhe de faturamento: {}", request);
        FaturamentoDetalheResponse response = service.criar(request);
        log.info("Detalhe de faturamento criado com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os detalhes de faturamento cadastrados.
     * @return Lista de detalhes
     */
    @GetMapping
    @Operation(summary = "Listar detalhes de faturamento", description = "Lista todos os detalhes de faturamento cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de detalhes retornada com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoDetalheResponse.class)))
    })
    public ResponseEntity<List<FaturamentoDetalheResponse>> listarTodos() {
        log.info("Listando todos os detalhes de faturamento");
        List<FaturamentoDetalheResponse> detalhes = service.listarTodos();
        log.info("{} detalhes encontrados", detalhes.size());
        return ResponseEntity.ok(detalhes);
    }

    /**
     * Busca um detalhe de faturamento pelo ID.
     * @param id ID do detalhe
     * @return Detalhe encontrado ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar detalhe de faturamento por ID", description = "Busca um detalhe de faturamento pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalhe encontrado", content = @Content(schema = @Schema(implementation = FaturamentoDetalheResponse.class))),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<FaturamentoDetalheResponse> buscarPorId(
            @Parameter(description = "ID do detalhe de faturamento", required = true)
            @PathVariable Long id) {
        log.info("Buscando detalhe de faturamento pelo ID: {}", id);
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
     * Atualiza um detalhe de faturamento existente.
     * @param id ID do detalhe
     * @param request Novos dados do detalhe
     * @return Detalhe atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar detalhe de faturamento", description = "Atualiza um detalhe de faturamento existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalhe atualizado com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoDetalheResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<FaturamentoDetalheResponse> atualizar(
            @Parameter(description = "ID do detalhe de faturamento", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados do detalhe", required = true)
            @Valid @RequestBody FaturamentoDetalheRequest request) {
        log.info("Atualizando detalhe de faturamento: {}", id);
        FaturamentoDetalheResponse response = service.atualizar(id, request);
        log.info("Detalhe de faturamento atualizado com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um detalhe de faturamento do sistema.
     * @param id ID do detalhe
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar detalhe de faturamento", description = "Remove um detalhe de faturamento do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Detalhe removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Detalhe não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do detalhe de faturamento", required = true)
            @PathVariable Long id) {
        log.info("Removendo detalhe de faturamento: {}", id);
        service.deletar(id);
        log.info("Detalhe de faturamento removido com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 