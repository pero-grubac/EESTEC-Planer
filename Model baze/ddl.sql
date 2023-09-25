CREATE DATABASE  IF NOT EXISTS `eestecplaner` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eestecplaner`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eestecplaner
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `IdAdmin` int NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Lozinka` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IdAdmin`),
  UNIQUE KEY `KorisnickoIme` (`KorisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (9,'admin','$2a$10$0QM9FPHO0M7NznZP24nXS..GnEuEPRHLZdd/7czyeRCFN.Pnnh8VO');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clanodbora`
--

DROP TABLE IF EXISTS `clanodbora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clanodbora` (
  `IdClana` int NOT NULL,
  PRIMARY KEY (`IdClana`),
  CONSTRAINT `fk_ClanOdbora_Superuser1` FOREIGN KEY (`IdClana`) REFERENCES `superuser` (`IdSuperuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clanodbora`
--

LOCK TABLES `clanodbora` WRITE;
/*!40000 ALTER TABLE `clanodbora` DISABLE KEYS */;
INSERT INTO `clanodbora` VALUES (6),(34);
/*!40000 ALTER TABLE `clanodbora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorija`
--

DROP TABLE IF EXISTS `kategorija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorija` (
  `IdKategorija` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdTim` int NOT NULL,
  PRIMARY KEY (`IdKategorija`),
  KEY `fk_Kategorija_Tim1_idx` (`IdTim`),
  CONSTRAINT `IdTim` FOREIGN KEY (`IdTim`) REFERENCES `tim` (`IdTim`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija`
--

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;
INSERT INTO `kategorija` VALUES (1,'Zadati',4),(2,'Za prauzeti',4),(3,'Preuzeti',4),(4,'Završeni',4),(5,'Zadati',5),(6,'Za prauzeti',5),(7,'Preuzeti',5),(8,'Završeni',5),(9,'Zadati',6),(10,'Za prauzeti',6),(11,'Preuzeti',6),(12,'Završeni',6),(13,'Zadati',7),(14,'Za prauzeti',7),(15,'Preuzeti',7),(16,'Završeni',7),(17,'Zadati',8),(18,'Za prauzeti',8),(19,'Preuzeti',8),(20,'Završeni',8),(52,'Napomene',4);
/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`korisnik`@`localhost`*/ /*!50003 TRIGGER `kategorija_delete_trigger` BEFORE DELETE ON `kategorija` FOR EACH ROW BEGIN
    DELETE FROM Zadatak WHERE IdKategorija = OLD.IdKategorija;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `koordinator`
--

DROP TABLE IF EXISTS `koordinator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `koordinator` (
  `IdKoordinator` int NOT NULL,
  PRIMARY KEY (`IdKoordinator`),
  CONSTRAINT `fk_Kordinator_Superuser1` FOREIGN KEY (`IdKoordinator`) REFERENCES `superuser` (`IdSuperuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `koordinator`
--

LOCK TABLES `koordinator` WRITE;
/*!40000 ALTER TABLE `koordinator` DISABLE KEYS */;
INSERT INTO `koordinator` VALUES (7),(33),(35),(36),(37);
/*!40000 ALTER TABLE `koordinator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `Ime` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Prezime` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `KorisnickoIme` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Lozinka` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdKorisnika` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdKorisnika`),
  UNIQUE KEY `KorisnickoIme_UNIQUE` (`KorisnickoIme`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('Pero','Grubac','pero','$2a$10$IGk.tfspFeWMhMLHg0b/huOya2dT0AKiImYBkfWvYf1B8l45cbovi','pero@example.com',6),('Aleksandra','Stanković','sandra','$2a$10$lKQTqhJrY7BGdvwnNeyzreLlY/T2NF762c1UgYmrSRU3b5TQIO9jC','nema@mail.com',7),('dipsy','dipsy','dipsy','$2a$10$foxeNtksDkLOFFZNNSOfxeo/gyfPhc/AvMlgvE8bPmGcT/aaQ3rjK','dipsy@mail.com',33),('po','po','po','$2a$10$dvsvZsgDU5TO1D54/0D9tuawCPQupZ/U4zVqcuVXCgli6sRJiwqSy','po@mail.com',34),('raphael','tmnt','raphael','$2a$10$bnw/ZUHPdnwZKjCSGASLJObVWWmD1sKOpb1ls78iijunpuR6HDFEi','raphael@mail.com',35),('leonardo','tmnt','leo','$2a$10$x.RlTaz46PPZ6TWmTz2DTOKpdJX4VBnnvu4Ih62k6QjE9dseAujy2','leonardo@mail.com',36),('donatello','tmnt','donatello','$2a$10$KiewjE5QY4q95nf7QSR27OtIwJAIPuZZYZFBE68Da.ZVidYbd4Vka','donatello@mail.com',37),('michelangelo','tmnt','mike ','$2a$10$l5iKhgjwrfBBshsF14lyL.8aaTDGUmcQKEWccVTXUD4fERMU5NHbi','michelangelo@mail.com',38),('dora','marquez','dora ','$2a$10$Fvacjbf5vHhq2oeX7W0Ok.oRVYHu3VLNgkNL8oV9fM2sPwo1mhSo.','stfu@mail.com',39);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`pero`@`localhost`*/ /*!50003 TRIGGER `delete_superuser` AFTER DELETE ON `korisnik` FOR EACH ROW BEGIN
    DELETE FROM superuser WHERE IdSuperuser = OLD.IdKorisnika;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `korisnik_pripada_timu`
--

DROP TABLE IF EXISTS `korisnik_pripada_timu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_pripada_timu` (
  `Korisnik_IdKorisnika` int NOT NULL,
  `Tim_IdTim` int NOT NULL,
  PRIMARY KEY (`Korisnik_IdKorisnika`,`Tim_IdTim`),
  KEY `fk_Korisnik_has_Tim_Tim1_idx` (`Tim_IdTim`),
  KEY `fk_Korisnik_has_Tim_Korisnik1_idx` (`Korisnik_IdKorisnika`),
  CONSTRAINT `fk_Korisnik_has_Tim_Korisnik1` FOREIGN KEY (`Korisnik_IdKorisnika`) REFERENCES `korisnik` (`IdKorisnika`),
  CONSTRAINT `fk_Korisnik_has_Tim_Tim1` FOREIGN KEY (`Tim_IdTim`) REFERENCES `tim` (`IdTim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_pripada_timu`
--

LOCK TABLES `korisnik_pripada_timu` WRITE;
/*!40000 ALTER TABLE `korisnik_pripada_timu` DISABLE KEYS */;
INSERT INTO `korisnik_pripada_timu` VALUES (6,4),(7,4),(34,4),(37,4),(6,5),(7,5),(6,6),(36,6),(6,7),(33,7),(6,8),(34,8),(35,8),(37,8);
/*!40000 ALTER TABLE `korisnik_pripada_timu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik_radi_zadatak`
--

DROP TABLE IF EXISTS `korisnik_radi_zadatak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_radi_zadatak` (
  `Korisnik_IdKorisnika` int NOT NULL,
  `Zadatak_IdZadatak` int NOT NULL,
  PRIMARY KEY (`Korisnik_IdKorisnika`,`Zadatak_IdZadatak`),
  KEY `fk_Korisnik_has_Zadatak_Zadatak1_idx` (`Zadatak_IdZadatak`),
  KEY `fk_Korisnik_has_Zadatak_Korisnik1_idx` (`Korisnik_IdKorisnika`),
  CONSTRAINT `fk_Korisnik_has_Zadatak_Korisnik1` FOREIGN KEY (`Korisnik_IdKorisnika`) REFERENCES `korisnik` (`IdKorisnika`),
  CONSTRAINT `fk_Korisnik_has_Zadatak_Zadatak1` FOREIGN KEY (`Zadatak_IdZadatak`) REFERENCES `zadatak` (`IdZadatak`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_radi_zadatak`
--

LOCK TABLES `korisnik_radi_zadatak` WRITE;
/*!40000 ALTER TABLE `korisnik_radi_zadatak` DISABLE KEYS */;
INSERT INTO `korisnik_radi_zadatak` VALUES (6,3),(6,4),(6,8),(6,15),(37,19);
/*!40000 ALTER TABLE `korisnik_radi_zadatak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifikacija`
--

DROP TABLE IF EXISTS `notifikacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifikacija` (
  `IdObjava` int NOT NULL AUTO_INCREMENT,
  `Sadržaj` varchar(450) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `VrijemeKreiranja` datetime NOT NULL,
  `IdSuperuser` int NOT NULL,
  PRIMARY KEY (`IdObjava`),
  KEY `fk_Objava_Superuser1_idx` (`IdSuperuser`),
  CONSTRAINT `fk_Objava_Superuser1` FOREIGN KEY (`IdSuperuser`) REFERENCES `superuser` (`IdSuperuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifikacija`
--

LOCK TABLES `notifikacija` WRITE;
/*!40000 ALTER TABLE `notifikacija` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifikacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `superuser`
--

DROP TABLE IF EXISTS `superuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `superuser` (
  `IdSuperuser` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdSuperuser`),
  CONSTRAINT `fk_Superuser_Korisnik1` FOREIGN KEY (`IdSuperuser`) REFERENCES `korisnik` (`IdKorisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `superuser`
--

LOCK TABLES `superuser` WRITE;
/*!40000 ALTER TABLE `superuser` DISABLE KEYS */;
INSERT INTO `superuser` VALUES (6),(7),(33),(34),(35),(36),(37);
/*!40000 ALTER TABLE `superuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tim`
--

DROP TABLE IF EXISTS `tim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tim` (
  `IdTim` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdKoordinator` int DEFAULT NULL,
  PRIMARY KEY (`IdTim`),
  KEY `IdKoordinator` (`IdKoordinator`),
  CONSTRAINT `tim_ibfk_1` FOREIGN KEY (`IdKoordinator`) REFERENCES `koordinator` (`IdKoordinator`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tim`
--

LOCK TABLES `tim` WRITE;
/*!40000 ALTER TABLE `tim` DISABLE KEYS */;
INSERT INTO `tim` VALUES (4,'Design',37),(5,'HR',7),(6,'PR',36),(7,'FR',33),(8,'IT',35);
/*!40000 ALTER TABLE `tim` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`pero`@`localhost`*/ /*!50003 TRIGGER `after_tim_delete` AFTER DELETE ON `tim` FOR EACH ROW BEGIN
    DELETE FROM korisnik_pripada_timu
    WHERE Tim_IdTim = OLD.IdTim;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `zadatak`
--

DROP TABLE IF EXISTS `zadatak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zadatak` (
  `IdZadatak` int NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(450) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdKategorija` int NOT NULL,
  `Rok` datetime DEFAULT NULL,
  `IdAutora` int NOT NULL,
  `DatumKreiranja` datetime NOT NULL,
  `Naslov` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`IdZadatak`),
  KEY `fk_Zadatak_Kategorija1_idx` (`IdKategorija`),
  CONSTRAINT `fk_Zadatak_Kategorija1` FOREIGN KEY (`IdKategorija`) REFERENCES `kategorija` (`IdKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zadatak`
--

LOCK TABLES `zadatak` WRITE;
/*!40000 ALTER TABLE `zadatak` DISABLE KEYS */;
INSERT INTO `zadatak` VALUES (3,'Sample task update',1,'2023-12-02 00:00:00',37,'2023-02-02 00:00:00','Sample Title update'),(4,'Sample task update 2',1,'2023-12-02 00:00:00',37,'2023-09-21 19:45:41','Sample Title update 2'),(8,'drugi tim',13,'2023-12-02 00:00:00',33,'2023-09-21 19:46:47','Sample Title'),(15,'...',17,'2023-09-29 17:00:00',6,'2023-09-23 17:00:23','Zadatak'),(17,'...',18,'2023-09-29 17:06:00',6,'2023-09-23 17:06:47','Zadatak od clana odbora'),(19,'...',17,'2023-09-30 17:18:00',6,'2023-09-23 17:18:53','Novi zadatak'),(20,'...',2,'2023-09-29 17:19:00',6,'2023-09-23 17:19:15','Novi zadatak'),(22,'',1,'2023-09-30 13:28:00',37,'2023-09-24 13:29:03','Novi zadatak'),(25,'dfsdfsdfsdf',1,NULL,37,'2023-09-24 14:38:03','dfsfsdfsfdfdfdsdfs'),(26,'Propisano dokumentom Brand Policy Paper - January 2021',52,NULL,37,'2023-09-24 15:13:07','Koristiti crvenu boju: #e52a30'),(27,'Sa sve cetiri strane oko logotipa mora postojati razmak velicine slova \"e\" u logotipu. \n\nPropisano dokumentom: Brand Policy Paper - January 2021.',52,NULL,37,'2023-09-24 15:13:38','Paziti na razmak oko logotipa');
/*!40000 ALTER TABLE `zadatak` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`korisnik`@`localhost`*/ /*!50003 TRIGGER `zadatak_BEFORE_DELETE` BEFORE DELETE ON `zadatak` FOR EACH ROW BEGIN
DELETE FROM korisnik_radi_zadatak WHERE Zadatak_IdZadatak =OLD.IdZadatak;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `zahtjev`
--

DROP TABLE IF EXISTS `zahtjev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zahtjev` (
  `KorisničkoIme` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Ime` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Lozinka` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdZahtjev` int NOT NULL AUTO_INCREMENT,
  `Prezime` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `DatumKreiranja` datetime DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`IdZahtjev`),
  UNIQUE KEY `KorisničkoIme_UNIQUE` (`KorisničkoIme`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zahtjev`
--

LOCK TABLES `zahtjev` WRITE;
/*!40000 ALTER TABLE `zahtjev` DISABLE KEYS */;
INSERT INTO `zahtjev` VALUES ('sandraPhoenix','aleksandra','$2a$10$stRJNurDfI6N6bwIv1M1KelzQd52zPFIJQLVQZcQNW73BcC.KhtOO',22,'rodic','2023-02-02 00:00:00','nemamejlaidalje@mail.com'),('sandra','sandra','$2a$10$SMECEK1Yt7RuHaZLqN0.muPBz6cfPProJOx0EgBBjbUQieJKDBxsW',23,'obrascic','2023-02-02 00:00:00','sandi@mail.com'),('test','Test','$2a$10$ZRxIlTRLvKgVM82oA41lQeMOyjwIkbA93EzF8PCfDaPxhy/y.N47K',26,'Testovic','2023-09-22 11:22:55','test@gmail.com'),('novi','novi','$2a$10$l3g5HYQluFWJHJq9qZMd6erzS8wnZj01yi3WES4PNhrbt4kAb1otS',27,'nalog','2023-09-23 13:14:21','novi@mail.com'),('sofija','sofija','$2a$10$KXAnFRc042fDUSA4J2VNIOOzG1KDRjUXR8TZ1eExWcDn.P4A7IeNe',28,'rodic','2023-09-23 13:15:15','sofija@gmail.com'),('perica','perica','$2a$10$jobcqvVhsiKkaiCpqtSKROjFeQNGW/2UfZJafdC5ukArdBXPN2ZpW',29,'peric','2023-09-23 13:15:52','perica@gmail.com');
/*!40000 ALTER TABLE `zahtjev` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'eestecplaner'
--

--
-- Dumping routines for database 'eestecplaner'
--
/*!50003 DROP PROCEDURE IF EXISTS `DeleteKorisnikFromKorisnikRadiZadatak` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`korisnik`@`localhost` PROCEDURE `DeleteKorisnikFromKorisnikRadiZadatak`(IN korisnikId INT, IN timId INT)
BEGIN
  -- Delete korisnik from korisnik_radi_zadatak for zadataks associated with categories of the team
  DELETE krz
  FROM korisnik_radi_zadatak krz
  JOIN zadatak z ON krz.Zadatak_IdZadatak = z.IdZadatak
  JOIN kategorija k ON z.IdKategorija = k.IdKategorija
  WHERE krz.Korisnik_IdKorisnika = korisnikId AND k.IdTim = timId;
  
  DELETE kpt
  FROM korisnik_pripada_timu kpt
  WHERE kpt.Korisnik_IdKorisnika = korisnikId AND kpt.Tim_IdTim = timId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteUserAndAssociatedData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`korisnik`@`localhost` PROCEDURE `DeleteUserAndAssociatedData`(IN UserId INT)
BEGIN
    DELETE FROM korisnik_radi_zadatak
    WHERE Korisnik_IdKorisnika = UserId;

    DELETE FROM zadatak
    WHERE IdAutora = UserId;

    DELETE FROM korisnik_pripada_timu
    WHERE Korisnik_IdKorisnika = UserId;

    UPDATE tim
    SET IdKoordinator = NULL
    WHERE IdKoordinator = UserId;

    DELETE FROM koordinator WHERE IdKoordinator = UserId;

    DELETE FROM clanodbora WHERE IdClana = UserId;

    DELETE FROM superuser WHERE IdSuperuser = UserId;
    
    DELETE FROM korisnik WHERE IdKorisnika = UserId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-25 14:33:50
