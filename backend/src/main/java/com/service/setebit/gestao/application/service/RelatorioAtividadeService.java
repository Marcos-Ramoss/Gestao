package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.domain.RecursoDomain;
import com.service.setebit.gestao.domain.RelatorioAtividadeDomain;
import com.service.setebit.gestao.domain.repository.RecursoRepository;
import com.service.setebit.gestao.domain.repository.RelatorioAtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static com.service.setebit.gestao.application.service.PdfExtratorUtil.MesEnum;

@Service
@RequiredArgsConstructor
public class RelatorioAtividadeService {
    private final RelatorioAtividadeRepository RelatorioAtividadeRepository;
    private final RecursoRepository recursoRepository;
    private static final String PASTA_TXT = "files";

    @Transactional
    public List<RelatorioAtividadeDomain> processarUpload(MultipartFile[] files) {
        List<RelatorioAtividadeDomain> resultado = new ArrayList<>();
        Arrays.stream(files).forEach( pdfFile -> resultado.add(processarUpload(pdfFile)));
        return resultado;
    }

    public RelatorioAtividadeDomain processarUpload(MultipartFile pdfFile) {
        try {
            // Garante que a pasta existe
            File pasta = new File(PASTA_TXT);
            if (!pasta.exists()) pasta.mkdirs();

            // Extrai informações do PDF e salva o .txt
            Map<String, String> info = PdfExtratorUtil.extrairInformacoes(pdfFile, PASTA_TXT);

            // Conversão do mês de String para Integer usando o enum
            Integer mesInt = MesEnum.fromString(info.get("mes"));

            RecursoDomain recursoDomain = recursoRepository.buscarRecursoPorNome(info.getOrDefault("colaborador", ""));

            // Monta o objeto de domínio manualmente (builder manual)
            RelatorioAtividadeDomain relatorio = RelatorioAtividadeDomain.builder()
                    .cliente(info.getOrDefault("cliente", ""))
                    .ano(parseIntSafe(info.get("ano")))
                    .mes(mesInt)
                    .recurso(recursoDomain)
                    .nomeProjeto(info.getOrDefault("nomeProjeto", ""))
                    .horaTotalProjeto(parseDoubleSafe(info.get("horaTotalProjeto")))
                    .build();

            // Persiste (mock)
            return RelatorioAtividadeRepository.salvar(relatorio);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar PDF", e);
        }
    }

    public List<RelatorioAtividadeDomain> listarTodos() {
        return RelatorioAtividadeRepository.listarTodos();
    }

    private Integer parseIntSafe(String valor) {
        try { return Integer.parseInt(valor.trim()); } catch (Exception e) { return null; }
    }
    private Double parseDoubleSafe(String valor) {
        try { return Double.parseDouble(valor.replace(",", ".").replaceAll("[^0-9.]", "").trim()); } catch (Exception e) { return null; }
    }
} 