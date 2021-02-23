-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: supermercado
-- ------------------------------------------------------
-- Server version	8.0.18

CREATE DATABASE IF NOT EXISTS `supermercado`;

USE `supermercado`;

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
-- Table structure for table `cobra`
--

DROP TABLE IF EXISTS `cobra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobra` (
  `Compra_ID_Compra` int(11) NOT NULL,
  `Empleado_ID_Empleado` int(11) NOT NULL,
  PRIMARY KEY (`Compra_ID_Compra`,`Empleado_ID_Empleado`),
  KEY `fk_Compra_has_Empleado_Empleado1_idx` (`Empleado_ID_Empleado`),
  KEY `fk_Compra_has_Empleado_Compra1_idx` (`Compra_ID_Compra`),
  CONSTRAINT `fk_Compra_has_Empleado_Compra1` FOREIGN KEY (`Compra_ID_Compra`) REFERENCES `compra` (`ID_Compra`),
  CONSTRAINT `fk_Compra_has_Empleado_Empleado1` FOREIGN KEY (`Empleado_ID_Empleado`) REFERENCES `empleado` (`ID_Empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobra`
--

LOCK TABLES `cobra` WRITE;
/*!40000 ALTER TABLE `cobra` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `ID_Compra` int(11) NOT NULL AUTO_INCREMENT,
  `NumProducto` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`ID_Compra`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,2,'2020-01-01'),(2,2,'2020-01-01'),(3,3,'2021-02-23'),(4,3,'2021-02-23'),(5,3,'2021-02-23'),(6,3,'2021-02-23'),(7,3,'2021-02-23'),(8,3,'2021-02-23'),(9,4,'2021-02-23'),(10,4,'2021-02-23'),(11,4,'2021-02-23'),(12,2,'2021-02-23'),(13,2,'2021-02-23'),(14,2,'2021-02-23'),(15,2,'2021-02-23'),(16,2,'2021-02-23'),(17,2,'2021-02-23'),(18,2,'2021-02-23'),(19,2,'2021-02-23'),(20,1,'2021-02-23'),(21,1,'2021-02-23'),(22,1,'2021-02-23'),(23,1,'2021-02-23');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compuesto`
--

DROP TABLE IF EXISTS `compuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compuesto` (
  `Producto_ID_Producto` int(11) NOT NULL,
  `Compra_ID_Compra` int(11) NOT NULL,
  PRIMARY KEY (`Producto_ID_Producto`,`Compra_ID_Compra`),
  KEY `fk_Producto_has_Compra_Compra1_idx` (`Compra_ID_Compra`),
  KEY `fk_Producto_has_Compra_Producto_idx` (`Producto_ID_Producto`),
  CONSTRAINT `fk_Producto_has_Compra_Compra1` FOREIGN KEY (`Compra_ID_Compra`) REFERENCES `compra` (`ID_Compra`),
  CONSTRAINT `fk_Producto_has_Compra_Producto` FOREIGN KEY (`Producto_ID_Producto`) REFERENCES `producto` (`ID_Producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compuesto`
--

LOCK TABLES `compuesto` WRITE;
/*!40000 ALTER TABLE `compuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `compuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `ID_Empleado` int(11) NOT NULL,
  `Ultima_Sesion` date DEFAULT NULL,
  `Fecha_Contratacion` date NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'2021-02-23','2020-01-01','Carlos'),(2,'2021-01-11','2020-05-03','Jose');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `ID_Producto` int(11) NOT NULL,
  `Nombre_Producto` varchar(45) NOT NULL,
  `Precio_Venta` int(11) NOT NULL,
  `Precio_Proveedor` int(11) NOT NULL,
  `Cantidad_Stock` int(11) NOT NULL,
  PRIMARY KEY (`ID_Producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Disco duro',500,250,32),(2,'USB',600,250,29),(3,'Monitor',1200,750,33),(4,'Raton',800,550,12);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-23 21:50:15
