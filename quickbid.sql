-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: quickbid
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auctions`
--

DROP TABLE IF EXISTS `auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auctions` (
  `auction_id` int(11) NOT NULL,
  `bid_increment` decimal(10,2) DEFAULT NULL,
  `minimum_bid` decimal(10,2) DEFAULT NULL,
  `copies_sold` int(11) DEFAULT NULL,
  `buyer_id` char(11) DEFAULT NULL,
  `monitor_id` char(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `reserve` decimal(10,2) DEFAULT NULL,
  `current_bid` decimal(10,2) DEFAULT NULL,
  `current_high_bid` decimal(10,2) DEFAULT NULL,
  `open_bid` decimal(10,2) DEFAULT NULL,
  `max_bid` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`auction_id`),
  KEY `fk_auction_item_id` (`item_id`),
  KEY `fk_auction_monitor_id` (`monitor_id`),
  KEY `fk_auction_max_bid_idx` (`max_bid`),
  CONSTRAINT `fk_auction_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_auction_monitor_id` FOREIGN KEY (`monitor_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` VALUES (1,1.00,NULL,1,'111-11-1011',NULL,1,10.00,11.00,11.00,5.00,NULL),(2,10.00,NULL,NULL,NULL,NULL,NULL,2000.00,NULL,NULL,1000.00,NULL);
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bid` (
  `customer_id` char(20) NOT NULL,
  `auction_id` int(11) NOT NULL,
  `bid_time` datetime NOT NULL,
  `bid_price` decimal(10,2) DEFAULT NULL,
  `customer_max_bid` decimal(10,2) DEFAULT NULL,
  `current_winner` char(20) DEFAULT NULL,
  `bidcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`auction_id`,`bid_time`,`customer_id`),
  KEY `fk_bid_customer_id_idx` (`customer_id`),
  KEY `idx_bid_customer_max_bid` (`customer_max_bid`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_bid_auction_id` FOREIGN KEY (`auction_id`) REFERENCES `auctions` (`auction_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_bid_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ssn`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES ('111-11-1012',1,'2018-10-23 20:22:01',5.00,10.00,'111-11-1012',NULL),('111-11-1011',1,'2018-10-23 20:22:02',9.00,10.00,'111-11-1012',NULL),('111-11-1011',1,'2018-10-23 20:22:03',10.00,10.00,'111-11-1012',NULL),('111-11-1011',1,'2018-10-23 20:22:04',10.00,10.00,'111-11-1011',NULL);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bought_items`
--

DROP TABLE IF EXISTS `bought_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bought_items` (
  `customer_id` int(11) NOT NULL,
  `auction_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`auction_id`),
  KEY `pk_auction_id` (`auction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bought_items`
--

LOCK TABLES `bought_items` WRITE;
/*!40000 ALTER TABLE `bought_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `bought_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `rating` int(11) DEFAULT NULL,
  `ccNum` char(19) DEFAULT NULL,
  `ssn` char(11) NOT NULL,
  `customer_id` char(20) DEFAULT NULL,
  PRIMARY KEY (`ssn`),
  CONSTRAINT `fk_customer_ssn` FOREIGN KEY (`ssn`) REFERENCES `person` (`ssn`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'1234-5678-1234-5678','111-11-1011','shiyong'),(1,'5678-1234-5678-1234','111-11-1012','haixia'),(1,'2345-6789-2345-6789','111-11-1013','john'),(1,'6789-2345-6789-2345','111-11-1014','phil');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `start_date` date DEFAULT NULL,
  `hourly_rate` decimal(10,2) DEFAULT NULL,
  `employee_id` char(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `fk_employee_ssn` FOREIGN KEY (`employee_id`) REFERENCES `person` (`ssn`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('1998-11-01',60.00,'111-11-1015',NULL),('1999-02-02',50.00,'111-11-1016',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `description` mediumtext,
  `name` char(255) DEFAULT NULL,
  `type` char(255) DEFAULT NULL,
  `num_copies` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,NULL,'Titanic','DVD',4),(2,NULL,'Nissan Sentra','Car',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `person` (
  `ssn` char(11) NOT NULL,
  `last_name` char(20) NOT NULL,
  `first_name` char(20) NOT NULL,
  `address` char(255) DEFAULT NULL,
  `city` char(20) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `email` char(50) DEFAULT NULL,
  PRIMARY KEY (`ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('111-11-1011',' Lu','ShiYong','123 Success Street','Stony Brook','NY',11790,'(516)632-8959','shiyong@cs.sunysb.edu'),('111-11-1012',' Du','Haixia','456 Fortune Road','Stony Brook','NY',11790,'(516)632-4360','dhaixia@cs.sunysb.edu'),('111-11-1013','Smith','John','789 Peace Blvd.','Los Angeles','CA',12345,'(412)443-4321','shlu@ic.sunysb.edu'),('111-11-1014',' Phil','Lewis','135 Knowledge Lane','Stony Brook','NY',11794,'(516)666-8888','pml@cs.sunysb.edu'),('111-11-1015',' Smith','David','123 College road','Stony Brook','NY',11790,'(516)215-2345','smith.david@cs.sunysb.edu'),('111-11-1016',' Warren','David','456 Sunken Street','Stony Brook','NY',11794,'(516)632-9987','warren.david@cs.sunysb.edu');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `post` (
  `close_date` datetime DEFAULT NULL,
  `open_date` datetime DEFAULT NULL,
  `customer_id` char(11) NOT NULL,
  `auction_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`auction_id`),
  KEY `fk_post_auction_id_idx` (`auction_id`),
  CONSTRAINT `fk_post_auction_id` FOREIGN KEY (`auction_id`) REFERENCES `auctions` (`auction_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_poster_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('2008-12-16 13:00:00','2018-10-23 19:28:20','111-11-1013',2),('2008-12-16 13:00:00','2018-10-23 19:34:15','111-11-1014',1);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sold_items`
--

DROP TABLE IF EXISTS `sold_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sold_items` (
  `customer_id` int(11) NOT NULL,
  `auction_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`auction_id`),
  KEY `fk_auction_id` (`auction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sold_items`
--

LOCK TABLES `sold_items` WRITE;
/*!40000 ALTER TABLE `sold_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `sold_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-23 20:22:37
