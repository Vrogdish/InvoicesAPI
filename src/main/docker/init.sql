CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    reference VARCHAR(50),
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50)

);

INSERT INTO users (first_name,last_name,email) VALUES
('Cedric','Gache', 'gachecedric@gmail.com'),
('John','Doe','johndoe@exemple.com');


INSERT INTO products (name,reference,description,price,user_id) VALUES
('Pommes', 'pom', '1 kg', 3.2 , 1),
('Fraise', 'fra', 'Barquette de 500gr', 8, 1),
('Bananes', 'ban', '1 kg', 5, 2)