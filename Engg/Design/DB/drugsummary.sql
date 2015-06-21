/*
SQLyog Community v12.08 (32 bit)
MySQL - 5.6.23-log : Database - drugsummary
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drugsummary` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `drugsummary`;

/*Table structure for table `age_group_list` */

DROP TABLE IF EXISTS `age_group_list`;

CREATE TABLE `age_group_list` (
  `age_group_id` decimal(10,0) NOT NULL,
  `age_group` varchar(50) DEFAULT NULL,
  `min_age` decimal(3,0) DEFAULT NULL,
  `max_age` decimal(3,0) DEFAULT NULL,
  PRIMARY KEY (`age_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `age_group_list` */

insert  into `age_group_list`(`age_group_id`,`age_group`,`min_age`,`max_age`) values ('1','unknown',NULL,NULL),('2','0 to 20','0','20'),('3','21 to 40','21','40'),('4','41 to 60','41','60'),('5','61 to 80','61','80');

/*Table structure for table `country_list` */

DROP TABLE IF EXISTS `country_list`;

CREATE TABLE `country_list` (
  `country_id` decimal(10,0) NOT NULL,
  `country_code` char(2) DEFAULT NULL,
  `country_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `country_list` */

insert  into `country_list`(`country_id`,`country_code`,`country_name`) values ('1','US','US'),('2','IN','India'),('3','US','United status of America');

/*Table structure for table `drug_indication` */

DROP TABLE IF EXISTS `drug_indication`;

CREATE TABLE `drug_indication` (
  `drug_indication_id` decimal(10,0) NOT NULL,
  `drug_indication_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`drug_indication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `drug_indication` */

insert  into `drug_indication`(`drug_indication_id`,`drug_indication_code`) values ('1','Unknown'),('2','RHEUMATOID ARTHRITIS'),('3','HYPOPHARYNGEAL CANCER');

/*Table structure for table `drug_list` */

DROP TABLE IF EXISTS `drug_list`;

CREATE TABLE `drug_list` (
  `drug_id` decimal(10,0) NOT NULL,
  `drug_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `drug_list` */

insert  into `drug_list`(`drug_id`,`drug_name`) values ('1','UNKNOWN'),('2','PREDNISONE'),('3','HUMIRA'),('4','CYCLOPHOSPHAMIDE'),('5',NULL);

/*Table structure for table `drug_substance_list` */

DROP TABLE IF EXISTS `drug_substance_list`;

CREATE TABLE `drug_substance_list` (
  `drug_substance_id` decimal(10,0) NOT NULL,
  `drug_substance` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`drug_substance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `drug_substance_list` */

insert  into `drug_substance_list`(`drug_substance_id`,`drug_substance`) values ('1','Unknown'),('2','METOPROLOL'),('3','ERGOCALCIFEROL'),('4','DENOSUMAB');

/*Table structure for table `drug_summary` */

DROP TABLE IF EXISTS `drug_summary`;

CREATE TABLE `drug_summary` (
  `drug_summary_id` decimal(10,0) NOT NULL,
  `drug_id` decimal(10,0) DEFAULT NULL,
  `drug_indication` decimal(10,0) DEFAULT NULL,
  `drug_substance_id` decimal(10,0) DEFAULT NULL,
  `drug_reaction_id` decimal(10,0) DEFAULT NULL,
  `event_severity_id` decimal(10,0) DEFAULT NULL,
  `country_id` decimal(10,0) DEFAULT NULL,
  `gender_id` decimal(10,0) DEFAULT NULL,
  `age_group_id` decimal(10,0) DEFAULT NULL,
  `weight_group_id` decimal(10,0) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `event_count` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`drug_summary_id`),
  KEY `drug_id_fk` (`drug_id`),
  KEY `drug_substance_fk` (`drug_substance_id`),
  KEY `drug_reaction_id_fk` (`drug_reaction_id`),
  KEY `event_severity_id_fk` (`event_severity_id`),
  KEY `age_group_id_fk` (`age_group_id`),
  KEY `gender_id_fk` (`gender_id`),
  KEY `country_id_fk` (`country_id`),
  KEY `drug_ind_id_fk` (`drug_indication`),
  KEY `weight_group_id` (`weight_group_id`),
  CONSTRAINT `age_group_id_fk` FOREIGN KEY (`age_group_id`) REFERENCES `age_group_list` (`age_group_id`),
  CONSTRAINT `country_id_fk` FOREIGN KEY (`country_id`) REFERENCES `country_list` (`country_id`),
  CONSTRAINT `drug_id_fk` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `drug_ind_id_fk` FOREIGN KEY (`drug_indication`) REFERENCES `drug_indication` (`drug_indication_id`),
  CONSTRAINT `drug_reaction_id_fk` FOREIGN KEY (`drug_reaction_id`) REFERENCES `reaction_list` (`reaction_id`),
  CONSTRAINT `drug_substance_fk` FOREIGN KEY (`drug_substance_id`) REFERENCES `drug_substance_list` (`drug_substance_id`),
  CONSTRAINT `event_severity_id_fk` FOREIGN KEY (`event_severity_id`) REFERENCES `event_severity` (`event_severity_id`),
  CONSTRAINT `gender_id_fk` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`),
  CONSTRAINT `weight_group_id` FOREIGN KEY (`weight_group_id`) REFERENCES `weight_group_list` (`weight_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `drug_summary` */

insert  into `drug_summary`(`drug_summary_id`,`drug_id`,`drug_indication`,`drug_substance_id`,`drug_reaction_id`,`event_severity_id`,`country_id`,`gender_id`,`age_group_id`,`weight_group_id`,`start_date`,`end_date`,`event_count`) values ('1','1','1','1','1','1','1','1','1','1','2014-03-01','2015-03-31','134'),('2','2','1','2','2','1','2','1','1','1','2014-05-01','2014-05-31','234'),('3','2','2','2','2','2','2','2','1','1','2014-06-01','2014-06-30','145');

/*Table structure for table `event_severity` */

DROP TABLE IF EXISTS `event_severity`;

CREATE TABLE `event_severity` (
  `event_severity_id` decimal(10,0) NOT NULL,
  `serious_code` decimal(2,0) DEFAULT NULL,
  `serious description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`event_severity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `event_severity` */

insert  into `event_severity`(`event_severity_id`,`serious_code`,`serious description`) values ('1','1',NULL),('2','2',NULL),('3','3',NULL);

/*Table structure for table `gender` */

DROP TABLE IF EXISTS `gender`;

CREATE TABLE `gender` (
  `gender_id` decimal(10,0) NOT NULL,
  `gender_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gender` */

insert  into `gender`(`gender_id`,`gender_name`) values ('1','Unknown'),('2','Female'),('3','male');

/*Table structure for table `reaction_list` */

DROP TABLE IF EXISTS `reaction_list`;

CREATE TABLE `reaction_list` (
  `reaction_id` decimal(10,0) NOT NULL,
  `reaction_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`reaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reaction_list` */

insert  into `reaction_list`(`reaction_id`,`reaction_code`) values ('1','Unknown'),('2','Injury'),('3','Tooth disorder');

/*Table structure for table `weight_group_list` */

DROP TABLE IF EXISTS `weight_group_list`;

CREATE TABLE `weight_group_list` (
  `weight_group_id` decimal(10,0) NOT NULL,
  `weight_group` varchar(20) DEFAULT NULL,
  `min_age` decimal(3,0) DEFAULT NULL,
  `max_age` decimal(3,0) DEFAULT NULL,
  PRIMARY KEY (`weight_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weight_group_list` */

insert  into `weight_group_list`(`weight_group_id`,`weight_group`,`min_age`,`max_age`) values ('1','Unknown',NULL,NULL),('2','upto 50','0','50'),('3','50 to 100','50','100'),('4','101 to 150','100','150'),('5','151 to 200','151','200'),('6','200 to 250','201','250');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;