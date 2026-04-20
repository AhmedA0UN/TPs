CREATE DATABASE  IF NOT EXISTS `javaproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `javaproject`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: javaproject
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commande` (
  `idC` int NOT NULL,
  `etat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `idU` int DEFAULT NULL,
  `prixC` double DEFAULT '0',
  PRIMARY KEY (`idC`),
  KEY `idU _idx` (`idU`),
  CONSTRAINT `idU` FOREIGN KEY (`idU`) REFERENCES `utilisateur` (`idU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plat`
--

DROP TABLE IF EXISTS `plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plat` (
  `idPlat` int NOT NULL AUTO_INCREMENT,
  `nomPlat` varchar(45) NOT NULL,
  `prix` double NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idPlat`),
  UNIQUE KEY `nomPlat_UNIQUE` (`nomPlat`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plat`
--

LOCK TABLES `plat` WRITE;
/*!40000 ALTER TABLE `plat` DISABLE KEYS */;
INSERT INTO `plat` VALUES (1,'Brik au Thon',2,'salé'),(2,'Pommes au Frites',2,'salé'),(3,'Sauce Kamounia',4,'salé'),(4,'Omlette au Thon',3.5,'salé'),(5,'Ojja ',5,'salé'),(6,'Ojja Fruit de Mer',10.5,'salé'),(7,'Ojja Merguez',7,'salé'),(8,'Pizza Thon',7,'salé'),(9,'Pizza 4 saisons',8,'salé'),(10,'Mlewi Speciale ',7,'salé'),(11,'Crepe Chocolat ',2.8,'sucré'),(12,'Crepe NUTELLA ',4.5,'sucré'),(13,'Gaufre au Sucre',2,'sucré'),(14,'Gaufre au Chocolat ',3,'sucré'),(15,'Gaufre Nutella ',4,'sucré'),(16,'TIRAMISU',7,'sucré'),(17,'JWAJEM',999,'sucré');
/*!40000 ALTER TABLE `plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platscommande`
--

DROP TABLE IF EXISTS `platscommande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platscommande` (
  `idPC` int NOT NULL AUTO_INCREMENT,
  `idPlat` int DEFAULT NULL,
  `nomPlat` varchar(45) DEFAULT NULL,
  `quantite` int NOT NULL,
  `idU` int DEFAULT NULL,
  PRIMARY KEY (`idPC`),
  KEY `fk_u` (`idU`),
  KEY `fk_plat_idx` (`idPlat`),
  CONSTRAINT `fk_plat` FOREIGN KEY (`idPlat`) REFERENCES `plat` (`idPlat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_u` FOREIGN KEY (`idU`) REFERENCES `utilisateur` (`idU`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platscommande`
--

LOCK TABLES `platscommande` WRITE;
/*!40000 ALTER TABLE `platscommande` DISABLE KEYS */;
/*!40000 ALTER TABLE `platscommande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `idU` int NOT NULL,
  `nomU` varchar(45) DEFAULT NULL,
  `mdpU` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'serveur','mdpserveur','serveur'),(2,'chef','mdpchef','chef'),(4,'yassin','yassin','client'),(5,'yassin sassi','cha9chamboula','client'),(6,'ahmed','3isamui','client'),(7,'hamed','yassin','client'),(33,'yassinsb','yassin','client'),(44,'ahmed','ahmedmdp','client'),(77,'yassin','yassin','client'),(88,'new ','mdpnew','client'),(99,'new','mdpnew','client'),(122,'yassinsb','yassin','client'),(123,'azer','AZER','client'),(331,'YASSIN','yassin','client');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-09 14:59:20
