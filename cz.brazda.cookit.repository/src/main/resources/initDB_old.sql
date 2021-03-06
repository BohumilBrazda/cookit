-- MySQL Script generated by MySQL Workbench
-- Sat May 13 15:13:16 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cookit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cookit` ;

-- -----------------------------------------------------
-- Schema cookit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cookit` DEFAULT CHARACTER SET utf8 ;
USE `cookit` ;

-- -----------------------------------------------------
-- Table `cookit`.`author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`author` ;

CREATE TABLE IF NOT EXISTS `cookit`.`author` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `second_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`category` ;

CREATE TABLE IF NOT EXISTS `cookit`.`category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`ingredient` ;

CREATE TABLE IF NOT EXISTS `cookit`.`ingredient` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`meal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`meal` ;

CREATE TABLE IF NOT EXISTS `cookit`.`meal` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`pictures` ;

CREATE TABLE IF NOT EXISTS `cookit`.`pictures` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `picture` BLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`meal_picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`meal_picture` ;

CREATE TABLE IF NOT EXISTS `cookit`.`meal_picture` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `meal_id` BIGINT(20) NOT NULL,
  `picture_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_PICTURE_ID_idx` (`picture_id` ASC),
  INDEX `fk_meal_meal_id` (`meal_id` ASC),
  CONSTRAINT `fk_meal_meal_id`
    FOREIGN KEY (`meal_id`)
    REFERENCES `cookit`.`meal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pictures_picture_id`
    FOREIGN KEY (`picture_id`)
    REFERENCES `cookit`.`pictures` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`meal_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`meal_recipe` ;

CREATE TABLE IF NOT EXISTS `cookit`.`meal_recipe` (
  `meal_ID` BIGINT(20) NOT NULL,
  `recipes_ID` BIGINT(20) NOT NULL,
  UNIQUE INDEX `UK_2yry8ylceald5t6y334yd0r82` (`recipes_ID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`user_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`user_event` ;

CREATE TABLE IF NOT EXISTS `cookit`.`user_event` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL DEFAULT NULL,
  `author_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usr_event_author_idx` (`author_id` ASC),
  CONSTRAINT `fk_usr_event_author`
    FOREIGN KEY (`author_id`)
    REFERENCES `cookit`.`author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`recipeDto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`recipeDto` ;

CREATE TABLE IF NOT EXISTS `cookit`.`recipeDto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL DEFAULT NULL,
  `portions` INT(11) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `meal_id` BIGINT(20) NOT NULL,
  `created_id` BIGINT(20) NOT NULL,
  `edited_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_RECIPE_MEAL_idx` (`meal_id` ASC),
  INDEX `fk_recipe_created_idx` (`created_id` ASC),
  INDEX `fk_recipe_edited_idx` (`edited_id` ASC),
  CONSTRAINT `fk_recipe_created`
    FOREIGN KEY (`created_id`)
    REFERENCES `cookit`.`user_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_edited`
    FOREIGN KEY (`edited_id`)
    REFERENCES `cookit`.`user_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_meal`
    FOREIGN KEY (`meal_id`)
    REFERENCES `cookit`.`meal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`recipe_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`recipe_category` ;

CREATE TABLE IF NOT EXISTS `cookit`.`recipe_category` (
  `recipe_ID` BIGINT(20) NOT NULL,
  `categories_ID` BIGINT(20) NOT NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`recipe_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`recipe_item` ;

CREATE TABLE IF NOT EXISTS `cookit`.`recipe_item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `ingredient_id` BIGINT(20) NULL DEFAULT NULL,
  `amounth` DOUBLE NULL DEFAULT NULL,
  `unit` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cookit`.`recipe_picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cookit`.`recipe_picture` ;

CREATE TABLE IF NOT EXISTS `cookit`.`recipe_picture` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `recipeDto` BIGINT(20) NOT NULL,
  `picture_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_PICTURE_ID_idx` (`picture_id` ASC),
  INDEX `fk_recipe_id` (`recipeDto` ASC),
  CONSTRAINT `fk_recipe_id`
    FOREIGN KEY (`recipeDto`)
    REFERENCES `cookit`.`recipeDto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_picture_id`
    FOREIGN KEY (`picture_id`)
    REFERENCES `cookit`.`pictures` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
