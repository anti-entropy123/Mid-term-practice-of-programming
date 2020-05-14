# Host:   (Version: 5.5.53)
# Date: 2020-05-14 14:58:07
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "application"
#

DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `user_id` varchar(11) NOT NULL DEFAULT '',
  `application_id` varchar(11) NOT NULL DEFAULT '',
  `startTime` varchar(30) DEFAULT NULL,
  `endTime` varchar(30) DEFAULT NULL,
  `reason` text,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`application_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "application"
#

/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;

#
# Structure for table "holiday_balance"
#

DROP TABLE IF EXISTS `holiday_balance`;
CREATE TABLE `holiday_balance` (
  `id` varchar(11) NOT NULL DEFAULT '',
  `type` varchar(255) NOT NULL DEFAULT '',
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "holiday_balance"
#

/*!40000 ALTER TABLE `holiday_balance` DISABLE KEYS */;
/*!40000 ALTER TABLE `holiday_balance` ENABLE KEYS */;

#
# Structure for table "leader_opinion"
#

DROP TABLE IF EXISTS `leader_opinion`;
CREATE TABLE `leader_opinion` (
  `application_id` varchar(11) NOT NULL DEFAULT '',
  `opinion` text,
  `result` varchar(20) DEFAULT NULL,
  `leader_id` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "leader_opinion"
#

/*!40000 ALTER TABLE `leader_opinion` DISABLE KEYS */;
/*!40000 ALTER TABLE `leader_opinion` ENABLE KEYS */;

#
# Structure for table "member"
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` varchar(11) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "member"
#

/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

#
# Structure for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `user_id` varchar(11) NOT NULL DEFAULT '',
  `application_id` varchar(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`application_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "message"
#

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

#
# Structure for table "overtime"
#

DROP TABLE IF EXISTS `overtime`;
CREATE TABLE `overtime` (
  `user_id` varchar(11) NOT NULL DEFAULT '',
  `date` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "overtime"
#

/*!40000 ALTER TABLE `overtime` DISABLE KEYS */;
/*!40000 ALTER TABLE `overtime` ENABLE KEYS */;

#
# Structure for table "record"
#

DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` varchar(11) NOT NULL DEFAULT '',
  `date` varchar(30) NOT NULL DEFAULT '',
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "record"
#

/*!40000 ALTER TABLE `record` DISABLE KEYS */;
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
