-- MySQL dump 10.13  Distrib 5.7.33, for Linux (x86_64)
--
-- Host: localhost    Database: cps298at
-- ------------------------------------------------------
-- Server version	5.7.33-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary table structure for view `CustTickets`
--

DROP TABLE IF EXISTS `CustTickets`;
/*!50001 DROP VIEW IF EXISTS `CustTickets`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `CustTickets` AS SELECT 
 1 AS `customer_id`,
 1 AS `showing_id`,
 1 AS `date`,
 1 AS `time`,
 1 AS `movie_id`,
 1 AS `title`,
 1 AS `seat`,
 1 AS `purchase_id`,
 1 AS `price`,
 1 AS `ticket_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `SoldTickets`
--

DROP TABLE IF EXISTS `SoldTickets`;
/*!50001 DROP VIEW IF EXISTS `SoldTickets`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `SoldTickets` AS SELECT 
 1 AS `Date`,
 1 AS `MovieTitle`,
 1 AS `Time`,
 1 AS `Screen`,
 1 AS `Customer`,
 1 AS `TkID`,
 1 AS `Seat`,
 1 AS `Price`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='Admin logins';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Brian','open_sesame','blt',NULL),(2,'alpha','wolfpack','alpha','kpcook@wccnet.edu');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `zipcode` varchar(12) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Smith','John','Lewis','1980-10-31','48104','jsmith','badpassword','kpcook@wccnet.edu'),(2,'Doe','Jennifer','Ann','1985-02-02','48197','jdoe','badpassword','kpcook@wccnet.edu'),(3,'Guest',NULL,NULL,NULL,NULL,'','',NULL),(4,'Spears','Britney','Jean','1981-12-02','90210','','',NULL),(5,'Brady','Tom','Edward','1977-08-03','33602','','',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `rated` tinyint(4) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `image_link` varchar(100) DEFAULT NULL,
  `duration` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='Numerical Rating Conversion: G=1, PG=2, PG13=3, R=4, NC17=5, UR=6';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Raya and the Last Dragon',2,'2021-03-05','raya.png',107),(2,'Avengers: Endgame',3,'2019-04-26','avengers.png',181),(3,'The Croods: A New Age',2,'2020-11-25','croods.png',95),(4,'The Lovebirds',4,'2020-05-22','lovebirds.png',86),(5,'Us',4,'2019-03-22','us.png',116),(6,'The Lion King',1,'1994-06-24','lionking.png',88),(7,'Ralph Breaks the Internet',2,'2018-11-21','ralphinternet.png',112);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `approval` varchar(45) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_idx` (`customer_id`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COMMENT='tracks tickets in one purchase and total amount';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_id_idx` (`customer_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='tracks refund amount for cancelled tickets';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
INSERT INTO `refund` VALUES (1,1,2000,NULL,NULL,'covid');
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `screen`
--

DROP TABLE IF EXISTS `screen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `screen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT 'Screen A',
  `max_rows` tinyint(4) DEFAULT '7',
  `max_cols` tinyint(4) DEFAULT '8',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='max_rows default is 7, max_cols (seats) is 8';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screen`
--

LOCK TABLES `screen` WRITE;
/*!40000 ALTER TABLE `screen` DISABLE KEYS */;
INSERT INTO `screen` VALUES (1,'Screen A',7,8),(2,'Screen B',9,10),(3,'Screen C',6,12),(4,'Screen D',14,14),(5,'Screen E',30,14);
/*!40000 ALTER TABLE `screen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row` varchar(2) NOT NULL,
  `seat_number` int(11) NOT NULL,
  `screen_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`row`,`seat_number`,`screen_id`),
  KEY `fk_seat_screen1_idx` (`screen_id`),
  CONSTRAINT `fk_seat_screen1` FOREIGN KEY (`screen_id`) REFERENCES `screen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1920 DEFAULT CHARSET=utf8mb4 COMMENT='row and seat_number combinations have a unique constraint- they make up a unique composite key.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1086,'A',1,1),(1142,'A',1,2),(1232,'A',1,3),(1304,'A',1,4),(1500,'A',1,5),(1087,'A',2,1),(1143,'A',2,2),(1233,'A',2,3),(1305,'A',2,4),(1501,'A',2,5),(1088,'A',3,1),(1144,'A',3,2),(1234,'A',3,3),(1306,'A',3,4),(1502,'A',3,5),(1089,'A',4,1),(1145,'A',4,2),(1235,'A',4,3),(1307,'A',4,4),(1503,'A',4,5),(1090,'A',5,1),(1146,'A',5,2),(1236,'A',5,3),(1308,'A',5,4),(1504,'A',5,5),(1091,'A',6,1),(1147,'A',6,2),(1237,'A',6,3),(1309,'A',6,4),(1505,'A',6,5),(1092,'A',7,1),(1148,'A',7,2),(1238,'A',7,3),(1310,'A',7,4),(1506,'A',7,5),(1093,'A',8,1),(1149,'A',8,2),(1239,'A',8,3),(1311,'A',8,4),(1507,'A',8,5),(1150,'A',9,2),(1240,'A',9,3),(1312,'A',9,4),(1508,'A',9,5),(1151,'A',10,2),(1241,'A',10,3),(1313,'A',10,4),(1509,'A',10,5),(1242,'A',11,3),(1314,'A',11,4),(1510,'A',11,5),(1243,'A',12,3),(1315,'A',12,4),(1511,'A',12,5),(1316,'A',13,4),(1512,'A',13,5),(1317,'A',14,4),(1513,'A',14,5),(1864,'AA',1,5),(1865,'AA',2,5),(1866,'AA',3,5),(1867,'AA',4,5),(1868,'AA',5,5),(1869,'AA',6,5),(1870,'AA',7,5),(1871,'AA',8,5),(1872,'AA',9,5),(1873,'AA',10,5),(1874,'AA',11,5),(1875,'AA',12,5),(1876,'AA',13,5),(1877,'AA',14,5),(1094,'B',1,1),(1152,'B',1,2),(1244,'B',1,3),(1318,'B',1,4),(1514,'B',1,5),(1095,'B',2,1),(1153,'B',2,2),(1245,'B',2,3),(1319,'B',2,4),(1515,'B',2,5),(1096,'B',3,1),(1154,'B',3,2),(1246,'B',3,3),(1320,'B',3,4),(1516,'B',3,5),(1097,'B',4,1),(1155,'B',4,2),(1247,'B',4,3),(1321,'B',4,4),(1517,'B',4,5),(1098,'B',5,1),(1156,'B',5,2),(1248,'B',5,3),(1322,'B',5,4),(1518,'B',5,5),(1099,'B',6,1),(1157,'B',6,2),(1249,'B',6,3),(1323,'B',6,4),(1519,'B',6,5),(1100,'B',7,1),(1158,'B',7,2),(1250,'B',7,3),(1324,'B',7,4),(1520,'B',7,5),(1101,'B',8,1),(1159,'B',8,2),(1251,'B',8,3),(1325,'B',8,4),(1521,'B',8,5),(1160,'B',9,2),(1252,'B',9,3),(1326,'B',9,4),(1522,'B',9,5),(1161,'B',10,2),(1253,'B',10,3),(1327,'B',10,4),(1523,'B',10,5),(1254,'B',11,3),(1328,'B',11,4),(1524,'B',11,5),(1255,'B',12,3),(1329,'B',12,4),(1525,'B',12,5),(1330,'B',13,4),(1526,'B',13,5),(1331,'B',14,4),(1527,'B',14,5),(1878,'BB',1,5),(1879,'BB',2,5),(1880,'BB',3,5),(1881,'BB',4,5),(1882,'BB',5,5),(1883,'BB',6,5),(1884,'BB',7,5),(1885,'BB',8,5),(1886,'BB',9,5),(1887,'BB',10,5),(1888,'BB',11,5),(1889,'BB',12,5),(1890,'BB',13,5),(1891,'BB',14,5),(1102,'C',1,1),(1162,'C',1,2),(1256,'C',1,3),(1332,'C',1,4),(1528,'C',1,5),(1103,'C',2,1),(1163,'C',2,2),(1257,'C',2,3),(1333,'C',2,4),(1529,'C',2,5),(1104,'C',3,1),(1164,'C',3,2),(1258,'C',3,3),(1334,'C',3,4),(1530,'C',3,5),(1105,'C',4,1),(1165,'C',4,2),(1259,'C',4,3),(1335,'C',4,4),(1531,'C',4,5),(1106,'C',5,1),(1166,'C',5,2),(1260,'C',5,3),(1336,'C',5,4),(1532,'C',5,5),(1107,'C',6,1),(1167,'C',6,2),(1261,'C',6,3),(1337,'C',6,4),(1533,'C',6,5),(1108,'C',7,1),(1168,'C',7,2),(1262,'C',7,3),(1338,'C',7,4),(1534,'C',7,5),(1109,'C',8,1),(1169,'C',8,2),(1263,'C',8,3),(1339,'C',8,4),(1535,'C',8,5),(1170,'C',9,2),(1264,'C',9,3),(1340,'C',9,4),(1536,'C',9,5),(1171,'C',10,2),(1265,'C',10,3),(1341,'C',10,4),(1537,'C',10,5),(1266,'C',11,3),(1342,'C',11,4),(1538,'C',11,5),(1267,'C',12,3),(1343,'C',12,4),(1539,'C',12,5),(1344,'C',13,4),(1540,'C',13,5),(1345,'C',14,4),(1541,'C',14,5),(1892,'CC',1,5),(1893,'CC',2,5),(1894,'CC',3,5),(1895,'CC',4,5),(1896,'CC',5,5),(1897,'CC',6,5),(1898,'CC',7,5),(1899,'CC',8,5),(1900,'CC',9,5),(1901,'CC',10,5),(1902,'CC',11,5),(1903,'CC',12,5),(1904,'CC',13,5),(1905,'CC',14,5),(1110,'D',1,1),(1172,'D',1,2),(1268,'D',1,3),(1346,'D',1,4),(1542,'D',1,5),(1111,'D',2,1),(1173,'D',2,2),(1269,'D',2,3),(1347,'D',2,4),(1543,'D',2,5),(1112,'D',3,1),(1174,'D',3,2),(1270,'D',3,3),(1348,'D',3,4),(1544,'D',3,5),(1113,'D',4,1),(1175,'D',4,2),(1271,'D',4,3),(1349,'D',4,4),(1545,'D',4,5),(1114,'D',5,1),(1176,'D',5,2),(1272,'D',5,3),(1350,'D',5,4),(1546,'D',5,5),(1115,'D',6,1),(1177,'D',6,2),(1273,'D',6,3),(1351,'D',6,4),(1547,'D',6,5),(1116,'D',7,1),(1178,'D',7,2),(1274,'D',7,3),(1352,'D',7,4),(1548,'D',7,5),(1117,'D',8,1),(1179,'D',8,2),(1275,'D',8,3),(1353,'D',8,4),(1549,'D',8,5),(1180,'D',9,2),(1276,'D',9,3),(1354,'D',9,4),(1550,'D',9,5),(1181,'D',10,2),(1277,'D',10,3),(1355,'D',10,4),(1551,'D',10,5),(1278,'D',11,3),(1356,'D',11,4),(1552,'D',11,5),(1279,'D',12,3),(1357,'D',12,4),(1553,'D',12,5),(1358,'D',13,4),(1554,'D',13,5),(1359,'D',14,4),(1555,'D',14,5),(1906,'DD',1,5),(1907,'DD',2,5),(1908,'DD',3,5),(1909,'DD',4,5),(1910,'DD',5,5),(1911,'DD',6,5),(1912,'DD',7,5),(1913,'DD',8,5),(1914,'DD',9,5),(1915,'DD',10,5),(1916,'DD',11,5),(1917,'DD',12,5),(1918,'DD',13,5),(1919,'DD',14,5),(1118,'E',1,1),(1182,'E',1,2),(1280,'E',1,3),(1360,'E',1,4),(1556,'E',1,5),(1119,'E',2,1),(1183,'E',2,2),(1281,'E',2,3),(1361,'E',2,4),(1557,'E',2,5),(1120,'E',3,1),(1184,'E',3,2),(1282,'E',3,3),(1362,'E',3,4),(1558,'E',3,5),(1121,'E',4,1),(1185,'E',4,2),(1283,'E',4,3),(1363,'E',4,4),(1559,'E',4,5),(1122,'E',5,1),(1186,'E',5,2),(1284,'E',5,3),(1364,'E',5,4),(1560,'E',5,5),(1123,'E',6,1),(1187,'E',6,2),(1285,'E',6,3),(1365,'E',6,4),(1561,'E',6,5),(1124,'E',7,1),(1188,'E',7,2),(1286,'E',7,3),(1366,'E',7,4),(1562,'E',7,5),(1125,'E',8,1),(1189,'E',8,2),(1287,'E',8,3),(1367,'E',8,4),(1563,'E',8,5),(1190,'E',9,2),(1288,'E',9,3),(1368,'E',9,4),(1564,'E',9,5),(1191,'E',10,2),(1289,'E',10,3),(1369,'E',10,4),(1565,'E',10,5),(1290,'E',11,3),(1370,'E',11,4),(1566,'E',11,5),(1291,'E',12,3),(1371,'E',12,4),(1567,'E',12,5),(1372,'E',13,4),(1568,'E',13,5),(1373,'E',14,4),(1569,'E',14,5),(1126,'F',1,1),(1192,'F',1,2),(1292,'F',1,3),(1374,'F',1,4),(1570,'F',1,5),(1127,'F',2,1),(1193,'F',2,2),(1293,'F',2,3),(1375,'F',2,4),(1571,'F',2,5),(1128,'F',3,1),(1194,'F',3,2),(1294,'F',3,3),(1376,'F',3,4),(1572,'F',3,5),(1129,'F',4,1),(1195,'F',4,2),(1295,'F',4,3),(1377,'F',4,4),(1573,'F',4,5),(1130,'F',5,1),(1196,'F',5,2),(1296,'F',5,3),(1378,'F',5,4),(1574,'F',5,5),(1131,'F',6,1),(1197,'F',6,2),(1297,'F',6,3),(1379,'F',6,4),(1575,'F',6,5),(1132,'F',7,1),(1198,'F',7,2),(1298,'F',7,3),(1380,'F',7,4),(1576,'F',7,5),(1133,'F',8,1),(1199,'F',8,2),(1299,'F',8,3),(1381,'F',8,4),(1577,'F',8,5),(1200,'F',9,2),(1300,'F',9,3),(1382,'F',9,4),(1578,'F',9,5),(1201,'F',10,2),(1301,'F',10,3),(1383,'F',10,4),(1579,'F',10,5),(1302,'F',11,3),(1384,'F',11,4),(1580,'F',11,5),(1303,'F',12,3),(1385,'F',12,4),(1581,'F',12,5),(1386,'F',13,4),(1582,'F',13,5),(1387,'F',14,4),(1583,'F',14,5),(1134,'G',1,1),(1202,'G',1,2),(1388,'G',1,4),(1584,'G',1,5),(1135,'G',2,1),(1203,'G',2,2),(1389,'G',2,4),(1585,'G',2,5),(1136,'G',3,1),(1204,'G',3,2),(1390,'G',3,4),(1586,'G',3,5),(1137,'G',4,1),(1205,'G',4,2),(1391,'G',4,4),(1587,'G',4,5),(1138,'G',5,1),(1206,'G',5,2),(1392,'G',5,4),(1588,'G',5,5),(1139,'G',6,1),(1207,'G',6,2),(1393,'G',6,4),(1589,'G',6,5),(1140,'G',7,1),(1208,'G',7,2),(1394,'G',7,4),(1590,'G',7,5),(1141,'G',8,1),(1209,'G',8,2),(1395,'G',8,4),(1591,'G',8,5),(1210,'G',9,2),(1396,'G',9,4),(1592,'G',9,5),(1211,'G',10,2),(1397,'G',10,4),(1593,'G',10,5),(1398,'G',11,4),(1594,'G',11,5),(1399,'G',12,4),(1595,'G',12,5),(1400,'G',13,4),(1596,'G',13,5),(1401,'G',14,4),(1597,'G',14,5),(1212,'H',1,2),(1402,'H',1,4),(1598,'H',1,5),(1213,'H',2,2),(1403,'H',2,4),(1599,'H',2,5),(1214,'H',3,2),(1404,'H',3,4),(1600,'H',3,5),(1215,'H',4,2),(1405,'H',4,4),(1601,'H',4,5),(1216,'H',5,2),(1406,'H',5,4),(1602,'H',5,5),(1217,'H',6,2),(1407,'H',6,4),(1603,'H',6,5),(1218,'H',7,2),(1408,'H',7,4),(1604,'H',7,5),(1219,'H',8,2),(1409,'H',8,4),(1605,'H',8,5),(1220,'H',9,2),(1410,'H',9,4),(1606,'H',9,5),(1221,'H',10,2),(1411,'H',10,4),(1607,'H',10,5),(1412,'H',11,4),(1608,'H',11,5),(1413,'H',12,4),(1609,'H',12,5),(1414,'H',13,4),(1610,'H',13,5),(1415,'H',14,4),(1611,'H',14,5),(1222,'I',1,2),(1416,'I',1,4),(1612,'I',1,5),(1223,'I',2,2),(1417,'I',2,4),(1613,'I',2,5),(1224,'I',3,2),(1418,'I',3,4),(1614,'I',3,5),(1225,'I',4,2),(1419,'I',4,4),(1615,'I',4,5),(1226,'I',5,2),(1420,'I',5,4),(1616,'I',5,5),(1227,'I',6,2),(1421,'I',6,4),(1617,'I',6,5),(1228,'I',7,2),(1422,'I',7,4),(1618,'I',7,5),(1229,'I',8,2),(1423,'I',8,4),(1619,'I',8,5),(1230,'I',9,2),(1424,'I',9,4),(1620,'I',9,5),(1231,'I',10,2),(1425,'I',10,4),(1621,'I',10,5),(1426,'I',11,4),(1622,'I',11,5),(1427,'I',12,4),(1623,'I',12,5),(1428,'I',13,4),(1624,'I',13,5),(1429,'I',14,4),(1625,'I',14,5),(1430,'J',1,4),(1626,'J',1,5),(1431,'J',2,4),(1627,'J',2,5),(1432,'J',3,4),(1628,'J',3,5),(1433,'J',4,4),(1629,'J',4,5),(1434,'J',5,4),(1630,'J',5,5),(1435,'J',6,4),(1631,'J',6,5),(1436,'J',7,4),(1632,'J',7,5),(1437,'J',8,4),(1633,'J',8,5),(1438,'J',9,4),(1634,'J',9,5),(1439,'J',10,4),(1635,'J',10,5),(1440,'J',11,4),(1636,'J',11,5),(1441,'J',12,4),(1637,'J',12,5),(1442,'J',13,4),(1638,'J',13,5),(1443,'J',14,4),(1639,'J',14,5),(1444,'K',1,4),(1640,'K',1,5),(1445,'K',2,4),(1641,'K',2,5),(1446,'K',3,4),(1642,'K',3,5),(1447,'K',4,4),(1643,'K',4,5),(1448,'K',5,4),(1644,'K',5,5),(1449,'K',6,4),(1645,'K',6,5),(1450,'K',7,4),(1646,'K',7,5),(1451,'K',8,4),(1647,'K',8,5),(1452,'K',9,4),(1648,'K',9,5),(1453,'K',10,4),(1649,'K',10,5),(1454,'K',11,4),(1650,'K',11,5),(1455,'K',12,4),(1651,'K',12,5),(1456,'K',13,4),(1652,'K',13,5),(1457,'K',14,4),(1653,'K',14,5),(1458,'L',1,4),(1654,'L',1,5),(1459,'L',2,4),(1655,'L',2,5),(1460,'L',3,4),(1656,'L',3,5),(1461,'L',4,4),(1657,'L',4,5),(1462,'L',5,4),(1658,'L',5,5),(1463,'L',6,4),(1659,'L',6,5),(1464,'L',7,4),(1660,'L',7,5),(1465,'L',8,4),(1661,'L',8,5),(1466,'L',9,4),(1662,'L',9,5),(1467,'L',10,4),(1663,'L',10,5),(1468,'L',11,4),(1664,'L',11,5),(1469,'L',12,4),(1665,'L',12,5),(1470,'L',13,4),(1666,'L',13,5),(1471,'L',14,4),(1667,'L',14,5),(1472,'M',1,4),(1668,'M',1,5),(1473,'M',2,4),(1669,'M',2,5),(1474,'M',3,4),(1670,'M',3,5),(1475,'M',4,4),(1671,'M',4,5),(1476,'M',5,4),(1672,'M',5,5),(1477,'M',6,4),(1673,'M',6,5),(1478,'M',7,4),(1674,'M',7,5),(1479,'M',8,4),(1675,'M',8,5),(1480,'M',9,4),(1676,'M',9,5),(1481,'M',10,4),(1677,'M',10,5),(1482,'M',11,4),(1678,'M',11,5),(1483,'M',12,4),(1679,'M',12,5),(1484,'M',13,4),(1680,'M',13,5),(1485,'M',14,4),(1681,'M',14,5),(1486,'N',1,4),(1682,'N',1,5),(1487,'N',2,4),(1683,'N',2,5),(1488,'N',3,4),(1684,'N',3,5),(1489,'N',4,4),(1685,'N',4,5),(1490,'N',5,4),(1686,'N',5,5),(1491,'N',6,4),(1687,'N',6,5),(1492,'N',7,4),(1688,'N',7,5),(1493,'N',8,4),(1689,'N',8,5),(1494,'N',9,4),(1690,'N',9,5),(1495,'N',10,4),(1691,'N',10,5),(1496,'N',11,4),(1692,'N',11,5),(1497,'N',12,4),(1693,'N',12,5),(1498,'N',13,4),(1694,'N',13,5),(1499,'N',14,4),(1695,'N',14,5),(1696,'O',1,5),(1697,'O',2,5),(1698,'O',3,5),(1699,'O',4,5),(1700,'O',5,5),(1701,'O',6,5),(1702,'O',7,5),(1703,'O',8,5),(1704,'O',9,5),(1705,'O',10,5),(1706,'O',11,5),(1707,'O',12,5),(1708,'O',13,5),(1709,'O',14,5),(1710,'P',1,5),(1711,'P',2,5),(1712,'P',3,5),(1713,'P',4,5),(1714,'P',5,5),(1715,'P',6,5),(1716,'P',7,5),(1717,'P',8,5),(1718,'P',9,5),(1719,'P',10,5),(1720,'P',11,5),(1721,'P',12,5),(1722,'P',13,5),(1723,'P',14,5),(1724,'Q',1,5),(1725,'Q',2,5),(1726,'Q',3,5),(1727,'Q',4,5),(1728,'Q',5,5),(1729,'Q',6,5),(1730,'Q',7,5),(1731,'Q',8,5),(1732,'Q',9,5),(1733,'Q',10,5),(1734,'Q',11,5),(1735,'Q',12,5),(1736,'Q',13,5),(1737,'Q',14,5),(1738,'R',1,5),(1739,'R',2,5),(1740,'R',3,5),(1741,'R',4,5),(1742,'R',5,5),(1743,'R',6,5),(1744,'R',7,5),(1745,'R',8,5),(1746,'R',9,5),(1747,'R',10,5),(1748,'R',11,5),(1749,'R',12,5),(1750,'R',13,5),(1751,'R',14,5),(1752,'S',1,5),(1753,'S',2,5),(1754,'S',3,5),(1755,'S',4,5),(1756,'S',5,5),(1757,'S',6,5),(1758,'S',7,5),(1759,'S',8,5),(1760,'S',9,5),(1761,'S',10,5),(1762,'S',11,5),(1763,'S',12,5),(1764,'S',13,5),(1765,'S',14,5),(1766,'T',1,5),(1767,'T',2,5),(1768,'T',3,5),(1769,'T',4,5),(1770,'T',5,5),(1771,'T',6,5),(1772,'T',7,5),(1773,'T',8,5),(1774,'T',9,5),(1775,'T',10,5),(1776,'T',11,5),(1777,'T',12,5),(1778,'T',13,5),(1779,'T',14,5),(1780,'U',1,5),(1781,'U',2,5),(1782,'U',3,5),(1783,'U',4,5),(1784,'U',5,5),(1785,'U',6,5),(1786,'U',7,5),(1787,'U',8,5),(1788,'U',9,5),(1789,'U',10,5),(1790,'U',11,5),(1791,'U',12,5),(1792,'U',13,5),(1793,'U',14,5),(1794,'V',1,5),(1795,'V',2,5),(1796,'V',3,5),(1797,'V',4,5),(1798,'V',5,5),(1799,'V',6,5),(1800,'V',7,5),(1801,'V',8,5),(1802,'V',9,5),(1803,'V',10,5),(1804,'V',11,5),(1805,'V',12,5),(1806,'V',13,5),(1807,'V',14,5),(1808,'W',1,5),(1809,'W',2,5),(1810,'W',3,5),(1811,'W',4,5),(1812,'W',5,5),(1813,'W',6,5),(1814,'W',7,5),(1815,'W',8,5),(1816,'W',9,5),(1817,'W',10,5),(1818,'W',11,5),(1819,'W',12,5),(1820,'W',13,5),(1821,'W',14,5),(1822,'X',1,5),(1823,'X',2,5),(1824,'X',3,5),(1825,'X',4,5),(1826,'X',5,5),(1827,'X',6,5),(1828,'X',7,5),(1829,'X',8,5),(1830,'X',9,5),(1831,'X',10,5),(1832,'X',11,5),(1833,'X',12,5),(1834,'X',13,5),(1835,'X',14,5),(1836,'Y',1,5),(1837,'Y',2,5),(1838,'Y',3,5),(1839,'Y',4,5),(1840,'Y',5,5),(1841,'Y',6,5),(1842,'Y',7,5),(1843,'Y',8,5),(1844,'Y',9,5),(1845,'Y',10,5),(1846,'Y',11,5),(1847,'Y',12,5),(1848,'Y',13,5),(1849,'Y',14,5),(1850,'Z',1,5),(1851,'Z',2,5),(1852,'Z',3,5),(1853,'Z',4,5),(1854,'Z',5,5),(1855,'Z',6,5),(1856,'Z',7,5),(1857,'Z',8,5),(1858,'Z',9,5),(1859,'Z',10,5),(1860,'Z',11,5),(1861,'Z',12,5),(1862,'Z',13,5),(1863,'Z',14,5);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showing`
--

DROP TABLE IF EXISTS `showing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `showing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT '2021-01-15',
  `movie_id` int(11) NOT NULL,
  `screen_id` int(11) NOT NULL,
  `time` time NOT NULL,
  `price` int(11) DEFAULT '500',
  PRIMARY KEY (`id`),
  KEY `fk_show_movie_idx` (`movie_id`),
  KEY `fk_showing_screen1_idx` (`screen_id`),
  CONSTRAINT `fk_show_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `fk_showing_screen1` FOREIGN KEY (`screen_id`) REFERENCES `screen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showing`
--

LOCK TABLES `showing` WRITE;
/*!40000 ALTER TABLE `showing` DISABLE KEYS */;
INSERT INTO `showing` VALUES (1,'2021-01-16',1,1,'11:00:00',500),(2,'2021-01-15',1,1,'13:30:00',500),(3,'2021-01-15',1,1,'16:00:00',1000),(4,'2021-01-16',1,1,'18:30:00',1000),(5,'2021-01-15',1,1,'21:00:00',1000),(6,'2021-01-15',2,2,'11:00:00',500),(7,'2021-01-15',2,2,'13:30:00',500),(8,'2021-01-15',2,2,'16:00:00',1000),(9,'2021-01-16',2,2,'18:30:00',1000),(10,'2021-01-15',2,2,'21:00:00',1000),(11,'2021-01-15',3,3,'11:00:00',500),(12,'2021-01-16',3,3,'13:30:00',500),(13,'2021-01-15',3,3,'16:00:00',1000),(14,'2021-01-15',3,3,'18:30:00',1000),(15,'2021-01-15',3,3,'21:00:00',1000),(16,'2021-01-16',6,4,'11:00:00',500),(17,'2021-01-16',6,4,'13:30:00',500),(18,'2021-01-15',6,4,'16:00:00',1000),(19,'2021-01-15',4,4,'18:30:00',1000),(20,'2021-01-15',4,4,'21:00:00',1000),(21,'2021-01-16',7,5,'11:00:00',500),(22,'2021-01-16',7,5,'13:30:00',500),(23,'2021-01-16',7,5,'16:00:00',1000),(24,'2021-01-16',5,5,'18:30:00',1000),(25,'2021-01-15',5,5,'21:00:00',1000);
/*!40000 ALTER TABLE `showing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `purchase_id` int(11) DEFAULT NULL,
  `showing_id` int(11) NOT NULL,
  `seat_id` int(11) NOT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `fk_seat_showing_idx` (`showing_id`,`seat_id`),
  KEY `fk_customer_has_showing_customer1_idx` (`customer_id`),
  KEY `fk_purchase_idx` (`purchase_id`),
  KEY `fk_seat_idx` (`seat_id`),
  KEY `fk_showing_idx` (`showing_id`),
  CONSTRAINT `fk_customer_has_showing_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_purchase` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seat` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_showing` FOREIGN KEY (`showing_id`) REFERENCES `showing` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='A ticket is for one seat in one showing';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `CustTickets`
--

/*!50001 DROP VIEW IF EXISTS `CustTickets`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`cps298at`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `CustTickets` AS select `customer`.`id` AS `customer_id`,`showing`.`id` AS `showing_id`,`showing`.`date` AS `date`,`showing`.`time` AS `time`,`movie`.`id` AS `movie_id`,`movie`.`title` AS `title`,concat(`seat`.`row`,`seat`.`seat_number`) AS `seat`,`purchase`.`id` AS `purchase_id`,`ticket`.`price` AS `price`,`ticket`.`ticket_id` AS `ticket_id` from (((((`ticket` join `customer` on((`customer`.`id` = `ticket`.`customer_id`))) join `purchase` on((`purchase`.`id` = `ticket`.`purchase_id`))) join `showing` on((`showing`.`id` = `ticket`.`showing_id`))) join `movie` on((`movie`.`id` = `showing`.`movie_id`))) join `seat` on((`seat`.`id` = `ticket`.`seat_id`))) order by `customer`.`id`,`showing`.`date`,`showing`.`time` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `SoldTickets`
--

/*!50001 DROP VIEW IF EXISTS `SoldTickets`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`cps298at`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `SoldTickets` AS select `showing`.`date` AS `Date`,`movie`.`title` AS `MovieTitle`,left(`showing`.`time`,5) AS `Time`,`showing`.`screen_id` AS `Screen`,trim(concat_ws(' ',ifnull(`customer`.`first_name`,''),ifnull(`customer`.`last_name`,''))) AS `Customer`,`ticket`.`ticket_id` AS `TkID`,concat_ws('',`seat`.`row`,`seat`.`seat_number`) AS `Seat`,`ticket`.`price` AS `Price` from (((((`ticket` join `showing` on((`showing`.`id` = `ticket`.`showing_id`))) join `customer` on((`ticket`.`customer_id` = `customer`.`id`))) join `movie` on((`showing`.`movie_id` = `movie`.`id`))) join `seat` on((`ticket`.`seat_id` = `seat`.`id`))) join `screen` on((`showing`.`screen_id` = `screen`.`id`))) order by `showing`.`date`,`showing`.`time` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-02  4:56:31
