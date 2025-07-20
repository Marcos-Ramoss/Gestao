package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.FaturamentoRequest;
import com.service.setebit.gestao.adapter.dto.FaturamentoResponse;
import com.service.setebit.gestao.application.service.FaturamentoService;
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
 * Controller responsável pela gestão de faturamentos.
 */
@RestController
@RequestMapping("/faturamentos")
@Tag(name = "Faturamentos", description = "API para gestão de faturamentos")
@RequiredArgsConstructor
@Slf4j
public class FaturamentoController {
    private final FaturamentoService service;

    /**
     * Cria um novo faturamento.
     * @param request Dados do faturamento
     * @return Faturamento criado
     */
    @PostMapping
    @Operation(summary = "Criar faturamento", description = "Cria um novo faturamento no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Faturamento criado com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<FaturamentoResponse> criar(
            @Parameter(description = "Dados do faturamento", required = true)
            @Valid @RequestBody FaturamentoRequest request) {
        log.info("Criando faturamento: {}", request);
        FaturamentoResponse response = service.criar(request);
        log.info("Faturamento criado com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os faturamentos cadastrados.
     * @return Lista de faturamentos
     */
    @GetMapping
    @Operation(summary = "Listar faturamentos", description = "Lista todos os faturamentos cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de faturamentos retornada com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoResponse.class)))
    })
    public ResponseEntity<List<FaturamentoResponse>> listarTodos() {
        log.info("Listando todos os faturamentos");
        List<FaturamentoResponse> faturamentos = service.listarTodos();
        log.info("{} faturamentos encontrados", faturamentos.size());
        return ResponseEntity.ok(faturamentos);
    }

    /**
     * Busca um faturamento pelo ID.
     * @param id ID do faturamento
     * @return Faturamento encontrado ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar faturamento por ID", description = "Busca um faturamento pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Faturamento encontrado", content = @Content(schema = @Schema(implementation = FaturamentoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Faturamento não encontrado", content = @Content)
    })
    public ResponseEntity<FaturamentoResponse> buscarPorId(
            @Parameter(description = "ID do faturamento", required = true)
            @PathVariable Long id) {
        log.info("Buscando faturamento pelo ID: {}", id);
        return service.buscarPorId(id)
                .map(faturamento -> {
                    log.info("Faturamento encontrado: {}", faturamento.id());
                    return ResponseEntity.ok(faturamento);
                })
                .orElseGet(() -> {
                    log.warn("Faturamento não encontrado: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza um faturamento existente.
     * @param id ID do faturamento
     * @param request Novos dados do faturamento
     * @return Faturamento atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar faturamento", description = "Atualiza um faturamento existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Faturamento atualizado com sucesso", content = @Content(schema = @Schema(implementation = FaturamentoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Faturamento não encontrado", content = @Content)
    })
    public ResponseEntity<FaturamentoResponse> atualizar(
            @Parameter(description = "ID do faturamento", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados do faturamento", required = true)
            @Valid @RequestBody FaturamentoRequest request) {
        log.info("Atualizando faturamento: {}", id);
        FaturamentoResponse response = service.atualizar(id, request);
        log.info("Faturamento atualizado com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um faturamento do sistema.
     * @param id ID do faturamento
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar faturamento", description = "Remove um faturamento do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Faturamento removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Faturamento não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do faturamento", required = true)
            @PathVariable Long id) {
        log.info("Removendo faturamento: {}", id);
        service.deletar(id);
        log.info("Faturamento removido com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 