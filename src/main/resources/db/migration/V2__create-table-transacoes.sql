CREATE TABLE transacoes(

    id BIGINT NOT NULL AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    operacao VARCHAR(10) NOT NULL,
    valor NUMERIC(20,2) NOT NULL,
    taxa_aplicada NUMERIC(20,2) NOT NULL,
    saldo_anterior NUMERIC(20,2) NOT NULL,
    novo_saldo NUMERIC(20,2) NOT NULL,
    data DATE NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);


