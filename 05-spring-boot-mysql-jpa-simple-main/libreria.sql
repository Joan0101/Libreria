DROP DATABASE IF EXISTS libreria;

CREATE DATABASE IF NOT EXISTS libreria;

USE libreria;

DROP TABLE IF EXISTS user;


CREATE TABLE IF NOT EXISTS user (
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    genero varchar (20) DEFAULT NULL,
    libro varchar(255) DEFAULT NULL,
    precio double DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 10;

INSERT INTO
    user (id, genero, libro, precio)
VALUES
    (1, 'Fantasia', ' El señor de los anillos ', 2200),
    (2, 'Fantasia', ' Harry Potter ',1800),
    (3,'Ciencia Ficcion','Farenheit 451', 2100),
    (4,'Aventura','Robinson Crusoe',1600),
    (5,'Aventura','Las Aventuras de Tom Sawyer',1300),
    (6,'Aventura','Viaje al Centro de La Tierra',1800),
    (7,'Drama','La Metamorfosis',2500),
    (8,'Drama','Estudio en Esmeralda',1900),
    (9,'Drama','Rebelion en la Granja',1500);