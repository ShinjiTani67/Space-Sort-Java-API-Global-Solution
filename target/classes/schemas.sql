CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_sample (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL
);

CREATE TABLE tb_user (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO tb_sample (description, date, name, owner)
VALUES
('Amostra de solo marciano', '2026-06-01', 'Sample 1', 'NASA'),
('Fragmento lunar', '2026-06-02', 'Sample 2', 'ESA');

INSERT INTO tb_user (name, email, password, role)
VALUES
('Neil Armstrong', 'neil@space.com', '12345', 'ROLE_ASTRONAUT'),
('Buzz Aldrin', 'buzz@space.com', '12345', 'ROLE_ASTRONAUT'),
('Fernando', 'fernando@email.com', '12345', 'ROLE_CIVIL'),
('Maria Silva', 'maria@email.com', '12345', 'ROLE_CIVIL');
