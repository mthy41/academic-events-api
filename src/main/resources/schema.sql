CREATE TABLE IF NOT EXISTS Administrador(
	CPF VARCHAR(11),
	Nome VARCHAR(100) NOT NULL,
	Foto TEXT,
	Email VARCHAR(100) UNIQUE,
	Senha VARCHAR(250),
	Telefone VARCHAR(20),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),
	Role Varchar(30) NOT NULL,

	PRIMARY KEY(CPF)
);

CREATE TABLE IF NOT EXISTS Evento(
	Codigo VARCHAR(36),
	Nome VARCHAR(100) UNIQUE,
	Descricao TEXT,
	Banner TEXT,
	Miniatura TEXT,
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

CREATE TABLE IF NOT EXISTS MiniCurso(
	Codigo VARCHAR(36),
	Codigo_Evento VARCHAR(36),
	Miniatura TEXT,
	Titulo VARCHAR(100) UNIQUE NOT NULL,
	Descricao TEXT,
	DataInicio DATE,
	DataFim DATE,
	Banner TEXT,
	Status BOOLEAN,
	Vagas INT,

	PRIMARY KEY(Codigo),
	FOREIGN KEY(Codigo_Evento) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Administra_Minicurso(
	CPF_Admin VARCHAR(11),
	Codigo_MC VARCHAR(36),

	PRIMARY KEY(CPF_Admin, Codigo_MC),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Administra_Evento(
	CPF_Admin VARCHAR(11),
	Codigo_Ev VARCHAR(36),

	PRIMARY KEY(CPF_Admin, Codigo_Ev),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Professor(
	CPF VARCHAR(11),
	Foto TEXT,
	Nome VARCHAR(100) NOT NULL,
	Email VARCHAR(100) UNIQUE,
	Senha VARCHAR(250),
	Telefone VARCHAR(20) NOT NULL,
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),
	Role VARCHAR(30) NOT NULL,

	PRIMARY KEY(CPF)
);

CREATE TABLE IF NOT EXISTS Ministra_Minicurso(
	Codigo_MC VARCHAR(36),
	CPF_Prof VARCHAR(11),

	PRIMARY KEY(Codigo_MC, CPF_Prof),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
);

CREATE TABLE IF NOT EXISTS Participante(
	CPF VARCHAR(11),
	Nome VARCHAR(100) NOT NULL,
	Foto TEXT,
	Telefone VARCHAR(20) NOT NULL,
	Email VARCHAR(100) UNIQUE,
	Senha VARCHAR(250),
	Rua VARCHAR(100),
	Numero VARCHAR(20),
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),
	Role VARCHAR(30) NOT NULL,

	PRIMARY KEY(CPF)
);

CREATE TABLE IF NOT EXISTS Participa_MC(
	Codigo_Ev VARCHAR(36),
	CPF_Participante VARCHAR(11),
	Codigo_MC VARCHAR(36),
	
	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Palestra(
	Codigo VARCHAR(36),
	Codigo_Ev VARCHAR (36),
	Tema VARCHAR(100) UNIQUE,
	Data DATE,
	Hora TIME,
	
	PRIMARY KEY(Codigo),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Participa_Palestra(
	Codigo_Ev VARCHAR(36),
	CPF_Participante VARCHAR(11),
	Codigo_Palestra VARCHAR(36),
	
	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_Palestra),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
);

CREATE TABLE IF NOT EXISTS Chekin_Palestra(
	Codigo_Ev VARCHAR(36),
	CPF_Participante VARCHAR(11),
	Codigo_Palestra VARCHAR(36),
	Data_Chekin DATE,
	
	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_Palestra, Data_Chekin),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
);

CREATE TABLE IF NOT EXISTS Chekin_MC(
	Codigo_Ev VARCHAR(36),
	CPF_Participante VARCHAR(11),CREATE TABLE IF NOT EXISTS Administrador(
                                 	CPF VARCHAR(11),
                                 	Nome VARCHAR(100) NOT NULL,
                                 	Foto TEXT,
                                 	Email VARCHAR(100) UNIQUE,
                                 	Senha VARCHAR(250),
                                 	Telefone VARCHAR(20),
                                 	Rua VARCHAR(100),
                                 	Numero VARCHAR(20),
                                 	Bairro VARCHAR(100),
                                 	Cidade VARCHAR(100),
                                 	Estado VARCHAR(100),
                                 	Role Varchar(30) NOT NULL,

                                 	PRIMARY KEY(CPF)
                                 );

                                 CREATE TABLE IF NOT EXISTS Evento(
                                 	Codigo VARCHAR(36),
                                 	Nome VARCHAR(100) UNIQUE,
                                 	Descricao TEXT,
                                 	Banner TEXT,
                                 	Miniatura TEXT,
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

                                 CREATE TABLE IF NOT EXISTS MiniCurso(
                                 	Codigo VARCHAR(36),
                                 	Codigo_Evento VARCHAR(36),
                                 	Miniatura TEXT,
                                 	Titulo VARCHAR(100) UNIQUE NOT NULL,
                                 	Descricao TEXT,
                                 	DataInicio DATE,
                                 	DataFim DATE,
                                 	Banner TEXT,
                                 	Status BOOLEAN,
                                 	Vagas INT,

                                 	PRIMARY KEY(Codigo),
                                 	FOREIGN KEY(Codigo_Evento) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );


                                 CREATE TABLE IF NOT EXISTS Administra_Minicurso(
                                 	CPF_Admin VARCHAR(11),
                                 	Codigo_MC VARCHAR(36),

                                 	PRIMARY KEY(CPF_Admin, Codigo_MC),
                                 	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
                                 	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );

                                 CREATE TABLE IF NOT EXISTS Administra_Evento(
                                 	CPF_Admin VARCHAR(11),
                                 	Codigo_Ev VARCHAR(36),

                                 	PRIMARY KEY(CPF_Admin, Codigo_Ev),
                                 	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );

                                 CREATE TABLE IF NOT EXISTS Professor(
                                 	CPF VARCHAR(11),
                                 	Foto TEXT,
                                 	Nome VARCHAR(100) NOT NULL,
                                 	Email VARCHAR(100) UNIQUE,
                                 	Senha VARCHAR(250),
                                 	Telefone VARCHAR(20) NOT NULL,
                                 	Rua VARCHAR(100),
                                 	Numero VARCHAR(20),
                                 	Bairro VARCHAR(100),
                                 	Cidade VARCHAR(100),
                                 	Estado VARCHAR(100),
                                 	Role VARCHAR(30) NOT NULL,

                                 	PRIMARY KEY(CPF)
                                 );

                                 CREATE TABLE IF NOT EXISTS Ministra_Minicurso(
                                 	Codigo_MC VARCHAR(36),
                                 	CPF_Prof VARCHAR(11),

                                 	PRIMARY KEY(Codigo_MC, CPF_Prof),
                                 	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
                                 	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
                                 );

                                 CREATE TABLE IF NOT EXISTS Participante(
                                 	CPF VARCHAR(11),
                                 	Nome VARCHAR(100) NOT NULL,
                                 	Foto TEXT,
                                 	Telefone VARCHAR(20) NOT NULL,
                                 	Email VARCHAR(100) UNIQUE,
                                 	Senha VARCHAR(250),
                                 	Rua VARCHAR(100),
                                 	Numero VARCHAR(20),
                                 	Bairro VARCHAR(100),
                                 	Cidade VARCHAR(100),
                                 	Estado VARCHAR(100),
                                 	Role VARCHAR(30) NOT NULL,

                                 	PRIMARY KEY(CPF)
                                 );

                                 CREATE TABLE IF NOT EXISTS Participa_MC(
                                 	Codigo_Ev VARCHAR(36),
                                 	CPF_Participante VARCHAR(11),
                                 	Codigo_MC VARCHAR(36),

                                 	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
                                 	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
                                 	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );

                                 CREATE TABLE IF NOT EXISTS Palestra(
                                 	Codigo VARCHAR(36),
                                 	Codigo_Ev VARCHAR (36),
                                 	Tema VARCHAR(100) UNIQUE,
                                 	Data DATE,
                                 	Hora TIME,

                                 	PRIMARY KEY(Codigo),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );

                                 CREATE TABLE IF NOT EXISTS Participa_Palestra(
                                 	Codigo_Ev VARCHAR(36),
                                 	CPF_Participante VARCHAR(11),
                                 	Codigo_Palestra VARCHAR(36),

                                 	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_Palestra),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
                                 	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
                                 	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
                                 );

                                 CREATE TABLE IF NOT EXISTS Chekin_Palestra(
                                 	Codigo_Ev VARCHAR(36),
                                 	CPF_Participante VARCHAR(11),
                                 	Codigo_Palestra VARCHAR(36),
                                 	Data_Chekin DATE,

                                 	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_Palestra, Data_Chekin),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
                                 	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
                                 	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
                                 );

                                 CREATE TABLE IF NOT EXISTS Chekin_MC(
                                 	Codigo_Ev VARCHAR(36),
                                 	CPF_Participante VARCHAR(11),
                                 	Codigo_MC VARCHAR(36),
                                 	Data_Chekin DATE,

                                 	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC, Data_Chekin),
                                 	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
                                 	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
                                 	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );

                                 CREATE TABLE IF NOT EXISTS Palestrantes(
                                 	Palestrante VARCHAR(100) UNIQUE,
                                 	Codigo_Palestra VARCHAR(36),

                                 	PRIMARY KEY(Palestrante, Codigo_Palestra),
                                 	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
                                 );

                                 CREATE TABLE IF NOT EXISTS Certificado(
                                 	Numero VARCHAR(20),
                                 	Codigo_MC VARCHAR(36),
                                 	CPF_Participante VARCHAR(11),
                                 	Nome VARCHAR(100) NOT NULL,
                                 	CargaHoraria TIME NOT NULL,

                                 	PRIMARY KEY(Numero, Codigo_MC),
                                 	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
                                 	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
                                 );
	Codigo_MC VARCHAR(36),
	Data_Chekin DATE,
	
	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC, Data_Chekin),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Palestrantes(
	Palestrante VARCHAR(100) UNIQUE,
	Codigo_Palestra VARCHAR(36),
	
	PRIMARY KEY(Palestrante, Codigo_Palestra),
	FOREIGN KEY(Codigo_Palestra) REFERENCES Palestra(Codigo)
);

CREATE TABLE IF NOT EXISTS Certificado(
	Numero VARCHAR(20),
	Codigo_MC VARCHAR(36),
	CPF_Participante VARCHAR(11),
	Nome VARCHAR(100) NOT NULL,
	CargaHoraria TIME NOT NULL,

	PRIMARY KEY(Numero, Codigo_MC),
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo) ON DELETE CASCADE ON UPDATE CASCADE
);