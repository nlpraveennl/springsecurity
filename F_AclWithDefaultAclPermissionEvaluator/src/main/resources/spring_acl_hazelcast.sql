-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.48


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema spring_acl_hazelcache
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ spring_acl_hazelcache;
USE spring_acl_hazelcache;

--
-- Table structure for table `spring_acl_hazelcache`.`acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spring_acl_hazelcache`.`acl_class`
--

/*!40000 ALTER TABLE `acl_class` DISABLE KEYS */;
INSERT INTO `acl_class` (`id`,`class`) VALUES 
 (1,'com.pvn.mvctiles.vo.ProjectVO');
/*!40000 ALTER TABLE `acl_class` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL DEFAULT '0',
  `entry_note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`),
  CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spring_acl_hazelcache`.`acl_entry`
--

/*!40000 ALTER TABLE `acl_entry` DISABLE KEYS */;
INSERT INTO `acl_entry` (`id`,`acl_object_identity`,`ace_order`,`sid`,`mask`,`granting`,`audit_success`,`audit_failure`,`entry_note`) VALUES 
 (1,1,1,1,1,1,1,1,'Project(Proj-1), admin, read-allowed'),
 (2,2,1,1,1,1,1,1,'Project(Proj-2), admin, read-allowed'),
 (3,3,1,1,1,1,1,1,'Project(Proj-3), admin, read-allowed'),
 (4,4,1,1,1,1,1,1,'Project(Proj-4), admin, read-allowed'),
 (5,5,1,1,1,1,1,1,'Project(Proj-5), admin, read-allowed'),
 (6,6,1,1,1,1,1,1,'Project(Proj-6), admin, read-allowed'),
 (7,1,2,4,1,1,1,1,'Project(Proj-1), projmgr1, read-allowed, is a parent entry for proj-3,proj-5'),
 (8,4,2,5,1,1,1,1,'Project(Proj-2), projmgr2, read-allowed, is a parent entry for proj-4,proj-6'),
 (9,1,3,1,2,1,1,1,NULL);
/*!40000 ALTER TABLE `acl_entry` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`acl_object_identity`
--

DROP TABLE IF EXISTS `acl_object_identity`;
CREATE TABLE `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`),
  CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spring_acl_hazelcache`.`acl_object_identity`
--

/*!40000 ALTER TABLE `acl_object_identity` DISABLE KEYS */;
INSERT INTO `acl_object_identity` (`id`,`object_id_class`,`object_id_identity`,`parent_object`,`owner_sid`,`entries_inheriting`) VALUES 
 (1,1,1,NULL,1,0),
 (2,1,3,1,1,1),
 (3,1,5,1,1,1),
 (4,1,2,NULL,1,0),
 (5,1,4,4,1,1),
 (6,1,6,4,1,1);
/*!40000 ALTER TABLE `acl_object_identity` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`acl_sid`
--

DROP TABLE IF EXISTS `acl_sid`;
CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spring_acl_hazelcache`.`acl_sid`
--

/*!40000 ALTER TABLE `acl_sid` DISABLE KEYS */;
INSERT INTO `acl_sid` (`id`,`principal`,`sid`) VALUES 
 (6,1,'praveen'),
 (4,1,'projmgr1'),
 (5,1,'projmgr2'),
 (1,0,'ROLE_ADMIN'),
 (3,0,'ROLE_MGR'),
 (2,0,'ROLE_USER');
/*!40000 ALTER TABLE `acl_sid` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`issue`
--

DROP TABLE IF EXISTS `issue`;
CREATE TABLE `issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `issueType` enum('EPIC','NEW_FEATURE','TASK','BUG') NOT NULL DEFAULT 'NEW_FEATURE',
  `assignee` int(10) unsigned DEFAULT NULL,
  `reporter` int(10) unsigned NOT NULL DEFAULT '0',
  `projectId` int(10) unsigned NOT NULL DEFAULT '0',
  `description` varchar(512) DEFAULT NULL,
  `summary` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`issue`
--

/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` (`id`,`name`,`issueType`,`assignee`,`reporter`,`projectId`,`description`,`summary`) VALUES 
 (1,'P1E1','EPIC',3,2,1,'Project1 Epic1 Desc','P1E1 Epic summary'),
 (2,'P1T1','TASK',4,2,1,'Project1 Task1 Desc','P1T1 Task Summary'),
 (3,'P1T2','TASK',5,2,1,'Project1 Task2 Desc','P1T2 Task Summary'),
 (4,'P2E1','EPIC',3,2,2,'Project2 Epic1 Desc','P2E1 Epic summary'),
 (5,'P2T1','TASK',3,2,2,'Project2 Task1 Desc','P2T1 Task Summary'),
 (6,'P2T2','TASK',4,2,2,'Project2 Task2 Desc','P2T2 Task Summary'),
 (7,'P3E1','EPIC',5,2,3,'Proj-3 Test Epic1 Desc','Project-3 Test Epic 1');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`issuecomment`
--

DROP TABLE IF EXISTS `issuecomment`;
CREATE TABLE `issuecomment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `issueId` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  `message` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`issuecomment`
--

/*!40000 ALTER TABLE `issuecomment` DISABLE KEYS */;
INSERT INTO `issuecomment` (`id`,`issueId`,`userId`,`message`) VALUES 
 (1,1,2,'Created Epic P1E1'),
 (4,1,2,'Please check'),
 (5,1,1,'Checked. All ok.'),
 (7,2,2,'Created Task P1T1');
/*!40000 ALTER TABLE `issuecomment` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `description` varchar(512) NOT NULL DEFAULT '',
  `mgr` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`project`
--

/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`,`name`,`description`,`mgr`) VALUES 
 (1,'Proj-1','Proj-1 Descriptn',11),
 (2,'Proj-2','Proj-2 Descr',12),
 (3,'Proj-3','Proj-3 desc',11),
 (4,'Proj-4','Proj-4 desc',12),
 (5,'Proj-5','Proj-5 Desc',11),
 (6,'Proj-6','Proj-6 Desc',12);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`rolemaster`
--

DROP TABLE IF EXISTS `rolemaster`;
CREATE TABLE `rolemaster` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`rolemaster`
--

/*!40000 ALTER TABLE `rolemaster` DISABLE KEYS */;
INSERT INTO `rolemaster` (`id`,`name`) VALUES 
 (1,'ROLE_ADMIN'),
 (2,'ROLE_USER'),
 (3,'ROLE_APIUSER'),
 (4,'ROLE_MGR');
/*!40000 ALTER TABLE `rolemaster` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`user_role_mapping`
--

DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping` (
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  `roleId` int(10) unsigned NOT NULL DEFAULT '0',
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_mapping_userId_fk` (`userId`),
  KEY `FK_user_role_mapping_roleId_fk` (`roleId`),
  CONSTRAINT `FK_user_role_mapping_roleId_fk` FOREIGN KEY (`roleId`) REFERENCES `rolemaster` (`id`),
  CONSTRAINT `FK_user_role_mapping_userId_fk` FOREIGN KEY (`userId`) REFERENCES `userdetails` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`user_role_mapping`
--

/*!40000 ALTER TABLE `user_role_mapping` DISABLE KEYS */;
INSERT INTO `user_role_mapping` (`userId`,`roleId`,`id`) VALUES 
 (1,1,1),
 (2,2,3),
 (3,2,4),
 (4,2,5),
 (5,2,6),
 (6,2,7),
 (7,2,8),
 (8,2,9),
 (9,2,10),
 (10,2,11),
 (11,4,12),
 (11,2,13),
 (12,4,14),
 (12,2,15),
 (13,1,16),
 (13,2,17),
 (1,2,20),
 (14,1,21),
 (14,2,22),
 (15,2,23),
 (16,2,24),
 (16,1,25);
/*!40000 ALTER TABLE `user_role_mapping` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE `userdetails` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL DEFAULT '',
  `lastName` varchar(45) DEFAULT NULL,
  `userName` varchar(45) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(45) NOT NULL DEFAULT '',
  `gender` enum('MALE','FEMALE') NOT NULL DEFAULT 'MALE',
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`userdetails`
--

/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`id`,`firstName`,`lastName`,`userName`,`password`,`email`,`gender`,`enabled`) VALUES 
 (1,'Praveen','Lalasangi','praveen','$2a$10$WR5GRc5rpjSNRQBV6SFWZuhoxYvHpuwOkqju92C4xjLmM9Mf97.Be','nlpraveennl@gmail.com','MALE',1),
 (2,'Vedantha','Lalasangi','vedanta','$2a$10$Zz4q0u6Xr3/O9gZXcoryzeuU9zBd2I7pivvqmzSWXZ5fY5PzxI7RK','vedanta@gmail.com','MALE',1),
 (3,'User1','U1LastName','user1','$2a$10$07qE5h2zkkGMRPjpHm/.6OMjRiKpXW8a/7u0uQO.AplizkwRH19R.','user1@gmail.com','MALE',1),
 (4,'User2','U2LastName','user2','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user2@gmail.com','FEMALE',1),
 (5,'User3','U3LastName','user3','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user3@gmail.com','MALE',1),
 (6,'User4','U4LastName','user4','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user4@gmail.com','FEMALE',1),
 (7,'User5','U5LastName','user5','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user5@gmail.com','MALE',1),
 (8,'User6','U6LastName','user6','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user6@gmail.com','FEMALE',1);
INSERT INTO `userdetails` (`id`,`firstName`,`lastName`,`userName`,`password`,`email`,`gender`,`enabled`) VALUES 
 (9,'User7','U7LastName','user7','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user7@gmail.com','MALE',1),
 (10,'user8','U8LastName','user8','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','user8@gmail.com','MALE',1),
 (11,'PM1','PM1LastName','projmgr1','$2a$10$Qeb7BCWLL8V.YR1iLYq.MOZ..4YY7A1wtuiSpweeCNaz5Cz42vB6G','pm1@gmail.com','MALE',1),
 (12,'PM2','PM2LastName','projmgr2','$2a$10$Ej06xAfIwwjJ8/UtRqT7RehC/w5DdXX/kSXceYzyX3.r68/kf3b7y','pm2@gmail.com','MALE',1),
 (13,'Admin','adminLastName','admin','$2a$10$pb7e8NgNLMTGIvVPQjDHxOrbWSM/owrMSeyMFTomAthjWYcDAOh7O','admin@gmail.com','MALE',1),
 (14,'AddCache01FN','AddCache01LN','AddCache01','$2a$10$Nah1uZWYgMB7Yr6LlW6hQe2gdekFJi3KVzoKU17hNGB77X.rEVxRC','AddCache01@gggg.com','FEMALE',1),
 (15,'AddCache02FN','AddCache02LN','AddCache02','$2a$10$K4X9EaGbLP0Be8pxx.ZxVO9VyYlgsq4.uZclqZ22NNh/Z/192/VW6','AddCache2@gggg.com','MALE',1),
 (16,'AddCache03FN','AddCache03LN','AddCache03','$2a$10$MOxrrDsWj6zXIZ3LF8iQSe4cMSRHTT.pW167reshHp7fPm7g05SKq','AddCache3@gggg.com','MALE',1);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;


--
-- Table structure for table `spring_acl_hazelcache`.`userdetails_user_role_mapping`
--

DROP TABLE IF EXISTS `userdetails_user_role_mapping`;
CREATE TABLE `userdetails_user_role_mapping` (
  `UserDetails_id` int(11) NOT NULL,
  `roleList_id` int(11) NOT NULL,
  UNIQUE KEY `UK_9wctxi2vv814x31r34si6pgil` (`roleList_id`),
  KEY `FKfnvhcdbvjh13d519u8txfclrm` (`UserDetails_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spring_acl_hazelcache`.`userdetails_user_role_mapping`
--

/*!40000 ALTER TABLE `userdetails_user_role_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `userdetails_user_role_mapping` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
