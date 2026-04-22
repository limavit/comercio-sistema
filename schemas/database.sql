CREATE TABLE empresa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(18),
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE perfil (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    empresa_id BIGINT NOT NULL,
    perfil_id BIGINT,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(150),
    empresa_id BIGINT NOT NULL,

    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE fornecedor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    empresa_id BIGINT NOT NULL,

    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    empresa_id BIGINT NOT NULL,

    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE produto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    categoria_id BIGINT,
    fornecedor_id BIGINT,
    empresa_id BIGINT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id),
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE estoque (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    produto_id BIGINT NOT NULL,
    quantidade INT DEFAULT 0,
    empresa_id BIGINT NOT NULL,

    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE venda (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT,
    empresa_id BIGINT NOT NULL,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total DECIMAL(12,2),

    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE item_venda (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2),

    FOREIGN KEY (venda_id) REFERENCES venda(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
