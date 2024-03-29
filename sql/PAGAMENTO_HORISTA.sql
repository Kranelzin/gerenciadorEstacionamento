CREATE TABLE PAGAMENTO_HORISTA(
	PAGAMENTO_HORISTA_ID INT AUTO_INCREMENT,
  EMPRESA_ID INT,
  CODIGO VARCHAR(20),
  VAGA INT,
	PAGAMENTO_ID INT,
	CONSTRAINT PK_PAGAMENTO_HORISTA PRIMARY KEY (PAGAMENTO_HORISTA_ID),
  CONSTRAINT FK_PAGAMENTO_HORISTA_BOX_VAGA FOREIGN KEY (EMPRESA_ID, CODIGO, VAGA) REFERENCES BOX_VAGA(EMPRESA_ID, CODIGO, VAGA),
	CONSTRAINT FK_PAGAMENTO_HORISTA_PAGAMENTO FOREIGN KEY (PAGAMENTO_ID) REFERENCES PAGAMENTO(PAGAMENTO_ID)
);