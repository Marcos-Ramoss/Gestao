package com.breth.otimizando_relatorios.adapter.controller;

import com.breth.otimizando_relatorios.application.service.RelatorioAtividadeService;
import com.breth.otimizando_relatorios.domain.RelatorioAtividadeDomain;
import com.breth.otimizando_relatorios.infrastructure.entity.RelatorioAtividadeEntity;
import com.breth.otimizando_relatorios.infrastructure.repository.RelatorioAtividadeJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "relatorio-atividade-controller")
public class RelatorioAtividadeController {
    private final RelatorioAtividadeService service;
    private final RelatorioAtividadeJpaRepository jpaRepository;

    public RelatorioAtividadeController(RelatorioAtividadeService service, RelatorioAtividadeJpaRepository jpaRepository) {
        this.service = service;
        this.jpaRepository = jpaRepository;
    }

    @Operation(summary = "Upload de múltiplos relatórios em PDF")
    @PostMapping(value = "/upload/{ano}/{mes}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<RelatorioAtividadeDomain>> uploadRelatorios(
            @PathVariable Integer ano, @PathVariable Integer mes,
            @Parameter(description = "Arquivos PDF dos relatórios", required = true) @RequestPart("files") MultipartFile[] files) {
        List<RelatorioAtividadeDomain> relatorios = Arrays.stream(files)
                .map(service::processarUpload)
                .toList();
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping(value = "/{ano}/{mes}", produces = "application/json")
    @Operation(summary = "Lista todos os relatórios de atividade")
    public ResponseEntity<List<RelatorioAtividadeDomain>> listarTodos(
        @PathVariable Integer ano, @PathVariable Integer mes
    ) {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/exportar/{ano}/{mes}")
    public void exportarRelatorios(@PathVariable Integer ano, @PathVariable Integer mes, HttpServletResponse response) throws IOException {
        List<RelatorioAtividadeEntity> relatorios = jpaRepository.findByAnoAndMes(ano, mes);

        // Criação do Excel com POI
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Relatórios");

        // Cabeçalho
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Cliente");
        header.createCell(1).setCellValue("Ano");
        header.createCell(2).setCellValue("Mês");
        header.createCell(3).setCellValue("Colaborador");
        header.createCell(4).setCellValue("Projeto");
        header.createCell(5).setCellValue("Horas");

        // Dados
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

        // Configura a resposta HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=relatorios.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}