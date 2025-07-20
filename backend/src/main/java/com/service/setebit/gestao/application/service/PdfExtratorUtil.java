package com.service.setebit.gestao.application.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class PdfExtratorUtil {
    public static Map<String, String> extrairInformacoes(MultipartFile pdfFile, String pastaDestino) throws IOException {
        // Salva o PDF temporariamente
        File tempPdf = File.createTempFile("temp", ".pdf");
        pdfFile.transferTo(tempPdf);

        // Extrai o texto do PDF
        String textoExtraido;
        try (PDDocument document = PDDocument.load(tempPdf)) {
            PDFTextStripper stripper = new PDFTextStripper();
            textoExtraido = stripper.getText(document);
        }

        // Salva o texto extraído em um arquivo .txt
        String nomeTxt = pdfFile.getOriginalFilename().replace(".pdf", ".txt");
        File txtFile = new File(pastaDestino, nomeTxt);
        try (FileOutputStream fos = new FileOutputStream(txtFile)) {
            fos.write(textoExtraido.getBytes());
        }

        // Extrai informações específicas do texto conforme o layout do documento fornecido
        Map<String, String> info = new HashMap<>();
        info.put("caminhoArquivoTxt", txtFile.getAbsolutePath());
        info.putAll(extrairCamposRelatorio(textoExtraido));
        tempPdf.delete();
        return info;
    }

    private static Map<String, String> extrairCamposRelatorio(String texto) {
        Map<String, String> campos = new HashMap<>();
        // Regex para capturar Cliente, Ano e Mês na mesma linha
        // Exemplo: Cliente: Ano:PRODAM-SP 2025 Mês: Maio
        String regex = "Cliente:\\s*Ano:([\\w\\- ]+)\\s+(\\d{4})\\s+Mês:\\s*([\\wçÇ]+)";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(texto);
        if (matcher.find()) {
            campos.put("cliente", matcher.group(1).trim());
            campos.put("ano", matcher.group(2).trim());
            campos.put("mes", matcher.group(3).trim());
        } else {
            campos.put("cliente", "");
            campos.put("ano", "");
            campos.put("mes", "");
        }
        campos.put("colaborador", extrairCampo(texto, "Colaborador:", "\n"));
        campos.put("nomeProjeto", extrairCampo(texto, "Projeto:", "No Projeto:"));
        campos.put("horaTotalProjeto", extrairCampo(texto, "No Projeto:", "h").replace(":", "").trim());
        return campos;
    }

    /**
     * Extrai o valor entre dois delimitadores. Se o delimitador final não for encontrado, pega até o fim do texto.
     */
    private static String extrairCampo(String texto, String inicio, String fim) {
        int idxInicio = texto.indexOf(inicio);
        if (idxInicio == -1) return "";
        idxInicio += inicio.length();
        int idxFim = texto.indexOf(fim, idxInicio);
        if (idxFim == -1) idxFim = texto.length();
        return texto.substring(idxInicio, idxFim).replaceAll("[\n\r]", "").trim();
    }

    // Enum para meses em português
    public enum MesEnum {
        JANEIRO(1, "janeiro"),
        FEVEREIRO(2, "fevereiro"),
        MARCO(3, "marco", "março"),
        ABRIL(4, "abril"),
        MAIO(5, "maio"),
        JUNHO(6, "junho"),
        JULHO(7, "julho"),
        AGOSTO(8, "agosto"),
        SETEMBRO(9, "setembro"),
        OUTUBRO(10, "outubro"),
        NOVEMBRO(11, "novembro"),
        DEZEMBRO(12, "dezembro");

        private final int numero;
        private final String[] aliases;

        MesEnum(int numero, String... aliases) {
            this.numero = numero;
            this.aliases = aliases;
        }
        public int getNumero() { return numero; }
        public String[] getAliases() { return aliases; }

        private static final Map<String, MesEnum> LOOKUP = new HashMap<>();
        static {
            for (MesEnum mes : values()) {
                for (String alias : mes.aliases) {
                    LOOKUP.put(alias.toLowerCase(Locale.ROOT), mes);
                }
            }
        }
        public static Integer fromString(String mes) {
            if (mes == null) return null;
            MesEnum m = LOOKUP.get(mes.trim().toLowerCase(Locale.ROOT));
            return m != null ? m.getNumero() : null;
        }
    }

    // Exemplo de uso na extração:
    // Integer mesInt = MesEnum.fromString(campos.get("mes"));
}