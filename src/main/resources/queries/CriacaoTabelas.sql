	CPF VARCHAR(11),
	Nome VARCHAR(100),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(CPF)
);

CREATE TABLE TelefoneAdmin(
	CPF_Admin VARCHAR(11),
	Telefone VARCHAR(20),
	PRIMARY KEY(CPF_Admin, Telefone),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF)
);

CREATE TABLE EmailAdmin(
	CPF_Admin VARCHAR(11),
	Email VARCHAR(100),
	PRIMARY KEY(CPF_Admin, Email),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF)
);

CREATE TABLE Evento(
	Codigo VARCHAR(10),
	Codigo_ListaPresenca VARCHAR(10),
	DataInicio DATE,
	DataFim DATE,
	Instituicao VARCHAR(100),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(Codigo)
);

CREATE TABLE MiniCurso(
	Codigo VARCHAR(10),
	Codigo_ListaPresenca VARCHAR(10),
	Titulo VARCHAR(100),
	Descricao TEXT,
	DataInicio DATE,
	DataFim DATE,
	Status BOOLEAN,
	vagas INT,

	PRIMARY KEY(Codigo)
);

CREATE TABLE Administra_Ev_MC(
	CPF_Admin VARCHAR(11),
	Codigo_Ev VARCHAR(10),
	Codigo_MC VARCHAR(10),

	PRIMARY KEY(CPF_Admin, Codigo_Ev, Codigo_MC),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo)
);

CREATE TABLE Professor(
	CPF VARCHAR(11),
	Nome VARCHAR(100),
	Email VARCHAR(100),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(CPF)
);

CREATE TABLE TelefoneProf(
	CPF_Prof VARCHAR(11),
	Telefone VARCHAR(20),

	PRIMARY KEY(CPF_Prof, Telefone),
	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
);

CREATE TABLE Ministra_MC_Prof(
	Codigo_MC VARCHAR(10),
	CPF_Prof VARCHAR(11),

	PRIMARY KEY(Codigo_MC, CPF_Prof),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo),
	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
);

CREATE TABLE Participante(
	CPF VARCHAR(11),
	Codigo_ListaPresenca VARCHAR(10),
	Nome VARCHAR(100),
	Telefone VARCHAR(20),
	Email VARCHAR(100),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(CPF)
);

CREATE TABLE ListaPresenca(
	Codigo VARCHAR(10),

	PRIMARY KEY(Codigo)
);

ALTER TABLE Evento
ADD CONSTRAINT FK_Evento FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

ALTER TABLE MiniCurso
ADD CONSTRAINT FK_MiniCurso FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

ALTER TABLE Participante
ADD CONSTRAINT FK_Participante FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

CREATE TABLE Participa(
	Codigo_Ev VARCHAR(10),
	CPF_Participante VARCHAR(11),
	Codigo_MC VARCHAR(10),

	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo),
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo)
);

CREATE TABLE Certificado(
	Numero VARCHAR(20),
	Codigo_MC VARCHAR(10),
	CPF_Participante VARCHAR(11),
	Nome VARCHAR(100),
	CargaHoraria TIME,

	PRIMARY KEY(Numero),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo),
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF)
);
