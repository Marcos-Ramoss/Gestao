package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.OrdemServicoRequest;
import com.service.setebit.gestao.adapter.dto.OrdemServicoResponse;
import com.service.setebit.gestao.application.service.OrdemServicoService;
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
 * Controller responsável pela gestão de ordens de serviço.
 */
@RestController
@RequestMapping("/ordens-servico")
@Tag(name = "Ordens de Serviço", description = "API para gestão de ordens de serviço")
@RequiredArgsConstructor
@Slf4j
public class OrdemServicoController {
    private final OrdemServicoService service;

    /**
     * Cria uma nova ordem de serviço.
     * @param request Dados da ordem de serviço
     * @return Ordem de serviço criada
     */
    @PostMapping
    @Operation(summary = "Criar ordem de serviço", description = "Cria uma nova ordem de serviço no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ordem de serviço criada com sucesso", content = @Content(schema = @Schema(implementation = OrdemServicoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<OrdemServicoResponse> criar(
            @Parameter(description = "Dados da ordem de serviço", required = true)
            @Valid @RequestBody OrdemServicoRequest request) {
        log.info("Criando ordem de serviço: {}", request);
        OrdemServicoResponse response = service.criar(request);
        log.info("Ordem de serviço criada com sucesso: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todas as ordens de serviço cadastradas.
     * @return Lista de ordens de serviço
     */
    @GetMapping
    @Operation(summary = "Listar ordens de serviço", description = "Lista todas as ordens de serviço cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ordens de serviço retornada com sucesso", content = @Content(schema = @Schema(implementation = OrdemServicoResponse.class)))
    })
    public ResponseEntity<List<OrdemServicoResponse>> listarTodos() {
        log.info("Listando todas as ordens de serviço");
        List<OrdemServicoResponse> ordens = service.listarTodos();
        log.info("{} ordens de serviço encontradas", ordens.size());
        return ResponseEntity.ok(ordens);
    }

    /**
     * Busca uma ordem de serviço pelo ID.
     * @param id ID da ordem de serviço
     * @return Ordem de serviço encontrada ou 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar ordem de serviço por ID", description = "Busca uma ordem de serviço pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ordem de serviço encontrada", content = @Content(schema = @Schema(implementation = OrdemServicoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada", content = @Content)
    })
    public ResponseEntity<OrdemServicoResponse> buscarPorId(
            @Parameter(description = "ID da ordem de serviço", required = true)
            @PathVariable Long id) {
        log.info("Buscando ordem de serviço pelo ID: {}", id);
        return service.buscarPorId(id)
                .map(ordem -> {
                    log.info("Ordem de serviço encontrada: {}", ordem.id());
                    return ResponseEntity.ok(ordem);
                })
                .orElseGet(() -> {
                    log.warn("Ordem de serviço não encontrada: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza uma ordem de serviço existente.
     * @param id ID da ordem de serviço
     * @param request Novos dados da ordem de serviço
     * @return Ordem de serviço atualizada
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ordem de serviço", description = "Atualiza uma ordem de serviço existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ordem de serviço atualizada com sucesso", content = @Content(schema = @Schema(implementation = OrdemServicoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada", content = @Content)
    })
    public ResponseEntity<OrdemServicoResponse> atualizar(
            @Parameter(description = "ID da ordem de serviço", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados da ordem de serviço", required = true)
            @Valid @RequestBody OrdemServicoRequest request) {
        log.info("Atualizando ordem de serviço: {}", id);
        OrdemServicoResponse response = service.atualizar(id, request);
        log.info("Ordem de serviço atualizada com sucesso: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove uma ordem de serviço do sistema.
     * @param id ID da ordem de serviço
     * @return 204 No Content se removida
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar ordem de serviço", description = "Remove uma ordem de serviço do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Ordem de serviço removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da ordem de serviço", required = true)
            @PathVariable Long id) {
        log.info("Removendo ordem de serviço: {}", id);
        service.deletar(id);
        log.info("Ordem de serviço removida com sucesso: {}", id);
        return ResponseEntity.noContent().build();
    }
} 