DROP DATABASE IF EXISTS simple;

CREATE DATABASE IF NOT EXISTS simple;

USE simple;

DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
    id int UNSIGNED NOT NULL AUTO_INCREMENT,
    email varchar(255) DEFAULT NULL,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 10;

INSERT INTO
    user (id, email, name)
VALUES
    (1, 'uno@gmail.com', 'Uno'),
    (2, 'dos@gmail.com', 'Dos'),
    (3, 'other@gmail.com', 'Other'),
    (4, 'some@gmail.com', 'Some'),
    (5, 'first@gmail.com', 'First'),
    (6, 'primero@gmail.com', 'Primero'),
    (7, 'segundo@gmail.com', 'Segundo'),
    (8, 'tercero@gmail.com', 'Tercero'),
    (9, 'cuarto@gmail.com', 'Cuarto');