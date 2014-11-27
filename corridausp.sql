CREATE schema corridausp;
SET search_path TO corridausp; 

ALTER SCHEMA corridausp OWNER TO mac439_grupo1_2014;

create domain TIPO_DECIMAL AS numeric(10,2);

CREATE TABLE Corredor (
  id SERIAL PRIMARY KEY,  
  nome varchar(100) not null,
  senha varchar(20) not null,
  email varchar(50) not null,
  data_nascimento date not null,
  peso TIPO_DECIMAL CHECK(peso > 0.00),
  altura TIPO_DECIMAL CHECK(altura > 0.00),
  sexo char(1) CHECK(sexo = 'M' or sexo = 'F'),
  atestado_medico boolean DEFAULT FALSE /*sera' uma breve explicacao da situacao medica do corredor*/
);

ALTER TABLE Corredor OWNER TO mac439_grupo1_2014;

CREATE TABLE TelCorredor(
  id_corredor int references Corredor(id) ON UPDATE CASCADE ON DELETE CASCADE,
  telefone varchar(20),
  PRIMARY KEY (id_corredor, telefone)
);

ALTER TABLE TelCorredor OWNER TO mac439_grupo1_2014;

CREATE TABLE Treinador(
  id SERIAL PRIMARY KEY  ,  
  nome varchar(100) not null,
  senha varchar(20) not null,
  email varchar(50) not null,
  curriculo text not null
);

ALTER TABLE Treinador OWNER TO mac439_grupo1_2014;

CREATE TABLE TelTreinador(
  id_treinador int references Treinador(id) ON UPDATE CASCADE ON DELETE CASCADE,
  telefone varchar(20),
  PRIMARY KEY (id_treinador, telefone)
);

ALTER TABLE TelTreinador OWNER TO mac439_grupo1_2014;

CREATE TABLE Treino (
  id SERIAL PRIMARY KEY,
  id_treinador int references Treinador(id) ON UPDATE CASCADE ON DELETE CASCADE,
  descricao varchar(150) not null,
  situacao varchar (20) not null,
  vaga_maxima int not null,
  vaga_minima int default 1
);

ALTER TABLE Treino  OWNER TO mac439_grupo1_2014;

/*Relacao inscreve*/
/*exemplo de utilizacao do campo data: corredores que avaliaram seus professores e possuem data-termino - data < K, onde K
e' uma constante, nao serao avaliacoes validas*/
CREATE TABLE TreinoCorredor(
  id_treino int references Treino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  id_corredor int references Corredor(id) ON UPDATE CASCADE ON DELETE CASCADE,
  data date not null,
  data_termino date not null, /*data da inscricao*/
  situacao_inscricao varchar(20) not null,
  nota int,
  comentario text,

  PRIMARY KEY (id_treino, id_corredor)
);

ALTER TABLE TreinoCorredor OWNER TO mac439_grupo1_2014;

CREATE INDEX data_vencimento ON TreinoCorredor (data_termino);

CREATE TABLE SessaoTreino(
  id SERIAL PRIMARY KEY,
  id_treino int references Treino(id) ON UPDATE CASCADE ON DELETE CASCADE, 
  hora time not null,
  data date not null
);

ALTER TABLE SessaoTreino OWNER TO mac439_grupo1_2014;

CREATE INDEX sessoesTreinos ON SessaoTreino (data);

/*Relacao participa*/
CREATE TABLE CorredorSessao(
  id_sessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  id_corredor int references Corredor(id) ON UPDATE CASCADE ON DELETE CASCADE,
  tempo_gasto time not null,
  distancia_percorrida TIPO_DECIMAL not null,
  PRIMARY KEY (id_sessao, id_corredor)
);

ALTER TABLE CorredorSessao OWNER TO mac439_grupo1_2014;

CREATE TABLE Lesao(
  id SERIAL PRIMARY KEY,
  id_corredor int references Corredor(id) ON UPDATE CASCADE ON DELETE CASCADE,
  id_sessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,
  descricao text
);

ALTER TABLE Lesao OWNER TO mac439_grupo1_2014;

CREATE TABLE PontoUsp(
  id SERIAL PRIMARY KEY,
  altitude TIPO_DECIMAL,
  rua varchar(100) not null,
  numero varchar(10) not null
);

ALTER TABLE PontoUsp OWNER TO mac439_grupo1_2014;

CREATE INDEX pontosusp ON PontoUsp(id);

CREATE TABLE Corrida(
  id SERIAL PRIMARY KEY,
  nome varchar(100) not null,
  id_sessao int references SessaoTreino(id) ON UPDATE CASCADE ON DELETE CASCADE,

  descricao text
);

ALTER TABLE Corrida OWNER TO mac439_grupo1_2014;

CREATE INDEX sessoes_corrida ON Corrida (id_sessao);

/*Relacao compoem*/
CREATE TABLE TrechoCorrida(
  id_corrida int references Corrida(id) ON UPDATE CASCADE ON DELETE CASCADE,
  pontoUm int references PontoUsp(id) ON UPDATE CASCADE ON DELETE CASCADE,
  pontoDois int references PontoUsp(id) ON UPDATE CASCADE ON DELETE CASCADE,
  PRIMARY KEY (pontoUm, pontoDois, id_corrida)  

);

ALTER TABLE TrechoCorrida OWNER TO mac439_grupo1_2014;

/*Relacao avalia*/
CREATE TABLE TrechoCorredor(
  id_corredor int references Corredor(id) ON UPDATE CASCADE,
  pontoUm int not NULL,
  pontoDois int not NULL,
  id_corrida int not NULL,
  FOREIGN KEY (pontoUm, pontoDois, id_corrida) references TrechoCorrida(pontoUm, pontoDois,id_corrida) ON UPDATE CASCADE ON DELETE CASCADE,
  nota int,
  data date, /*data em que a avaliacao foi feita*/
  comentario varchar(150)
);

ALTER TABLE TrechoCorredor OWNER TO mac439_grupo1_2014;

CREATE INDEX avaliacaoPorNota ON TrechoCorredor (nota);

/*um treino tem o numero maximo de vagas em vagas disponiveis no momento de sua criação*/
CREATE OR REPLACE FUNCTION update_Vagas_disponiveis()
RETURNS TRIGGER AS $$
BEGIN 
UPDATE Treino SET vaga_disponivel = vaga_maxima WHERE id=NEW.id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Vagas_disponiveis
AFTER INSERT ON Treino
FOR EACH ROW
EXECUTE PROCEDURE update_Vagas_disponiveis();


/*garante que um treino nao tera mais alunos do que o numero de vagas*/
CREATE OR REPLACE FUNCTION novas_Vagas_disponiveis()
RETURNS TRIGGER AS $$
BEGIN
IF ((SELECT vaga_disponivel FROM Treino WHERE id = NEW.id_treino) > 0) 
THEN 
   UPDATE Treino SET vaga_disponivel = vaga_disponivel - 1 WHERE id=NEW.id_treino;
   RETURN NEW;
END IF;
RAISE EXCEPTION 'Vagas esgotadas!!';
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Atualiza_vagas_disponiveis
BEFORE INSERT ON TreinoCorredor
FOR EACH ROW
EXECUTE PROCEDURE novas_Vagas_disponiveis();
