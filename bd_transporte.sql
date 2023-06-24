-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-06-2023 a las 21:07:11
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
-- Base de datos: `bd_transporte`
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
-- Estructura de tabla para la tabla `carga`
--

CREATE TABLE `carga` (
  `id_carga` int(11) NOT NULL,
  `id_camion` int(11) DEFAULT NULL,
  `id_electrodomestico` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carga`
--

INSERT INTO `carga` (`id_carga`, `id_camion`, `id_electrodomestico`, `fecha`) VALUES
(1, 1, 1, NULL),
(2, 2, 3, NULL),
(3, 1, 2, NULL),
(4, 3, 4, NULL),
(5, 2, 5, NULL),
(6, 3, 6, NULL),
(7, 4, 7, NULL),
(8, 1, 8, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `electrodomestico`
--

CREATE TABLE `electrodomestico` (
  `id_electrodomestico` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `peso_kg` decimal(10,2) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `electrodomestico`
--

INSERT INTO `electrodomestico` (`id_electrodomestico`, `nombre`, `peso_kg`, `tipo`) VALUES
(1, 'Televisor', 10.50, 'Electrodoméstico'),
(2, 'Refrigerador', 75.20, 'Electrodoméstico'),
(3, 'Lavadora', 50.00, 'Electrodoméstico'),
(4, 'Horno eléctrico', 20.70, 'Electrodoméstico'),
(5, 'Licuadora', 2.30, 'Electrodoméstico'),
(6, 'Microondas', 12.00, 'Electrodoméstico'),
(7, 'Aspiradora', 8.90, 'Electrodoméstico'),
(8, 'Secadora de ropa', 15.60, 'Electrodoméstico'),
(9, 'Cafetera', 3.10, 'Electrodoméstico'),
(10, 'Ventilador', 4.50, 'Electrodoméstico'),
(11, 'Plancha', 2.80, 'Electrodoméstico'),
(12, 'Batidora', 4.20, 'Electrodoméstico'),
(13, 'Tostadora', 1.90, 'Electrodoméstico'),
(14, 'Exprimidor de jugo', 2.50, 'Electrodoméstico'),
(15, 'Asador eléctrico', 9.70, 'Electrodoméstico'),
(16, 'Cocina eléctrica', 18.30, 'Electrodoméstico'),
(17, 'Plancha de cabello', 1.00, 'Electrodoméstico'),
(18, 'Ventilador de techo', 7.20, 'Electrodoméstico'),
(19, 'Máquina de coser', 11.80, 'Electrodoméstico'),
(20, 'Radiograbadora', 6.40, 'Electrodoméstico');

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
-- Indices de la tabla `carga`
--
ALTER TABLE `carga`
  ADD PRIMARY KEY (`id_carga`),
  ADD KEY `id_camion` (`id_camion`),
  ADD KEY `id_electrodomestico` (`id_electrodomestico`);

--
-- Indices de la tabla `electrodomestico`
--
ALTER TABLE `electrodomestico`
  ADD PRIMARY KEY (`id_electrodomestico`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carga`
--
ALTER TABLE `carga`
  ADD CONSTRAINT `carga_ibfk_1` FOREIGN KEY (`id_camion`) REFERENCES `camion` (`id_camion`),
  ADD CONSTRAINT `carga_ibfk_2` FOREIGN KEY (`id_electrodomestico`) REFERENCES `electrodomestico` (`id_electrodomestico`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
