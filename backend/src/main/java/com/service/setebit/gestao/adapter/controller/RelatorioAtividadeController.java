package com.service.setebit.gestao.adapter.controller;

import com.service.setebit.gestao.application.service.RelatorioAtividadeService;
import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;
import com.service.setebit.gestao.infrastructure.entity.RelatorioAtividadeEntity;
import com.service.setebit.gestao.infrastructure.repository.RelatorioAtividadeJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Controller responsável pela gestão de relatórios de atividade.
 */
@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatórios de Atividade", description = "API para upload, listagem e exportação de relatórios de atividade")
@RequiredArgsConstructor
@Slf4j
public class RelatorioAtividadeController {
    private final RelatorioAtividadeService service;
    private final RelatorioAtividadeJpaRepository jpaRepository;

    /**
     * Realiza upload de múltiplos relatórios em PDF.
     */
    @Operation(summary = "Upload de múltiplos relatórios em PDF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Upload realizado com sucesso", content = @Content(schema = @Schema(implementation = RelatorioAtividadeDomain.class)))
    })
    @PostMapping(value = "/upload/{ano}/{mes}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<RelatorioAtividadeDomain>> uploadRelatorios(
            @Parameter(description = "Ano dos relatórios", required = true) @PathVariable Integer ano,
            @Parameter(description = "Mês dos relatórios", required = true) @PathVariable Integer mes,
            @Parameter(description = "Arquivos PDF dos relatórios", required = true) @RequestPart("files") MultipartFile[] files) {
        log.info("Upload de {} relatórios para ano={}, mes={}", files.length, ano, mes);
        List<RelatorioAtividadeDomain> relatorios = Arrays.stream(files)
                .map(service::processarUpload)
                .toList();
        log.info("Upload realizado com sucesso");
        return ResponseEntity.ok(relatorios);
    }

    /**
     * Lista todos os relatórios de atividade para um ano e mês.
     */
    @GetMapping(value = "/{ano}/{mes}", produces = "application/json")
    @Operation(summary = "Lista todos os relatórios de atividade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de relatórios retornada com sucesso", content = @Content(schema = @Schema(implementation = RelatorioAtividadeDomain.class)))
    })
    public ResponseEntity<List<RelatorioAtividadeDomain>> listarTodos(
        @Parameter(description = "Ano dos relatórios", required = true) @PathVariable Integer ano,
        @Parameter(description = "Mês dos relatórios", required = true) @PathVariable Integer mes
    ) {
        log.info("Listando relatórios para ano={}, mes={}", ano, mes);
        return ResponseEntity.ok(service.listarTodos());
    }

    /**
     * Exporta relatórios de atividade para Excel.
     */
    @GetMapping("/exportar/{ano}/{mes}")
    @Operation(summary = "Exportar relatórios de atividade para Excel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Arquivo Excel gerado com sucesso", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro ao gerar o arquivo", content = @Content)
    })
    public void exportarRelatorios(
            @Parameter(description = "Ano dos relatórios", required = true) @PathVariable Integer ano,
            @Parameter(description = "Mês dos relatórios", required = true) @PathVariable Integer mes,
            HttpServletResponse response) throws IOException {
        log.info("Exportando relatórios para ano={}, mes={}", ano, mes);
        List<RelatorioAtividadeEntity> relatorios = jpaRepository.findByAnoAndMes(ano, mes);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Relatórios");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Cliente");
        header.createCell(1).setCellValue("Ano");
        header.createCell(2).setCellValue("Mês");
        header.createCell(3).setCellValue("Colaborador");
        header.createCell(4).setCellValue("Projeto");
        header.createCell(5).setCellValue("Horas");

        int rowIdx = 1;
        for (RelatorioAtividadeEntity rel : relatorios) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(rel.getCliente());
            row.createCell(1).setCellValue(rel.getAno());
            row.createCell(2).setCellValue(rel.getMes());
            row.createCell(3).setCellValue(rel.getColaborador());
            row.createCell(4).setCellValue(rel.getNomeProjeto());
            row.createCell(5).setCellValue(rel.getHoraTotalProjeto() != null ? rel.getHoraTotalProjeto() : 0);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorios.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
        log.info("Exportação concluída com sucesso");
    }
}