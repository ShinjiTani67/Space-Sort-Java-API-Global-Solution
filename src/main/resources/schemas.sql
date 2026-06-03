CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_sample (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL
);

CREATE TABLE tb_account (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE tb_civil (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO tb_sample (description, date, name, owner)
VALUES
('Amostra de solo marciano', '2026-06-01', 'Sample 1', 'NASA'),
('Fragmento lunar', '2026-06-02', 'Sample 2', 'ESA');

INSERT INTO tb_account (name, email)
VALUES
('Neil Armstrong', 'neil@space.com'),
('Buzz Aldrin', 'buzz@space.com');

INSERT INTO tb_civil (name, email)
VALUES
('Fernando Tanigushi', 'fernando@email.com'),
('Maria Silva', 'maria@email.com');