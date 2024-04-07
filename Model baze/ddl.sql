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
INSERT INTO `clanodbora` VALUES (42),(46);
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
INSERT INTO `koordinator` VALUES (40),(41),(43),(47),(48);
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
INSERT INTO `korisnik` VALUES ('Sofija','Rodić','sofija','$2a$10$LqQvVfwdMpbQNVH7cMeJ/etF5x1ArP1.kcs8MVJ1BvRtpzn7CoQJu','sofija.rodic@student.etf.unibl.org',40,0),('Aleksandra','Stanković','aleksandra','$2a$10$oq1b87SgSdlE8usyDlfsx.nGQda4fWE9hZHRyhznuL2N8S.tISFLG','aleksandra.stankovic@student.etf.unibl.org',41,0),('Mirko','Topić','mirko','$2a$10$bk20aV3hpt0tdHmZ6rzqHuz9lfV9mxDoJoGy.vtcp1y.3PGxphmdG','mirko.topic@student.etf.unibl.org',42,0),('Pero','Grubač','pero','$2a$10$0YsD8KyR3pyLq.XPW8TALeZFJD.mJPaX2vFJZE6SxJMicvhtKaEz2','perogrubac@gamil.com',43,0),('Marko','Marković','marko','$2a$10$YTUuVfO/qCz3B0SmGLa/v.USV9Ab.st8l16GFkLN5j7STpZNZV4E.','marko.markovic@gmail.com',44,0),('Jovan','Jovanic','jovan','$2a$10$gKV19kPe2vHG26mf4JqnBOge1cthoUqrRY4YtHlch7PgOL7LNedHG','jovan.jovanic@gmail.com',45,0),('mihailo','vasic','mihailo','$2a$10$HBTRlx3CcDu4JTf48bRB.esEirJjpZ2ROicReLNdIVq3mrUByRbtS','vacic@gmail.com',46,0),('aleksandar','drljaca','aleksandar','$2a$10$yTUPp92d76N4mv18w7T17eLbzmXkWbImFiLas9oVSEA.7g884yhx6','aleksandar@gmail.com',47,0),('stefan','milakovic','stefan','$2a$10$Y2eVyZ8I0Q8nS5h.Hgalv.cTHq5I52LhNT5uA2tTvEWFZ5GpGQin.','stefan@gmail.com',48,0),('bojan','ramic','bojan','$2a$10$5clWRwKh.KsrSOasHVC3heGMvgsODzwIOSlyUfTWS4tYNlGXtAzs.','bojan@gmail.com',49,0),('milenko','miljenovic','milenko','$2a$10$lB3N.CVJO6aJ30dz64AVG.W2HCSMH4EES70U7pc0LfusLmXr5a2om','milenko@gmail.com',50,0);
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
INSERT INTO `korisnik_pripada_timu` VALUES (41,4),(42,4),(43,4),(46,4),(42,5),(43,5),(46,5),(47,5),(40,6),(42,6),(43,6),(46,6),(42,7),(46,7),(48,7),(40,8),(42,8),(43,8),(46,8);
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
INSERT INTO `korisnik_radi_zadatak` VALUES (41,30),(43,30),(41,31),(40,32),(41,32),(42,32),(43,32),(43,33),(43,35),(42,126),(46,127),(42,128),(41,129),(40,130),(40,131),(43,132),(46,133),(41,134),(46,135),(42,136),(41,137),(46,138),(43,139),(46,140),(43,141),(46,142),(40,143),(46,144),(43,145),(41,146),(41,147),(42,148),(43,149),(43,150),(41,151),(46,152),(40,153),(43,154),(43,155),(43,156),(40,157),(43,158),(41,159),(42,160),(43,161),(40,162),(42,163),(43,164),(41,165),(43,166),(46,167),(41,168),(42,169),(41,170),(40,171),(41,172),(46,173),(41,174),(42,175),(46,176),(41,177),(43,178),(46,179),(41,180),(42,181),(41,182),(46,183),(41,184),(43,185),(43,186),(46,187),(41,188),(41,189),(42,190),(41,191),(40,192),(43,193),(43,194),(41,195),(46,196),(41,197),(43,198),(41,199),(42,200),(47,201),(46,202),(43,203),(46,204),(43,205),(42,206),(43,207),(43,208),(43,209),(47,210),(46,211),(43,212),(43,213),(43,214),(47,215),(43,216),(43,217),(46,218),(46,219),(43,220),(47,221),(43,222),(47,223),(42,224),(46,225),(43,226),(43,227),(42,228),(46,229),(47,230),(47,231),(47,232),(42,233),(42,234),(43,235),(46,236),(47,237),(47,238),(46,239),(42,240),(42,241),(46,242),(47,243),(42,244),(47,245),(43,246),(47,247),(42,248),(43,249),(43,250),(42,251),(43,252),(46,253),(46,254),(43,255),(47,256),(46,257),(42,258),(47,259),(46,260),(46,261),(43,262),(46,263),(42,264),(46,265),(46,266),(47,267),(47,268),(46,269),(42,270),(42,271),(46,272),(42,273),(47,274),(43,275),(46,276),(42,277),(43,278),(43,279),(42,280),(46,281),(47,282),(46,283),(42,284),(46,285),(42,286),(47,287),(42,288),(42,289),(40,290),(42,291),(46,292),(40,293),(43,294),(43,295),(40,296),(42,297),(40,298),(46,299),(43,300),(46,301),(46,302),(40,303),(43,304),(42,305),(42,306),(40,307),(43,308),(43,309),(42,310),(42,311),(40,312),(42,313),(40,314),(46,315),(42,316),(43,317),(43,318),(40,319),(40,320),(43,321),(40,322),(43,323),(46,324),(40,325),(42,326),(42,327),(43,328),(43,329),(46,330),(40,331),(42,332),(42,333),(40,334),(46,335),(46,336),(46,337),(42,338),(46,339),(46,340),(46,341),(42,342),(42,343),(43,344),(42,345),(40,346),(43,347),(43,348),(40,349),(46,350),(46,351),(43,352),(46,353),(40,354),(46,355),(43,356),(46,357),(43,358),(46,359),(40,360),(46,361),(42,362),(42,363),(43,364),(43,365),(40,366),(40,367),(46,368),(42,369),(43,370),(46,371),(42,372),(48,373),(48,374),(46,375),(42,376),(46,377),(48,378),(42,379),(42,380),(48,381),(46,382),(46,383),(42,384),(48,385),(48,386),(46,387),(42,388),(46,389),(42,390),(42,391),(48,392),(48,393),(42,394),(48,395),(42,396),(48,397),(48,398),(42,399),(42,400),(48,401),(48,402),(48,403),(48,404),(48,405),(42,406),(46,407),(48,408),(46,409),(48,410),(48,411),(48,412),(48,413),(48,414),(46,415),(48,416),(42,417),(42,418),(46,419),(42,420),(46,421),(48,422),(46,423),(48,424),(46,425),(48,426),(48,427),(42,428),(48,429),(46,430),(42,431),(46,432),(46,433),(48,434),(42,435),(46,436),(46,437),(40,438),(43,439),(40,440),(40,441),(46,442),(43,443),(46,444),(42,445),(40,446),(40,447),(43,448),(46,449),(40,450),(46,451),(40,452),(40,453),(43,454),(43,455),(43,456),(42,457),(42,458),(42,459),(42,460),(42,461),(46,462),(43,463),(40,464),(46,465),(46,466),(42,467),(43,468),(46,469),(42,470),(46,471),(43,472),(40,473),(43,474),(42,475),(46,476),(43,477),(40,478),(42,479),(42,480),(42,481),(42,482),(40,483),(43,484),(40,485),(42,486),(40,487),(46,488),(42,489),(40,490),(42,491),(40,492),(43,493),(46,494),(43,495),(43,496),(46,497),(46,498),(42,499),(42,500),(46,501),(42,502),(42,503),(46,504),(40,505),(46,506),(42,507),(40,508),(43,509),(43,510),(43,511),(40,512),(43,513),(46,514),(43,515),(43,516),(40,517),(40,518),(42,519),(46,520),(40,521),(40,522),(40,523),(40,524),(46,525),(46,526),(42,527),(42,528),(43,528),(42,529),(43,529),(40,530),(46,530),(46,531),(46,532),(40,533),(42,534),(42,535),(46,536),(40,537),(40,538),(42,539),(43,540),(46,541),(46,580),(40,581),(42,582),(46,583),(42,584),(42,585),(46,586),(40,587),(43,588),(42,589),(43,590),(40,591),(46,643),(42,644),(43,645),(47,646),(42,647),(42,648),(42,649),(42,650),(43,651),(46,652),(43,653),(42,654),(47,655),(47,656),(42,706),(42,707),(42,708),(43,709),(41,710),(41,711),(42,712),(43,713),(43,714),(41,715),(42,716),(41,717),(41,718),(42,719),(43,720),(42,769),(42,770),(42,771),(46,772),(40,773),(46,774),(46,775),(42,776),(40,777),(42,778),(42,779),(42,780),(46,781),(42,782),(42,783),(40,784),(43,785),(42,786),(46,787),(42,842),(42,843),(48,844),(46,845),(48,846),(48,847),(48,848),(42,849),(48,850),(46,851),(48,852),(46,853),(48,854),(42,855),(42,856),(42,857),(46,858),(42,859),(46,860),(48,861),(43,912);
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
  `IdTim` int DEFAULT NULL,
  PRIMARY KEY (`IdLog`),
  KEY `fk_logovi_has_poruka_loga_idx` (`IdPoruka`),
  KEY `fk_log_has_idtim_idx` (`IdTim`),
  CONSTRAINT `fk_log_has_idtim` FOREIGN KEY (`IdTim`) REFERENCES `tim` (`IdTim`),
  CONSTRAINT `fk_logovi_has_poruka_loga` FOREIGN KEY (`IdPoruka`) REFERENCES `poruka_loga` (`IdPoruke`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (4,'2024-02-23 14:50:29',12,'aleksandra',NULL),(5,'2024-02-24 16:33:15',12,'aleksandra',NULL),(6,'2024-02-28 18:21:03',12,'aleksandra',NULL),(7,'2024-02-28 18:22:42',12,'admin',NULL),(8,'2024-02-28 18:26:08',12,'admin',NULL),(9,'2024-02-28 18:27:10',12,'aleksandra',NULL),(10,'2024-02-28 18:31:50',12,'aleksandra',NULL),(11,'2024-02-28 19:59:27',12,'aleksandra',NULL),(12,'2024-02-28 20:04:02',12,'admin',NULL),(13,'2024-02-28 20:52:43',4,'jovan',4),(14,'2024-02-28 21:24:03',12,'aleksandra',NULL),(15,'2024-02-28 22:00:27',12,'aleksandra',NULL),(16,'2024-02-29 16:13:58',12,'aleksandra',NULL),(17,'2024-02-29 16:51:04',12,'aleksandra',NULL),(18,'2024-02-29 17:28:11',12,'aleksandra',NULL),(19,'2024-02-29 18:07:11',12,'aleksandra',NULL),(20,'2024-02-29 18:40:46',12,'aleksandra',NULL),(21,'2024-02-29 19:10:45',12,'aleksandra',NULL),(22,'2024-02-29 19:22:24',12,'aleksandra',NULL),(23,'2024-02-29 19:41:02',12,'aleksandra',NULL),(24,'2024-02-29 20:55:37',12,'aleksandra',NULL),(25,'2024-02-29 20:58:01',12,'aleksandra',NULL),(26,'2024-02-29 21:33:23',12,'aleksandra',NULL),(27,'2024-02-29 22:04:15',12,'aleksandra',NULL),(28,'2024-02-29 22:07:56',11,'aleksandra',NULL),(29,'2024-02-29 22:16:44',12,'aleksandra',NULL),(30,'2024-02-29 22:49:58',12,'aleksandra',NULL),(31,'2024-02-29 23:21:05',12,'aleksandra',NULL),(32,'2024-03-01 10:49:17',1,'mihailo',NULL),(33,'2024-03-01 10:49:41',1,'aleksandar',NULL),(34,'2024-03-01 10:50:08',1,'stefan',NULL),(35,'2024-03-01 10:50:54',12,'admin',NULL),(36,'2024-03-01 10:50:58',2,'jovan',NULL),(37,'2024-03-01 10:51:00',2,'mihailo',NULL),(38,'2024-03-01 10:51:02',2,'aleksandar',NULL),(39,'2024-03-01 10:51:03',2,'stefan',NULL),(40,'2024-03-01 10:57:02',1,'bojan',NULL),(41,'2024-03-01 10:57:29',1,'milenko',NULL),(42,'2024-03-01 10:58:08',12,'admin',NULL),(43,'2024-03-01 10:58:13',2,'bojan',NULL),(44,'2024-03-01 10:58:15',2,'milenko',NULL),(47,'2024-03-01 11:13:32',12,'admin',NULL),(53,'2024-03-01 11:32:57',12,'admin',NULL),(54,'2024-03-01 11:33:03',2,'a',NULL),(55,'2024-03-05 19:49:31',12,'aleksandra',NULL),(56,'2024-03-05 19:59:57',11,'mirko',NULL),(57,'2024-03-05 20:00:22',11,'mirko',NULL),(58,'2024-03-05 20:01:31',11,'marko',NULL),(59,'2024-03-05 20:02:35',11,'marko',NULL),(60,'2024-03-05 20:02:51',12,'aleksandra',NULL),(61,'2024-03-05 20:03:59',11,'marko',NULL),(62,'2024-03-05 20:05:28',11,'marko',NULL),(63,'2024-03-05 20:07:37',11,'marko',NULL),(64,'2024-03-05 20:15:15',12,'aleksandra',NULL),(65,'2024-03-05 20:18:01',12,'mihailo',NULL),(66,'2024-03-05 20:18:15',11,'marko',NULL),(67,'2024-03-05 20:19:44',12,'mihailo',NULL),(68,'2024-03-06 21:00:28',12,'mihailo',NULL),(69,'2024-03-06 21:04:52',12,'mihailo',NULL),(70,'2024-03-06 21:41:49',12,'mihailo',NULL),(71,'2024-03-06 23:44:12',12,'mihailo',NULL),(72,'2024-03-06 23:49:14',12,'mihailo',NULL),(73,'2024-03-07 00:18:21',12,'mihailo',NULL),(74,'2024-03-07 17:15:40',12,'mihailo',NULL),(75,'2024-03-07 17:19:25',12,'mirko',NULL),(76,'2024-03-07 17:19:50',8,'mirko',NULL),(77,'2024-03-07 17:20:21',8,'mirko',NULL),(78,'2024-03-07 17:20:30',8,'mirko',NULL),(79,'2024-03-07 17:23:47',8,'mirko',NULL),(80,'2024-03-07 17:32:10',11,'pero',NULL),(81,'2024-03-07 17:32:21',11,'pero',NULL),(82,'2024-03-07 17:32:30',12,'mihailo',NULL),(83,'2024-03-07 17:32:48',11,'mirko',NULL),(84,'2024-03-07 17:32:52',12,'mirko',NULL),(85,'2024-03-07 18:28:22',12,'aleksandra',NULL),(86,'2024-03-07 18:30:36',8,'aleksandra',NULL),(87,'2024-03-07 18:35:15',8,'aleksandra',NULL),(88,'2024-03-07 18:40:22',12,'aleksandra',NULL),(89,'2024-03-07 18:40:56',8,'aleksandra',NULL),(90,'2024-03-07 18:44:02',8,'aleksandra',NULL),(91,'2024-03-07 18:47:35',8,'aleksandra',NULL),(92,'2024-03-07 19:11:57',12,'aleksandar',NULL),(93,'2024-03-07 19:12:13',12,'aleksandra',NULL),(94,'2024-03-07 19:12:26',8,'aleksandra',NULL),(95,'2024-03-07 19:14:29',8,'aleksandra',NULL),(96,'2024-03-07 19:18:39',8,'aleksandra',NULL),(97,'2024-03-07 19:19:20',8,'aleksandra',NULL),(98,'2024-03-07 19:20:28',8,'aleksandra',NULL),(99,'2024-03-07 19:21:46',8,'aleksandra',NULL),(100,'2024-03-07 19:25:22',8,'aleksandra',NULL),(101,'2024-04-05 21:04:27',12,'admin',NULL),(102,'2024-04-05 21:04:39',10,'admin',NULL),(103,'2024-04-05 21:04:49',10,'admin',NULL),(104,'2024-04-05 21:04:56',10,'admin',NULL),(105,'2024-04-05 21:33:52',12,'mihailo',NULL),(106,'2024-04-05 21:37:25',12,'mihailo',NULL),(107,'2024-04-05 22:02:41',12,'mihailo',NULL),(108,'2024-04-05 22:27:55',12,'mihailo',NULL),(109,'2024-04-06 16:31:20',12,'aleksandra',NULL),(110,'2024-04-06 16:33:14',8,'aleksandra',NULL),(111,'2024-04-06 16:34:25',8,'aleksandra',NULL),(112,'2024-04-06 16:37:47',7,'aleksandra',NULL),(113,'2024-04-06 16:38:05',7,'aleksandra',NULL),(114,'2024-04-06 16:38:19',6,'aleksandra',NULL),(115,'2024-04-06 16:39:32',7,'aleksandra',NULL),(116,'2024-04-06 16:53:28',12,'admin',NULL),(117,'2024-04-06 17:07:30',12,'pero',NULL),(118,'2024-04-06 17:08:24',6,'pero',NULL),(119,'2024-04-06 17:08:27',6,'pero',NULL),(120,'2024-04-06 17:08:49',12,'pero',NULL),(121,'2024-04-06 17:08:55',6,'pero',NULL),(122,'2024-04-06 17:08:58',6,'pero',NULL),(123,'2024-04-06 17:09:01',6,'pero',NULL),(124,'2024-04-06 17:09:03',6,'pero',NULL),(125,'2024-04-06 17:09:06',6,'pero',NULL),(126,'2024-04-06 17:09:09',6,'pero',NULL),(127,'2024-04-06 17:09:10',6,'pero',NULL),(128,'2024-04-06 17:09:22',12,'pero',NULL),(129,'2024-04-06 17:09:28',6,'pero',NULL),(130,'2024-04-06 17:09:36',6,'pero',NULL),(131,'2024-04-06 17:09:39',5,'pero',NULL),(132,'2024-04-06 17:09:56',6,'pero',NULL),(133,'2024-04-06 17:10:07',6,'pero',NULL),(134,'2024-04-06 23:50:20',12,'mirko',NULL),(135,'2024-04-06 23:59:42',12,'mirko',NULL),(136,'2024-04-07 00:01:00',12,'pero',NULL),(137,'2024-04-07 00:07:14',12,'mirko',NULL),(138,'2024-04-07 00:07:25',11,'aleksadra',NULL),(139,'2024-04-07 00:07:32',12,'aleksandra',NULL),(140,'2024-04-07 00:09:15',8,'aleksandra',NULL),(141,'2024-04-07 00:10:56',8,'aleksandra',NULL),(142,'2024-04-07 00:12:21',8,'aleksandra',NULL),(143,'2024-04-07 00:14:09',7,'aleksandra',NULL),(144,'2024-04-07 00:23:36',7,'aleksandra',NULL),(145,'2024-04-07 00:24:00',7,'aleksandra',NULL),(146,'2024-04-07 00:27:11',12,'mirko',NULL),(147,'2024-04-07 09:09:47',12,'aleksandra',NULL),(148,'2024-04-07 09:20:40',12,'mihailo',NULL),(149,'2024-04-07 09:22:07',11,'sofija',NULL),(150,'2024-04-07 09:22:11',12,'sofija',NULL),(151,'2024-04-07 09:23:08',12,'aleksandar',NULL),(152,'2024-04-07 09:24:04',12,'stefan',NULL),(153,'2024-04-07 09:24:28',11,'pero',NULL),(154,'2024-04-07 09:24:38',11,'pero',NULL),(155,'2024-04-07 09:24:41',11,'pero',NULL),(156,'2024-04-07 09:24:45',11,'pero',NULL),(157,'2024-04-07 09:24:49',11,'pero',NULL),(158,'2024-04-07 09:24:53',12,'pero',NULL),(159,'2024-04-07 09:32:02',12,'stefan',NULL),(160,'2024-04-07 09:32:17',12,'aleksandar',NULL);
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
INSERT INTO `superuser` VALUES (40),(41),(42),(43),(46),(47),(48);
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
INSERT INTO `tim` VALUES (4,'Design',41),(5,'HR',47),(6,'PR',40),(7,'FR',48),(8,'IT',43);
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
) ENGINE=InnoDB AUTO_INCREMENT=916 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zadatak`
--

LOCK TABLES `zadatak` WRITE;
/*!40000 ALTER TABLE `zadatak` DISABLE KEYS */;
INSERT INTO `zadatak` VALUES (29,'Oko logotipa treba da postoji razmak širine slova \"e\" u logotipu.\n\nPropisano dokumentom Brand Policy Paper - January 2021.',53,NULL,41,'2023-09-25 16:03:18','Paziti na razmak oko logotipa!','2023-02-25',1),(30,'Kreirati poster dimenzija A3 za događaj ______.\n\nPoštovati BPP!\n\nPošto je za štampanje, raditi u CMYK color mode-u. ',4,'2023-10-06 16:06:00',41,'2023-09-25 16:06:55','Kreirati poster za događaj','2024-02-25',1),(31,'Napraviti u Canvi okvir za slike za predstojeći događaj gdje će PR tim kasnije moći da dodaje slike. \n\nPoštovati BPP. \n\nKad bude gotovo, uploadovati na Google Drive PR tima.',4,'2024-04-09 20:14:00',41,'2023-09-25 16:09:48','Napraviti okvir za slike za događaj','2024-02-25',0),(32,'Potrebno napraviti objavu za drustvene mreze za najavu predavanja. \n\nPotrebno da bude u 3 dimenzije: Instagram story. Instagram objava, Facebook cover image.',4,'2023-09-30 16:13:00',41,'2023-09-25 16:14:57','Dizajnirati objavu za najavu predavanja','2024-03-25',1),(33,'Navesti osnovne informacije: mjesto, vrijeme i datum odrzavanja. \nNapisati ko je predavac, i par recenica o predavacu. \nNapisati koja je tema.\nKoristiti dosta emotikona.\nIzmjena',10,'2024-06-05 18:23:00',40,'2023-09-25 16:24:12','Napisati opis za objavu u najavi dogadjaja','2024-01-25',1),(35,'Čestitaj Stefanu rođendan.',10,'2024-02-23 09:45:00',40,'2023-12-17 21:45:52','Rođendan','2024-01-25',1),(126,'random_text_1',4,'2023-01-14 00:00:00',41,'2023-01-16 00:00:00','Title','2023-01-17',1),(127,'random_text_2',4,'2023-01-15 00:00:00',41,'2023-01-31 00:00:00','Title','2023-01-31',1),(128,'random_text_3',4,'2023-01-02 00:00:00',41,'2023-01-21 00:00:00','Title','2023-01-22',1),(129,'random_text_1',4,'2023-02-08 00:00:00',41,'2023-02-22 00:00:00','Title','2023-02-23',1),(130,'random_text_2',4,'2023-02-07 00:00:00',41,'2023-02-21 00:00:00','Title','2023-02-22',1),(131,'random_text_3',4,'2023-02-05 00:00:00',41,'2023-02-20 00:00:00','Title','2023-02-21',1),(132,'random_text_4',4,'2023-02-01 00:00:00',41,'2023-02-17 00:00:00','Title','2023-02-18',1),(133,'random_text_5',4,'2023-02-10 00:00:00',41,'2023-02-26 00:00:00','Title','2023-02-27',1),(134,'random_text_1',4,'2023-03-04 00:00:00',41,'2023-03-22 00:00:00','Title','2023-03-23',1),(135,'random_text_2',4,'2023-03-10 00:00:00',41,'2023-03-28 00:00:00','Title','2023-03-29',1),(136,'random_text_3',4,'2023-03-13 00:00:00',41,'2023-03-25 00:00:00','Title','2023-03-26',1),(137,'random_text_4',4,'2023-03-10 00:00:00',41,'2023-03-27 00:00:00','Title','2023-03-28',1),(138,'random_text_5',4,'2023-03-14 00:00:00',41,'2023-03-20 00:00:00','Title','2023-03-21',1),(139,'random_text_6',4,'2023-03-07 00:00:00',41,'2023-03-29 00:00:00','Title','2023-03-30',1),(140,'random_text_7',4,'2023-03-01 00:00:00',41,'2023-03-28 00:00:00','Title','2023-03-29',1),(141,'random_text_1',4,'2023-04-02 00:00:00',41,'2023-04-25 00:00:00','Title','2023-04-26',1),(142,'random_text_2',4,'2023-04-07 00:00:00',41,'2023-04-27 00:00:00','Title','2023-04-28',1),(143,'random_text_3',4,'2023-04-13 00:00:00',41,'2023-04-19 00:00:00','Title','2023-04-20',1),(144,'random_text_4',4,'2023-04-12 00:00:00',41,'2023-04-23 00:00:00','Title','2023-04-24',1),(145,'random_text_5',4,'2023-04-01 00:00:00',41,'2023-04-18 00:00:00','Title','2023-04-19',1),(146,'random_text_6',4,'2023-04-10 00:00:00',41,'2023-04-29 00:00:00','Title','2023-04-30',1),(147,'random_text_7',4,'2023-04-03 00:00:00',41,'2023-04-17 00:00:00','Title','2023-04-18',1),(148,'random_text_8',4,'2023-04-09 00:00:00',41,'2023-04-29 00:00:00','Title','2023-04-30',1),(149,'random_text_9',4,'2023-04-04 00:00:00',41,'2023-04-22 00:00:00','Title','2023-04-23',1),(150,'random_text_10',4,'2023-04-15 00:00:00',41,'2023-04-26 00:00:00','Title','2023-04-27',1),(151,'random_text_1',4,'2023-05-15 00:00:00',41,'2023-05-19 00:00:00','Title','2023-05-20',1),(152,'random_text_1',4,'2023-06-11 00:00:00',41,'2023-06-28 00:00:00','Title','2023-06-29',1),(153,'random_text_2',4,'2023-06-07 00:00:00',41,'2023-06-20 00:00:00','Title','2023-06-21',1),(154,'random_text_3',4,'2023-06-09 00:00:00',41,'2023-06-29 00:00:00','Title','2023-06-30',1),(155,'random_text_4',4,'2023-06-10 00:00:00',41,'2023-06-23 00:00:00','Title','2023-06-24',1),(156,'random_text_5',4,'2023-06-04 00:00:00',41,'2023-06-15 00:00:00','Title','2023-06-16',1),(157,'random_text_6',4,'2023-06-03 00:00:00',41,'2023-06-18 00:00:00','Title','2023-06-19',1),(158,'random_text_7',4,'2023-06-15 00:00:00',41,'2023-06-17 00:00:00','Title','2023-06-18',1),(159,'random_text_8',4,'2023-06-11 00:00:00',41,'2023-06-22 00:00:00','Title','2023-06-23',1),(160,'random_text_1',4,'2023-07-14 00:00:00',41,'2023-07-23 00:00:00','Title','2023-07-24',1),(161,'random_text_2',4,'2023-07-05 00:00:00',41,'2023-07-31 00:00:00','Title','2023-07-31',1),(162,'random_text_3',4,'2023-07-03 00:00:00',41,'2023-07-18 00:00:00','Title','2023-07-19',1),(163,'random_text_4',4,'2023-07-14 00:00:00',41,'2023-07-29 00:00:00','Title','2023-07-30',1),(164,'random_text_5',4,'2023-07-03 00:00:00',41,'2023-07-20 00:00:00','Title','2023-07-21',1),(165,'random_text_6',4,'2023-07-14 00:00:00',41,'2023-07-22 00:00:00','Title','2023-07-23',1),(166,'random_text_7',4,'2023-07-07 00:00:00',41,'2023-07-18 00:00:00','Title','2023-07-19',1),(167,'random_text_8',4,'2023-07-02 00:00:00',41,'2023-07-20 00:00:00','Title','2023-07-21',1),(168,'random_text_9',4,'2023-07-11 00:00:00',41,'2023-07-23 00:00:00','Title','2023-07-24',1),(169,'random_text_10',4,'2023-07-09 00:00:00',41,'2023-07-22 00:00:00','Title','2023-07-23',1),(170,'random_text_1',4,'2023-08-01 00:00:00',41,'2023-08-24 00:00:00','Title','2023-08-25',1),(171,'random_text_2',4,'2023-08-01 00:00:00',41,'2023-08-25 00:00:00','Title','2023-08-26',1),(172,'random_text_3',4,'2023-08-01 00:00:00',41,'2023-08-18 00:00:00','Title','2023-08-19',1),(173,'random_text_4',4,'2023-08-05 00:00:00',41,'2023-08-28 00:00:00','Title','2023-08-29',1),(174,'random_text_5',4,'2023-08-06 00:00:00',41,'2023-08-16 00:00:00','Title','2023-08-17',1),(175,'random_text_6',4,'2023-08-05 00:00:00',41,'2023-08-19 00:00:00','Title','2023-08-20',1),(176,'random_text_7',4,'2023-08-07 00:00:00',41,'2023-08-27 00:00:00','Title','2023-08-28',1),(177,'random_text_8',4,'2023-08-05 00:00:00',41,'2023-08-20 00:00:00','Title','2023-08-21',1),(178,'random_text_1',4,'2023-09-14 00:00:00',41,'2023-09-19 00:00:00','Title','2023-09-20',1),(179,'random_text_2',4,'2023-09-05 00:00:00',41,'2023-09-27 00:00:00','Title','2023-09-28',1),(180,'random_text_1',4,'2023-10-15 00:00:00',41,'2023-10-19 00:00:00','Title','2023-10-20',1),(181,'random_text_2',4,'2023-10-09 00:00:00',41,'2023-10-22 00:00:00','Title','2023-10-23',1),(182,'random_text_3',4,'2023-10-07 00:00:00',41,'2023-10-24 00:00:00','Title','2023-10-25',1),(183,'random_text_4',4,'2023-10-06 00:00:00',41,'2023-10-27 00:00:00','Title','2023-10-28',1),(184,'random_text_5',4,'2023-10-11 00:00:00',41,'2023-10-22 00:00:00','Title','2023-10-23',1),(185,'random_text_6',4,'2023-10-10 00:00:00',41,'2023-10-18 00:00:00','Title','2023-10-19',1),(186,'random_text_7',4,'2023-10-10 00:00:00',41,'2023-10-30 00:00:00','Title','2023-10-31',1),(187,'random_text_8',4,'2023-10-04 00:00:00',41,'2023-10-25 00:00:00','Title','2023-10-26',1),(188,'random_text_9',4,'2023-10-04 00:00:00',41,'2023-10-16 00:00:00','Title','2023-10-17',1),(189,'random_text_1',4,'2023-11-06 00:00:00',41,'2023-11-22 00:00:00','Title','2023-11-23',1),(190,'random_text_2',4,'2023-11-09 00:00:00',41,'2023-11-25 00:00:00','Title','2023-11-26',1),(191,'random_text_3',4,'2023-11-03 00:00:00',41,'2023-11-23 00:00:00','Title','2023-11-24',1),(192,'random_text_4',4,'2023-11-10 00:00:00',41,'2023-11-19 00:00:00','Title','2023-11-20',1),(193,'random_text_5',4,'2023-11-09 00:00:00',41,'2023-11-26 00:00:00','Title','2023-11-27',1),(194,'random_text_1',4,'2023-12-06 00:00:00',41,'2023-12-26 00:00:00','Title','2023-12-27',1),(195,'random_text_2',4,'2023-12-09 00:00:00',41,'2023-12-28 00:00:00','Title','2023-12-29',1),(196,'random_text_3',4,'2023-12-15 00:00:00',41,'2023-12-31 00:00:00','Title','2023-12-31',1),(197,'random_text_4',4,'2023-12-13 00:00:00',41,'2023-12-15 00:00:00','Title','2023-12-16',1),(198,'random_text_5',4,'2023-12-11 00:00:00',41,'2023-12-26 00:00:00','Title','2023-12-27',1),(199,'random_text_6',4,'2023-12-06 00:00:00',41,'2023-12-22 00:00:00','Title','2023-12-23',1),(200,'random_text_1',8,'2023-01-15 00:00:00',47,'2023-01-29 00:00:00','Title','2023-01-30',1),(201,'random_text_2',8,'2023-01-09 00:00:00',47,'2023-01-28 00:00:00','Title','2023-01-29',1),(202,'random_text_3',8,'2023-01-06 00:00:00',47,'2023-01-22 00:00:00','Title','2023-01-23',1),(203,'random_text_4',8,'2023-01-15 00:00:00',47,'2023-01-27 00:00:00','Title','2023-01-28',1),(204,'random_text_5',8,'2023-01-11 00:00:00',47,'2023-01-21 00:00:00','Title','2023-01-22',1),(205,'random_text_6',8,'2023-01-11 00:00:00',47,'2023-01-18 00:00:00','Title','2023-01-19',1),(206,'random_text_7',8,'2023-01-13 00:00:00',47,'2023-01-16 00:00:00','Title','2023-01-17',1),(207,'random_text_8',8,'2023-01-11 00:00:00',47,'2023-01-23 00:00:00','Title','2023-01-24',1),(208,'random_text_9',8,'2023-01-07 00:00:00',47,'2023-01-18 00:00:00','Title','2023-01-19',1),(209,'random_text_1',8,'2023-02-12 00:00:00',47,'2023-02-17 00:00:00','Title','2023-02-18',1),(210,'random_text_2',8,'2023-02-14 00:00:00',47,'2023-02-18 00:00:00','Title','2023-02-19',1),(211,'random_text_3',8,'2023-02-03 00:00:00',47,'2023-02-18 00:00:00','Title','2023-02-19',1),(212,'random_text_4',8,'2023-02-01 00:00:00',47,'2023-02-23 00:00:00','Title','2023-02-24',1),(213,'random_text_5',8,'2023-02-06 00:00:00',47,'2023-02-28 00:00:00','Title','2023-02-28',1),(214,'random_text_6',8,'2023-02-09 00:00:00',47,'2023-02-22 00:00:00','Title','2023-02-23',1),(215,'random_text_7',8,'2023-02-11 00:00:00',47,'2023-02-24 00:00:00','Title','2023-02-25',1),(216,'random_text_1',8,'2023-03-03 00:00:00',47,'2023-03-20 00:00:00','Title','2023-03-21',1),(217,'random_text_2',8,'2023-03-10 00:00:00',47,'2023-03-19 00:00:00','Title','2023-03-20',1),(218,'random_text_3',8,'2023-03-03 00:00:00',47,'2023-03-21 00:00:00','Title','2023-03-22',1),(219,'random_text_4',8,'2023-03-11 00:00:00',47,'2023-03-18 00:00:00','Title','2023-03-19',1),(220,'random_text_5',8,'2023-03-03 00:00:00',47,'2023-03-16 00:00:00','Title','2023-03-17',1),(221,'random_text_6',8,'2023-03-14 00:00:00',47,'2023-03-20 00:00:00','Title','2023-03-21',1),(222,'random_text_7',8,'2023-03-04 00:00:00',47,'2023-03-22 00:00:00','Title','2023-03-23',1),(223,'random_text_8',8,'2023-03-05 00:00:00',47,'2023-03-21 00:00:00','Title','2023-03-22',1),(224,'random_text_1',8,'2023-04-08 00:00:00',47,'2023-04-18 00:00:00','Title','2023-04-19',1),(225,'random_text_2',8,'2023-04-01 00:00:00',47,'2023-04-18 00:00:00','Title','2023-04-19',1),(226,'random_text_3',8,'2023-04-13 00:00:00',47,'2023-04-19 00:00:00','Title','2023-04-20',1),(227,'random_text_4',8,'2023-04-12 00:00:00',47,'2023-04-29 00:00:00','Title','2023-04-30',1),(228,'random_text_5',8,'2023-04-08 00:00:00',47,'2023-04-21 00:00:00','Title','2023-04-22',1),(229,'random_text_6',8,'2023-04-01 00:00:00',47,'2023-04-16 00:00:00','Title','2023-04-17',1),(230,'random_text_7',8,'2023-04-02 00:00:00',47,'2023-04-18 00:00:00','Title','2023-04-19',1),(231,'random_text_1',8,'2023-05-11 00:00:00',47,'2023-05-31 00:00:00','Title','2023-05-31',1),(232,'random_text_2',8,'2023-05-02 00:00:00',47,'2023-05-21 00:00:00','Title','2023-05-22',1),(233,'random_text_3',8,'2023-05-08 00:00:00',47,'2023-05-25 00:00:00','Title','2023-05-26',1),(234,'random_text_4',8,'2023-05-01 00:00:00',47,'2023-05-18 00:00:00','Title','2023-05-19',1),(235,'random_text_5',8,'2023-05-10 00:00:00',47,'2023-05-23 00:00:00','Title','2023-05-24',1),(236,'random_text_6',8,'2023-05-13 00:00:00',47,'2023-05-20 00:00:00','Title','2023-05-21',1),(237,'random_text_1',8,'2023-06-05 00:00:00',47,'2023-06-18 00:00:00','Title','2023-06-19',1),(238,'random_text_2',8,'2023-06-09 00:00:00',47,'2023-06-29 00:00:00','Title','2023-06-30',1),(239,'random_text_3',8,'2023-06-15 00:00:00',47,'2023-06-24 00:00:00','Title','2023-06-25',1),(240,'random_text_4',8,'2023-06-13 00:00:00',47,'2023-06-30 00:00:00','Title','2023-06-30',1),(241,'random_text_5',8,'2023-06-01 00:00:00',47,'2023-06-19 00:00:00','Title','2023-06-20',1),(242,'random_text_6',8,'2023-06-15 00:00:00',47,'2023-06-26 00:00:00','Title','2023-06-27',1),(243,'random_text_7',8,'2023-06-13 00:00:00',47,'2023-06-16 00:00:00','Title','2023-06-17',1),(244,'random_text_1',8,'2023-07-13 00:00:00',47,'2023-07-22 00:00:00','Title','2023-07-23',1),(245,'random_text_2',8,'2023-07-06 00:00:00',47,'2023-07-25 00:00:00','Title','2023-07-26',1),(246,'random_text_3',8,'2023-07-07 00:00:00',47,'2023-07-19 00:00:00','Title','2023-07-20',1),(247,'random_text_4',8,'2023-07-11 00:00:00',47,'2023-07-22 00:00:00','Title','2023-07-23',1),(248,'random_text_5',8,'2023-07-04 00:00:00',47,'2023-07-27 00:00:00','Title','2023-07-28',1),(249,'random_text_6',8,'2023-07-11 00:00:00',47,'2023-07-31 00:00:00','Title','2023-07-31',1),(250,'random_text_7',8,'2023-07-07 00:00:00',47,'2023-07-27 00:00:00','Title','2023-07-28',1),(251,'random_text_8',8,'2023-07-02 00:00:00',47,'2023-07-27 00:00:00','Title','2023-07-28',1),(252,'random_text_9',8,'2023-07-11 00:00:00',47,'2023-07-30 00:00:00','Title','2023-07-31',1),(253,'random_text_1',8,'2023-08-09 00:00:00',47,'2023-08-27 00:00:00','Title','2023-08-28',1),(254,'random_text_2',8,'2023-08-05 00:00:00',47,'2023-08-28 00:00:00','Title','2023-08-29',1),(255,'random_text_3',8,'2023-08-11 00:00:00',47,'2023-08-16 00:00:00','Title','2023-08-17',1),(256,'random_text_4',8,'2023-08-09 00:00:00',47,'2023-08-28 00:00:00','Title','2023-08-29',1),(257,'random_text_5',8,'2023-08-01 00:00:00',47,'2023-08-21 00:00:00','Title','2023-08-22',1),(258,'random_text_6',8,'2023-08-15 00:00:00',47,'2023-08-15 00:00:00','Title','2023-08-16',1),(259,'random_text_7',8,'2023-08-12 00:00:00',47,'2023-08-22 00:00:00','Title','2023-08-23',1),(260,'random_text_8',8,'2023-08-07 00:00:00',47,'2023-08-16 00:00:00','Title','2023-08-17',1),(261,'random_text_1',8,'2023-09-03 00:00:00',47,'2023-09-28 00:00:00','Title','2023-09-29',1),(262,'random_text_2',8,'2023-09-07 00:00:00',47,'2023-09-30 00:00:00','Title','2023-09-30',1),(263,'random_text_3',8,'2023-09-15 00:00:00',47,'2023-09-25 00:00:00','Title','2023-09-26',1),(264,'random_text_4',8,'2023-09-07 00:00:00',47,'2023-09-25 00:00:00','Title','2023-09-26',1),(265,'random_text_5',8,'2023-09-15 00:00:00',47,'2023-09-25 00:00:00','Title','2023-09-26',1),(266,'random_text_6',8,'2023-09-12 00:00:00',47,'2023-09-26 00:00:00','Title','2023-09-27',1),(267,'random_text_7',8,'2023-09-15 00:00:00',47,'2023-09-16 00:00:00','Title','2023-09-17',1),(268,'random_text_1',8,'2023-10-08 00:00:00',47,'2023-10-24 00:00:00','Title','2023-10-25',1),(269,'random_text_2',8,'2023-10-03 00:00:00',47,'2023-10-24 00:00:00','Title','2023-10-25',1),(270,'random_text_3',8,'2023-10-04 00:00:00',47,'2023-10-25 00:00:00','Title','2023-10-26',1),(271,'random_text_4',8,'2023-10-10 00:00:00',47,'2023-10-19 00:00:00','Title','2023-10-20',1),(272,'random_text_5',8,'2023-10-12 00:00:00',47,'2023-10-31 00:00:00','Title','2023-10-31',1),(273,'random_text_6',8,'2023-10-15 00:00:00',47,'2023-10-18 00:00:00','Title','2023-10-19',1),(274,'random_text_7',8,'2023-10-07 00:00:00',47,'2023-10-18 00:00:00','Title','2023-10-19',1),(275,'random_text_8',8,'2023-10-02 00:00:00',47,'2023-10-28 00:00:00','Title','2023-10-29',1),(276,'random_text_9',8,'2023-10-10 00:00:00',47,'2023-10-21 00:00:00','Title','2023-10-22',1),(277,'random_text_10',8,'2023-10-13 00:00:00',47,'2023-10-28 00:00:00','Title','2023-10-29',1),(278,'random_text_1',8,'2023-11-11 00:00:00',47,'2023-11-26 00:00:00','Title','2023-11-27',1),(279,'random_text_2',8,'2023-11-09 00:00:00',47,'2023-11-24 00:00:00','Title','2023-11-25',1),(280,'random_text_3',8,'2023-11-11 00:00:00',47,'2023-11-22 00:00:00','Title','2023-11-23',1),(281,'random_text_4',8,'2023-11-06 00:00:00',47,'2023-11-23 00:00:00','Title','2023-11-24',1),(282,'random_text_1',8,'2023-12-08 00:00:00',47,'2023-12-27 00:00:00','Title','2023-12-28',1),(283,'random_text_2',8,'2023-12-06 00:00:00',47,'2023-12-31 00:00:00','Title','2023-12-31',1),(284,'random_text_3',8,'2023-12-14 00:00:00',47,'2023-12-27 00:00:00','Title','2023-12-28',1),(285,'random_text_4',8,'2023-12-10 00:00:00',47,'2023-12-19 00:00:00','Title','2023-12-20',1),(286,'random_text_5',8,'2023-12-08 00:00:00',47,'2023-12-19 00:00:00','Title','2023-12-20',1),(287,'random_text_6',8,'2023-12-10 00:00:00',47,'2023-12-21 00:00:00','Title','2023-12-22',1),(288,'random_text_7',8,'2023-12-01 00:00:00',47,'2023-12-18 00:00:00','Title','2023-12-19',1),(289,'random_text_6',6,'2023-12-05 00:00:00',40,'2023-12-23 00:00:00','Title','2023-12-24',1),(290,'random_text_1',6,'2023-01-12 00:00:00',40,'2023-01-17 00:00:00','Title','2023-01-18',1),(291,'random_text_1',6,'2023-02-05 00:00:00',40,'2023-02-22 00:00:00','Title','2023-02-23',1),(292,'random_text_2',6,'2023-02-02 00:00:00',40,'2023-02-21 00:00:00','Title','2023-02-22',1),(293,'random_text_3',6,'2023-02-08 00:00:00',40,'2023-02-20 00:00:00','Title','2023-02-21',1),(294,'random_text_4',6,'2023-02-07 00:00:00',40,'2023-02-18 00:00:00','Title','2023-02-19',1),(295,'random_text_5',6,'2023-02-01 00:00:00',40,'2023-02-25 00:00:00','Title','2023-02-26',1),(296,'random_text_6',6,'2023-02-09 00:00:00',40,'2023-02-25 00:00:00','Title','2023-02-26',1),(297,'random_text_7',6,'2023-02-08 00:00:00',40,'2023-02-25 00:00:00','Title','2023-02-26',1),(298,'random_text_8',6,'2023-02-01 00:00:00',40,'2023-02-26 00:00:00','Title','2023-02-27',1),(299,'random_text_1',6,'2023-03-08 00:00:00',40,'2023-03-16 00:00:00','Title','2023-03-17',1),(300,'random_text_2',6,'2023-03-14 00:00:00',40,'2023-03-21 00:00:00','Title','2023-03-22',1),(301,'random_text_3',6,'2023-03-02 00:00:00',40,'2023-03-23 00:00:00','Title','2023-03-24',1),(302,'random_text_4',6,'2023-03-02 00:00:00',40,'2023-03-16 00:00:00','Title','2023-03-17',1),(303,'random_text_5',6,'2023-03-11 00:00:00',40,'2023-03-26 00:00:00','Title','2023-03-27',1),(304,'random_text_6',6,'2023-03-08 00:00:00',40,'2023-03-24 00:00:00','Title','2023-03-25',1),(305,'random_text_7',6,'2023-03-07 00:00:00',40,'2023-03-23 00:00:00','Title','2023-03-24',1),(306,'random_text_8',6,'2023-03-11 00:00:00',40,'2023-03-21 00:00:00','Title','2023-03-22',1),(307,'random_text_9',6,'2023-03-13 00:00:00',40,'2023-03-25 00:00:00','Title','2023-03-26',1),(308,'random_text_10',6,'2023-03-01 00:00:00',40,'2023-03-15 00:00:00','Title','2023-03-16',1),(309,'random_text_1',6,'2023-04-01 00:00:00',40,'2023-04-26 00:00:00','Title','2023-04-27',1),(310,'random_text_2',6,'2023-04-14 00:00:00',40,'2023-04-25 00:00:00','Title','2023-04-26',1),(311,'random_text_3',6,'2023-04-15 00:00:00',40,'2023-04-30 00:00:00','Title','2023-04-30',1),(312,'random_text_4',6,'2023-04-09 00:00:00',40,'2023-04-30 00:00:00','Title','2023-04-30',1),(313,'random_text_5',6,'2023-04-06 00:00:00',40,'2023-04-21 00:00:00','Title','2023-04-22',1),(314,'random_text_6',6,'2023-04-14 00:00:00',40,'2023-04-16 00:00:00','Title','2023-04-17',1),(315,'random_text_1',6,'2023-05-07 00:00:00',40,'2023-05-17 00:00:00','Title','2023-05-18',1),(316,'random_text_2',6,'2023-05-10 00:00:00',40,'2023-05-18 00:00:00','Title','2023-05-19',1),(317,'random_text_3',6,'2023-05-06 00:00:00',40,'2023-05-18 00:00:00','Title','2023-05-19',1),(318,'random_text_4',6,'2023-05-07 00:00:00',40,'2023-05-18 00:00:00','Title','2023-05-19',1),(319,'random_text_5',6,'2023-05-06 00:00:00',40,'2023-05-22 00:00:00','Title','2023-05-23',1),(320,'random_text_6',6,'2023-05-10 00:00:00',40,'2023-05-19 00:00:00','Title','2023-05-20',1),(321,'random_text_7',6,'2023-05-12 00:00:00',40,'2023-05-28 00:00:00','Title','2023-05-29',1),(322,'random_text_8',6,'2023-05-15 00:00:00',40,'2023-05-19 00:00:00','Title','2023-05-20',1),(323,'random_text_1',6,'2023-06-04 00:00:00',40,'2023-06-21 00:00:00','Title','2023-06-22',1),(324,'random_text_2',6,'2023-06-06 00:00:00',40,'2023-06-15 00:00:00','Title','2023-06-16',1),(325,'random_text_3',6,'2023-06-01 00:00:00',40,'2023-06-30 00:00:00','Title','2023-06-30',1),(326,'random_text_4',6,'2023-06-13 00:00:00',40,'2023-06-27 00:00:00','Title','2023-06-28',1),(327,'random_text_5',6,'2023-06-13 00:00:00',40,'2023-06-16 00:00:00','Title','2023-06-17',1),(328,'random_text_6',6,'2023-06-15 00:00:00',40,'2023-06-15 00:00:00','Title','2023-06-16',1),(329,'random_text_7',6,'2023-06-07 00:00:00',40,'2023-06-22 00:00:00','Title','2023-06-23',1),(330,'random_text_1',6,'2023-07-11 00:00:00',40,'2023-07-16 00:00:00','Title','2023-07-17',1),(331,'random_text_2',6,'2023-07-07 00:00:00',40,'2023-07-19 00:00:00','Title','2023-07-20',1),(332,'random_text_3',6,'2023-07-04 00:00:00',40,'2023-07-21 00:00:00','Title','2023-07-22',1),(333,'random_text_4',6,'2023-07-01 00:00:00',40,'2023-07-23 00:00:00','Title','2023-07-24',1),(334,'random_text_5',6,'2023-07-09 00:00:00',40,'2023-07-20 00:00:00','Title','2023-07-21',1),(335,'random_text_6',6,'2023-07-03 00:00:00',40,'2023-07-31 00:00:00','Title','2023-07-31',1),(336,'random_text_7',6,'2023-07-12 00:00:00',40,'2023-07-27 00:00:00','Title','2023-07-28',1),(337,'random_text_8',6,'2023-07-04 00:00:00',40,'2023-07-23 00:00:00','Title','2023-07-24',1),(338,'random_text_9',6,'2023-07-01 00:00:00',40,'2023-07-28 00:00:00','Title','2023-07-29',1),(339,'random_text_10',6,'2023-07-07 00:00:00',40,'2023-07-24 00:00:00','Title','2023-07-25',1),(340,'random_text_1',6,'2023-08-04 00:00:00',40,'2023-08-19 00:00:00','Title','2023-08-20',1),(341,'random_text_2',6,'2023-08-05 00:00:00',40,'2023-08-31 00:00:00','Title','2023-08-31',1),(342,'random_text_3',6,'2023-08-06 00:00:00',40,'2023-08-18 00:00:00','Title','2023-08-19',1),(343,'random_text_4',6,'2023-08-04 00:00:00',40,'2023-08-19 00:00:00','Title','2023-08-20',1),(344,'random_text_5',6,'2023-08-04 00:00:00',40,'2023-08-20 00:00:00','Title','2023-08-21',1),(345,'random_text_6',6,'2023-08-02 00:00:00',40,'2023-08-23 00:00:00','Title','2023-08-24',1),(346,'random_text_7',6,'2023-08-08 00:00:00',40,'2023-08-22 00:00:00','Title','2023-08-23',1),(347,'random_text_8',6,'2023-08-02 00:00:00',40,'2023-08-28 00:00:00','Title','2023-08-29',1),(348,'random_text_9',6,'2023-08-05 00:00:00',40,'2023-08-16 00:00:00','Title','2023-08-17',1),(349,'random_text_1',6,'2023-09-13 00:00:00',40,'2023-09-23 00:00:00','Title','2023-09-24',1),(350,'random_text_2',6,'2023-09-12 00:00:00',40,'2023-09-27 00:00:00','Title','2023-09-28',1),(351,'random_text_3',6,'2023-09-15 00:00:00',40,'2023-09-21 00:00:00','Title','2023-09-22',1),(352,'random_text_1',6,'2023-10-05 00:00:00',40,'2023-10-26 00:00:00','Title','2023-10-27',1),(353,'random_text_2',6,'2023-10-07 00:00:00',40,'2023-10-26 00:00:00','Title','2023-10-27',1),(354,'random_text_3',6,'2023-10-08 00:00:00',40,'2023-10-27 00:00:00','Title','2023-10-28',1),(355,'random_text_4',6,'2023-10-09 00:00:00',40,'2023-10-25 00:00:00','Title','2023-10-26',1),(356,'random_text_5',6,'2023-10-01 00:00:00',40,'2023-10-21 00:00:00','Title','2023-10-22',1),(357,'random_text_6',6,'2023-10-06 00:00:00',40,'2023-10-16 00:00:00','Title','2023-10-17',1),(358,'random_text_7',6,'2023-10-02 00:00:00',40,'2023-10-25 00:00:00','Title','2023-10-26',1),(359,'random_text_8',6,'2023-10-10 00:00:00',40,'2023-10-17 00:00:00','Title','2023-10-18',1),(360,'random_text_1',6,'2023-11-15 00:00:00',40,'2023-11-16 00:00:00','Title','2023-11-17',1),(361,'random_text_2',6,'2023-11-02 00:00:00',40,'2023-11-27 00:00:00','Title','2023-11-28',1),(362,'random_text_3',6,'2023-11-11 00:00:00',40,'2023-11-22 00:00:00','Title','2023-11-23',1),(363,'random_text_4',6,'2023-11-07 00:00:00',40,'2023-11-15 00:00:00','Title','2023-11-16',1),(364,'random_text_5',6,'2023-11-05 00:00:00',40,'2023-11-22 00:00:00','Title','2023-11-23',1),(365,'random_text_6',6,'2023-11-01 00:00:00',40,'2023-11-21 00:00:00','Title','2023-11-22',1),(366,'random_text_7',6,'2023-11-13 00:00:00',40,'2023-11-15 00:00:00','Title','2023-11-16',1),(367,'random_text_1',6,'2023-12-07 00:00:00',40,'2023-12-30 00:00:00','Title','2023-12-31',1),(368,'random_text_2',6,'2023-12-15 00:00:00',40,'2023-12-23 00:00:00','Title','2023-12-24',1),(369,'random_text_3',6,'2023-12-11 00:00:00',40,'2023-12-25 00:00:00','Title','2023-12-26',1),(370,'random_text_4',6,'2023-12-02 00:00:00',40,'2023-12-29 00:00:00','Title','2023-12-30',1),(371,'random_text_5',6,'2023-12-03 00:00:00',40,'2023-12-19 00:00:00','Title','2023-12-20',1),(372,'random_text_6',6,'2023-12-05 00:00:00',40,'2023-12-23 00:00:00','Title','2023-12-24',1),(373,'random_text_1',16,'2023-01-10 00:00:00',48,'2023-01-28 00:00:00','Title','2023-01-29',1),(374,'random_text_2',16,'2023-01-05 00:00:00',48,'2023-01-27 00:00:00','Title','2023-01-28',1),(375,'random_text_3',16,'2023-01-01 00:00:00',48,'2023-01-24 00:00:00','Title','2023-01-25',1),(376,'random_text_4',16,'2023-01-09 00:00:00',48,'2023-01-28 00:00:00','Title','2023-01-29',1),(377,'random_text_5',16,'2023-01-09 00:00:00',48,'2023-01-22 00:00:00','Title','2023-01-23',1),(378,'random_text_1',16,'2023-02-02 00:00:00',48,'2023-02-19 00:00:00','Title','2023-02-20',1),(379,'random_text_2',16,'2023-02-08 00:00:00',48,'2023-02-22 00:00:00','Title','2023-02-23',1),(380,'random_text_1',16,'2023-03-11 00:00:00',48,'2023-03-23 00:00:00','Title','2023-03-24',1),(381,'random_text_2',16,'2023-03-05 00:00:00',48,'2023-03-16 00:00:00','Title','2023-03-17',1),(382,'random_text_3',16,'2023-03-10 00:00:00',48,'2023-03-20 00:00:00','Title','2023-03-21',1),(383,'random_text_4',16,'2023-03-08 00:00:00',48,'2023-03-30 00:00:00','Title','2023-03-31',1),(384,'random_text_5',16,'2023-03-07 00:00:00',48,'2023-03-17 00:00:00','Title','2023-03-18',1),(385,'random_text_6',16,'2023-03-12 00:00:00',48,'2023-03-16 00:00:00','Title','2023-03-17',1),(386,'random_text_7',16,'2023-03-07 00:00:00',48,'2023-03-16 00:00:00','Title','2023-03-17',1),(387,'random_text_8',16,'2023-03-08 00:00:00',48,'2023-03-28 00:00:00','Title','2023-03-29',1),(388,'random_text_9',16,'2023-03-10 00:00:00',48,'2023-03-26 00:00:00','Title','2023-03-27',1),(389,'random_text_10',16,'2023-03-10 00:00:00',48,'2023-03-21 00:00:00','Title','2023-03-22',1),(390,'random_text_1',16,'2023-04-09 00:00:00',48,'2023-04-21 00:00:00','Title','2023-04-22',1),(391,'random_text_2',16,'2023-04-13 00:00:00',48,'2023-04-30 00:00:00','Title','2023-04-30',1),(392,'random_text_1',16,'2023-05-01 00:00:00',48,'2023-05-15 00:00:00','Title','2023-05-16',1),(393,'random_text_2',16,'2023-05-07 00:00:00',48,'2023-05-27 00:00:00','Title','2023-05-28',1),(394,'random_text_1',16,'2023-06-07 00:00:00',48,'2023-06-29 00:00:00','Title','2023-06-30',1),(395,'random_text_2',16,'2023-06-03 00:00:00',48,'2023-06-23 00:00:00','Title','2023-06-24',1),(396,'random_text_3',16,'2023-06-14 00:00:00',48,'2023-06-25 00:00:00','Title','2023-06-26',1),(397,'random_text_4',16,'2023-06-07 00:00:00',48,'2023-06-25 00:00:00','Title','2023-06-26',1),(398,'random_text_5',16,'2023-06-04 00:00:00',48,'2023-06-21 00:00:00','Title','2023-06-22',1),(399,'random_text_6',16,'2023-06-11 00:00:00',48,'2023-06-28 00:00:00','Title','2023-06-29',1),(400,'random_text_7',16,'2023-06-02 00:00:00',48,'2023-06-16 00:00:00','Title','2023-06-17',1),(401,'random_text_8',16,'2023-06-08 00:00:00',48,'2023-06-17 00:00:00','Title','2023-06-18',1),(402,'random_text_9',16,'2023-06-02 00:00:00',48,'2023-06-23 00:00:00','Title','2023-06-24',1),(403,'random_text_1',16,'2023-07-06 00:00:00',48,'2023-07-30 00:00:00','Title','2023-07-31',1),(404,'random_text_2',16,'2023-07-08 00:00:00',48,'2023-07-17 00:00:00','Title','2023-07-18',1),(405,'random_text_3',16,'2023-07-02 00:00:00',48,'2023-07-20 00:00:00','Title','2023-07-21',1),(406,'random_text_4',16,'2023-07-01 00:00:00',48,'2023-07-18 00:00:00','Title','2023-07-19',1),(407,'random_text_5',16,'2023-07-11 00:00:00',48,'2023-07-16 00:00:00','Title','2023-07-17',1),(408,'random_text_6',16,'2023-07-09 00:00:00',48,'2023-07-23 00:00:00','Title','2023-07-24',1),(409,'random_text_7',16,'2023-07-14 00:00:00',48,'2023-07-15 00:00:00','Title','2023-07-16',1),(410,'random_text_1',16,'2023-08-10 00:00:00',48,'2023-08-22 00:00:00','Title','2023-08-23',1),(411,'random_text_2',16,'2023-08-05 00:00:00',48,'2023-08-16 00:00:00','Title','2023-08-17',1),(412,'random_text_3',16,'2023-08-01 00:00:00',48,'2023-08-16 00:00:00','Title','2023-08-17',1),(413,'random_text_4',16,'2023-08-10 00:00:00',48,'2023-08-27 00:00:00','Title','2023-08-28',1),(414,'random_text_5',16,'2023-08-13 00:00:00',48,'2023-08-31 00:00:00','Title','2023-08-31',1),(415,'random_text_6',16,'2023-08-08 00:00:00',48,'2023-08-29 00:00:00','Title','2023-08-30',1),(416,'random_text_1',16,'2023-09-03 00:00:00',48,'2023-09-25 00:00:00','Title','2023-09-26',1),(417,'random_text_2',16,'2023-09-07 00:00:00',48,'2023-09-18 00:00:00','Title','2023-09-19',1),(418,'random_text_3',16,'2023-09-08 00:00:00',48,'2023-09-21 00:00:00','Title','2023-09-22',1),(419,'random_text_4',16,'2023-09-08 00:00:00',48,'2023-09-24 00:00:00','Title','2023-09-25',1),(420,'random_text_5',16,'2023-09-10 00:00:00',48,'2023-09-26 00:00:00','Title','2023-09-27',1),(421,'random_text_6',16,'2023-09-15 00:00:00',48,'2023-09-28 00:00:00','Title','2023-09-29',1),(422,'random_text_1',16,'2023-10-05 00:00:00',48,'2023-10-27 00:00:00','Title','2023-10-28',1),(423,'random_text_2',16,'2023-10-07 00:00:00',48,'2023-10-25 00:00:00','Title','2023-10-26',1),(424,'random_text_3',16,'2023-10-06 00:00:00',48,'2023-10-30 00:00:00','Title','2023-10-31',1),(425,'random_text_4',16,'2023-10-06 00:00:00',48,'2023-10-20 00:00:00','Title','2023-10-21',1),(426,'random_text_5',16,'2023-10-03 00:00:00',48,'2023-10-19 00:00:00','Title','2023-10-20',1),(427,'random_text_6',16,'2023-10-01 00:00:00',48,'2023-10-26 00:00:00','Title','2023-10-27',1),(428,'random_text_1',16,'2023-11-12 00:00:00',48,'2023-11-17 00:00:00','Title','2023-11-18',1),(429,'random_text_2',16,'2023-11-06 00:00:00',48,'2023-11-25 00:00:00','Title','2023-11-26',1),(430,'random_text_3',16,'2023-11-07 00:00:00',48,'2023-11-30 00:00:00','Title','2023-11-30',1),(431,'random_text_4',16,'2023-11-15 00:00:00',48,'2023-11-25 00:00:00','Title','2023-11-26',1),(432,'random_text_5',16,'2023-11-14 00:00:00',48,'2023-11-23 00:00:00','Title','2023-11-24',1),(433,'random_text_6',16,'2023-11-12 00:00:00',48,'2023-11-28 00:00:00','Title','2023-11-29',1),(434,'random_text_1',16,'2023-12-12 00:00:00',48,'2023-12-28 00:00:00','Title','2023-12-29',1),(435,'random_text_2',16,'2023-12-06 00:00:00',48,'2023-12-23 00:00:00','Title','2023-12-24',1),(436,'random_text_3',16,'2023-12-04 00:00:00',48,'2023-12-29 00:00:00','Title','2023-12-30',1),(437,'random_text_4',16,'2023-12-06 00:00:00',48,'2023-12-24 00:00:00','Title','2023-12-25',1),(438,'random_text_1',20,'2023-01-04 00:00:00',43,'2023-01-29 00:00:00','Title','2023-01-30',1),(439,'random_text_2',20,'2023-01-07 00:00:00',43,'2023-01-20 00:00:00','Title','2023-01-21',1),(440,'random_text_3',20,'2023-01-12 00:00:00',43,'2023-01-25 00:00:00','Title','2023-01-26',1),(441,'random_text_4',20,'2023-01-11 00:00:00',43,'2023-01-18 00:00:00','Title','2023-01-19',1),(442,'random_text_1',20,'2023-02-10 00:00:00',43,'2023-02-23 00:00:00','Title','2023-02-24',1),(443,'random_text_1',20,'2023-03-01 00:00:00',43,'2023-03-25 00:00:00','Title','2023-03-26',1),(444,'random_text_2',20,'2023-03-15 00:00:00',43,'2023-03-27 00:00:00','Title','2023-03-28',1),(445,'random_text_1',20,'2023-04-04 00:00:00',43,'2023-04-26 00:00:00','Title','2023-04-27',1),(446,'random_text_2',20,'2023-04-12 00:00:00',43,'2023-04-16 00:00:00','Title','2023-04-17',1),(447,'random_text_1',20,'2023-05-10 00:00:00',43,'2023-05-18 00:00:00','Title','2023-05-19',1),(448,'random_text_2',20,'2023-05-11 00:00:00',43,'2023-05-29 00:00:00','Title','2023-05-30',1),(449,'random_text_3',20,'2023-05-06 00:00:00',43,'2023-05-20 00:00:00','Title','2023-05-21',1),(450,'random_text_1',20,'2023-06-09 00:00:00',43,'2023-06-27 00:00:00','Title','2023-06-28',1),(451,'random_text_2',20,'2023-06-13 00:00:00',43,'2023-06-17 00:00:00','Title','2023-06-18',1),(452,'random_text_1',20,'2023-07-15 00:00:00',43,'2023-07-22 00:00:00','Title','2023-07-23',1),(453,'random_text_2',20,'2023-07-03 00:00:00',43,'2023-07-24 00:00:00','Title','2023-07-25',1),(454,'random_text_3',20,'2023-07-14 00:00:00',43,'2023-07-27 00:00:00','Title','2023-07-28',1),(455,'random_text_4',20,'2023-07-02 00:00:00',43,'2023-07-30 00:00:00','Title','2023-07-31',1),(456,'random_text_5',20,'2023-07-02 00:00:00',43,'2023-07-22 00:00:00','Title','2023-07-23',1),(457,'random_text_6',20,'2023-07-04 00:00:00',43,'2023-07-19 00:00:00','Title','2023-07-20',1),(458,'random_text_7',20,'2023-07-04 00:00:00',43,'2023-07-28 00:00:00','Title','2023-07-29',1),(459,'random_text_1',20,'2023-08-02 00:00:00',43,'2023-08-16 00:00:00','Title','2023-08-17',1),(460,'random_text_2',20,'2023-08-01 00:00:00',43,'2023-08-29 00:00:00','Title','2023-08-30',1),(461,'random_text_3',20,'2023-08-15 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(462,'random_text_4',20,'2023-08-03 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(463,'random_text_5',20,'2023-08-08 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(464,'random_text_6',20,'2023-08-10 00:00:00',43,'2023-08-26 00:00:00','Title','2023-08-27',1),(465,'random_text_1',20,'2023-09-15 00:00:00',43,'2023-09-25 00:00:00','Title','2023-09-26',1),(466,'random_text_2',20,'2023-09-04 00:00:00',43,'2023-09-20 00:00:00','Title','2023-09-21',1),(467,'random_text_3',20,'2023-09-05 00:00:00',43,'2023-09-30 00:00:00','Title','2023-09-30',1),(468,'random_text_4',20,'2023-09-10 00:00:00',43,'2023-09-21 00:00:00','Title','2023-09-22',1),(469,'random_text_5',20,'2023-09-03 00:00:00',43,'2023-09-17 00:00:00','Title','2023-09-18',1),(470,'random_text_6',20,'2023-09-04 00:00:00',43,'2023-09-25 00:00:00','Title','2023-09-26',1),(471,'random_text_7',20,'2023-09-06 00:00:00',43,'2023-09-20 00:00:00','Title','2023-09-21',1),(472,'random_text_8',20,'2023-09-06 00:00:00',43,'2023-09-18 00:00:00','Title','2023-09-19',1),(473,'random_text_9',20,'2023-09-03 00:00:00',43,'2023-09-17 00:00:00','Title','2023-09-18',1),(474,'random_text_10',20,'2023-09-12 00:00:00',43,'2023-09-26 00:00:00','Title','2023-09-27',1),(475,'random_text_1',20,'2023-10-12 00:00:00',43,'2023-10-18 00:00:00','Title','2023-10-19',1),(476,'random_text_2',20,'2023-10-11 00:00:00',43,'2023-10-23 00:00:00','Title','2023-10-24',1),(477,'random_text_3',20,'2023-10-06 00:00:00',43,'2023-10-20 00:00:00','Title','2023-10-21',1),(478,'random_text_4',20,'2023-10-07 00:00:00',43,'2023-10-20 00:00:00','Title','2023-10-21',1),(479,'random_text_5',20,'2023-10-04 00:00:00',43,'2023-10-17 00:00:00','Title','2023-10-18',1),(480,'random_text_6',20,'2023-10-01 00:00:00',43,'2023-10-28 00:00:00','Title','2023-10-29',1),(481,'random_text_1',20,'2023-11-15 00:00:00',43,'2023-11-28 00:00:00','Title','2023-11-29',1),(482,'random_text_1',20,'2023-12-06 00:00:00',43,'2023-12-18 00:00:00','Title','2023-12-19',1),(483,'random_text_1',20,'2023-01-04 00:00:00',43,'2023-01-29 00:00:00','Title','2023-01-30',1),(484,'random_text_2',20,'2023-01-07 00:00:00',43,'2023-01-20 00:00:00','Title','2023-01-21',1),(485,'random_text_3',20,'2023-01-12 00:00:00',43,'2023-01-25 00:00:00','Title','2023-01-26',1),(486,'random_text_4',20,'2023-01-11 00:00:00',43,'2023-01-18 00:00:00','Title','2023-01-19',1),(487,'random_text_1',20,'2023-02-10 00:00:00',43,'2023-02-23 00:00:00','Title','2023-02-24',1),(488,'random_text_1',20,'2023-03-01 00:00:00',43,'2023-03-25 00:00:00','Title','2023-03-26',1),(489,'random_text_2',20,'2023-03-15 00:00:00',43,'2023-03-27 00:00:00','Title','2023-03-28',1),(490,'random_text_1',20,'2023-04-04 00:00:00',43,'2023-04-26 00:00:00','Title','2023-04-27',1),(491,'random_text_2',20,'2023-04-12 00:00:00',43,'2023-04-16 00:00:00','Title','2023-04-17',1),(492,'random_text_1',20,'2023-05-10 00:00:00',43,'2023-05-18 00:00:00','Title','2023-05-19',1),(493,'random_text_2',20,'2023-05-11 00:00:00',43,'2023-05-29 00:00:00','Title','2023-05-30',1),(494,'random_text_3',20,'2023-05-06 00:00:00',43,'2023-05-20 00:00:00','Title','2023-05-21',1),(495,'random_text_1',20,'2023-06-09 00:00:00',43,'2023-06-27 00:00:00','Title','2023-06-28',1),(496,'random_text_2',20,'2023-06-13 00:00:00',43,'2023-06-17 00:00:00','Title','2023-06-18',1),(497,'random_text_1',20,'2023-07-15 00:00:00',43,'2023-07-22 00:00:00','Title','2023-07-23',1),(498,'random_text_2',20,'2023-07-03 00:00:00',43,'2023-07-24 00:00:00','Title','2023-07-25',1),(499,'random_text_3',20,'2023-07-14 00:00:00',43,'2023-07-27 00:00:00','Title','2023-07-28',1),(500,'random_text_4',20,'2023-07-02 00:00:00',43,'2023-07-30 00:00:00','Title','2023-07-31',1),(501,'random_text_5',20,'2023-07-02 00:00:00',43,'2023-07-22 00:00:00','Title','2023-07-23',1),(502,'random_text_6',20,'2023-07-04 00:00:00',43,'2023-07-19 00:00:00','Title','2023-07-20',1),(503,'random_text_7',20,'2023-07-04 00:00:00',43,'2023-07-28 00:00:00','Title','2023-07-29',1),(504,'random_text_1',20,'2023-08-02 00:00:00',43,'2023-08-16 00:00:00','Title','2023-08-17',1),(505,'random_text_2',20,'2023-08-01 00:00:00',43,'2023-08-29 00:00:00','Title','2023-08-30',1),(506,'random_text_3',20,'2023-08-15 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(507,'random_text_4',20,'2023-08-03 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(508,'random_text_5',20,'2023-08-08 00:00:00',43,'2023-08-25 00:00:00','Title','2023-08-26',1),(509,'random_text_6',20,'2023-08-10 00:00:00',43,'2023-08-26 00:00:00','Title','2023-08-27',1),(510,'random_text_1',20,'2023-09-15 00:00:00',43,'2023-09-25 00:00:00','Title','2023-09-26',1),(511,'random_text_2',20,'2023-09-04 00:00:00',43,'2023-09-20 00:00:00','Title','2023-09-21',1),(512,'random_text_3',20,'2023-09-05 00:00:00',43,'2023-09-30 00:00:00','Title','2023-09-30',1),(513,'random_text_4',20,'2023-09-10 00:00:00',43,'2023-09-21 00:00:00','Title','2023-09-22',1),(514,'random_text_5',20,'2023-09-03 00:00:00',43,'2023-09-17 00:00:00','Title','2023-09-18',1),(515,'random_text_6',20,'2023-09-04 00:00:00',43,'2023-09-25 00:00:00','Title','2023-09-26',1),(516,'random_text_7',20,'2023-09-06 00:00:00',43,'2023-09-20 00:00:00','Title','2023-09-21',1),(517,'random_text_8',20,'2023-09-06 00:00:00',43,'2023-09-18 00:00:00','Title','2023-09-19',1),(518,'random_text_9',20,'2023-09-03 00:00:00',43,'2023-09-17 00:00:00','Title','2023-09-18',1),(519,'random_text_10',20,'2023-09-12 00:00:00',43,'2023-09-26 00:00:00','Title','2023-09-27',1),(520,'random_text_1',20,'2023-10-12 00:00:00',43,'2023-10-18 00:00:00','Title','2023-10-19',1),(521,'random_text_2',20,'2023-10-11 00:00:00',43,'2023-10-23 00:00:00','Title','2023-10-24',1),(522,'random_text_3',20,'2023-10-06 00:00:00',43,'2023-10-20 00:00:00','Title','2023-10-21',1),(523,'random_text_4',20,'2023-10-07 00:00:00',43,'2023-10-20 00:00:00','Title','2023-10-21',1),(524,'random_text_5',20,'2023-10-04 00:00:00',43,'2023-10-17 00:00:00','Title','2023-10-18',1),(525,'random_text_6',20,'2023-10-01 00:00:00',43,'2023-10-28 00:00:00','Title','2023-10-29',1),(526,'random_text_1',20,'2023-11-15 00:00:00',43,'2023-11-28 00:00:00','Title','2023-11-29',1),(527,'random_text_1',20,'2023-12-06 00:00:00',43,'2023-12-18 00:00:00','Title','2023-12-19',1),(528,'random_text_1',20,'2024-01-11 00:00:00',43,'2024-01-19 00:00:00','Title','2024-01-20',1),(529,'random_text_2',20,'2024-01-02 00:00:00',43,'2024-01-20 00:00:00','Title','2024-01-21',1),(530,'random_text_3',20,'2024-01-04 00:00:00',43,'2024-01-28 00:00:00','Title','2024-01-29',1),(531,'random_text_4',20,'2024-01-11 00:00:00',43,'2024-01-19 00:00:00','Title','2024-01-20',1),(532,'random_text_1',20,'2024-02-03 00:00:00',43,'2024-02-21 00:00:00','Title','2024-02-22',1),(533,'random_text_2',20,'2024-02-10 00:00:00',43,'2024-02-20 00:00:00','Title','2024-02-21',1),(534,'random_text_3',20,'2024-02-14 00:00:00',43,'2024-02-20 00:00:00','Title','2024-02-21',1),(535,'random_text_4',20,'2024-02-02 00:00:00',43,'2024-02-19 00:00:00','Title','2024-02-20',1),(536,'random_text_5',20,'2024-02-06 00:00:00',43,'2024-02-28 00:00:00','Title','2024-02-29',1),(537,'random_text_6',20,'2024-02-12 00:00:00',43,'2024-02-26 00:00:00','Title','2024-02-27',1),(538,'random_text_7',20,'2024-02-14 00:00:00',43,'2024-02-14 00:00:00','Title','2024-02-15',1),(539,'random_text_8',20,'2024-02-10 00:00:00',43,'2024-02-21 00:00:00','Title','2024-02-22',1),(540,'random_text_1',20,'2024-03-10 00:00:00',43,'2024-03-22 00:00:00','Title','2024-03-23',1),(541,'random_text_2',20,'2024-03-12 00:00:00',43,'2024-03-24 00:00:00','Title','2024-03-25',1),(580,'random_text_1',6,'2024-01-09 00:00:00',40,'2024-01-27 00:00:00','Title','2024-01-28',1),(581,'random_text_2',6,'2024-01-12 00:00:00',40,'2024-01-25 00:00:00','Title','2024-01-26',1),(582,'random_text_3',6,'2024-01-02 00:00:00',40,'2024-01-18 00:00:00','Title','2024-01-19',1),(583,'random_text_1',6,'2024-02-08 00:00:00',40,'2024-02-21 00:00:00','Title','2024-02-22',1),(584,'random_text_1',6,'2024-03-08 00:00:00',40,'2024-03-16 00:00:00','Title','2024-03-17',1),(585,'random_text_2',6,'2024-03-05 00:00:00',40,'2024-03-29 00:00:00','Title','2024-03-30',1),(586,'random_text_3',6,'2024-03-03 00:00:00',40,'2024-03-20 00:00:00','Title','2024-03-21',1),(587,'random_text_4',6,'2024-03-11 00:00:00',40,'2024-03-25 00:00:00','Title','2024-03-26',1),(588,'random_text_5',6,'2024-03-01 00:00:00',40,'2024-03-24 00:00:00','Title','2024-03-25',1),(589,'random_text_6',6,'2024-03-10 00:00:00',40,'2024-03-28 00:00:00','Title','2024-03-29',1),(590,'random_text_7',6,'2024-03-15 00:00:00',40,'2024-03-30 00:00:00','Title','2024-03-31',1),(591,'random_text_8',6,'2024-03-14 00:00:00',40,'2024-03-26 00:00:00','Title','2024-03-27',1),(643,'random_text_1',8,'2024-01-01 00:00:00',47,'2024-01-30 00:00:00','Title','2024-01-31',1),(644,'random_text_1',8,'2024-02-01 00:00:00',47,'2024-02-16 00:00:00','Title','2024-02-17',1),(645,'random_text_2',8,'2024-02-14 00:00:00',47,'2024-02-25 00:00:00','Title','2024-02-26',1),(646,'random_text_3',8,'2024-02-11 00:00:00',47,'2024-02-25 00:00:00','Title','2024-02-26',1),(647,'random_text_4',8,'2024-02-14 00:00:00',47,'2024-02-18 00:00:00','Title','2024-02-19',1),(648,'random_text_5',8,'2024-02-13 00:00:00',47,'2024-02-20 00:00:00','Title','2024-02-21',1),(649,'random_text_6',8,'2024-02-07 00:00:00',47,'2024-02-28 00:00:00','Title','2024-02-29',1),(650,'random_text_7',8,'2024-02-02 00:00:00',47,'2024-02-16 00:00:00','Title','2024-02-17',1),(651,'random_text_8',8,'2024-02-10 00:00:00',47,'2024-02-22 00:00:00','Title','2024-02-23',1),(652,'random_text_9',8,'2024-02-09 00:00:00',47,'2024-02-20 00:00:00','Title','2024-02-21',1),(653,'random_text_1',8,'2024-03-13 00:00:00',47,'2024-03-23 00:00:00','Title','2024-03-24',1),(654,'random_text_2',8,'2024-03-07 00:00:00',47,'2024-03-26 00:00:00','Title','2024-03-27',1),(655,'random_text_3',8,'2024-03-03 00:00:00',47,'2024-03-21 00:00:00','Title','2024-03-22',1),(656,'random_text_4',8,'2024-03-01 00:00:00',47,'2024-03-20 00:00:00','Title','2024-03-21',1),(706,'random_text_1',4,'2024-01-09 00:00:00',41,'2024-01-31 00:00:00','Title','2024-01-31',1),(707,'random_text_2',4,'2024-01-12 00:00:00',41,'2024-01-23 00:00:00','Title','2024-01-24',1),(708,'random_text_3',4,'2024-01-02 00:00:00',41,'2024-01-19 00:00:00','Title','2024-01-20',1),(709,'random_text_4',4,'2024-01-07 00:00:00',41,'2024-01-20 00:00:00','Title','2024-01-21',1),(710,'random_text_5',4,'2024-01-12 00:00:00',41,'2024-01-16 00:00:00','Title','2024-01-17',1),(711,'random_text_6',4,'2024-01-03 00:00:00',41,'2024-01-17 00:00:00','Title','2024-01-18',1),(712,'random_text_7',4,'2024-01-11 00:00:00',41,'2024-01-30 00:00:00','Title','2024-01-31',1),(713,'random_text_8',4,'2024-01-05 00:00:00',41,'2024-01-18 00:00:00','Title','2024-01-19',1),(714,'random_text_9',4,'2024-01-13 00:00:00',41,'2024-01-29 00:00:00','Title','2024-01-30',1),(715,'random_text_10',4,'2024-01-02 00:00:00',41,'2024-01-15 00:00:00','Title','2024-01-16',1),(716,'random_text_1',4,'2024-02-05 00:00:00',41,'2024-02-16 00:00:00','Title','2024-02-17',1),(717,'random_text_2',4,'2024-02-14 00:00:00',41,'2024-02-19 00:00:00','Title','2024-02-20',1),(718,'random_text_1',4,'2024-03-04 00:00:00',41,'2024-03-26 00:00:00','Title','2024-03-27',1),(719,'random_text_2',4,'2024-03-09 00:00:00',41,'2024-03-16 00:00:00','Title','2024-03-17',1),(720,'random_text_3',4,'2024-03-04 00:00:00',41,'2024-03-15 00:00:00','Title','2024-03-16',1),(769,'random_text_1',12,'2024-01-05 00:00:00',40,'2024-01-19 00:00:00','Title','2024-01-20',1),(770,'random_text_1',12,'2024-02-03 00:00:00',40,'2024-02-16 00:00:00','Title','2024-02-17',1),(771,'random_text_2',12,'2024-02-06 00:00:00',40,'2024-02-19 00:00:00','Title','2024-02-20',1),(772,'random_text_3',12,'2024-02-04 00:00:00',40,'2024-02-19 00:00:00','Title','2024-02-20',1),(773,'random_text_4',12,'2024-02-13 00:00:00',40,'2024-02-25 00:00:00','Title','2024-02-26',1),(774,'random_text_5',12,'2024-02-03 00:00:00',40,'2024-02-18 00:00:00','Title','2024-02-19',1),(775,'random_text_6',12,'2024-02-09 00:00:00',40,'2024-02-19 00:00:00','Title','2024-02-20',1),(776,'random_text_7',12,'2024-02-12 00:00:00',40,'2024-02-18 00:00:00','Title','2024-02-19',1),(777,'random_text_8',12,'2024-02-13 00:00:00',40,'2024-02-14 00:00:00','Title','2024-02-15',1),(778,'random_text_9',12,'2024-02-03 00:00:00',40,'2024-02-28 00:00:00','Title','2024-02-29',1),(779,'random_text_10',12,'2024-02-02 00:00:00',40,'2024-02-22 00:00:00','Title','2024-02-23',1),(780,'random_text_1',12,'2024-03-14 00:00:00',40,'2024-03-25 00:00:00','Title','2024-03-26',1),(781,'random_text_2',12,'2024-03-10 00:00:00',40,'2024-03-31 00:00:00','Title','2024-03-31',1),(782,'random_text_3',12,'2024-03-02 00:00:00',40,'2024-03-15 00:00:00','Title','2024-03-16',1),(783,'random_text_4',12,'2024-03-06 00:00:00',40,'2024-03-31 00:00:00','Title','2024-03-31',1),(784,'random_text_5',12,'2024-03-05 00:00:00',40,'2024-03-21 00:00:00','Title','2024-03-22',1),(785,'random_text_6',12,'2024-03-09 00:00:00',40,'2024-03-31 00:00:00','Title','2024-03-31',1),(786,'random_text_7',12,'2024-03-05 00:00:00',40,'2024-03-16 00:00:00','Title','2024-03-17',1),(787,'random_text_8',12,'2024-03-07 00:00:00',40,'2024-03-23 00:00:00','Title','2024-03-24',1),(842,'random_text_1',16,'2024-01-13 00:00:00',78,'2024-01-28 00:00:00','Title','2024-01-29',1),(843,'random_text_2',16,'2024-01-05 00:00:00',78,'2024-01-16 00:00:00','Title','2024-01-17',1),(844,'random_text_3',16,'2024-01-08 00:00:00',78,'2024-01-21 00:00:00','Title','2024-01-22',1),(845,'random_text_4',16,'2024-01-01 00:00:00',78,'2024-01-26 00:00:00','Title','2024-01-27',1),(846,'random_text_5',16,'2024-01-09 00:00:00',78,'2024-01-24 00:00:00','Title','2024-01-25',1),(847,'random_text_1',16,'2024-02-03 00:00:00',78,'2024-02-17 00:00:00','Title','2024-02-18',1),(848,'random_text_2',16,'2024-02-10 00:00:00',78,'2024-02-18 00:00:00','Title','2024-02-19',1),(849,'random_text_3',16,'2024-02-09 00:00:00',78,'2024-02-20 00:00:00','Title','2024-02-21',1),(850,'random_text_4',16,'2024-02-08 00:00:00',78,'2024-02-14 00:00:00','Title','2024-02-15',1),(851,'random_text_5',16,'2024-02-14 00:00:00',78,'2024-02-18 00:00:00','Title','2024-02-19',1),(852,'random_text_6',16,'2024-02-02 00:00:00',78,'2024-02-15 00:00:00','Title','2024-02-16',1),(853,'random_text_7',16,'2024-02-03 00:00:00',78,'2024-02-26 00:00:00','Title','2024-02-27',1),(854,'random_text_8',16,'2024-02-10 00:00:00',78,'2024-02-18 00:00:00','Title','2024-02-19',1),(855,'random_text_1',16,'2024-03-15 00:00:00',78,'2024-03-27 00:00:00','Title','2024-03-28',1),(856,'random_text_2',16,'2024-03-08 00:00:00',78,'2024-03-25 00:00:00','Title','2024-03-26',1),(857,'random_text_3',16,'2024-03-10 00:00:00',78,'2024-03-16 00:00:00','Title','2024-03-17',1),(858,'random_text_4',16,'2024-03-03 00:00:00',78,'2024-03-29 00:00:00','Title','2024-03-30',1),(859,'random_text_5',16,'2024-03-12 00:00:00',78,'2024-03-28 00:00:00','Title','2024-03-29',1),(860,'random_text_6',16,'2024-03-04 00:00:00',78,'2024-03-17 00:00:00','Title','2024-03-18',1),(861,'random_text_7',16,'2024-03-11 00:00:00',78,'2024-03-25 00:00:00','Title','2024-03-26',1),(911,'',2,'2024-04-12 10:32:00',41,'2024-04-06 16:33:14','Dizajnirati plakat za dogadjaj',NULL,0),(912,'',3,'2024-04-09 16:34:00',41,'2024-04-06 16:34:25','Objava za instagram',NULL,0);
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

-- Dump completed on 2024-04-07  9:41:32
