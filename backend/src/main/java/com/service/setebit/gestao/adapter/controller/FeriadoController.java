package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.FeriadoRequest;
import com.service.setebit.gestao.adapter.dto.FeriadoResponse;
import com.service.setebit.gestao.application.service.FeriadoService;
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
 * Controller responsável pela gestão de feriados.
 */
@RestController
@RequestMapping("/feriados")
@Tag(name = "Feriados", description = "API para gestão de feriados")
@RequiredArgsConstructor
@Slf4j
public class FeriadoController {
    private final FeriadoService service;

    /**
     * Cria um novo feriado.
     * @param request Dados do feriado
     * @return Feriado criado
     */
    @PostMapping
    @Operation(summary = "Criar feriado", description = "Cria um novo feriado no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Feriado criado com sucesso", content = @Content(schema = @Schema(implementation = FeriadoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<FeriadoResponse> criar(
            @Parameter(description = "Dados do feriado", required = true)
            @Valid @RequestBody FeriadoRequest request) {
        log.info("Criando feriado: {}", request);
        FeriadoResponse response = service.criar(request);
        log.info("Feriado criado com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os feriados cadastrados.
     * @return Lista de feriados
     */
    @GetMapping
    @Operation(summary = "Listar feriados", description = "Lista todos os feriados cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de feriados retornada com sucesso", content = @Content(schema = @Schema(implementation = FeriadoResponse.class)))
    })
    public ResponseEntity<List<FeriadoResponse>> listarTodos() {
        log.info("Listando todos os feriados");
        List<FeriadoResponse> feriados = service.listarTodos();
        log.info("{} feriados encontrados", feriados.size());
        return ResponseEntity.ok(feriados);
    }

    /**
     * Busca um feriado pelo ID.
     * @param id ID do feriado
     * @return Feriado encontrado ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar feriado por ID", description = "Busca um feriado pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Feriado encontrado", content = @Content(schema = @Schema(implementation = FeriadoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Feriado não encontrado", content = @Content)
    })
    public ResponseEntity<FeriadoResponse> buscarPorId(
            @Parameter(description = "ID do feriado", required = true)
            @PathVariable Long id) {
        log.info("Buscando feriado pelo ID: {}", id);
        return service.buscarPorId(id)
                .map(feriado -> {
                    log.info("Feriado encontrado: {}", feriado.id());
                    return ResponseEntity.ok(feriado);
                })
                .orElseGet(() -> {
                    log.warn("Feriado não encontrado: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza um feriado existente.
     * @param id ID do feriado
     * @param request Novos dados do feriado
     * @return Feriado atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar feriado", description = "Atualiza um feriado existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Feriado atualizado com sucesso", content = @Content(schema = @Schema(implementation = FeriadoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Feriado não encontrado", content = @Content)
    })
    public ResponseEntity<FeriadoResponse> atualizar(
            @Parameter(description = "ID do feriado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados do feriado", required = true)
            @Valid @RequestBody FeriadoRequest request) {
        log.info("Atualizando feriado: {}", id);
        FeriadoResponse response = service.atualizar(id, request);
        log.info("Feriado atualizado com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um feriado do sistema.
     * @param id ID do feriado
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar feriado", description = "Remove um feriado do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Feriado removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Feriado não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do feriado", required = true)
            @PathVariable Long id) {
        log.info("Removendo feriado: {}", id);
        service.deletar(id);
        log.info("Feriado removido com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 