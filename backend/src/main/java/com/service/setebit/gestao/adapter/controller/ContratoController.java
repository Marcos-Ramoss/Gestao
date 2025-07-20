package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.ContratoRequest;
import com.service.setebit.gestao.adapter.dto.ContratoResponse;
import com.service.setebit.gestao.application.service.ContratoService;
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
 * Controller responsável pela gestão de contratos.
 */
@RestController
@RequestMapping("/contratos")
@Tag(name = "Contratos", description = "API para gestão de contratos")
@RequiredArgsConstructor
@Slf4j
public class ContratoController {
    private final ContratoService service;

    /**
     * Cria um novo contrato.
     * @param request Dados do contrato
     * @return Contrato criado
     */
    @PostMapping
    @Operation(summary = "Criar contrato", description = "Cria um novo contrato no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso", content = @Content(schema = @Schema(implementation = ContratoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<ContratoResponse> criar(
            @Parameter(description = "Dados do contrato", required = true)
            @Valid @RequestBody ContratoRequest request) {
        log.info("Criando contrato: {}", request);
        ContratoResponse response = service.criar(request);
        log.info("Contrato criado com sucesso: {}", response.codigoContrato());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os contratos cadastrados.
     * @return Lista de contratos
     */
    @GetMapping
    @Operation(summary = "Listar contratos", description = "Lista todos os contratos cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso", content = @Content(schema = @Schema(implementation = ContratoResponse.class)))
    })
    public ResponseEntity<List<ContratoResponse>> listarTodos() {
        log.info("Listando todos os contratos");
        List<ContratoResponse> contratos = service.listarTodos();
        log.info("{} contratos encontrados", contratos.size());
        return ResponseEntity.ok(contratos);
    }

    /**
     * Busca um contrato pelo código.
     * @param codigoContrato Código do contrato
     * @return Contrato encontrado ou 404
     */
    @GetMapping("/{codigoContrato}")
    @Operation(summary = "Buscar contrato por código", description = "Busca um contrato pelo código.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato encontrado", content = @Content(schema = @Schema(implementation = ContratoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado", content = @Content)
    })
    public ResponseEntity<ContratoResponse> buscarPorId(
            @Parameter(description = "Código do contrato", required = true)
            @PathVariable String codigoContrato) {
        log.info("Buscando contrato pelo código: {}", codigoContrato);
        return service.buscarPorId(codigoContrato)
                .map(contrato -> {
                    log.info("Contrato encontrado: {}", contrato.codigoContrato());
                    return ResponseEntity.ok(contrato);
                })
                .orElseGet(() -> {
                    log.warn("Contrato não encontrado: {}", codigoContrato);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza um contrato existente.
     * @param codigoContrato Código do contrato
     * @param request Novos dados do contrato
     * @return Contrato atualizado
     */
    @PutMapping("/{codigoContrato}")
    @Operation(summary = "Atualizar contrato", description = "Atualiza um contrato existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso", content = @Content(schema = @Schema(implementation = ContratoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado", content = @Content)
    })
    public ResponseEntity<ContratoResponse> atualizar(
            @Parameter(description = "Código do contrato", required = true)
            @PathVariable String codigoContrato,
            @Parameter(description = "Novos dados do contrato", required = true)
            @Valid @RequestBody ContratoRequest request) {
        log.info("Atualizando contrato: {}", codigoContrato);
        ContratoResponse response = service.atualizar(codigoContrato, request);
        log.info("Contrato atualizado com sucesso: {}", response.codigoContrato());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove um contrato do sistema.
     * @param codigoContrato Código do contrato
     * @return 204 No Content se removido
     */
    @DeleteMapping("/{codigoContrato}")
    @Operation(summary = "Deletar contrato", description = "Remove um contrato do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contrato removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado", content = @Content)
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "Código do contrato", required = true)
            @PathVariable String codigoContrato) {
        log.info("Removendo contrato: {}", codigoContrato);
        service.deletar(codigoContrato);
        log.info("Contrato removido com sucesso: {}", codigoContrato);
        return ResponseEntity.noContent().build();
    }
} 