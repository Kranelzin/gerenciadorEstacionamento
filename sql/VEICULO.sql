CREATE TABLE VEICULO(
  VEICULO_ID INT AUTO_INCREMENT,
  MODELO VARCHAR(30) UNIQUE,
  MARCA VARCHAR(30) NOT NULL,
  CONSTRAINT PK_VEICULO PRIMARY KEY (VEICULO_ID)
);