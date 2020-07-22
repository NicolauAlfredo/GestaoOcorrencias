SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema policia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `policia` DEFAULT CHARACTER SET utf8 ;
USE `policia` ;

-- -----------------------------------------------------
-- Table `policia`.`profissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`profissao` (
  `id_profissao` INT NOT NULL AUTO_INCREMENT,
  `nome_profissao` VARCHAR(245) NULL,
  PRIMARY KEY (`id_profissao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`provincia` (
  `id_provincia` INT NOT NULL AUTO_INCREMENT,
  `nome_provincia` VARCHAR(245) NULL,
  PRIMARY KEY (`id_provincia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`municipio` (
  `id_municipio` INT NOT NULL AUTO_INCREMENT,
  `nome_municipio` VARCHAR(245) NULL,
  `id_provincia` INT NOT NULL,
  PRIMARY KEY (`id_municipio`),
  INDEX `fk_municipio_provincia_idx` (`id_provincia` ASC),
  CONSTRAINT `fk_municipio_provincia`
    FOREIGN KEY (`id_provincia`)
    REFERENCES `policia`.`provincia` (`id_provincia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`autuado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`autuado` (
  `id_autuado` INT NOT NULL AUTO_INCREMENT,
  `nome_autuado` VARCHAR(45) NULL,
  `pai_autuado` VARCHAR(45) NULL,
  `mae_autuado` VARCHAR(45) NULL,
  `bi_autuado` VARCHAR(45) NULL,
  `residencia_autuado` VARCHAR(45) NULL,
  `data_nascimento_autuado` DATE NULL,
  `sexo_autuado` VARCHAR(45) NULL,
  `proximidade_autuado` VARCHAR(45) NULL,
  `estado_civil_autuado` VARCHAR(45) NULL,
  `data_emissao_bi_autuado` VARCHAR(45) NULL,
  `data_validade_bi_autuado` VARCHAR(45) NULL,
  `telefone_autuado` VARCHAR(45) NULL,
  `id_profissao` INT NOT NULL,
  `id_municipio` INT NOT NULL,
  PRIMARY KEY (`id_autuado`),
  INDEX `fk_autuado_profissao1_idx` (`id_profissao` ASC),
  INDEX `fk_autuado_municipio1_idx` (`id_municipio` ASC),
  CONSTRAINT `fk_autuado_profissao1`
    FOREIGN KEY (`id_profissao`)
    REFERENCES `policia`.`profissao` (`id_profissao`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_autuado_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `policia`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`patente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`patente` (
  `id_patente` INT NOT NULL AUTO_INCREMENT,
  `nome_patente` VARCHAR(245) NULL,
  PRIMARY KEY (`id_patente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`posto_trabalho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`posto_trabalho` (
  `id_posto_trabalho` INT NOT NULL AUTO_INCREMENT,
  `nome_posto_trabalho` VARCHAR(245) NULL,
  `numero_posto_trabalho` VARCHAR(245) NULL,
  `id_municipio` INT NOT NULL,
  PRIMARY KEY (`id_posto_trabalho`),
  INDEX `fk_posto_trabalho_municipio1_idx` (`id_municipio` ASC),
  CONSTRAINT `fk_posto_trabalho_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `policia`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`autuante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`autuante` (
  `id_autuante` INT NOT NULL AUTO_INCREMENT,
  `nome_autuante` VARCHAR(45) NULL,
  `pai_autuante` VARCHAR(45) NULL,
  `mae_autuante` VARCHAR(45) NULL,
  `bi_autuante` VARCHAR(45) NULL,
  `residencia_autuante` VARCHAR(45) NULL,
  `data_nascimento_autuante` VARCHAR(45) NULL,
  `sexo_autuante` VARCHAR(45) NULL,
  `altura_autuante` VARCHAR(45) NULL,
  `data_emissao_bi_autuante` VARCHAR(45) NULL,
  `data_validade_bi_autuante` VARCHAR(45) NULL,
  `nip_autuante` VARCHAR(45) NULL,
  `telefone_autuante` VARCHAR(45) NULL,
  `id_patente` INT NOT NULL,
  `id_municipio` INT NOT NULL,
  `id_posto_trabalho` INT NOT NULL,
  PRIMARY KEY (`id_autuante`),
  INDEX `fk_autuante_patente1_idx` (`id_patente` ASC),
  INDEX `fk_autuante_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_autuante_posto_trabalho1_idx` (`id_posto_trabalho` ASC),
  CONSTRAINT `fk_autuante_patente1`
    FOREIGN KEY (`id_patente`)
    REFERENCES `policia`.`patente` (`id_patente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_autuante_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `policia`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_autuante_posto_trabalho1`
    FOREIGN KEY (`id_posto_trabalho`)
    REFERENCES `policia`.`posto_trabalho` (`id_posto_trabalho`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`tipo_ocorrencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`tipo_ocorrencia` (
  `id_tipo_ocorrencia` INT NOT NULL AUTO_INCREMENT,
  `nome_tipo_ocorrencia` VARCHAR(45) NULL,
  PRIMARY KEY (`id_tipo_ocorrencia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`testemunha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`testemunha` (
  `id_testemunha` INT NOT NULL AUTO_INCREMENT,
  `nome_testemunha` VARCHAR(45) NULL,
  `pai_testemunha` VARCHAR(45) NULL,
  `mae_testemunha` VARCHAR(45) NULL,
  `bi_testemunha` VARCHAR(45) NULL,
  `residencia_testemunha` VARCHAR(45) NULL,
  `data_nascimento_testemunha` VARCHAR(45) NULL,
  `sexo_testemunha` VARCHAR(45) NULL,
  `proximidade_testemunha` VARCHAR(45) NULL,
  `estado_civil_testemunha` VARCHAR(45) NULL,
  `data_emissao_bi_testemunha` VARCHAR(45) NULL,
  `data_validade_bi_testemunha` VARCHAR(45) NULL,
  `telefone_testemunha` VARCHAR(45) NULL,
  `id_municipio` INT NOT NULL,
  `id_profissao` INT NOT NULL,
  PRIMARY KEY (`id_testemunha`),
  INDEX `fk_testemunha_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_testemunha_profissao1_idx` (`id_profissao` ASC),
  CONSTRAINT `fk_testemunha_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `policia`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_testemunha_profissao1`
    FOREIGN KEY (`id_profissao`)
    REFERENCES `policia`.`profissao` (`id_profissao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`ocorrencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`ocorrencia` (
  `id_ocorrencia` INT NOT NULL AUTO_INCREMENT,
  `data_ocorrencia` DATE NULL,
  `hora_ocorrencia` VARCHAR(45) NULL,
  `descricao_ocorrencia` VARCHAR(45) NULL,
  `rua_ocorrencia` VARCHAR(45) NULL,
  `cidade_ocorrencia` VARCHAR(45) NULL,
  `bairro_ocorrencia` VARCHAR(45) NULL,
  `proximidade_ocorrencia` VARCHAR(45) NULL,
  `id_autuado` INT NOT NULL,
  `id_autuante` INT NOT NULL,
  `id_tipo_ocorrencia` INT NOT NULL,
  `id_municipio` INT NOT NULL,
  `id_testemunha` INT NOT NULL,
  `id_testemunha1` INT NOT NULL,
  PRIMARY KEY (`id_ocorrencia`),
  INDEX `fk_ocorrencia_autuado1_idx` (`id_autuado` ASC),
  INDEX `fk_ocorrencia_autuante1_idx` (`id_autuante` ASC),
  INDEX `fk_ocorrencia_tipo_ocorrencia1_idx` (`id_tipo_ocorrencia` ASC),
  INDEX `fk_ocorrencia_municipio1_idx` (`id_municipio` ASC),
  INDEX `fk_ocorrencia_testemunha1_idx` (`id_testemunha` ASC),
  INDEX `fk_ocorrencia_testemunha2_idx` (`id_testemunha1` ASC),
  CONSTRAINT `fk_ocorrencia_autuado1`
    FOREIGN KEY (`id_autuado`)
    REFERENCES `policia`.`autuado` (`id_autuado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ocorrencia_autuante1`
    FOREIGN KEY (`id_autuante`)
    REFERENCES `policia`.`autuante` (`id_autuante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ocorrencia_tipo_ocorrencia1`
    FOREIGN KEY (`id_tipo_ocorrencia`)
    REFERENCES `policia`.`tipo_ocorrencia` (`id_tipo_ocorrencia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ocorrencia_municipio1`
    FOREIGN KEY (`id_municipio`)
    REFERENCES `policia`.`municipio` (`id_municipio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ocorrencia_testemunha1`
    FOREIGN KEY (`id_testemunha`)
    REFERENCES `policia`.`testemunha` (`id_testemunha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocorrencia_testemunha2`
    FOREIGN KEY (`id_testemunha1`)
    REFERENCES `policia`.`testemunha` (`id_testemunha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `policia`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia`.`administrador` (
  `id_administrador` INT NOT NULL AUTO_INCREMENT,
  `nome_administrador` VARCHAR(245) NOT NULL,
  `data_nascimento_administrador` VARCHAR(45) NOT NULL,
  `sexo_administrador` VARCHAR(45) NOT NULL,
  `bi_administrador` VARCHAR(45) NOT NULL,
  `nip_administrador` VARCHAR(45) NOT NULL,
  `email_administrador` VARCHAR(45) NULL,
  `telefone_administrador` VARCHAR(45) NULL,
  `palavra_passe_administrador` VARCHAR(245) NOT NULL,
  PRIMARY KEY (`id_administrador`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
