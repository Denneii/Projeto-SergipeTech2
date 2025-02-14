-- init.sql
CREATE TABLE IF NOT EXISTS veiculo (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    ano INTEGER NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    quantidade_portas INTEGER,
    tipo_combustivel VARCHAR(50),
    cilindrada INTEGER,
    tipo VARCHAR(20) NOT NULL
);
