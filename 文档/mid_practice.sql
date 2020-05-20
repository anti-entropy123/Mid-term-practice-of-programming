# Host: localhost  (Version: 5.5.53)
# Date: 2020-05-17 17:53:25
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "application"
#

DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) NOT NULL DEFAULT '0',
  `startTime` varchar(30) DEFAULT NULL,
  `endTime` varchar(30) DEFAULT NULL,
  `reason` text,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`application_id`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

#
# Data for table "application"
#

/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (123,1,'2000/6/21','2020/6/21','i want to play a game','out'),(123,2,'2020/7/21','2020/7/22','i still want to play a game','annualLeave');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;

#
# Structure for table "holiday_balance"
#

DROP TABLE IF EXISTS `holiday_balance`;
CREATE TABLE `holiday_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT '',
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "holiday_balance"
#

/*!40000 ALTER TABLE `holiday_balance` DISABLE KEYS */;
INSERT INTO `holiday_balance` VALUES (123,'annualLeave',5);
/*!40000 ALTER TABLE `holiday_balance` ENABLE KEYS */;

#
# Structure for table "leader_opinion"
#

DROP TABLE IF EXISTS `leader_opinion`;
CREATE TABLE `leader_opinion` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `opinion` text,
  `result` varchar(20) DEFAULT NULL,
  `leader_id` varchar(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`application_id`,`leader_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "leader_opinion"
#

/*!40000 ALTER TABLE `leader_opinion` DISABLE KEYS */;
INSERT INTO `leader_opinion` VALUES (1,'i agree with you','agree','1'),(1,'refuse','agree','123');
/*!40000 ALTER TABLE `leader_opinion` ENABLE KEYS */;

#
# Structure for table "member"
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "member"
#

/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'root','yjn','总经理'),(2,'pass','rlj','副总经理'),(3,'pass','mxf','普通员工'),(123,'尤嘉宁','希儿','副总经理'),(1234,'尤嘉宁','希儿','副总经');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

#
# Structure for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`application_id`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

#
# Data for table "message"
#

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (123,1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

#
# Structure for table "overtime"
#

DROP TABLE IF EXISTS `overtime`;
CREATE TABLE `overtime` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`date`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

#
# Data for table "overtime"
#

/*!40000 ALTER TABLE `overtime` DISABLE KEYS */;
INSERT INTO `overtime` VALUES (3,'2020/12/1'),(123,'2020/5/17');
/*!40000 ALTER TABLE `overtime` ENABLE KEYS */;

#
# Structure for table "record"
#

DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(30) NOT NULL DEFAULT '',
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

#
# Data for table "record"
#

/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,'2020/5/14','signed'),(2,'2020/5/14','signed'),(123,'2020/5/17','signed');
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
