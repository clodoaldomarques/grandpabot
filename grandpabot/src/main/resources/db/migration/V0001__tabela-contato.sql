CREATE TABLE Contato(
	id int NOT NULL,
	telefone varchar(20) NOT NULL,
	primeiro_nome varchar(100) NULL,
	ultimo_nome varchar(100) NULL,
	bot_do_cadastro varchar(50) NULL,
	CONSTRAINT PK_Contato PRIMARY KEY (id)
)