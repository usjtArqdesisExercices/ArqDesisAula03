SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `projeto` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `projeto` ;


-- -----------------------------------------------------
-- Table `projeto` .`Cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto`.`Cliente` (
  `idCliente` INT NOT NULL ,
  `Nome` VARCHAR(55) NOT NULL ,
  `Agencia` INT(3) NOT NULL ,
  `Conta` INT(3) NOT NULL ,
  PRIMARY KEY (`idCliente`) )
ENGINE = InnoDB;
-- -----------------------------------------------------
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `projeto` .`Saldo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto`.`Saldo` (
  `idSaldo` INT NOT NULL ,
  `Cliente_idCliente` INT NOT NULL ,
  `Valor` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`idSaldo`) ,
  INDEX `fk_Saldo_Cliente_idx` (`Cliente_idCliente` ASC) ,
  CONSTRAINT `fk_Saldo_Cliente`
    FOREIGN KEY (`Cliente_idCliente` )
    REFERENCES `projeto`.`Cliente` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Insert Saldo
-- -----------------------------------------------------
INSERT INTO SALDO VALUE (1, 1, 1000.00);
INSERT INTO SALDO VALUE (2, 2, 2000.00);
INSERT INTO SALDO VALUE (3, 3, 3000.00);
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `projeto`.`Transferencia`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto`.`Transferencia` (
  `idTransferencia` INT NOT NULL AUTO_INCREMENT,
  `CodClienteCred` INT NOT NULL ,
  `CodClienteDeb` INT NOT NULL ,
  `ValorTransferencia` DECIMAL(10,2) NOT NULL ,
  `SaldoAtual` DECIMAL(10,2) NOT NULL ,
  `DataTransferencia` DATE NOT NULL ,
  PRIMARY KEY (`idTransferencia`) ,
  INDEX `fk_Transferencia_Cliente1_idx` (`CodClienteCred` ASC) ,
  INDEX `fk_Transferencia_Cliente2_idx` (`CodClienteDeb` ASC) ,
  CONSTRAINT `fk_Transferencia_Cliente1`
    FOREIGN KEY (`CodClienteCred` )
    REFERENCES `projeto`.`Cliente` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transferencia_Cliente2`
    FOREIGN KEY (`CodClienteDeb` )
    REFERENCES `projeto`.`Cliente` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`Saque`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto`.`Saque` (
  `idSaque` INT NOT NULL AUTO_INCREMENT ,
  `CodCliente` INT NOT NULL ,
  `ValorSaque` DECIMAL(10,2) NOT NULL ,
  `SaldoAtual` DECIMAL(10,2) NOT NULL ,
  `DataSaque` DATE NOT NULL ,
  PRIMARY KEY (`idSaque`) ,
  INDEX `fk_Saque_Cliente1_idx` (`CodCliente` ASC) ,
  CONSTRAINT `fk_Saque_Cliente1`
    FOREIGN KEY (`CodCliente` )
    REFERENCES `projeto`.`Cliente` (`idCliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`operadoras`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto`.`operadoras` (
  `idOperadoras` INT(11) NOT NULL AUTO_INCREMENT ,
  `CodigoOperadora` INT NOT NULL ,
  `NomeOperadora` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idOperadoras`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Insert Pperadoras
-- -----------------------------------------------------
INSERT INTO OPERADORAS VALUE (1, 111, 'AGUA');
INSERT INTO OPERADORAS VALUE (2, 222, 'LUZ');
INSERT INTO OPERADORAS VALUE (3, 333, 'TELEFONE');
-- -----------------------------------------------------
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `projeto`.`debitoautomatico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`debitoautomatico` (
  `idDebitoAutomatico` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `CodCliente` INT(11) NOT NULL COMMENT '',
  `CodOperadoras` INT(11) NOT NULL COMMENT '',
  `CodConsumidor` INT NOT NULL COMMENT '',
  `ValorDebito` DECIMAL(10,2) NOT NULL COMMENT '',
  `EhBaixado` VARCHAR(55) NOT NULL ,
  `DataDebito` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`idDebitoAutomatico`)  COMMENT '',
  INDEX `fk_DebitoAutomatico_Cliente1_idx` (`CodCliente` ASC)  COMMENT '',
  INDEX `fk_DebitoAutomatico_Operadoras1_idx` (`CodOperadoras` ASC)  COMMENT '',
  CONSTRAINT `fk_DebitoAutomatico_Cliente1`
    FOREIGN KEY (`CodCliente`)
    REFERENCES `projeto`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DebitoAutomatico_Operadoras1`
    FOREIGN KEY (`CodOperadoras`)
    REFERENCES `projeto`.`operadoras` (`idOperadoras`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `projeto`.`MovimentacaoBancaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`MovimentacaoBancaria` (
  `idMovimentacaoBancaria` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `CodCliente` INT NOT NULL COMMENT '',
  `TipoMovimentacao` INT NOT NULL COMMENT '1 - Transferencia / 2 - Saque / 3 - Debito Auto',
  `Tipo` INT NOT NULL COMMENT '1 - Credito / 2 - Debito\n',
  `ValorMovimentacao` DECIMAL(10,2) NOT NULL COMMENT '',
  `SaldoAtual` DECIMAL(10,2) NOT NULL COMMENT '',

  `DataMovimentacao` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`idMovimentacaoBancaria`)  COMMENT '',
  INDEX `fk_MovimentacaoBancaria_Cliente1_idx` (`CodCliente` ASC)  COMMENT '',
  CONSTRAINT `fk_MovimentacaoBancaria_Cliente1`
    FOREIGN KEY (`CodCliente`)
    REFERENCES `projeto`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `projeto` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


/*ALTER TABLE cliente AUTO_INCREMENT = 1
ALTER TABLE movimentacaobancaria AUTO_INCREMENT = 1
ALTER TABLE saque AUTO_INCREMENT = 1*/

-- Insert em MOVIMENTACAOBANCARIA Para Extrato
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (1,1,2,2,500.00,1000.00,'2016-08-12');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (2,1,2,2,700.00,300.00,'2016-08-12');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (3,2,2,2,50.00,1250.00,'2016-08-13');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (4,2,2,2,25.00,1225.00,'2016-08-13');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (5,3,2,2,37.00,963.00,'2016-08-14');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (6,3,2,2,63.00,900.00,'2016-08-14');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (7,4,2,2,250.00,750.00,'2016-08-15');
INSERT INTO `MOVIMENTACAOBANCARIA` (`idMovimentacaoBancaria`,`CodCliente`,`TipoMovimentacao`,`Tipo`,`ValorMovimentacao`,`SaldoAtual`,`DataMovimentacao`) VALUES (8,4,2,2,50.00,700.00,'2016-08-15');


-- Insert em SAQUE
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (1,1,500.00,1000.00,'2016-08-12');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (2,1,700.00,300.00,'2016-08-12');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (3,2,50.00,1250.00,'2016-08-13');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (4,2,25.00,1225.00,'2016-08-13');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (5,3,37.00,963.00,'2016-08-14');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (6,3,63.00,900.00,'2016-08-14');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (7,4,250.00,750.00,'2016-08-15');
INSERT INTO `SAQUE` (`idSaque`,`CodCliente`,`ValorSaque`,`SaldoAtual`,`DataSaque`) VALUES (8,4,50.00,700.00,'2016-08-15');

-- insert cliente
INSERT INTO `CLIENTE` (`idCliente`,`Nome`,`Agencia`,`Conta`) VALUES (1,'ABIMAEL',123,123);
INSERT INTO `CLIENTE` (`idCliente`,`Nome`,`Agencia`,`Conta`) VALUES (2,'JAQUES',456,456);
INSERT INTO `CLIENTE` (`idCliente`,`Nome`,`Agencia`,`Conta`) VALUES (3,'DOUGLAS',789,789);
INSERT INTO `CLIENTE` (`idCliente`,`Nome`,`Agencia`,`Conta`) VALUES (4,'JOAO',987,987);

SELECT * FROM SAQUE;
SELECT * FROM CLIENTE;
SELECT * FROM MOVIMENTACAOBANCARIA;

-- SELECT s.idSaque, s.CodCliente, s.ValorSaque, s.SaldoAtual, current_date() from saque s

-- INSERT INTO Saque (idSaque, CodCliente, ValorSaque, SaldoAtual, DataSaque) VALUES (29, 3, 150.00, 700.00, current_date())

-- SELECT * FROM Saque where idSaque = (select max(idSaque) from SAQUE where CodCliente = 3);

 
/*SELECT * FROM CLIENTE;
SELECT * FROM SALDO;
SELECT * FROM TRANSFERENCIA;
SELECT * FROM SAQUE;
SELECT * FROM OPERADORAS;
SELECT * FROM DEBITOAUTOMATICO;
SELECT * FROM MOVIMENTACAOBANCARIA;

SELECT * FROM MOVIMENTACAOBANCARIA M WHERE M.DataMovimentacao >= '2016-08-14' AND M.DataMovimentacao <= '2016-08-14' AND M.CodCliente = 2;
SELECT * FROM MovimentacaoBancaria WHERE CodCliente = 1 and DataMovimentacao >= '2015-10-15' and DataMovimentacao <= '2015-10-19';
SELECT COUNT(DISTINCT DataMovimentacao) FROM MovimentacaoBancaria WHERE CodCliente = 1 and DataMovimentacao >= '2015-10-15' and DataMovimentacao <= '2015-10-19';
INSERT INTO TRANSFERENCIA VALUE (40, 1, 2, 100.50, 500.00, '2016-10-11');
-- para mostrar no extrato com credito
SELECT DataDebito FROM DEBITOAUTOMATICO where DataDebito > '2015-10-13';*/