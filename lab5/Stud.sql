drop  database if exists hospital; 
create database hospital DEFAULT CHARACTER SET utf8;

use hospital;

DROP TABLE if exists patients ; 

DROP TABLE if exists doctors ; 

DROP TABLE if exists illnesses ; 
CREATE TABLE `hospital`.`doctors` (
  `doc_id` BIGINT NOT NULL AUTO_INCREMENT,
  `doc_name` VARCHAR(45) NULL COMMENT '',
  `department` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`doc_id`)  COMMENT '');
  
INSERT INTO `hospital`.`doctors` (`doc_id`, `doc_name`, `department`) VALUES ('1', 'Piskunov D.V.', 'surgery');
INSERT INTO `hospital`.`doctors` (`doc_id`, `doc_name`, `department`) VALUES ('2', 'Boravik K.V.', 'cardiology');
  
  CREATE TABLE `hospital`.`patients` (
  `pat_id` BIGINT NOT NULL AUTO_INCREMENT,
  `pat_name` VARCHAR(45) NULL COMMENT '',
  `surname` VARCHAR(45) NULL COMMENT '',
  `phone_number` VARCHAR(20) NULL COMMENT '',
  `doc_id` BIGINT NULL COMMENT '',
  PRIMARY KEY (`pat_id`)  COMMENT '',
  CONSTRAINT `gk`
    FOREIGN KEY (`doc_id`)
    REFERENCES `hospital`.`doctors` (`doc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
  
    
INSERT INTO `hospital`.`patients` (`pat_id`, `pat_name`, `surname`, `phone_number`, `doc_id`) VALUES ('1', 'Daniil', 'Rummo', '322223322', '1');
INSERT INTO `hospital`.`patients` (`pat_id`, `pat_name`, `surname`, `phone_number`, `doc_id`) VALUES ('2', 'Artem', 'Udovik', '231456', '1');
INSERT INTO `hospital`.`patients` (`pat_id`, `pat_name`, `surname`, `phone_number`, `doc_id`) VALUES ('3', 'Nikita', 'Sinevich', '1234567890', '2');


   CREATE TABLE `hospital`.`illnesses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '',
  `date_start` date NULL COMMENT '',
  `date_end` date NULL COMMENT '',
  `pat_id` BIGINT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `sk`
    FOREIGN KEY (`pat_id`)
    REFERENCES `hospital`.`patients` (`pat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
INSERT INTO `hospital`.`illnesses` (`id`, `name`, `date_start`, `date_end`, `pat_id`) VALUES ('1', 'GRIPP', '25.03.2003', '30.03.2003', '1');
INSERT INTO `hospital`.`illnesses` (`id`, `name`, `date_start`, `date_end`, `pat_id`) VALUES ('2', 'KORONA', '08.04.2004', NULL, '1');
INSERT INTO `hospital`.`illnesses` (`id`, `name`, `date_start`, `date_end`, `pat_id`) VALUES ('3', 'VETRYANKA', '24.04.2004', '27.04.2004', '2');

CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` varchar(64) NOT NULL,
	`password` varchar(64) NOT NULL,
	`authority` varchar(64) NOT NULL,
	PRIMARY KEY (`id`)
);


insert into `users` (id, username, password, authority) values 
	(1, 'admin', '$2a$10$jrryFNptnoGWwyWhxc47eeeHpin/LPOut7J221Xv4DB3qTswVcvJS', 'ROLE_ADMIN'),
	(2, 'user', '$2a$10$I0BOCCDqRH6905RIlUmgd.2L008fmT3QvFtjEynyJQ2WoKDFRNGo6','ROLE_USER');