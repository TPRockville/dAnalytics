CREATE DATABASE `dAnalytics`;

USE `dAnalytics`;

/*Table structure for table `age_group_list` */

DROP TABLE IF EXISTS `age_group_list`;

CREATE TABLE `age_group_list` (
  `age_group_id` bigint(100) NOT NULL,
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
  `country_id` bigint(100) NOT NULL,
  `country_code` varchar(50) DEFAULT NULL,
  `country_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `drug_indication` */

DROP TABLE IF EXISTS `drug_indication`;

CREATE TABLE `drug_indication` (
  `drug_indication_id` bigint(100) NOT NULL,
  `drug_indication_code` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`drug_indication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `drug_list`;

CREATE TABLE `drug_list` (
  `drug_id` bigint(100) NOT NULL,
  `drug_name` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `drug_substance_list` */

DROP TABLE IF EXISTS `drug_substance_list`;

CREATE TABLE `drug_substance_list` (
  `drug_substance_id` bigint(100) NOT NULL,
  `drug_substance` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`drug_substance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `gender` */

DROP TABLE IF EXISTS `gender`;

CREATE TABLE `gender` (
  `gender_id` bigint(100) NOT NULL,
  `gender_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gender` */

insert  into `gender`(`gender_id`,`gender_name`) values ('0','Unknown'),('1','Female'),('2','male');

/*Table structure for table `reaction_list` */

DROP TABLE IF EXISTS `reaction_list`;

CREATE TABLE `reaction_list` (
  `reaction_id` bigint(100) NOT NULL,
  `reaction_code` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`reaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `weight_group_list` */

DROP TABLE IF EXISTS `weight_group_list`;

CREATE TABLE `weight_group_list` (
  `weight_group_id` bigint(100) NOT NULL,
  `weight_group` varchar(20) DEFAULT NULL,
  `min_age` decimal(3,0) DEFAULT NULL,
  `max_age` decimal(3,0) DEFAULT NULL,
  PRIMARY KEY (`weight_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weight_group_list` */

insert  into `weight_group_list`(`weight_group_id`,`weight_group`,`min_age`,`max_age`) values ('1','Unknown',NULL,NULL),('2','upto 50','0','50'),('3','50 to 100','50','100'),('4','101 to 150','100','150'),('5','151 to 200','151','200'),('6','200 to 250','201','250');

/*Table structure for table `drug_summary` */

DROP TABLE IF EXISTS `drug_summary`;

CREATE TABLE `drug_summary` (
  `drug_summary_id` bigint(100) NOT NULL auto_increment,
  `drug_id` bigint(100) DEFAULT NULL,
  `country_id` bigint(100) DEFAULT NULL,
  `gender_id` bigint(100) DEFAULT NULL,
  `age_group_id` bigint(100) DEFAULT NULL,
  `weight_group_id` bigint(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `event_count` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`drug_summary_id`),
  KEY `drug_id_fk` (`drug_id`),
  KEY `age_group_id_fk` (`age_group_id`),
  KEY `gender_id_fk` (`gender_id`),
  KEY `country_id_fk` (`country_id`),
  KEY `weight_group_id` (`weight_group_id`),
  CONSTRAINT `age_group_id_fk` FOREIGN KEY (`age_group_id`) REFERENCES `age_group_list` (`age_group_id`),
  CONSTRAINT `country_id_fk` FOREIGN KEY (`country_id`) REFERENCES `country_list` (`country_id`),
  CONSTRAINT `drug_id_fk` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gender_id_fk` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`),
  CONSTRAINT `weight_group_id` FOREIGN KEY (`weight_group_id`) REFERENCES `weight_group_list` (`weight_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `drug_reaction_summary` */

DROP TABLE IF EXISTS `drug_reaction_summary`;

CREATE TABLE `drug_reaction_summary` (
  `drug_reaction_summary_id` bigint(100) NOT NULL auto_increment,
  `drug_id` bigint(100) DEFAULT NULL,
  `reaction_id` bigint(100) DEFAULT NULL,
  `event_count` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`drug_reaction_summary_id`),
  KEY `drug_id_fk1` (`drug_id`),
  KEY `drug_reaction_id_pk` (`reaction_id`),
  CONSTRAINT `drug_id_fk1` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `drug_reaction_id_pk` FOREIGN KEY (`reaction_id`) REFERENCES `reaction_list` (`reaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*Table structure for table `drug_characterization` */

DROP TABLE IF EXISTS `drug_characterization`;

CREATE TABLE `drug_characterization` (
  `drug_characterization_id` bigint(10) NOT NULL,
  `drug_characterization_code` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`drug_characterization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table drug_characterization` */

insert  into `drug_characterization`(`drug_characterization_id`,`drug_characterization_code`) values (1,1),(2,2),(3,3);

/*Table structure for table `drug_char_summary` */

DROP TABLE IF EXISTS `drug_char_summary`;

CREATE TABLE `drug_char_summary` (
  `drug_char_summary_id` bigint(100) NOT NULL auto_increment,
  `drug_id` bigint(100) DEFAULT NULL,
  `drug_characterization_id` bigint(100) DEFAULT NULL,
  `event_count` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`drug_char_summary_id`),
  KEY `drug_char_id_fk` (`drug_char_summary_id`),
  KEY `drug_characterization_id_pk` (`drug_characterization_id`),
  CONSTRAINT `drug_id_char_fk` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `drug_char_id_fk` FOREIGN KEY (`drug_characterization_id`) REFERENCES `drug_characterization` (`drug_characterization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*Table structure for table `drug_summary_month` */

DROP TABLE IF EXISTS `drug_summary_month`;

CREATE TABLE `drug_summary_month` (
  `drug_summary_month_id` bigint(100) NOT NULL auto_increment,
  `drug_id` bigint(100) DEFAULT NULL,
  `country_id` bigint(100) DEFAULT NULL,
  `gender_id` bigint(100) DEFAULT NULL,
  `age_group_id` bigint(100) DEFAULT NULL,
  `weight_group_id` bigint(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `event_count` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`drug_summary_month_id`),
  KEY `drug_id_month_fk` (`drug_id`),
  KEY `age_group_id_month_fk` (`age_group_id`),
  KEY `gender_id_month_fk` (`gender_id`),
  KEY `country_id_month_fk` (`country_id`),
  KEY `weight_group_month_id` (`weight_group_id`),
  CONSTRAINT `age_group_id_month_fk` FOREIGN KEY (`age_group_id`) REFERENCES `age_group_list` (`age_group_id`),
  CONSTRAINT `country_id_month_fk` FOREIGN KEY (`country_id`) REFERENCES `country_list` (`country_id`),
  CONSTRAINT `drug_id_month_fk` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `gender_id_month_fk` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`),
  CONSTRAINT `weight_group_month_id` FOREIGN KEY (`weight_group_id`) REFERENCES `weight_group_list` (`weight_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*Table structure for table `drug_indication_link` */

DROP TABLE IF EXISTS `drug_indication_link`;

CREATE TABLE `drug_indication_link` (
  `drug_id` bigint(100) DEFAULT NULL,
  `drug_indication_id` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`drug_id`,`drug_indication_id`),
  KEY `drug_id_ind_fk` (`drug_id`),
  KEY `drug_ind_link_id_fk` (`drug_indication_id`),
  CONSTRAINT `drug_id_ind_fk` FOREIGN KEY (`drug_id`) REFERENCES `drug_list` (`drug_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `drug_ind_link_id_fk` FOREIGN KEY (`drug_indication_id`) REFERENCES `drug_indication` (`drug_indication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `drug_spikes`;

CREATE TABLE `drug_spikes` (
  `spike_id` bigint(255) NOT NULL,
  `drug_id` bigint(255) DEFAULT NULL,
  `drug_event_avg` varchar(255) DEFAULT NULL,
  `event_count` varchar(255) DEFAULT NULL,
  `drug_event_threshold` varchar(255) DEFAULT NULL,
  `spike_date` datetime DEFAULT NULL,
  PRIMARY KEY (`spike_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `medicaid` (
  `id` int(11) NOT NULL,
  `Year` varchar(4) NOT NULL,
  `Primary_County` longtext NOT NULL,
  `Dual_Eligible` longtext NOT NULL,
  `Major_Diagnostic_Category` longtext NOT NULL,
  `Episode_Disease_Category` longtext NOT NULL,
  `Beneficiaries_with_Condition` bigint(10) NOT NULL,
  `Beneficiaries_with_Admissions` bigint(10) NOT NULL,
  `Total_Inpatient_Admissions` bigint(10) NOT NULL,
  `Beneficiaries_with_ER_Visits` bigint(10) NOT NULL,
  `Total_ER_Visits` bigint(10) NOT NULL
);


CREATE TABLE hospital_discharge_details
(
id int(11) not null,
health_service_area longtext ,
hospital_county longtext NOT NULL,
operating_certificate_number bigint(10) ,
facility_id bigint(10) ,
facility_name longtext ,
age_group varchar(10) ,
zipcode_3digits varchar(10) ,
gender varchar(10) ,
race longtext ,
ethnicity longtext ,
length_of_stay bigint(10) ,
admit_day_of_week varchar(10) ,
type_of_admission longtext ,
patient_disposition longtext ,
discharge_year varchar(4),
discharge_day_of_week varchar(10) ,
ccs_diagnosis_code bigint(10) ,
ccs_diagnosis_description longtext ,
ccs_procedure_code bigint(10) ,
ccs_procedure_description longtext ,
apr_drg_code bigint(10) ,
apr_drg_description longtext ,
apr_mdc_code bigint(10) ,
apr_mdc_description longtext ,
apr_severity_of_illness_code bigint(10) ,
apr_severity_of_illness_description longtext ,
apr_risk_of_mortality varchar(10) ,
apr_medical_surgical_description varchar(100) ,
source_of_payment_1 varchar(100) ,
source_of_payment_2 varchar(100) ,
source_of_payment_3 varchar(100) ,
attending_provider_license_number bigint(10) ,
operating_Provider_License_number bigint(10) ,
other_provider_license_Number bigint(10) ,
birth_weight int(11) ,
abortion_edit_indicator varchar(10),
emergency_department_indicator varchar(10),
total_charges longtext ,
total_costs longtext 
);


create table er_summary
(
er_summary_id int not null,
year varchar(4),
episode_disease_category longtext,
sum_total_er_visits bigint(10),
primary key(er_summary_id)
);


create table discharge_summary
(
discharge_summary_id int not null,
discharge_year varchar(4),
apr_drg_description longtext,
total_count bigint(10),
primary key(discharge_summary_id)
);

CREATE TABLE `country_code` (
  `id` bigint(100) NOT NULL DEFAULT '0',
  `country_name` varchar(100) DEFAULT NULL,
  `country_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `drug_only_summary_month`
    (
        `drug_only_summary_month_id` bigint(255) NOT NULL AUTO_INCREMENT,
        `drug_id` bigint(100),
        `start_date` DATE,
        `end_date` DATE,
        `event_count` bigint(100),
        PRIMARY KEY (`drug_only_summary_month_id`),
        CONSTRAINT `drug_only_summary_month_fk1` FOREIGN KEY (`drug_id`) REFERENCES drug_list (`drug_id`)
      )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX DS_DRUG_IDX ON drug_summary(drug_id);
CREATE INDEX DS_DRUG_AGE_IDX ON drug_summary(drug_id,age_group_id);
CREATE INDEX DS_DRUG_COUNTRY_IDX ON drug_summary(drug_id,country_id);
CREATE INDEX DS_DRUG_GENDER_IDX ON drug_summary(drug_id,gender_id);
CREATE INDEX DS_DRUG_WEIGHT_IDX ON drug_summary(drug_id,weight_group_id);
CREATE INDEX DS_DRUG_DATE_IDX ON drug_summary(drug_id,start_date);

CREATE INDEX DS_M_DRUG_IDX ON drug_summary_month(drug_id);
CREATE INDEX DS_M_DRUG_AGE_IDX ON drug_summary_month(drug_id,age_group_id);
CREATE INDEX DS_M_DRUG_COUNTRY_IDX ON drug_summary_month(drug_id,country_id);
CREATE INDEX DS_M_DRUG_GENDER_IDX ON drug_summary_month(drug_id,gender_id);
CREATE INDEX DS_M_DRUG_WEIGHT_IDX ON drug_summary_month(drug_id,weight_group_id);
CREATE INDEX DS_M_DRUG_DATE_IDX ON drug_summary_month(drug_id,start_date);

CREATE INDEX DOS_M_DRUG_IDX ON drug_only_summary_month(drug_id);
CREATE INDEX DOS_M_DATE_IDX ON drug_only_summary_month(start_date);