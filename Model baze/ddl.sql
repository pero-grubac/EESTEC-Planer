-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: eestecplaner
-- ------------------------------------------------------
-- Server version	8.0.34

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
INSERT INTO `clanodbora` VALUES (42);
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija`
--

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;
INSERT INTO `kategorija` VALUES (1,'Zadati',4),(2,'Za preuzeti',4),(3,'Preuzeti',4),(4,'Završeni',4),(5,'Zadati',5),(6,'Za preuzeti',5),(7,'Preuzeti',5),(8,'Završeni',5),(9,'Zadati',6),(10,'Za preuzeti',6),(11,'Preuzeti',6),(12,'Završeni',6),(13,'Zadati',7),(14,'Za preuzeti',7),(15,'Preuzeti',7),(16,'Završeni',7),(17,'Zadati',8),(18,'Za preuzeti',8),(19,'Preuzeti',8),(20,'Završeni',8),(53,'Napomene',4);
/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `komentar`
--

DROP TABLE IF EXISTS `komentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `komentar` (
  `IdKomentar` int NOT NULL AUTO_INCREMENT,
  `IdZadatak` int NOT NULL,
  `IdKorisnik` int NOT NULL,
  `Datum` datetime NOT NULL,
  `Tekst` varchar(255) NOT NULL,
  PRIMARY KEY (`IdKomentar`),
  KEY `fk_komentar_has_korisnik_idx` (`IdKorisnik`),
  KEY `fk_komentar_has_zadatak_idx` (`IdZadatak`),
  CONSTRAINT `fk_komentar_has_korisnik` FOREIGN KEY (`IdKorisnik`) REFERENCES `korisnik` (`IdKorisnika`),
  CONSTRAINT `fk_komentar_has_zadatak` FOREIGN KEY (`IdZadatak`) REFERENCES `zadatak` (`IdZadatak`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `komentar`
--

LOCK TABLES `komentar` WRITE;
/*!40000 ALTER TABLE `komentar` DISABLE KEYS */;
/*!40000 ALTER TABLE `komentar` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `koordinator` VALUES (40),(41),(43);
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
  `Obrisan` tinyint DEFAULT '0',
  PRIMARY KEY (`IdKorisnika`),
  UNIQUE KEY `KorisnickoIme_UNIQUE` (`KorisnickoIme`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('Sofija','Rodić','sofija','$2a$10$LqQvVfwdMpbQNVH7cMeJ/etF5x1ArP1.kcs8MVJ1BvRtpzn7CoQJu','sofija.rodic@student.etf.unibl.org',40,0),('Aleksandra','Stanković','aleksandra','$2a$10$oq1b87SgSdlE8usyDlfsx.nGQda4fWE9hZHRyhznuL2N8S.tISFLG','aleksandra.stankovic@student.etf.unibl.org',41,0),('Mirko','Topić','mirko','$2a$10$bk20aV3hpt0tdHmZ6rzqHuz9lfV9mxDoJoGy.vtcp1y.3PGxphmdG','mirko.topic@student.etf.unibl.org',42,0),('Pero','Grubač','pero','$2a$10$0YsD8KyR3pyLq.XPW8TALeZFJD.mJPaX2vFJZE6SxJMicvhtKaEz2','pero.grubac@student.etf.unibl.org',43,0),('Marko','Marković','marko','$2a$10$YTUuVfO/qCz3B0SmGLa/v.USV9Ab.st8l16GFkLN5j7STpZNZV4E.','marko.markovic@gmail.com',44,0),('Jovan','Jovanic','jovan','$2a$10$gKV19kPe2vHG26mf4JqnBOge1cthoUqrRY4YtHlch7PgOL7LNedHG','jovan.jovanic@gmail.com',45,0),('mihailo','vasic','mihailo','$2a$10$HBTRlx3CcDu4JTf48bRB.esEirJjpZ2ROicReLNdIVq3mrUByRbtS','vacic@gmail.com',46,0),('aleksandar','drljaca','aleksandar','$2a$10$yTUPp92d76N4mv18w7T17eLbzmXkWbImFiLas9oVSEA.7g884yhx6','aleksandar@gmail.com',47,0),('stefan','milakovic','stefan','$2a$10$Y2eVyZ8I0Q8nS5h.Hgalv.cTHq5I52LhNT5uA2tTvEWFZ5GpGQin.','stefan@gmail.com',48,0),('bojan','ramic','bojan','$2a$10$5clWRwKh.KsrSOasHVC3heGMvgsODzwIOSlyUfTWS4tYNlGXtAzs.','bojan@gmail.com',49,0),('milenko','miljenovic','milenko','$2a$10$lB3N.CVJO6aJ30dz64AVG.W2HCSMH4EES70U7pc0LfusLmXr5a2om','milenko@gmail.com',50,0);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `korisnik_pripada_timu` VALUES (41,4),(42,4),(43,4),(42,5),(43,5),(40,6),(42,6),(43,6),(42,7),(40,8),(42,8),(43,8);
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
INSERT INTO `korisnik_radi_zadatak` VALUES (41,30),(43,30),(41,31),(40,32),(41,32),(42,32),(43,32),(43,33),(43,35),(43,36),(43,37),(43,38),(43,39),(43,40),(43,41),(43,42),(43,43),(40,44),(41,45),(42,46),(43,47),(44,48),(40,49),(41,50),(42,51),(43,52),(44,53),(40,54),(41,55),(42,56),(43,57),(44,58),(40,59),(41,60),(42,61),(43,62),(44,63),(40,64),(41,65),(42,66),(43,67),(44,68),(40,69),(41,70),(42,71),(43,72),(44,73),(40,74),(41,75),(42,76),(43,77),(44,78),(40,79),(41,80),(42,81),(43,82),(44,83),(40,84),(41,85),(42,86),(43,87),(44,88),(40,89),(41,90),(42,91),(43,92),(44,93),(40,94),(41,95),(42,96),(43,97),(44,98),(40,99),(41,100),(40,101),(41,102),(42,103),(43,104),(44,105),(40,106),(41,107),(42,108),(43,109);
/*!40000 ALTER TABLE `korisnik_radi_zadatak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `IdLog` int NOT NULL AUTO_INCREMENT,
  `Datum` datetime NOT NULL,
  `IdPoruka` int NOT NULL,
  `Subjekat` varchar(45) NOT NULL,
  PRIMARY KEY (`IdLog`),
  KEY `fk_logovi_has_poruka_loga_idx` (`IdPoruka`),
  CONSTRAINT `fk_logovi_has_poruka_loga` FOREIGN KEY (`IdPoruka`) REFERENCES `poruka_loga` (`IdPoruke`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (4,'2024-02-23 14:50:29',12,'aleksandra'),(5,'2024-02-24 16:33:15',12,'aleksandra'),(6,'2024-02-28 18:21:03',12,'aleksandra'),(7,'2024-02-28 18:22:42',12,'admin'),(8,'2024-02-28 18:26:08',12,'admin'),(9,'2024-02-28 18:27:10',12,'aleksandra'),(10,'2024-02-28 18:31:50',12,'aleksandra'),(11,'2024-02-28 19:59:27',12,'aleksandra'),(12,'2024-02-28 20:04:02',12,'admin'),(13,'2024-02-28 20:52:43',12,'aleksandra'),(14,'2024-02-28 21:24:03',12,'aleksandra'),(15,'2024-02-28 22:00:27',12,'aleksandra'),(16,'2024-02-29 16:13:58',12,'aleksandra'),(17,'2024-02-29 16:51:04',12,'aleksandra'),(18,'2024-02-29 17:28:11',12,'aleksandra'),(19,'2024-02-29 18:07:11',12,'aleksandra'),(20,'2024-02-29 18:40:46',12,'aleksandra'),(21,'2024-02-29 19:10:45',12,'aleksandra'),(22,'2024-02-29 19:22:24',12,'aleksandra'),(23,'2024-02-29 19:41:02',12,'aleksandra'),(24,'2024-02-29 20:55:37',12,'aleksandra'),(25,'2024-02-29 20:58:01',12,'aleksandra'),(26,'2024-02-29 21:33:23',12,'aleksandra'),(27,'2024-02-29 22:04:15',12,'aleksandra'),(28,'2024-02-29 22:07:56',11,'aleksandra'),(29,'2024-02-29 22:16:44',12,'aleksandra'),(30,'2024-02-29 22:49:58',12,'aleksandra'),(31,'2024-02-29 23:21:05',12,'aleksandra'),(32,'2024-03-01 10:49:17',1,'mihailo'),(33,'2024-03-01 10:49:41',1,'aleksandar'),(34,'2024-03-01 10:50:08',1,'stefan'),(35,'2024-03-01 10:50:54',12,'admin'),(36,'2024-03-01 10:50:58',2,'jovan'),(37,'2024-03-01 10:51:00',2,'mihailo'),(38,'2024-03-01 10:51:02',2,'aleksandar'),(39,'2024-03-01 10:51:03',2,'stefan'),(40,'2024-03-01 10:57:02',1,'bojan'),(41,'2024-03-01 10:57:29',1,'milenko'),(42,'2024-03-01 10:58:08',12,'admin'),(43,'2024-03-01 10:58:13',2,'bojan'),(44,'2024-03-01 10:58:15',2,'milenko'),(47,'2024-03-01 11:13:32',12,'admin'),(53,'2024-03-01 11:32:57',12,'admin'),(54,'2024-03-01 11:33:03',2,'a');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
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
-- Table structure for table `poruka_loga`
--

DROP TABLE IF EXISTS `poruka_loga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poruka_loga` (
  `IdPoruke` int NOT NULL AUTO_INCREMENT,
  `Poruka` varchar(100) NOT NULL,
  PRIMARY KEY (`IdPoruke`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poruka_loga`
--

LOCK TABLES `poruka_loga` WRITE;
/*!40000 ALTER TABLE `poruka_loga` DISABLE KEYS */;
INSERT INTO `poruka_loga` VALUES (1,'Pokusaj registracije'),(2,'Uspjesno registrovan ( nalog potvrđen od registracije)'),(3,'Email nije uspjesno poslat'),(4,'Prijava u tim '),(5,'Prijava na zadatak'),(6,'Promjena kategorije zadatka'),(7,'Izmjena zadatka'),(8,'Kreiranje zadatka'),(9,'Arhiviran zadatak'),(10,'Promjena uloge'),(11,'Nespjesna prijava'),(12,'Uspjesna prijava');
/*!40000 ALTER TABLE `poruka_loga` ENABLE KEYS */;
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
INSERT INTO `superuser` VALUES (40),(41),(42),(43);
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
INSERT INTO `tim` VALUES (4,'Design',41),(5,'HR',NULL),(6,'PR',40),(7,'FR',NULL),(8,'IT',43);
/*!40000 ALTER TABLE `tim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zadatak`
--

DROP TABLE IF EXISTS `zadatak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zadatak` (
  `IdZadatak` int NOT NULL AUTO_INCREMENT,
  `Tekst` varchar(450) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdKategorija` int DEFAULT NULL,
  `Rok` datetime DEFAULT NULL,
  `IdAutora` int NOT NULL,
  `DatumKreiranja` datetime NOT NULL,
  `Naslov` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `DatumArhiviranja` date DEFAULT NULL,
  `Arhiviran` tinyint DEFAULT '0',
  PRIMARY KEY (`IdZadatak`),
  KEY `fk_Zadatak_Kategorija1_idx` (`IdKategorija`),
  CONSTRAINT `fk_Zadatak_Kategorija1` FOREIGN KEY (`IdKategorija`) REFERENCES `kategorija` (`IdKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zadatak`
--

LOCK TABLES `zadatak` WRITE;
/*!40000 ALTER TABLE `zadatak` DISABLE KEYS */;
INSERT INTO `zadatak` VALUES (29,'Oko logotipa treba da postoji razmak širine slova \"e\" u logotipu.\n\nPropisano dokumentom Brand Policy Paper - January 2021.',53,NULL,41,'2023-09-25 16:03:18','Paziti na razmak oko logotipa!','2023-09-25',0),(30,'Kreirati poster dimenzija A3 za događaj ______.\n\nPoštovati BPP!\n\nPošto je za štampanje, raditi u CMYK color mode-u. ',2,'2023-10-06 16:06:00',41,'2023-09-25 16:06:55','Kreirati poster za događaj','2024-10-25',1),(31,'Napraviti u Canvi okvir za slike za predstojeći događaj gdje će PR tim kasnije moći da dodaje slike. \n\nPoštovati BPP. \n\nKad bude gotovo, uploadovati na Google Drive PR tima.',10,'2023-10-08 16:09:00',41,'2023-09-25 16:09:48','Napraviti okvir za slike za događaj','2024-09-25',1),(32,'Potrebno napraviti objavu za drustvene mreze za najavu predavanja. \n\nPotrebno da bude u 3 dimenzije: Instagram story. Instagram objava, Facebook cover image.',2,'2023-09-30 16:13:00',41,'2023-09-25 16:14:57','Dizajnirati objavu za najavu predavanja','2024-09-25',1),(33,'Navesti osnovne informacije: mjesto, vrijeme i datum odrzavanja. \nNapisati ko je predavac, i par recenica o predavacu. \nNapisati koja je tema.\nKoristiti dosta emotikona.\nIzmjena',10,'2024-06-05 18:23:00',40,'2023-09-25 16:24:12','Napisati opis za objavu u najavi dogadjaja','2024-10-25',1),(35,'Čestitaj Stefanu rođendan.',10,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','Rođendan','2024-09-25',1),(36,'nesto',2,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(37,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(38,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(39,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(40,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-11-25',1),(41,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(42,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-11-25',1),(43,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(44,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-11-25',1),(45,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(46,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(47,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(48,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(49,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(50,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(51,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(52,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(53,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(54,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-09-25',1),(55,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-10-25',1),(56,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-10-25',1),(57,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-10-25',1),(58,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-10-25',1),(59,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-12-25',1),(60,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-12-25',1),(61,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-12-25',1),(62,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-12-25',1),(63,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2023-12-25',1),(64,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(65,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(66,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(67,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(68,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(69,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(70,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(71,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(72,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(73,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(74,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(75,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(76,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(77,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(78,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(79,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(80,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(81,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(82,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(83,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(84,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(85,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-10-25',1),(86,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(87,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(88,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(89,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(90,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-12-25',1),(91,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(92,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(93,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(94,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(95,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(96,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(97,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(98,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(99,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(100,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-09-25',1),(101,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(102,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(103,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(104,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(105,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(106,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(107,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(108,'nesto',13,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1),(109,'nesto',18,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','a','2024-11-25',1);
/*!40000 ALTER TABLE `zadatak` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zahtjev`
--

LOCK TABLES `zahtjev` WRITE;
/*!40000 ALTER TABLE `zahtjev` DISABLE KEYS */;
/*!40000 ALTER TABLE `zahtjev` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-01 11:37:48
