CREATE TABLE clientes(

    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    plano_exclusive BOOLEAN NOT NULL,
    saldo NUMERIC(20,2) DEFAULT 0,
    numero_conta VARCHAR(10) NOT NULL,
    data_nascimento DATE NOT NULL,

    PRIMARY KEY (id)
);

/*
    CHAT-GPT alert!
    O comando abaixo foi gerado pelo CHAT-GPT e tem
    como objetivo popular o banco com dados para testes.
*/
INSERT INTO clientes (nome, plano_exclusive, saldo, numero_conta, data_nascimento)
SELECT
        'Cliente' || LPAD(ROW_NUMBER() OVER (ORDER BY RANDOM()), 3, '0'),
        CASE WHEN ROUND(RANDOM()) = 1 THEN TRUE ELSE FALSE END,
        ROUND(RANDOM() * 100000, 2),
        CONCAT(
                LPAD(CAST(FLOOR(RAND() * 100000000) AS INTEGER), 8, '0'),
                '-',
                CAST(FLOOR(RAND() * 10) AS INTEGER)
            ),
        DATEADD('DAY', -FLOOR(RANDOM() * (DATEDIFF('DAY', '1940-01-01', '2005-01-01'))), '2005-01-01')
FROM
    SYSTEM_RANGE(1, 100);

