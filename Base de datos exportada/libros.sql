-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-11-2022 a las 21:32:59
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `libreria`
--

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `genero`, `libro`, `precio`) VALUES
(1, 'Fantasia', ' El señor de los anillos ', 2200),
(2, 'Fantasia', ' Harry Potter ', 1800),
(3, 'Ciencia Ficcion', 'Farenheit 451', 2100),
(4, 'Aventura', 'Robinson Crusoe', 1600),
(5, 'Aventura', 'Las Aventuras de Tom Sawyer', 1300),
(6, 'Aventura', 'Viaje al Centro de La Tierra', 1800),
(7, 'Drama', 'La Metamorfosis', 2500),
(8, 'Drama', 'Estudio en Escarlata', 1900),
(9, 'Drama', 'Rebelion en la Granja', 1500);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
