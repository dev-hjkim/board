-- MySQL Script generated by MySQL Workbench
-- Sun May 29 23:54:11 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`TB_MEMBER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_MEMBER` (
  `MEMBER_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `USER_ID` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(50) NOT NULL,
  `REG_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`MEMBER_NO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_BOARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_BOARD` (
  `BOARD_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(10) NOT NULL,
  `REG_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BOARD_NO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_POST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_POST` (
  `POST_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `MEMBER_NO` BIGINT NOT NULL,
  `BOARD_NO` BIGINT NOT NULL,
  `TITLE` VARCHAR(100) NOT NULL,
  `CONTENT` TEXT NOT NULL,
  `VIEW_CNT` INT NOT NULL DEFAULT 0,
  `REPLY_CNT` INT NOT NULL DEFAULT 0,
  `REG_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`POST_NO`),
  INDEX `fk_TB_POST_TB_MEMBER_idx` (`MEMBER_NO` ASC),
  INDEX `fk_TB_POST_TB_BOARD_idx` (`BOARD_NO` ASC),
  CONSTRAINT `fk_TB_POST_TB_MEMBER`
    FOREIGN KEY (`MEMBER_NO`)
    REFERENCES `mydb`.`TB_MEMBER` (`MEMBER_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_POST_TB_BOARD`
    FOREIGN KEY (`BOARD_NO`)
    REFERENCES `mydb`.`TB_BOARD` (`BOARD_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_COMMENT` (
  `COMMENT_NO` INT NOT NULL AUTO_INCREMENT,
  `POST_NO` BIGINT NOT NULL,
  `MEMBER_NO` BIGINT NOT NULL,
  `CONTENT` VARCHAR(255) NOT NULL,
  `REG_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DT` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`COMMENT_NO`),
  INDEX `fk_TB_COMMENT_TB_POST1_idx` (`POST_NO` ASC),
  INDEX `fk_TB_COMMENT_TB_MEMBER1_idx` (`MEMBER_NO` ASC),
  CONSTRAINT `fk_TB_COMMENT_TB_POST1`
    FOREIGN KEY (`POST_NO`)
    REFERENCES `mydb`.`TB_POST` (`POST_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_COMMENT_TB_MEMBER1`
    FOREIGN KEY (`MEMBER_NO`)
    REFERENCES `mydb`.`TB_MEMBER` (`MEMBER_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;