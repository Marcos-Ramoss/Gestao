-- gestao.area definição

CREATE TABLE `area` (
  `id_area` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id_area`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.feriado definição

CREATE TABLE `feriado` (
  `id_feriado` bigint NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  PRIMARY KEY (`id_feriado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- gestao.relatorio_atividade definição

CREATE TABLE `relatorio_atividade` (
  `id_rel_ativ` bigint NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) NOT NULL,
  `ano` int NOT NULL,
  `mes` int NOT NULL,
  `colaborador` varchar(100) NOT NULL,
  `nome_projeto` varchar(100) NOT NULL,
  `hora_total_projeto` float NOT NULL,
  PRIMARY KEY (`id_rel_ativ`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.usuario definição

CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.contrato definição

CREATE TABLE `contrato` (
  `codigo_contrato` varchar(100) NOT NULL,
  `id_area` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo_contrato`),
  KEY `id_area` (`id_area`),
  CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.contrato_detalhe definição

CREATE TABLE `contrato_detalhe` (
  `id_cont_det` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `PREPOSTO` varchar(255) DEFAULT NULL,
  `FISCAL` varchar(255) DEFAULT NULL,
  `GESTOR` varchar(255) DEFAULT NULL,
  `OBJETO` varchar(255) DEFAULT NULL,
  `PROCESSO_SEI` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cont_det`),
  KEY `codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `contrato_detalhe_ibfk_1` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.faturamento definição

CREATE TABLE `faturamento` (
  `id_faturamento` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `OBJETIVO` varchar(255) DEFAULT NULL,
  `VALOR` decimal(19,2) DEFAULT NULL,
  `NUMERO_MEDICAO` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_faturamento`),
  KEY `codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `faturamento_ibfk_1` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.faturamento_detalhe definição

CREATE TABLE `faturamento_detalhe` (
  `id_faturamento_detalhe` bigint NOT NULL AUTO_INCREMENT,
  `id_faturamento` bigint DEFAULT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_faturamento_detalhe`),
  KEY `id_faturamento` (`id_faturamento`),
  CONSTRAINT `faturamento_detalhe_ibfk_1` FOREIGN KEY (`id_faturamento`) REFERENCES `faturamento` (`id_faturamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.ordem_servico definição

CREATE TABLE `ordem_servico` (
  `id_ordem_servico` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(20) DEFAULT NULL,
  `NUMERO_OS` varchar(20) NOT NULL,
  PRIMARY KEY (`id_ordem_servico`),
  KEY `codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `ordem_servico_ibfk_1` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- gestao.recurso definição

CREATE TABLE `recurso` (
  `id_recurso` bigint NOT NULL AUTO_INCREMENT,
  `codigo_contrato` varchar(100) DEFAULT NULL,
  `NOME` varchar(255) NOT NULL,
  `FATOR_AJUSTE` bigint DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `codigo_contrato` (`codigo_contrato`),
  CONSTRAINT `recurso_ibfk_1` FOREIGN KEY (`codigo_contrato`) REFERENCES `contrato` (`codigo_contrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;