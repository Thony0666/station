DROP  database station;
Create database station;
\c station
CREATE TABLE if not exists Station
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(250) NOT NULL,
    place VARCHAR(250) NOT NULL,
    max_volume_gasoline DECIMAL(10, 2),
    max_volume_diesel DECIMAL(10, 2),
    max_volume_petrol DECIMAL(10, 2)
);

CREATE TABLE if not exists product
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    price DECIMAL(10, 2),
    quantity DECIMAL(10, 2)
);

CREATE TABLE if not exists operation
(
    id             SERIAL PRIMARY KEY,
    id_station           INT,
    id_product           INT,
    type           VARCHAR(50),
    quantity       DECIMAL(10, 2),
    amounts DECIMAL(10, 2),
    date_operation timestamp,
    FOREIGN KEY (id_station ) REFERENCES Station (id),
    FOREIGN KEY (id_product) REFERENCES product (id)
);