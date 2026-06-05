CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_sample (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL
);

CREATE TABLE tb_civil (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE tb_account (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO tb_sample (description, date, name, owner)
VALUES
('Amostra de solo marciano', '2026-06-01', 'Sample 1', 'NASA'),
('Fragmento lunar', '2026-06-02', 'Sample 2', 'ESA');

INSERT INTO tb_account (name, email, password, role)
VALUES
('Neil Armstrong', 'neil@space.com','$2a$10$7EqJtq98hPqEX7fNZaFWoOHi9vA6gM5N4n8r7D9xL1y2z3A4B5C6D', 'ASTRONAUT'),
('Buzz Aldrin', 'buzz@space.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi9vA6gM5N4n8r7D9xL1y2z3A4B5C6D', 'ASTRONAUT');


INSERT INTO tb_civil (name, email, password, role)
VALUES
('Fernando', 'fernando@email.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi9vA6gM5N4n8r7D9xL1y2z3A4B5C6D', 'CIVIL'),
('Maria Silva', 'maria@email.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi9vA6gM5N4n8r7D9xL1y2z3A4B5C6D', 'CIVIL');