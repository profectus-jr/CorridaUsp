CREATE schema corridausp;
SET search_path TO corridausp;

ALTER SCHEMA corridausp OWNER TO mac439_grupo1_2014;

create DOMAIN TIPO_DECIMAL AS numeric(10,2);

CREATE DOMAIN DIASEMANA VARCHAR(10) CHECK (VALUE IN ('segunda','terca','quarta','quinta','sexta','sabado','domingo'));

/*
CREATE TABLE Usuario (
  id SERIAL PRIMARY KEY,
  nome varchar(100) not null,
  senha varchar(100) not null,
  email varchar(50) not null,
  num_telefone varchar(20)
);
ALTER TABLE Usuario OWNER TO mac439_grupo1_2014;
*/

CREATE TABLE Corredor (
  idCorredor SERIAL PRIMARY KEY,
  nome varchar(100) not null,
  senha varchar(100) not null,
  email varchar(50) not null UNIQUE,
  num_telefone varchar(20),
  data_nascimento date not null,
  peso TIPO_DECIMAL CHECK(peso > 0.00),
  altura TIPO_DECIMAL CHECK(altura > 0.00),
  sexo char(1) CHECK(sexo = 'M' or sexo = 'F'),
  atestado_medico boolean DEFAULT FALSE /*sera' uma breve explicacao da situacao medica do corredor*/
);
ALTER TABLE Corredor OWNER TO mac439_grupo1_2014;

CREATE TABLE Treinador(
   idTreinador SERIAL PRIMARY KEY,
  nome varchar(100) not null,
  senha varchar(100) not null,
  email varchar(50) not null UNIQUE,
  num_telefone varchar(20),
  curriculo text not null
);
ALTER TABLE Treinador OWNER TO mac439_grupo1_2014;

CREATE TABLE Treino (
  id SERIAL PRIMARY KEY,
  idTreinador int references Treinador(idTreinador) ON UPDATE CASCADE ON DELETE CASCADE,
  descricao VARCHAR(150) NOT NULL,
  situacao VARCHAR(20) NOT NULL,
  vagas INT NOT NULL,
  numVagas int not null,
  data_inicio DATE,
  data_fim DATE
);
ALTER TABLE Treino  OWNER TO mac439_grupo1_2014;

/*Relacao inscreve*/
/*exemplo de utilizacao do campo data: corredores que avaliaram seus professores e possuem data-termino - data < K, onde K
e' uma constante, nao serao avaliacoes validas*/
CREATE TABLE TreinoCorredor(
  idTreino int references Treino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  idCorredor int references Corredor(idCorredor) ON UPDATE CASCADE ON DELETE CASCADE,
  data date not null,
  data_termino date, /*data da inscricao*/
  situacao_inscricao varchar(20) not null,
  nota int,
  comentario text,
  PRIMARY KEY (idTreino, idCorredor)
);
ALTER TABLE TreinoCorredor OWNER TO mac439_grupo1_2014;

-- CREATE INDEX data_vencimento ON TreinoCorredor (data_termino);

CREATE TABLE SessaoTreino(
  id SERIAL PRIMARY KEY,
  id_treino int references Treino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  dia_semana DIASEMANA not null,
  hora time not null,
  duracao TIPO_DECIMAL CHECK(duracao > 0.00)
);
ALTER TABLE SessaoTreino OWNER TO mac439_grupo1_2014;

/*Relacao participa*/
CREATE TABLE CorredorSessao(
  idSessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  idCorredor int references Corredor(idCorredor) ON UPDATE CASCADE ON DELETE CASCADE,
  tempo_gasto time not null,
  distancia_percorrida TIPO_DECIMAL not null,
  PRIMARY KEY (idSessao, idCorredor)
);
ALTER TABLE CorredorSessao OWNER TO mac439_grupo1_2014;

CREATE TABLE Lesao(
  id SERIAL PRIMARY KEY,
  idCorredor int references Corredor(idCorredor) ON UPDATE CASCADE ON DELETE CASCADE,
  idSessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  descricao text
);
ALTER TABLE Lesao OWNER TO mac439_grupo1_2014;

CREATE TABLE Ponto(
  id SERIAL PRIMARY KEY,
  altitude TIPO_DECIMAL,
  rua varchar(100) not null,
  numero varchar(10) not null
);
ALTER TABLE Ponto OWNER TO mac439_grupo1_2014;

-- CREATE INDEX ponto ON Ponto(id);

CREATE TABLE Corrida(
  id SERIAL PRIMARY KEY,
  nome varchar(100) not null,
  idSessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  descricao text
);
ALTER TABLE Corrida OWNER TO mac439_grupo1_2014;

-- CREATE INDEX sessoes_corrida ON Corrida (id_sessao);

--Trecho
CREATE TABLE Trecho(
  pontoInicial int references Ponto(id) ON UPDATE CASCADE ON DELETE CASCADE,
  pontoFinal int references Ponto(id) ON UPDATE CASCADE ON DELETE CASCADE,
  PRIMARY KEY (pontoInicial, pontoFinal)
);
ALTER TABLE Trecho OWNER TO mac439_grupo1_2014;

--TrechoCorrida
CREATE TABLE TrechoCorrida(
  	idCorrida int references Corrida(id) ON UPDATE CASCADE ON DELETE CASCADE,
  	pontoInicial int not NULL,
  	pontoFinal int not NULL,
  	trechoInicial boolean default false,
  	trechoFinal boolean default false,
 	FOREIGN KEY (pontoInicial, pontoFinal) references Trecho(pontoInicial, pontoFinal) ON UPDATE CASCADE ON DELETE CASCADE,
  	PRIMARY KEY (pontoInicial, pontoFinal, idCorrida)
);
ALTER TABLE TrechoCorrida OWNER TO mac439_grupo1_2014;

--TrechoCorredor
CREATE TABLE TrechoCorredor(
  idCorredor int references Corredor(idCorredor) ON UPDATE CASCADE,
  pontoInicial int not NULL,
  pontoFinal int not NULL,
  nota int CHECK(nota >= 0.0 or nota <= 10.0),
  data date, /*data em que a avaliacao foi feita*/
  comentario varchar(150),
  FOREIGN KEY (pontoInicial, pontoFinal) references Trecho(pontoInicial, pontoFinal) ON UPDATE CASCADE ON DELETE CASCADE,
  PRIMARY KEY (pontoInicial, pontoFinal, idCorredor)
);
ALTER TABLE TrechoCorredor OWNER TO mac439_grupo1_2014;

-- CREATE INDEX avaliacaoPorNota ON TrechoCorredor (nota);

-- População do banco de dados

--Usuario
--Treinador
--INSERT INTO Usuario(nome,senha,email,num_telefone) VALUES ('treinador1','treinador1','treinador1@mail','123456');
INSERT INTO Treinador(nome,senha,email,num_telefone,curriculo) VALUES ('treinador1','0ae48bf7e75f2efbeba64ed53559f4','treinador1@mail','123456','Treinador de corrida');
--Corredor
--INSERT INTO Usuario(nome,senha,email,num_telefone) VALUES ('corredor1','corredor1','corredor1@mail','789123');
INSERT INTO Corredor(nome,senha,email,num_telefone, data_nascimento, peso, altura, sexo) VALUES ('corredor1','d59911db401c76f7deabd71e1036d61','corredor1@mail','789123','09/13/1995',65,1.70,'M');

--Treino
INSERT INTO Treino(idTreinador, descricao, situacao, vagas, numVagas, data_inicio, data_fim) VALUES (1,'Principiantes','incricoes',10,10,'12/05/2014','12/20/2014');

--Sessao
INSERT INTO SessaoTreino(id_treino, dia_semana, hora, duracao) VALUES (1,'segunda',now(),2.0);  
INSERT INTO SessaoTreino(id_treino, dia_semana, hora, duracao) VALUES (1,'quarta',now(),3.0);
INSERT INTO SessaoTreino(id_treino, dia_semana, hora, duracao) VALUES (1,'sexta',now(),2.0);

--Corrida
INSERT INTO Corrida(nome, idSessao, descricao) VALUES ('Corrida 1',1,'Corrida de 5 km');
INSERT INTO Corrida(nome, idSessao, descricao) VALUES ('Corrida 2',2,'Corrida de 10 km');
INSERT INTO Corrida(nome, idSessao, descricao) VALUES ('Corrida 3',3,'Corrida de 15 km');

-- Pontos
INSERT INTO Ponto (altitude,rua,numero) VALUES (10.0,'K. Rua do Matão','1');
INSERT INTO Ponto (altitude,rua,numero) VALUES (15.0,'M. Av. Escola Politécnica','2');
INSERT INTO Ponto (altitude,rua,numero) VALUES (20.0,'N. Av. Marginal Pinheiros','3');
INSERT INTO Ponto (altitude,rua,numero) VALUES (10.0,'O. Rua Alvarenga','4');
INSERT INTO Ponto (altitude,rua,numero) VALUES (5.0,'P. Rua Francisco dos Santos','5');

-- Trechos
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (1,2);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (2,3);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (3,4);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (4,5);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (5,1);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (4,1);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (1,3);
INSERT INTO Trecho(pontoInicial, pontoFinal) VALUES (3,5);

--TrechoCorrida
--Corrida1
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (1,2,1,true,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (2,3,1,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (3,4,1,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (4,5,1,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (5,1,1,false,true);
--Corrida2
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (3,4,2,true,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (4,1,2,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (1,2,2,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (2,3,2,false,true);
--Corrida3
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (1,3,3,true,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (3,5,3,false,false);
INSERT INTO TrechoCorrida(pontoInicial, pontoFinal, idCorrida, trechoInicial, trechoFinal) VALUES (5,1,3,false,true);

--TrechoCorredor
INSERT INTO TrechoCorredor(pontoInicial, pontoFinal, idCorredor, nota, data, comentario) VALUES (1,2,1,6.0,'12/05/2014','Ok');
INSERT INTO TrechoCorredor(pontoInicial, pontoFinal, idCorredor, nota, data, comentario) VALUES (2,3,1,5.0,'09/13/2014','Aceitável');
INSERT INTO TrechoCorredor(pontoInicial, pontoFinal, idCorredor, nota, data, comentario) VALUES (3,4,1,6.0,'10/20/2014','Ok');
INSERT INTO TrechoCorredor(pontoInicial, pontoFinal, idCorredor, nota, data, comentario) VALUES (4,5,1,4.5,'05/09/2014','Muitos buracos');
INSERT INTO TrechoCorredor(pontoInicial, pontoFinal, idCorredor, nota, data, comentario) VALUES (5,1,1,7.0,'04/29/2014','Ok');



---garante que um treino nao tera mais alunos do que o numero de vagas
CREATE OR REPLACE FUNCTION novas_Vagas_disponiveis()
RETURNS TRIGGER AS $$
BEGIN
IF ((SELECT vagas FROM Treino WHERE id = NEW.idtreino) > 0)
THEN
   UPDATE Treino SET vagas = vagas - 1 WHERE id=NEW.idtreino;
   RETURN NEW;
END IF;
RAISE EXCEPTION 'Vagas esgotadas!!';
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Atualiza_vagas_disponiveis
BEFORE INSERT ON TreinoCorredor
FOR EACH ROW
EXECUTE PROCEDURE novas_Vagas_disponiveis();

