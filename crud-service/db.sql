create database myappdatabase;
CREATE TABLE `myappdatabase`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `zipcode` VARCHAR(10) NOT NULL,
  `street` VARCHAR(1024) NOT NULL,
  `number` INT NOT NULL,
  `neighborhood` VARCHAR(1024) NULL,
  `complement` VARCHAR(2048) NULL,
  `city` VARCHAR(1024) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`));
