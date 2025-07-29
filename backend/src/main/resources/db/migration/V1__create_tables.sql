-- 1. Crie a tabela contrato primeiro
CREATE TABLE `contrato` (
  `codigo_contrato` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 2. Agora crie as demais tabelas que referenciam contrato
-- Exemplo:
CREATE TABLE `area` (
  `id_area` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `codigo_contrato` varchar(100) NOT NULL,
  PRIMARY KEY (`id_area`),
  KEY `idx_codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `fk_area_contrato` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.feriado definição
CREATE TABLE `feriado` (
  `data` date NOT NULL,
  PRIMARY KEY (`data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.usuario definição
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.contrato_detalhe definição
CREATE TABLE `contrato_detalhe` (
  `id_cont_det` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `preposto` varchar(255) DEFAULT NULL,
  `fiscal` varchar(255) DEFAULT NULL,
  `gestor` varchar(255) DEFAULT NULL,
  `objeto` varchar(255) DEFAULT NULL,
  `processo_sei` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cont_det`),
  KEY `idx_codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `fk_contrato_detalhe_contrato` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.faturamento definição
CREATE TABLE `faturamento` (
  `id_faturamento` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `objetivo` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `numero_medicao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_faturamento`),
  KEY `idx_codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `fk_faturamento_contrato` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.faturamento_detalhe definição
CREATE TABLE `faturamento_detalhe` (
  `id_faturamento_detalhe` bigint NOT NULL AUTO_INCREMENT,
  `id_faturamento` bigint DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_faturamento_detalhe`),
  KEY `idx_id_faturamento` (`id_faturamento`),
  CONSTRAINT `fk_faturamento_detalhe_faturamento` FOREIGN KEY (`id_faturamento`) REFERENCES `faturamento` (`id_faturamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.ordem_servico definição
CREATE TABLE `ordem_servico` (
  `id_ordem_servico` bigint NOT NULL AUTO_INCREMENT,
  `id_area` bigint NOT NULL,
  `numero_os` varchar(20) NOT NULL,
  PRIMARY KEY (`id_ordem_servico`),
  KEY `idx_codigo_contrato` (`id_area`),
  CONSTRAINT `fk_ordem_servico_area` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.recurso definição
CREATE TABLE `recurso` (
  `id_recurso` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `fator_ajuste` bigint DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `idx_codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `fk_recurso_contrato` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.relatorio_atividade definição
CREATE TABLE `relatorio_atividade` (
  `id_rel_ativ` bigint NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) NOT NULL,
  `ano` int NOT NULL,
  `mes` int NOT NULL,
  `id_recurso` bigint NOT NULL,
  `nome_projeto` varchar(100) NOT NULL,
  `hora_total_projeto` float NOT NULL,
  PRIMARY KEY (`id_rel_ativ`),
  KEY `idx_id_recurso` (`id_recurso`),
  CONSTRAINT `fk_relatorio_atividade_recurso` FOREIGN KEY (`id_recurso`) REFERENCES `recurso` (`id_recurso`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;