-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-01-2025 a las 16:18:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mi_base_datos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `Codigo_categoria` mediumint(9) NOT NULL,
  `NomCategoria` mediumtext NOT NULL,
  `DesCategoria` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`Codigo_categoria`, `NomCategoria`, `DesCategoria`) VALUES
(1, 'Autos', ''),
(2, 'Anime', ''),
(3, 'Uniforme', ''),
(4, 'Tecnologia', ''),
(5, 'Hogar', ''),
(6, 'Deportes', ''),
(7, 'Musica', ''),
(8, 'Arte', ''),
(9, 'Fotografia', ''),
(10, 'Viajes', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `Codigo_cliente` int(11) NOT NULL,
  `NomCliente` varchar(50) NOT NULL,
  `ApeCliente` varchar(50) NOT NULL,
  `Teléfono` smallint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `Codigo_empleado` smallint(6) NOT NULL,
  `NomEmpleado` varchar(50) NOT NULL,
  `ApeEmpleado` varchar(50) NOT NULL,
  `Nac` date NOT NULL,
  `Usuario_Emp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`Codigo_empleado`, `NomEmpleado`, `ApeEmpleado`, `Nac`, `Usuario_Emp`) VALUES
(1, 'Luis', 'Lopez', '0000-00-00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `ID_USER` int(11) NOT NULL,
  `Usuario` varchar(50) NOT NULL,
  `contrasena` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`ID_USER`, `Usuario`, `contrasena`) VALUES
(1, 'tera', '1234'),
(2, 'Leo', '2005');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pide`
--

CREATE TABLE `pide` (
  `Codigo_pedido` int(11) NOT NULL,
  `Costo_total` float(9,4) NOT NULL,
  `Cantidad` tinyint(9) NOT NULL,
  `Color` varchar(40) NOT NULL,
  `Fecha_entrega` date NOT NULL,
  `Fecha_pedido` date NOT NULL,
  `Detalles` longtext NOT NULL,
  `Cliente` int(11) NOT NULL,
  `Producto` int(11) NOT NULL,
  `Empleado` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `Codigo_producto` int(11) NOT NULL,
  `Costo_base` float(9,4) NOT NULL,
  `Tecnica` mediumtext NOT NULL,
  `Tamaño` char(1) NOT NULL,
  `Descripcion` longtext NOT NULL,
  `Categoria` mediumint(9) NOT NULL,
  `Tipo` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`Codigo_producto`, `Costo_base`, `Tecnica`, `Tamaño`, `Descripcion`, `Categoria`, `Tipo`) VALUES
(1, 180.0000, 'DTF', 'M', 'Volkswagen', 1, 1),
(2, 180.0000, 'DTF', 'G', 'mclaren', 1, 1),
(3, 200.0000, 'DTF', 'G', 'naruto', 2, 1),
(4, 250.0000, 'DTF', 'X', 'dragon ball', 2, 1),
(5, 160.0000, 'sublimacion', '1', 'demon slayer', 2, 1),
(6, 180.0000, 'sublimacion', 'M', 'uniforme udg', 3, 2),
(7, 120.0000, 'sublimacion', '1', 'taza dragon ball', 2, 3),
(8, 150.0000, 'bordado', 'U', 'gorra deportiva', 6, 4),
(9, 200.0000, 'sublimacion', 'U', 'bolsa reutilizable', 5, 5),
(10, 300.0000, 'sublimacion', 'A', 'poster de banda musical', 7, 6),
(11, 100.0000, 'grabado', 'U', 'llavero de guitarra', 7, 7),
(12, 250.0000, 'sublimacion', 'U', 'termómetro digital', 4, 8),
(13, 180.0000, 'sublimacion', '5', 'almohada decorativa', 5, 9),
(14, 500.0000, 'serigrafia', 'A', 'mochila para camping', 10, 10),
(15, 220.0000, 'grabado', 'U', 'marco de fotos', 9, 5),
(16, 450.0000, 'bordado', 'X', 'chaqueta deportiva', 6, 1),
(17, 80.0000, 'sublimacion', 'U', 'taza magica', 5, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `Codigo_tipo` tinyint(4) NOT NULL,
  `Tipo` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`Codigo_tipo`, `Tipo`) VALUES
(1, 'Playera'),
(2, 'Uniforme'),
(3, 'Taza'),
(4, 'Gorra'),
(5, 'Bolsa'),
(6, 'Poster'),
(7, 'Llavero'),
(8, 'Termo'),
(9, 'Almohada'),
(10, 'Mochila');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `user` varchar(30) NOT NULL,
  `contrasena` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `user`, `contrasena`) VALUES
(1, 'tera', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Codigo_categoria`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Codigo_cliente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`Codigo_empleado`),
  ADD KEY `Empleado_Usuario_Emp_Login_ID_USER` (`Usuario_Emp`);

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`ID_USER`);

--
-- Indices de la tabla `pide`
--
ALTER TABLE `pide`
  ADD PRIMARY KEY (`Codigo_pedido`),
  ADD KEY `Pide_Cliente_Cliente_Codigo_cliente` (`Cliente`),
  ADD KEY `Pide_Producto_Producto_Codigo_producto` (`Producto`),
  ADD KEY `Pide_Empleado_Empleado_Codigo_empleado` (`Empleado`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`Codigo_producto`),
  ADD KEY `Producto_Categoria_Categoria_Codigo_categoria` (`Categoria`),
  ADD KEY `Producto_Tipo_Tipo_Codigo_tipo` (`Tipo`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`Codigo_tipo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `Codigo_categoria` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Codigo_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `Codigo_empleado` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `ID_USER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pide`
--
ALTER TABLE `pide`
  MODIFY `Codigo_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `Codigo_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `Empleado_Usuario_Emp_Login_ID_USER` FOREIGN KEY (`Usuario_Emp`) REFERENCES `login` (`ID_USER`);

--
-- Filtros para la tabla `pide`
--
ALTER TABLE `pide`
  ADD CONSTRAINT `Pide_Cliente_Cliente_Codigo_cliente` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`Codigo_cliente`),
  ADD CONSTRAINT `Pide_Empleado_Empleado_Codigo_empleado` FOREIGN KEY (`Empleado`) REFERENCES `empleado` (`Codigo_empleado`),
  ADD CONSTRAINT `Pide_Producto_Producto_Codigo_producto` FOREIGN KEY (`Producto`) REFERENCES `producto` (`Codigo_producto`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `Producto_Categoria_Categoria_Codigo_categoria` FOREIGN KEY (`Categoria`) REFERENCES `categoria` (`Codigo_categoria`),
  ADD CONSTRAINT `Producto_Tipo_Tipo_Codigo_tipo` FOREIGN KEY (`Tipo`) REFERENCES `tipo` (`Codigo_tipo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
