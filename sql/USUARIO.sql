CREATE TABLE USUARIO(
  USUARIO_ID INT AUTO_INCREMENT,
  TIPO TINYINT COMMENT'0 = CLIENTE; 1 = FUNCIONARIO; 2 = ADMIN'  DEFAULT 0 CHECK(TIPO IN(0,1,2)),
  NOME VARCHAR(50) NOT NULL,
  CPF_CNPJ VARCHAR(14) NOT NULL,
  EMPRESA_ID INT NOT NULL,
  CONSTRAINT PK_USUARIO PRIMARY KEY (USUARIO_ID)
);
