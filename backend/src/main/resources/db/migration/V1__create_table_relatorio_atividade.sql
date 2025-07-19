CREATE TABLE relatorio_atividade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    mes INT NOT NULL,
    colaborador VARCHAR(100) NOT NULL,
    nome_projeto VARCHAR(100) NOT NULL,
    hora_total_projeto FLOAT NOT NULL
); 