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
  `IdTim` int DEFAULT NULL,
  PRIMARY KEY (`IdLog`),
  KEY `fk_logovi_has_poruka_loga_idx` (`IdPoruka`),
  KEY `fk_log_has_idtim_idx` (`IdTim`),
  CONSTRAINT `fk_log_has_idtim` FOREIGN KEY (`IdTim`) REFERENCES `tim` (`IdTim`),
  CONSTRAINT `fk_logovi_has_poruka_loga` FOREIGN KEY (`IdPoruka`) REFERENCES `poruka_loga` (`IdPoruke`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=916 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 10:35:23
