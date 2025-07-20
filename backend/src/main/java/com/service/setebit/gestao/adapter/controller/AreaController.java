package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.adapter.dto.AreaRequest;
import com.service.setebit.gestao.adapter.dto.AreaResponse;
import com.service.setebit.gestao.application.service.AreaService;

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
 * Controller responsável pela gestão de áreas do sistema.
 * Permite criar, listar, buscar, atualizar e deletar áreas.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
@Tag(name = "Áreas", description = "API para gestão de áreas do sistema")
@Slf4j
public class AreaController {

    private final AreaService service;

    /**
     * Cria uma nova área no sistema.
     * 
     * @param request Dados da área a ser criada
     * @return Área criada com ID gerado
     */
    @PostMapping
    @Operation(
        summary = "Criar nova área",
        description = "Cria uma nova área no sistema com os dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Área criada com sucesso",
            content = @Content(schema = @Schema(implementation = AreaResponse.class))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Dados inválidos fornecidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content
        )
    })
    public ResponseEntity<AreaResponse> criar(
            @Parameter(description = "Dados da área a ser criada", required = true)
            @Valid @RequestBody AreaRequest request) {
        log.info("Criando nova área: {}", request.nome());
        AreaResponse response = service.criar(request);
        log.info("Área criada com sucesso. ID: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todas as áreas cadastradas no sistema.
     * 
     * @return Lista de todas as áreas
     */
    @GetMapping
    @Operation(
        summary = "Listar todas as áreas",
        description = "Retorna uma lista com todas as áreas cadastradas no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de áreas retornada com sucesso",
            content = @Content(schema = @Schema(implementation = AreaResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<AreaResponse>> listarTodos() {
        log.info("Listando todas as áreas");
        List<AreaResponse> areas = service.listarTodos();
        log.info("Encontradas {} áreas", areas.size());
        return ResponseEntity.ok(areas);
    }

    /**
     * Busca uma área específica pelo ID.
     * 
     * @param id ID da área a ser buscada
     * @return Área encontrada ou 404 se não existir
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar área por ID",
        description = "Retorna uma área específica baseada no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Área encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = AreaResponse.class))
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Área não encontrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content
        )
    })
    public ResponseEntity<AreaResponse> buscarPorId(
            @Parameter(description = "ID da área a ser buscada", required = true)
            @PathVariable Long id) {
        log.info("Buscando área com ID: {}", id);
        return service.buscarPorId(id)
                .map(area -> {
                    log.info("Área encontrada: {}", area.nome());
                    return ResponseEntity.ok(area);
                })
                .orElseGet(() -> {
                    log.warn("Área com ID {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Atualiza uma área existente.
     * 
     * @param id ID da área a ser atualizada
     * @param request Novos dados da área
     * @return Área atualizada
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar área",
        description = "Atualiza os dados de uma área existente baseada no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Área atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = AreaResponse.class))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Dados inválidos fornecidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Área não encontrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content
        )
    })
    public ResponseEntity<AreaResponse> atualizar(
            @Parameter(description = "ID da área a ser atualizada", required = true)
            @PathVariable Long id,
            @Parameter(description = "Novos dados da área", required = true)
            @Valid @RequestBody AreaRequest request) {
        log.info("Atualizando área com ID: {}, novos dados: {}", id, request.nome());
        AreaResponse response = service.atualizar(id, request);
        log.info("Área atualizada com sucesso. ID: {}", response.id());
        return ResponseEntity.ok(response);
    }

    /**
     * Remove uma área do sistema.
     * 
     * @param id ID da área a ser removida
     * @return 204 No Content se removida com sucesso
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar área",
        description = "Remove uma área do sistema baseada no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", 
            description = "Área removida com sucesso"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Área não encontrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da área a ser removida", required = true)
            @PathVariable Long id) {
        log.info("Removendo área com ID: {}", id);
        service.deletar(id);
        log.info("Área removida com sucesso. ID: {}", id);
        return ResponseEntity.noContent().build();
    }
} 