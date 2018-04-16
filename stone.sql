-- MySQL Script generated by MySQL Workbench
-- Mon Apr  9 11:40:25 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema stonebank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stonebank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stonebank` DEFAULT CHARACTER SET utf8 ;
USE `stonebank` ;

-- -----------------------------------------------------
-- Table `stonebank`.`tRol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tRol` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tRol` (
  `idtRol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtRol`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stonebank`.`tUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tUsuario` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tUsuario` (
  `dniUsuario` INT NOT NULL,
  `tRol_idtRol` INT NOT NULL,
  `numCuenta` INT NOT NULL,
  `hashContrasena` VARCHAR(64) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(300) NOT NULL,
  `telefono` INT NULL,
  `email` VARCHAR(300) NULL,
  `domicilio` VARCHAR(400) NULL,
  PRIMARY KEY (`dniUsuario`),
  INDEX `fk_tUsuario_tRol1_idx` (`tRol_idtRol` ASC),
  UNIQUE INDEX `numCuenta_UNIQUE` (`numCuenta` ASC),
  UNIQUE INDEX `dniUsuario_UNIQUE` (`dniUsuario` ASC),
  CONSTRAINT `fk_tUsuario_tRol1`
    FOREIGN KEY (`tRol_idtRol`)
    REFERENCES `stonebank`.`tRol` (`idtRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stonebank`.`tPermiso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tPermiso` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tPermiso` (
  `idtPermiso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idtPermiso`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stonebank`.`tMovimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tMovimiento` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tMovimiento` (
  `idtMovimiento` INT NOT NULL AUTO_INCREMENT,
  `tUsuario_dniUsuario` INT NOT NULL,
  `concepto` VARCHAR(200) NULL,
  `cantidad` DOUBLE NULL,
  `ibanEntidad` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`idtMovimiento`),
  INDEX `fk_tMovimiento_tUsuario_idx` (`tUsuario_dniUsuario` ASC),
  CONSTRAINT `fk_tMovimiento_tUsuario`
    FOREIGN KEY (`tUsuario_dniUsuario`)
    REFERENCES `stonebank`.`tUsuario` (`dniUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stonebank`.`tRol_has_tPermiso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tRol_has_tPermiso` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tRol_has_tPermiso` (
  `tRol_idtRol` INT NOT NULL,
  `tPermiso_idtPermiso` INT NOT NULL,
  PRIMARY KEY (`tRol_idtRol`, `tPermiso_idtPermiso`),
  INDEX `fk_tRol_has_tPermiso_tPermiso1_idx` (`tPermiso_idtPermiso` ASC),
  INDEX `fk_tRol_has_tPermiso_tRol1_idx` (`tRol_idtRol` ASC),
  CONSTRAINT `fk_tRol_has_tPermiso_tRol1`
    FOREIGN KEY (`tRol_idtRol`)
    REFERENCES `stonebank`.`tRol` (`idtRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tRol_has_tPermiso_tPermiso1`
    FOREIGN KEY (`tPermiso_idtPermiso`)
    REFERENCES `stonebank`.`tPermiso` (`idtPermiso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stonebank`.`tTranferencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stonebank`.`tTranferencia` ;

CREATE TABLE IF NOT EXISTS `stonebank`.`tTranferencia` (
  `id` INT NOT NULL,
  `DNIEmisor` INT NOT NULL,
  `DNIReceptor` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `concepto` VARCHAR(200) NULL,
  `fecha` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tUsuario_has_tUsuario_tUsuario2_idx` (`DNIReceptor` ASC),
  INDEX `fk_tUsuario_has_tUsuario_tUsuario1_idx` (`DNIEmisor` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_tUsuario_has_tUsuario_tUsuario1`
    FOREIGN KEY (`DNIEmisor`)
    REFERENCES `stonebank`.`tUsuario` (`dniUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tUsuario_has_tUsuario_tUsuario2`
    FOREIGN KEY (`DNIReceptor`)
    REFERENCES `stonebank`.`tUsuario` (`dniUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
