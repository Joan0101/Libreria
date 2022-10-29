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
    (1, 'Novela', ' El señor de los anillos ', 2200),
    (2, 'Novela', ' Harry Potter ',1800);