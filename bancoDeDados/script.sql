drop database IF EXISTS compravendaimoveis;
create database compravendaimoveis;
use compravendaimoveis;


CREATE TABLE cidade (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    nome VARCHAR(100) NOT NULL,
    uf VARCHAR(3) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    email VARCHAR(35) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL,
    papel VARCHAR(12),
    PRIMARY KEY (id)
);

CREATE TABLE imobiliaria (
    cnpj BIGINT NOT NULL UNIQUE,
	user_email VARCHAR(35) NOT NULL,
    nome VARCHAR(256) NOT NULL,
	descricao VARCHAR(256) NOT NULL,
	FOREIGN KEY (user_email)
        REFERENCES usuario (email),
    PRIMARY KEY (cnpj)
);

CREATE TABLE imovel (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
	imobiliaria_cnpj BIGINT NOT NULL,
    descricao VARCHAR(256) NOT NULL,
	valor FLOAT NOT NULL,
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade_id BIGINT NOT NULL,
    FOREIGN KEY (cidade_id)
        REFERENCES cidade (id),
	FOREIGN KEY (imobiliaria_cnpj)
        REFERENCES imobiliaria (cnpj),
    PRIMARY KEY (id)
);

CREATE TABLE foto (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    url VARCHAR(256) NOT NULL,
	imovel_id BIGINT NOT NULL,
	FOREIGN KEY (imovel_id)
        REFERENCES imovel (id),
    PRIMARY KEY (id)
);

CREATE TABLE cliente (
    cpf BIGINT NOT NULL UNIQUE,
	user_email VARCHAR(35) NOT NULL,
	nome VARCHAR(256) NOT NULL,
	sexo VARCHAR(10) NOT NULL,
    nascimento DATE NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    FOREIGN KEY (user_email)
        REFERENCES usuario (email),
    PRIMARY KEY (cpf)
);

CREATE TABLE proposta (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
	cliente_cpf BIGINT NOT NULL,
	imovel_id BIGINT NOT NULL,
	valor FLOAT NOT NULL,
	pagamento VARCHAR(256) NOT NULL,
	status VARCHAR(11) NOT NULL,
    dataemissao DATE NOT NULL,
    FOREIGN KEY (cliente_cpf)
        REFERENCES cliente (cpf),
    FOREIGN KEY (imovel_id)
        REFERENCES imovel (id),
    PRIMARY KEY (id) 
);



