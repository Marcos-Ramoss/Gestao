package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.RecursoRequest;
import com.service.setebit.gestao.adapter.dto.RecursoResponse;
import com.service.setebit.gestao.application.service.RecursoService;
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
 * Controller responsável pela gestão de recursos.
 */
@RestController
@RequestMapping("/recursos")
@Tag(name = "Recursos", description = "API para gestão de recursos")
@RequiredArgsConstructor
@Slf4j
public class RecursoController {
    private final RecursoService service;

    /**
     * Cria um novo recurso.
     * @param request Dados do recurso
     * @return Recurso criado
     */
    @PostMapping
    @Operation(summary = "Criar recurso", description = "Cria um novo recurso no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(schema = @Schema(implementation = RecursoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<RecursoResponse> criar(
            @Parameter(description = "Dados do recurso", required = true)
            @Valid @RequestBody RecursoRequest request) {
        log.info("Criando recurso: {}", request);
        RecursoResponse response = service.criar(request);
        log.info("Recurso criado com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os recursos cadastrados.
     * @return Lista de recursos
     */
    @GetMapping
    @Operation(summary = "Listar recursos", description = "Lista todos os recursos cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de recursos retornada com sucesso", content = @Content(schema = @Schema(implementation = RecursoResponse.class)))
    })
    public ResponseEntity<List<RecursoResponse>> listarTodos() {
        log.info("Listando todos os recursos");
        List<RecursoResponse> recursos = service.listarTodos();
        log.info("{} recursos encontrados", recursos.size());
        return ResponseEntity.ok(recursos);
    }

    /**
     * Busca um recurso pelo ID.
     * @param id ID do recurso
     * @return Recurso encontrado ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar recurso por ID", description = "Busca um recurso pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado", content = @Content(schema = @Schema(implementation = RecursoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content)
    })
    public ResponseEntity<RecursoResponse> buscarPorId(
            @Parameter(description = "ID do recurso", required = true)
            @PathVariable Long id) {
        log.info("Buscando recurso pelo ID: {}", id);
        return service.buscarPorId(id)
                .map(recurso -> {
                    log.info("Recurso encontrado: {}", recurso.id());
                    return ResponseEntity.ok(recurso);
                })
                .orElseGet(() -> {
                    log.warn("Recurso não encontrado: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza um recurso existente.
     * @param id ID do recurso
     * @param request Novos dados do recurso
     * @return Recurso atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar recurso", description = "Atualiza um recurso existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso atualizado com sucesso", content = @Content(schema = @Schema(implementation = RecursoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content)
    })
    public ResponseEntity<RecursoResponse> atualizar(
            @Parameter(description = "ID do recurso", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados do recurso", required = true)
            @Valid @RequestBody RecursoRequest request) {
        log.info("Atualizando recurso: {}", id);
        RecursoResponse response = service.atualizar(id, request);
        log.info("Recurso atualizado com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um recurso do sistema.
     * @param id ID do recurso
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar recurso", description = "Remove um recurso do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Recurso removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do recurso", required = true)
            @PathVariable Long id) {
        log.info("Removendo recurso: {}", id);
        service.deletar(id);
        log.info("Recurso removido com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 