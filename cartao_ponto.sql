-- 1. Cria e seleciona o banco
CREATE DATABASE IF NOT EXISTS cartao_ponto;
USE cartao_ponto;

-- 2. Tabela Departamento
CREATE TABLE departamento (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 3. Tabela Funcionário
CREATE TABLE funcionario (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    matricula        VARCHAR(20)  NOT NULL UNIQUE,
    nome_completo    VARCHAR(150) NOT NULL,
    departamento_id  INT          NOT NULL,
    CONSTRAINT fk_func_depto FOREIGN KEY (departamento_id)
        REFERENCES departamento(id)
);

-- 4. Tabela Registro de Ponto
CREATE TABLE registro_ponto (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    funcionario_id INT      NOT NULL,
    data_hora      DATETIME NOT NULL,
    CONSTRAINT fk_ponto_func FOREIGN KEY (funcionario_id)
        REFERENCES funcionario(id)
);

-- 5. Dados de exemplo (reproduz exatamente o relatório do enunciado)
INSERT INTO departamento (nome) VALUES ('Gestão de Pessoas');

INSERT INTO funcionario (matricula, nome_completo, departamento_id)
VALUES ('001', 'James Gosling', 1);

INSERT INTO registro_ponto (funcionario_id, data_hora) VALUES
(1, '2026-03-10 08:02:00'),
(1, '2026-03-10 12:01:00'),
(1, '2026-03-10 13:05:00'),
(1, '2026-03-10 17:58:00');

SELECT * FROM registro_ponto;