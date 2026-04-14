-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-12-2025 a las 22:35:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tasksphere`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `costos`
--

CREATE TABLE `costos` (
  `id_costo` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `costo_real` decimal(12,2) NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `costos`
--

INSERT INTO `costos` (`id_costo`, `id_proyecto`, `costo_real`, `observaciones`) VALUES
(1, 1, 5000.00, 'Primer pago de anticipo'),
(2, 1, 3200.50, 'Compra de equipos'),
(3, 2, 1200.00, 'Pago de plantilla'),
(4, 3, 4500.00, 'Desarrollo inicial del módulo base');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `id_proyecto` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `presupuesto_inicial` decimal(12,2) NOT NULL,
  `id_responsable` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`id_proyecto`, `nombre`, `descripcion`, `fecha_inicio`, `fecha_fin`, `presupuesto_inicial`, `id_responsable`) VALUES
(1, 'Sistema de compras', 'Desarrollo completo del sistema.', '2025-03-15', '2025-12-20', 25000.00, 1),
(2, 'Página Web Corporativa', 'Rediseño total de la página de la empresa.', '2025-05-01', '2025-12-10', 8000.00, 3),
(3, 'Aplicación Móvil', 'App para menejo de dinero', '2025-06-10', '2025-12-15', 15000.00, 2),
(5, 'PruebaIntento', 'Prueba', '2025-12-07', '2025-12-10', 2500.00, 4),
(6, 'PruebaSegundoIntento', 'PruebaSegundoIntento', '2025-12-11', '2025-12-15', 3500.00, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recordatorios`
--

CREATE TABLE `recordatorios` (
  `id_recordatorio` int(11) NOT NULL,
  `id_tarea` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recordatorios`
--

INSERT INTO `recordatorios` (`id_recordatorio`, `id_tarea`, `id_usuario`, `fecha_hora`) VALUES
(1, 1, 2, '2024-01-25 08:30:00'),
(2, 2, 1, '2024-02-01 09:00:00'),
(3, 3, 3, '2024-02-10 10:00:00'),
(4, 4, 2, '2024-03-20 14:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes_progreso`
--

CREATE TABLE `reportes_progreso` (
  `id_reporte` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `fecha_reporte` date NOT NULL,
  `porcentaje_avance` decimal(5,2) NOT NULL CHECK (`porcentaje_avance` between 0 and 100),
  `estado_actual` enum('Pendiente','En Progreso','Completado') NOT NULL,
  `comentario_admin` text DEFAULT NULL,
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reportes_progreso`
--

INSERT INTO `reportes_progreso` (`id_reporte`, `id_proyecto`, `fecha_reporte`, `porcentaje_avance`, `estado_actual`, `comentario_admin`, `id_admin`) VALUES
(1, 1, '2025-03-20', 10.00, 'En Progreso', 'Se completó el levantamiento de requerimientos y diagrama general del flujo de compras.', 1),
(2, 1, '2025-04-10', 35.00, 'Pendiente', 'Módulos iniciales del flujo de proveedores y órdenes en desarrollo.', 3),
(3, 2, '2025-05-10', 15.00, 'Completado', 'Diseño inicial y estructura de navegación completados.', 3),
(4, 2, '2025-06-01', 40.00, 'En Progreso', 'Se implementó la plantilla principal y parte del frontend.', 5),
(5, 3, '2025-06-15', 12.00, 'En Progreso', 'Mockups aprobados y estructura base de la app creada.', 1),
(6, 3, '2025-07-05', 32.50, 'En Progreso', 'Pantallas principales ya navegan; API en desarrollo.', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `riesgos`
--

CREATE TABLE `riesgos` (
  `id_riesgo` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `probabilidad` enum('Baja','Media','Alta') NOT NULL,
  `impacto` enum('Bajo','Medio','Alto') NOT NULL,
  `id_proyecto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `riesgos`
--

INSERT INTO `riesgos` (`id_riesgo`, `descripcion`, `probabilidad`, `impacto`, `id_proyecto`) VALUES
(1, 'Demora por falta de recursos.', 'Alta', 'Alto', 1),
(2, 'Problemas con hosting externo.', 'Media', 'Medio', 2),
(3, 'Cambio de requerimientos del cliente.', 'Alta', 'Alto', 3),
(4, 'Ausencia del responsable principal.', 'Baja', 'Alto', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `id_tarea` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` enum('Pendiente','En progreso','Completada','Atrasada') DEFAULT 'Pendiente',
  `fecha_inicio` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `id_proyecto` int(11) NOT NULL,
  `id_responsable` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id_tarea`, `nombre`, `descripcion`, `estado`, `fecha_inicio`, `fecha_vencimiento`, `comentario`, `id_proyecto`, `id_responsable`) VALUES
(1, 'Diseño UI', 'Crear prototipos de interfaz.', 'Pendiente', '2025-03-20', '2025-12-15', '', 1, 2),
(2, 'Base de datos inicial', 'Definir modelo y relaciones.', 'Pendiente', '2025-03-20', '2025-12-10', 'Prueba', 1, 1),
(3, 'Desarrollo web', 'Construcción HTML/CSS.', 'Completada', '2025-05-05', '2025-12-08', '', 2, 3),
(4, 'Desarrollo módulo ahorro', 'Programar módulo principal.', 'Pendiente', '2025-06-12', '2025-12-10', '', 3, 4),
(5, 'Pruebas funcionales', 'Probar funcionalidades completas.', 'Completada', '2025-05-03', '2025-12-12', '', 2, 5),
(9, 'Aja', 'Aja', 'Atrasada', '2025-12-10', '2025-12-15', NULL, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `rol` enum('Administrador','Colaborador') NOT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre`, `usuario`, `password`, `rol`, `estado`) VALUES
(1, 'Luna Delgado', 'Luna', 'l123', 'Administrador', 1),
(2, 'Carlos Delgado', 'Carlos', '2025', 'Colaborador', 1),
(3, 'Josue Montero', 'Josue', '1234', 'Administrador', 1),
(4, 'Jose Zeledon', 'Jose', 'abcd', 'Administrador', 1),
(5, 'Isaac Alfaro', 'Isaac', 'isaac12', 'Administrador', 1),
(6, 'Karen Ramírez', 'Karen', 'pass1', 'Colaborador', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `costos`
--
ALTER TABLE `costos`
  ADD PRIMARY KEY (`id_costo`),
  ADD KEY `id_proyecto` (`id_proyecto`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`id_proyecto`),
  ADD KEY `id_responsable` (`id_responsable`);

--
-- Indices de la tabla `recordatorios`
--
ALTER TABLE `recordatorios`
  ADD PRIMARY KEY (`id_recordatorio`),
  ADD KEY `id_tarea` (`id_tarea`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `reportes_progreso`
--
ALTER TABLE `reportes_progreso`
  ADD PRIMARY KEY (`id_reporte`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indices de la tabla `riesgos`
--
ALTER TABLE `riesgos`
  ADD PRIMARY KEY (`id_riesgo`),
  ADD KEY `id_proyecto` (`id_proyecto`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`id_tarea`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_responsable` (`id_responsable`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `costos`
--
ALTER TABLE `costos`
  MODIFY `id_costo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id_proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `recordatorios`
--
ALTER TABLE `recordatorios`
  MODIFY `id_recordatorio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reportes_progreso`
--
ALTER TABLE `reportes_progreso`
  MODIFY `id_reporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `riesgos`
--
ALTER TABLE `riesgos`
  MODIFY `id_riesgo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `id_tarea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `costos`
--
ALTER TABLE `costos`
  ADD CONSTRAINT `costos_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD CONSTRAINT `proyectos_ibfk_1` FOREIGN KEY (`id_responsable`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `recordatorios`
--
ALTER TABLE `recordatorios`
  ADD CONSTRAINT `recordatorios_ibfk_1` FOREIGN KEY (`id_tarea`) REFERENCES `tareas` (`id_tarea`),
  ADD CONSTRAINT `recordatorios_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `reportes_progreso`
--
ALTER TABLE `reportes_progreso`
  ADD CONSTRAINT `reportes_progreso_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`),
  ADD CONSTRAINT `reportes_progreso_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `riesgos`
--
ALTER TABLE `riesgos`
  ADD CONSTRAINT `riesgos_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`);

--
-- Filtros para la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD CONSTRAINT `tareas_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`),
  ADD CONSTRAINT `tareas_ibfk_2` FOREIGN KEY (`id_responsable`) REFERENCES `usuarios` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
