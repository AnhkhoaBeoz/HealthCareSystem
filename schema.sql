CREATE DATABASE  IF NOT EXISTS `healthcaresystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `healthcaresystem`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: healthcaresystem
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKk907esm71jpp122us65vjbr9c` (`role_id`),
  CONSTRAINT `FKgg9prcffdtjh08wib0i74plht` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`),
  CONSTRAINT `FKk907esm71jpp122us65vjbr9c` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (8,1),(9,2),(6,3),(7,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1aooua20sym86gluc3mxn1r5h` (`account_id`),
  CONSTRAINT `FK1aooua20sym86gluc3mxn1r5h` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (2,'2024-02-06 14:01:37.580959','e8043294-aca2-4349-b4e7-92b94fa8a863',7),(25,'2024-02-29 15:13:59.924891','bd0f7465-f5bb-4fa3-b931-b395ddb974fd',8);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_account`
--

DROP TABLE IF EXISTS `tb_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `assigned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_53com612iic0m9jcsay8i32rt` (`email`),
  UNIQUE KEY `UK_f6hygluf7f7hjekspdv3pgsdl` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_account`
--

LOCK TABLES `tb_account` WRITE;
/*!40000 ALTER TABLE `tb_account` DISABLE KEYS */;
INSERT INTO `tb_account` VALUES (1,'example_avatar_url','khoabeo@example.com','$2a$10$fHVEZIGIxUGL0Nomu7hmDuurQj23d6Are.o3qMxsRDgs5i.FfT5vC','khoabeo',0),(3,'exampl1e_avatar_url','khoabeo1@example.com','$2a$10$.FnqWhQkLXmqQzcuuBP93OTgSC1LiYeTOHxpWGZ.BMiFiouJf8M16','khoabeo1',0),(6,'exampl1e_avatar_url','khoabeo32@example.com','$2a$10$2uvtJGTEyhwzIHrFN7PgUehmLgKmEyY1CytyQ6p9J2HscZbNmtJ2q','khoabeokhoabeo3',0),(7,'exampl1e_avatar_url','test@example.com','$2a$10$C551fc/8g35/WO5CqJcaRe6elFRIr/i4pL8ELMaA/PHSRRn11eNEK','test',1),(8,'admin_url','admin@gmail.com','$2a$10$C551fc/8g35/WO5CqJcaRe6elFRIr/i4pL8ELMaA/PHSRRn11eNEK','admin',0),(9,'avartat','drkhoa@example.com','$2a$10$PIcafbv6bxeUm2xiJG7ToOzR6kiL25evHCMQLv5jDqGYrKf/83vs.','drkhoa',0),(11,'avartat','phuongNurse@example.com','$2a$10$Mor0NgZck4uAVszPqVwPrOVaRmSX15//Yy8hlI6mACdvw9xCYhuUC','phuongNurse',1);
/*!40000 ALTER TABLE `tb_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_appointment`
--

DROP TABLE IF EXISTS `tb_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date_at` datetime(6) DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `update_date_at` datetime(6) DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo2mcr811mn2fl6l72ufultl55` (`patient_id`),
  KEY `FKmfe0k4kcr4sp84qp8lwt26y6y` (`doctor_id`),
  CONSTRAINT `FKmfe0k4kcr4sp84qp8lwt26y6y` FOREIGN KEY (`doctor_id`) REFERENCES `tb_docter` (`id`),
  CONSTRAINT `FKo2mcr811mn2fl6l72ufultl55` FOREIGN KEY (`patient_id`) REFERENCES `tb_patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_appointment`
--

LOCK TABLES `tb_appointment` WRITE;
/*!40000 ALTER TABLE `tb_appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_docter`
--

DROP TABLE IF EXISTS `tb_docter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_docter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `specialization` varchar(255) NOT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmq8qnjwcusg6muywv4fjlpeme` (`account_id`),
  CONSTRAINT `FKmq8qnjwcusg6muywv4fjlpeme` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_docter`
--

LOCK TABLES `tb_docter` WRITE;
/*!40000 ALTER TABLE `tb_docter` DISABLE KEYS */;
INSERT INTO `tb_docter` VALUES (1,'DR.KHOA','BLOOD',9),(3,'Dr.Phuong21','CK2',NULL);
/*!40000 ALTER TABLE `tb_docter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_dutyschedule`
--

DROP TABLE IF EXISTS `tb_dutyschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_dutyschedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `schedule_date` datetime(6) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `nurse_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgs3cjg7f768ghhkgr7uof5a12` (`doctor_id`),
  KEY `FKpk50sj589ug507yg50t68loo9` (`nurse_id`),
  CONSTRAINT `FKgs3cjg7f768ghhkgr7uof5a12` FOREIGN KEY (`doctor_id`) REFERENCES `tb_docter` (`id`),
  CONSTRAINT `FKpk50sj589ug507yg50t68loo9` FOREIGN KEY (`nurse_id`) REFERENCES `tb_nurse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dutyschedule`
--

LOCK TABLES `tb_dutyschedule` WRITE;
/*!40000 ALTER TABLE `tb_dutyschedule` DISABLE KEYS */;
INSERT INTO `tb_dutyschedule` VALUES (1,'2024-03-01 08:00:00.000000','Morning','PENDING',3,1);
/*!40000 ALTER TABLE `tb_dutyschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_medicine`
--

DROP TABLE IF EXISTS `tb_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_medicine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_medicine`
--

LOCK TABLES `tb_medicine` WRITE;
/*!40000 ALTER TABLE `tb_medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_medicine_prescription`
--

DROP TABLE IF EXISTS `tb_medicine_prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_medicine_prescription` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dosage` double NOT NULL,
  `quantity` int NOT NULL,
  `medicine_id` bigint DEFAULT NULL,
  `prescription_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjijvj333jwg5ugd7fuormu2f` (`medicine_id`),
  KEY `FKcn3xcr10vvlue2llahfjvx9k3` (`prescription_id`),
  CONSTRAINT `FKcn3xcr10vvlue2llahfjvx9k3` FOREIGN KEY (`prescription_id`) REFERENCES `tb_prescription` (`id`),
  CONSTRAINT `FKhjijvj333jwg5ugd7fuormu2f` FOREIGN KEY (`medicine_id`) REFERENCES `tb_medicine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_medicine_prescription`
--

LOCK TABLES `tb_medicine_prescription` WRITE;
/*!40000 ALTER TABLE `tb_medicine_prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_medicine_prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_nurse`
--

DROP TABLE IF EXISTS `tb_nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_nurse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhan4vt3lspcxpiev4s9g6ffl0` (`account_id`),
  CONSTRAINT `FKhan4vt3lspcxpiev4s9g6ffl0` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_nurse`
--

LOCK TABLES `tb_nurse` WRITE;
/*!40000 ALTER TABLE `tb_nurse` DISABLE KEYS */;
INSERT INTO `tb_nurse` VALUES (1,'Phuong Update',11);
/*!40000 ALTER TABLE `tb_nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_patient`
--

DROP TABLE IF EXISTS `tb_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpl8mv773f591g7b1qv3238mj` (`account_id`),
  CONSTRAINT `FKpl8mv773f591g7b1qv3238mj` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_patient`
--

LOCK TABLES `tb_patient` WRITE;
/*!40000 ALTER TABLE `tb_patient` DISABLE KEYS */;
INSERT INTO `tb_patient` VALUES (1,'123 Main St','1990-05-15 12:00:00.000000','male','John Doe','555-1234',NULL),(3,'123 Main St','1990-05-15 12:00:00.000000','male','John Doe','555-1234',7);
/*!40000 ALTER TABLE `tb_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_prescription`
--

DROP TABLE IF EXISTS `tb_prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_prescription` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date_at` datetime(6) NOT NULL,
  `diagnosis` varchar(255) NOT NULL,
  `total_cost` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaur25jfi1xcbgepfe660dkj7s` (`user_id`),
  KEY `FKf2w7o0dp7ta94wmlmi9nkplnt` (`doctor_id`),
  CONSTRAINT `FKaur25jfi1xcbgepfe660dkj7s` FOREIGN KEY (`user_id`) REFERENCES `tb_account` (`id`),
  CONSTRAINT `FKf2w7o0dp7ta94wmlmi9nkplnt` FOREIGN KEY (`doctor_id`) REFERENCES `tb_docter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_prescription`
--

LOCK TABLES `tb_prescription` WRITE;
/*!40000 ALTER TABLE `tb_prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1ncmoedv5ta7r19y9d4oidn0y` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DOCTOR'),(4,'ROLE_NURSE'),(3,'ROLE_PATIENT');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-04 12:32:08
