-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 25-06-2023 a las 01:25:47
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_trans`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

CREATE TABLE `camion` (
  `id_camion` int(11) NOT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `capacidad_kg` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`id_camion`, `modelo`, `capacidad_kg`) VALUES
(1, 'Nissan Condor', 5000.00),
(2, 'Mercedes-Benz Actros', 4500.00),
(3, 'Volvo FH16', 6000.00),
(4, 'Scania R730', 5500.00),
(5, 'MAN TGX', 4000.00),
(6, 'Renault T', 5200.00),
(7, 'Iveco Stralis', 4800.00),
(8, 'DAF XF', 5300.00),
(9, 'Kenworth T680', 4700.00),
(10, 'Peterbilt 579', 5100.00),
(11, 'Freightliner Cascadia', 4600.00),
(12, 'Isuzu NPR', 5800.00),
(13, 'Hino 300', 4400.00),
(14, 'Mitsubishi Fuso Canter', 5700.00),
(15, 'Hyundai HD', 4200.00),
(16, 'Ford Cargo', 5400.00),
(17, 'Chevrolet Kodiak', 4900.00),
(18, 'Iveco Daily', 5600.00),
(19, 'Dodge Ram', 4300.00),
(20, 'Toyota Dyna', 5200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electrodomestico`
--

CREATE TABLE `electrodomestico` (
  `id_electrodomestico` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `peso_kg` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `electrodomestico`
--

INSERT INTO `electrodomestico` (`id_electrodomestico`, `nombre`, `peso_kg`) VALUES
(1, 'Televisor', 10.50),
(2, 'Refrigerador', 75.20),
(3, 'Lavadora', 50.00),
(4, 'Horno eléctrico', 20.70),
(5, 'Licuadora', 2.30),
(6, 'Microondas', 12.00),
(7, 'Aspiradora', 8.90),
(8, 'Secadora de ropa', 15.60),
(9, 'Cafetera', 3.10),
(10, 'Ventilador', 4.50),
(11, 'Plancha', 2.80),
(12, 'Batidora', 4.20),
(13, 'Tostadora', 1.90),
(14, 'Exprimidor de jugo', 2.50),
(15, 'Asador eléctrico', 9.70),
(16, 'Cocina eléctrica', 18.30),
(17, 'Plancha de cabello', 1.00),
(18, 'Ventilador de techo', 7.20),
(19, 'Máquina de coser', 11.80),
(20, 'Radiograbadora', 6.40),
(21, 'Calefactor', 5.50),
(22, 'Equipo de sonido', 8.70),
(23, 'Robot aspirador', 3.80),
(24, 'Cámara de seguridad', 2.10),
(25, 'Proyector', 6.80),
(26, 'Licuadora de mano', 1.60),
(27, 'Cepillo eléctrico', 0.90),
(28, 'Batidora de mano', 1.50),
(29, 'Freidora eléctrica', 4.40),
(30, 'Exprimidor manual', 0.80),
(31, 'Máquina de hacer pan', 5.20),
(32, 'Hervidor de agua', 1.20),
(33, 'Tetera eléctrica', 1.80),
(34, 'Cafetera espresso', 4.60),
(35, 'Plancha de vapor', 2.20),
(36, 'Radiodespertador', 0.70),
(37, 'Reproductor de DVD', 2.90),
(38, 'Ventilador de mesa', 3.50),
(39, 'Calentador de agua', 6.10),
(40, 'Hervidor de arroz', 2.40),
(41, 'Cortadora de césped', 11.30),
(42, 'Tostadora de pan', 1.30),
(43, 'Molino de café', 1.70),
(44, 'Lavavajillas', 16.90),
(45, 'Batidora de pie', 4.80),
(46, 'Procesador de alimentos', 3.70),
(47, 'Cámara digital', 0.60),
(48, 'Plancha para el pelo', 1.40),
(49, 'Cámara deportiva', 0.50),
(50, 'Amplificador de audio', 9.50),
(51, 'Cepillo de dientes eléctrico', 0.40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `id_envio` int(11) NOT NULL,
  `id_camion` int(11) DEFAULT NULL,
  `fecha_envio` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `envio`
--

INSERT INTO `envio` (`id_envio`, `id_camion`, `fecha_envio`) VALUES
(1, 1, '2023-06-25'),
(2, 2, '2023-06-25'),
(3, 3, '2023-06-26'),
(4, 1, '2023-06-26'),
(5, 2, '2023-06-27'),
(6, 3, '2023-06-27'),
(7, 4, '2023-06-28'),
(8, 1, '2023-06-28'),
(9, 2, '2023-06-29'),
(10, 3, '2023-06-29'),
(11, 4, '2023-06-30'),
(12, 1, '2023-06-30'),
(13, 2, '2023-07-01'),
(14, 3, '2023-07-01'),
(15, 4, '2023-07-02'),
(16, 1, '2023-07-02'),
(17, 2, '2023-07-03'),
(18, 3, '2023-07-03'),
(19, 4, '2023-07-04'),
(20, 1, '2023-07-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio_electrodomestico`
--

CREATE TABLE `envio_electrodomestico` (
  `id_envio_electrodomestico` int(11) NOT NULL,
  `id_envio` int(11) DEFAULT NULL,
  `id_electrodomestico` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `envio_electrodomestico`
--

INSERT INTO `envio_electrodomestico` (`id_envio_electrodomestico`, `id_envio`, `id_electrodomestico`) VALUES
(1, 1, 3),
(2, 2, 6),
(3, 3, 2),
(4, 4, 4),
(5, 5, 5),
(6, 6, 9),
(7, 7, 11),
(8, 8, 14),
(9, 9, 16),
(10, 10, 19),
(11, 11, 21),
(12, 12, 23),
(13, 13, 25),
(14, 14, 29),
(15, 15, 31),
(16, 16, 33),
(17, 17, 35),
(18, 18, 38),
(19, 19, 40),
(20, 20, 42),
(21, 1, 1),
(22, 2, 7),
(23, 3, 12),
(24, 4, 15),
(25, 5, 17),
(26, 6, 20),
(27, 7, 24),
(28, 8, 26),
(29, 9, 27),
(30, 10, 30),
(31, 11, 32),
(32, 12, 36),
(33, 13, 37),
(34, 14, 39),
(35, 15, 43),
(36, 16, 44),
(37, 17, 45),
(38, 18, 47),
(39, 19, 48),
(40, 20, 50),
(41, 1, 8),
(42, 2, 10),
(43, 3, 13),
(44, 4, 18),
(45, 5, 22),
(46, 6, 28),
(47, 7, 34),
(48, 8, 41),
(49, 9, 46),
(50, 10, 49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `contrasena` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `correo`, `contrasena`, `nombre`) VALUES
(1, 'admin@admin.com', 'admin', 'Diego D Mamani'),
(2, 'usuario2@example.com', 'contraseña2', 'Usuario 2'),
(3, 'usuario3@example.com', 'contraseña3', 'Usuario 3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`id_camion`);

--
-- Indices de la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  ADD PRIMARY KEY (`id_electrodomestico`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`id_envio`),
  ADD KEY `id_camion` (`id_camion`);

--
-- Indices de la tabla `envio_electrodomestico`
--
ALTER TABLE `envio_electrodomestico`
  ADD PRIMARY KEY (`id_envio_electrodomestico`),
  ADD KEY `id_envio` (`id_envio`),
  ADD KEY `id_electrodomestico` (`id_electrodomestico`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `camion`
--
ALTER TABLE `camion`
  MODIFY `id_camion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  MODIFY `id_electrodomestico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `id_envio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `envio_electrodomestico`
--
ALTER TABLE `envio_electrodomestico`
  MODIFY `id_envio_electrodomestico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `envio_ibfk_1` FOREIGN KEY (`id_camion`) REFERENCES `camion` (`id_camion`);

--
-- Filtros para la tabla `envio_electrodomestico`
--
ALTER TABLE `envio_electrodomestico`
  ADD CONSTRAINT `envio_electrodomestico_ibfk_1` FOREIGN KEY (`id_envio`) REFERENCES `envio` (`id_envio`),
  ADD CONSTRAINT `envio_electrodomestico_ibfk_2` FOREIGN KEY (`id_electrodomestico`) REFERENCES `electrodomestico` (`id_electrodomestico`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
