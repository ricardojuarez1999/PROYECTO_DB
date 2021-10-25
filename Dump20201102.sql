-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectobd1
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia` (
  `id_asistencia` int NOT NULL AUTO_INCREMENT,
  `hora_entrada` time NOT NULL,
  `hora_salida` time DEFAULT NULL,
  `fecha` date NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_asistencia`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carro`
--

DROP TABLE IF EXISTS `carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carro` (
  `id_carro` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(250) NOT NULL,
  `modelo` varchar(250) NOT NULL,
  `linea` varchar(250) NOT NULL,
  PRIMARY KEY (`id_carro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
/*!40000 ALTER TABLE `carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_equipo`
--

DROP TABLE IF EXISTS `detalle_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_equipo` (
  `id_detalle_equipo` int NOT NULL AUTO_INCREMENT,
  `id_equipo` int NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_detalle_equipo`),
  KEY `id_equipo` (`id_equipo`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `detalle_equipo_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_equipo_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_equipo`
--

LOCK TABLES `detalle_equipo` WRITE;
/*!40000 ALTER TABLE `detalle_equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura_compra`
--

DROP TABLE IF EXISTS `detalle_factura_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_factura_compra` (
  `id_detalle_factura_compra` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `id_factura_compra` int NOT NULL,
  `id_pieza` int NOT NULL,
  PRIMARY KEY (`id_detalle_factura_compra`),
  KEY `id_factura_compra` (`id_factura_compra`),
  KEY `id_pieza` (`id_pieza`),
  CONSTRAINT `detalle_factura_compra_ibfk_1` FOREIGN KEY (`id_factura_compra`) REFERENCES `factura_compra` (`id_factura_compra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_factura_compra_ibfk_2` FOREIGN KEY (`id_pieza`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura_compra`
--

LOCK TABLES `detalle_factura_compra` WRITE;
/*!40000 ALTER TABLE `detalle_factura_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura_extraordinaria`
--

DROP TABLE IF EXISTS `detalle_factura_extraordinaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_factura_extraordinaria` (
  `id_detalle_factura_extraordinaria` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `descripcion` varchar(400) NOT NULL,
  `id_factura_general` int NOT NULL,
  PRIMARY KEY (`id_detalle_factura_extraordinaria`),
  KEY `id_factura_general` (`id_factura_general`),
  CONSTRAINT `detalle_factura_extraordinaria_ibfk_1` FOREIGN KEY (`id_factura_general`) REFERENCES `factura_general` (`id_factura_general`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura_extraordinaria`
--

LOCK TABLES `detalle_factura_extraordinaria` WRITE;
/*!40000 ALTER TABLE `detalle_factura_extraordinaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura_extraordinaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura_producto`
--

DROP TABLE IF EXISTS `detalle_factura_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_factura_producto` (
  `id_detalle_factura_producto` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `id_factura_producto` int NOT NULL,
  `id_producto` int NOT NULL,
  PRIMARY KEY (`id_detalle_factura_producto`),
  KEY `id_factura_producto` (`id_factura_producto`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_factura_producto_ibfk_1` FOREIGN KEY (`id_factura_producto`) REFERENCES `factura_producto` (`id_factura_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_factura_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura_producto`
--

LOCK TABLES `detalle_factura_producto` WRITE;
/*!40000 ALTER TABLE `detalle_factura_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_factura_venta`
--

DROP TABLE IF EXISTS `detalle_factura_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_factura_venta` (
  `id_detalle_factura_venta` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `id_factura_venta` int NOT NULL,
  `id_pieza` int NOT NULL,
  PRIMARY KEY (`id_detalle_factura_venta`),
  KEY `id_factura_venta` (`id_factura_venta`),
  KEY `id_pieza` (`id_pieza`),
  CONSTRAINT `detalle_factura_venta_ibfk_1` FOREIGN KEY (`id_factura_venta`) REFERENCES `factura_venta` (`id_factura_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_factura_venta_ibfk_2` FOREIGN KEY (`id_pieza`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_factura_venta`
--

LOCK TABLES `detalle_factura_venta` WRITE;
/*!40000 ALTER TABLE `detalle_factura_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_factura_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pago`
--

DROP TABLE IF EXISTS `detalle_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pago` (
  `id_detalle_pago` int NOT NULL AUTO_INCREMENT,
  `detalle` varchar(400) NOT NULL,
  `monto` float NOT NULL,
  `id_pago` int NOT NULL,
  PRIMARY KEY (`id_detalle_pago`),
  KEY `id_pago` (`id_pago`),
  CONSTRAINT `detalle_pago_ibfk_1` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pago`
--

LOCK TABLES `detalle_pago` WRITE;
/*!40000 ALTER TABLE `detalle_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(250) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_persona` int NOT NULL,
  `id_puesto` int NOT NULL,
  `id_horario` int NOT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `id_persona` (`id_persona`),
  KEY `id_puesto` (`id_puesto`),
  KEY `id_horario` (`id_horario`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`id_puesto`) REFERENCES `puesto` (`id_puesto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `empleado_ibfk_3` FOREIGN KEY (`id_horario`) REFERENCES `horario` (`id_horario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `nit` varchar(20) NOT NULL,
  PRIMARY KEY (`id_empresa`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `direccion` (`direccion`),
  UNIQUE KEY `nit` (`nit`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'ISRAEL S.A.','6TA AV 8-29 ZONA 10','243698-A');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `id_equipo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_equipo`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_pieza`
--

DROP TABLE IF EXISTS `estado_pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_pieza` (
  `id_estado_pieza` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_estado_pieza`),
  UNIQUE KEY `estado` (`estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_pieza`
--

LOCK TABLES `estado_pieza` WRITE;
/*!40000 ALTER TABLE `estado_pieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_compra`
--

DROP TABLE IF EXISTS `factura_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_compra` (
  `id_factura_compra` int NOT NULL AUTO_INCREMENT,
  `id_proveedor` int NOT NULL,
  `id_factura_general` int NOT NULL,
  PRIMARY KEY (`id_factura_compra`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_factura_general` (`id_factura_general`),
  CONSTRAINT `factura_compra_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_compra_ibfk_2` FOREIGN KEY (`id_factura_general`) REFERENCES `factura_general` (`id_factura_general`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_compra`
--

LOCK TABLES `factura_compra` WRITE;
/*!40000 ALTER TABLE `factura_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_general`
--

DROP TABLE IF EXISTS `factura_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_general` (
  `id_factura_general` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total` float NOT NULL,
  `id_empleado` int NOT NULL,
  `id_empresa` int NOT NULL,
  `id_tipo_pago` int NOT NULL,
  `id_tipo_factura` int NOT NULL,
  PRIMARY KEY (`id_factura_general`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_empresa` (`id_empresa`),
  KEY `id_tipo_pago` (`id_tipo_pago`),
  KEY `id_tipo_factura` (`id_tipo_factura`),
  CONSTRAINT `factura_general_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_general_ibfk_2` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_general_ibfk_3` FOREIGN KEY (`id_tipo_pago`) REFERENCES `tipo_pago` (`id_tipo_pago`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_general_ibfk_4` FOREIGN KEY (`id_tipo_factura`) REFERENCES `tipo_factura` (`id_tipo_factura`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_general`
--

LOCK TABLES `factura_general` WRITE;
/*!40000 ALTER TABLE `factura_general` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_general` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_producto`
--

DROP TABLE IF EXISTS `factura_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_producto` (
  `id_factura_producto` int NOT NULL AUTO_INCREMENT,
  `id_proveedor` int NOT NULL,
  `id_factura_general` int NOT NULL,
  PRIMARY KEY (`id_factura_producto`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_factura_general` (`id_factura_general`),
  CONSTRAINT `factura_producto_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_producto_ibfk_2` FOREIGN KEY (`id_factura_general`) REFERENCES `factura_general` (`id_factura_general`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_producto`
--

LOCK TABLES `factura_producto` WRITE;
/*!40000 ALTER TABLE `factura_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_venta`
--

DROP TABLE IF EXISTS `factura_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_venta` (
  `id_factura_venta` int NOT NULL AUTO_INCREMENT,
  `id_persona` int NOT NULL,
  `id_factura_general` int NOT NULL,
  PRIMARY KEY (`id_factura_venta`),
  KEY `id_persona` (`id_persona`),
  KEY `id_factura_general` (`id_factura_general`),
  CONSTRAINT `factura_venta_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura_venta_ibfk_2` FOREIGN KEY (`id_factura_general`) REFERENCES `factura_general` (`id_factura_general`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_venta`
--

LOCK TABLES `factura_venta` WRITE;
/*!40000 ALTER TABLE `factura_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horario` (
  `id_horario` int NOT NULL AUTO_INCREMENT,
  `entrada` time NOT NULL,
  `salida` time NOT NULL,
  PRIMARY KEY (`id_horario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inasistencia`
--

DROP TABLE IF EXISTS `inasistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inasistencia` (
  `id_inasistencia` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `id_tipo_inasistencia` int NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_inasistencia`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_tipo_inasistencia` (`id_tipo_inasistencia`),
  CONSTRAINT `inasistencia_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inasistencia_ibfk_2` FOREIGN KEY (`id_tipo_inasistencia`) REFERENCES `tipo_inasistencia` (`id_tipo_inasistencia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inasistencia`
--

LOCK TABLES `inasistencia` WRITE;
/*!40000 ALTER TABLE `inasistencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `inasistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario_general`
--

DROP TABLE IF EXISTS `inventario_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario_general` (
  `id_inventario_general` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `id_producto` int NOT NULL,
  PRIMARY KEY (`id_inventario_general`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `inventario_general_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_general`
--

LOCK TABLES `inventario_general` WRITE;
/*!40000 ALTER TABLE `inventario_general` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario_general` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario_pieza`
--

DROP TABLE IF EXISTS `inventario_pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario_pieza` (
  `id_inventario_pieza` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `id_pieza` int NOT NULL,
  PRIMARY KEY (`id_inventario_pieza`),
  KEY `id_pieza` (`id_pieza`),
  CONSTRAINT `inventario_pieza_ibfk_1` FOREIGN KEY (`id_pieza`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_pieza`
--

LOCK TABLES `inventario_pieza` WRITE;
/*!40000 ALTER TABLE `inventario_pieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario_pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_pieza`
--

DROP TABLE IF EXISTS `marca_pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca_pieza` (
  `id_marca_pieza` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(250) NOT NULL,
  PRIMARY KEY (`id_marca_pieza`),
  UNIQUE KEY `marca` (`marca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_pieza`
--

LOCK TABLES `marca_pieza` WRITE;
/*!40000 ALTER TABLE `marca_pieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca_pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento_financiero`
--

DROP TABLE IF EXISTS `movimiento_financiero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento_financiero` (
  `id_movimiento_financiero` int NOT NULL AUTO_INCREMENT,
  `total` float NOT NULL,
  `monto` float NOT NULL,
  `fecha` date NOT NULL,
  `id_tipo_transaccion` int NOT NULL,
  PRIMARY KEY (`id_movimiento_financiero`),
  KEY `id_tipo_transaccion` (`id_tipo_transaccion`),
  CONSTRAINT `movimiento_financiero_ibfk_1` FOREIGN KEY (`id_tipo_transaccion`) REFERENCES `tipo_transaccion` (`id_tipo_transaccion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_financiero`
--

LOCK TABLES `movimiento_financiero` WRITE;
/*!40000 ALTER TABLE `movimiento_financiero` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_financiero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nombre_pieza`
--

DROP TABLE IF EXISTS `nombre_pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nombre_pieza` (
  `id_nombre_pieza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_nombre_pieza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nombre_pieza`
--

LOCK TABLES `nombre_pieza` WRITE;
/*!40000 ALTER TABLE `nombre_pieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `nombre_pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `pago` float NOT NULL,
  `fecha` date NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `dpi` varchar(250) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `apellido` varchar(250) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `nit` varchar(20) NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `dpi` (`dpi`),
  UNIQUE KEY `nit` (`nit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pieza`
--

DROP TABLE IF EXISTS `pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pieza` (
  `id_pieza` int NOT NULL AUTO_INCREMENT,
  `numero_pieza` int NOT NULL,
  `precio_compra` float NOT NULL,
  `precio_venta` float NOT NULL,
  `id_proveedor` int NOT NULL,
  `id_nombre_pieza` int NOT NULL,
  `id_estado_pieza` int NOT NULL,
  `id_marca_pieza` int NOT NULL,
  `id_carro` int NOT NULL,
  PRIMARY KEY (`id_pieza`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_nombre_pieza` (`id_nombre_pieza`),
  KEY `id_estado_pieza` (`id_estado_pieza`),
  KEY `id_marca_pieza` (`id_marca_pieza`),
  KEY `id_carro` (`id_carro`),
  CONSTRAINT `pieza_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pieza_ibfk_2` FOREIGN KEY (`id_nombre_pieza`) REFERENCES `nombre_pieza` (`id_nombre_pieza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pieza_ibfk_3` FOREIGN KEY (`id_estado_pieza`) REFERENCES `estado_pieza` (`id_estado_pieza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pieza_ibfk_4` FOREIGN KEY (`id_marca_pieza`) REFERENCES `marca_pieza` (`id_marca_pieza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pieza_ibfk_5` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id_carro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pieza`
--

LOCK TABLES `pieza` WRITE;
/*!40000 ALTER TABLE `pieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `precio` float NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `direccion` (`direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puesto`
--

DROP TABLE IF EXISTS `puesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puesto` (
  `id_puesto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `sueldo` float NOT NULL,
  PRIMARY KEY (`id_puesto`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puesto`
--

LOCK TABLES `puesto` WRITE;
/*!40000 ALTER TABLE `puesto` DISABLE KEYS */;
INSERT INTO `puesto` VALUES (1,'VENDEDOR',3600),(2,'SUPERVISOR',4200),(3,'JEFE',6000),(4,'GERENTE',10000),(5,'CONTADOR',5000),(6,'CONSERJE',3600);
/*!40000 ALTER TABLE `puesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_persona`
--

DROP TABLE IF EXISTS `telefono_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono_persona` (
  `id_telefono_persona` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `id_persona` int NOT NULL,
  PRIMARY KEY (`id_telefono_persona`),
  UNIQUE KEY `numero` (`numero`),
  KEY `id_persona` (`id_persona`),
  CONSTRAINT `telefono_persona_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_persona`
--

LOCK TABLES `telefono_persona` WRITE;
/*!40000 ALTER TABLE `telefono_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_proveedor`
--

DROP TABLE IF EXISTS `telefono_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono_proveedor` (
  `id_telefono_proveedor` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_telefono_proveedor`),
  UNIQUE KEY `numero` (`numero`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `telefono_proveedor_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_proveedor`
--

LOCK TABLES `telefono_proveedor` WRITE;
/*!40000 ALTER TABLE `telefono_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_factura`
--

DROP TABLE IF EXISTS `tipo_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_factura` (
  `id_tipo_factura` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(250) NOT NULL,
  PRIMARY KEY (`id_tipo_factura`),
  UNIQUE KEY `tipo` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_factura`
--

LOCK TABLES `tipo_factura` WRITE;
/*!40000 ALTER TABLE `tipo_factura` DISABLE KEYS */;
INSERT INTO `tipo_factura` VALUES (2,'COMPRA'),(4,'EXTRAORDINARIA'),(5,'PRODUCTO COMPRA'),(3,'PRODUCTO VENTA'),(1,'VENTA');
/*!40000 ALTER TABLE `tipo_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_inasistencia`
--

DROP TABLE IF EXISTS `tipo_inasistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_inasistencia` (
  `id_tipo_inasistencia` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_inasistencia`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_inasistencia`
--

LOCK TABLES `tipo_inasistencia` WRITE;
/*!40000 ALTER TABLE `tipo_inasistencia` DISABLE KEYS */;
INSERT INTO `tipo_inasistencia` VALUES (1,'JUSTIFICADA'),(2,'INJUSTIFICADA');
/*!40000 ALTER TABLE `tipo_inasistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_pago` (
  `id_tipo_pago` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(250) NOT NULL,
  PRIMARY KEY (`id_tipo_pago`),
  UNIQUE KEY `tipo` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` VALUES (1,'EFECTIVO'),(2,'TARJETA');
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_transaccion`
--

DROP TABLE IF EXISTS `tipo_transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_transaccion` (
  `id_tipo_transaccion` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(250) NOT NULL,
  PRIMARY KEY (`id_tipo_transaccion`),
  UNIQUE KEY `tipo` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_transaccion`
--

LOCK TABLES `tipo_transaccion` WRITE;
/*!40000 ALTER TABLE `tipo_transaccion` DISABLE KEYS */;
INSERT INTO `tipo_transaccion` VALUES (2,'EGRESO'),(1,'INGRESO');
/*!40000 ALTER TABLE `tipo_transaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacaciones`
--

DROP TABLE IF EXISTS `vacaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacaciones` (
  `id_vacaciones` int NOT NULL AUTO_INCREMENT,
  `fecha_salida` date NOT NULL,
  `fecha_entrada` date NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_vacaciones`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `vacaciones_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacaciones`
--

LOCK TABLES `vacaciones` WRITE;
/*!40000 ALTER TABLE `vacaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `vacaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-02  9:27:53
