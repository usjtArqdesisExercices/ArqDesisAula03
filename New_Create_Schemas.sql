-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema projeto
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `projeto` ;

-- -----------------------------------------------------
-- Schema projeto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`TIPO_MOVIMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TIPO_MOVIMENTACAO` (
  `idTIPO_MOVIMENTACAO` INT NOT NULL,
  `Descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTIPO_MOVIMENTACAO`))
ENGINE = InnoDB;

USE `projeto` ;

-- -----------------------------------------------------
-- Table `projeto`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`CLIENTE` (
  `idCliente` INT(11) NOT NULL,
  `Nome` VARCHAR(55) NOT NULL,
  `Agencia` INT(3) NOT NULL,
  `Conta` INT(3) NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `projeto`.`OPERADORAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`OPERADORAS` (
  `idOperadoras` INT(11) NOT NULL AUTO_INCREMENT,
  `CodigoOperadora` INT NOT NULL,
  `NomeOperadora` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOperadoras`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `projeto`.`DEBIT_AUTOMATICO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`DEBIT_AUTOMATICO` (
  `idDEBIT_AUTOMATICO` INT(11) NOT NULL,
  `idCliente` INT NOT NULL,
  `idOperadoras` INT(11) NOT NULL,
  `CodConsumidor` INT(11) NOT NULL,
  `ValorDebito` DECIMAL(10,2) NOT NULL,
  `DataDebito` DATE NOT NULL,
  PRIMARY KEY (`idDEBIT_AUTOMATICO`),
  CONSTRAINT `fk_DebitoAutomatico_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DebitoAutomatico_Operadoras1`
    FOREIGN KEY (`idOperadoras`)
    REFERENCES `projeto`.`OPERADORAS` (`idOperadoras`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_DebitoAutomatico_Cliente1_idx` ON `projeto`.`DEBIT_AUTOMATICO` (`idCliente` ASC);

CREATE INDEX `fk_DebitoAutomatico_Operadoras1_idx` ON `projeto`.`DEBIT_AUTOMATICO` (`idOperadoras` ASC);


-- -----------------------------------------------------
-- Table `projeto`.`TIPO_MOVIMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`TIPO_MOVIMENTACAO` (
  `idTipoMovimentacao` INT NOT NULL,
  `Descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`idTipoMovimentacao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`MOVIMENTACAO_BANCARIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`MOVIMENTACAO_BANCARIA` (
  `idMovimentacaoBancaria` INT(11) NOT NULL AUTO_INCREMENT,
  `CodCliente` INT(11) NOT NULL,
  `IdTipoMovimentacao` INT NOT NULL COMMENT '1 - Transferencia / 2 - Saque / 3 - Debito Automatico',
  `TipoCredDeb` INT(11) NOT NULL COMMENT '1 - Credito / 2 - Debito',
  `ValorMovimentacao` DECIMAL(10,2) NOT NULL,
  `SaldoAtual` DECIMAL(10,2) NOT NULL,
  `DataMovimentacao` DATE NOT NULL,
  PRIMARY KEY (`idMovimentacaoBancaria`),
  CONSTRAINT `fk_MovimentacaoBancaria_Cliente1`
    FOREIGN KEY (`CodCliente`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIMENTACAO_BANCARIA_TIPO_MOVIMENTACAO`
    FOREIGN KEY (`IdTipoMovimentacao`)
    REFERENCES `projeto`.`TIPO_MOVIMENTACAO` (`idTipoMovimentacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_MovimentacaoBancaria_Cliente1_idx` ON `projeto`.`MOVIMENTACAO_BANCARIA` (`CodCliente` ASC);

CREATE INDEX `fk_MOVIMENTACAO_BANCARIA_TIPO_MOVIMENTACAO_idx` ON `projeto`.`MOVIMENTACAO_BANCARIA` (`IdTipoMovimentacao` ASC);


-- -----------------------------------------------------
-- Table `projeto`.`SALDO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`SALDO` (
  `idSaldo` INT(11) NOT NULL,
  `Cliente_idCliente` INT(11) NOT NULL,
  `Valor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idSaldo`),
  CONSTRAINT `fk_Saldo_Cliente`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_Saldo_Cliente_idx` ON `projeto`.`SALDO` (`Cliente_idCliente` ASC);


-- -----------------------------------------------------
-- Table `projeto`.`SAQUE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`SAQUE` (
  `idSaque` INT(11) NOT NULL AUTO_INCREMENT,
  `CodCliente` INT(11) NOT NULL,
  `ValorSaque` DECIMAL(10,2) NOT NULL,
  `SaldoAtual` DECIMAL(10,2) NOT NULL,
  `DataSaque` DATE NOT NULL,
  PRIMARY KEY (`idSaque`),
  CONSTRAINT `fk_Saque_Cliente1`
    FOREIGN KEY (`CodCliente`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_Saque_Cliente1_idx` ON `projeto`.`SAQUE` (`CodCliente` ASC);


-- -----------------------------------------------------
-- Table `projeto`.`TRANSFERENCIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`TRANSFERENCIA` (
  `idTransferencia` INT(11) NOT NULL AUTO_INCREMENT,
  `CodClienteCred` INT(11) NOT NULL,
  `CodClienteDeb` INT(11) NOT NULL,
  `ValorTransferencia` DECIMAL(10,2) NOT NULL,
  `SaldoAtual` DECIMAL(10,2) NOT NULL,
  `DataTransferencia` DATE NOT NULL,
  PRIMARY KEY (`idTransferencia`),
  CONSTRAINT `fk_Transferencia_Cliente1`
    FOREIGN KEY (`CodClienteCred`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transferencia_Cliente2`
    FOREIGN KEY (`CodClienteDeb`)
    REFERENCES `projeto`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_Transferencia_Cliente1_idx` ON `projeto`.`TRANSFERENCIA` (`CodClienteCred` ASC);

CREATE INDEX `fk_Transferencia_Cliente2_idx` ON `projeto`.`TRANSFERENCIA` (`CodClienteDeb` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `projeto`.`CLIENTE`
-- -----------------------------------------------------
START TRANSACTION;
USE `projeto`;
INSERT INTO `projeto`.`CLIENTE` (`idCliente`, `Nome`, `Agencia`, `Conta`) VALUES (1, 'JOAO DA SILVA', 123, 123);
INSERT INTO `projeto`.`CLIENTE` (`idCliente`, `Nome`, `Agencia`, `Conta`) VALUES (2, 'CARLOS ALBERTO', 456, 456);
INSERT INTO `projeto`.`CLIENTE` (`idCliente`, `Nome`, `Agencia`, `Conta`) VALUES (3, 'MANOEL DE NOBREGA', 789, 789);
INSERT INTO `projeto`.`CLIENTE` (`idCliente`, `Nome`, `Agencia`, `Conta`) VALUES (4, 'VITOR HUGO PEDROSO', 987, 987);

COMMIT;


-- -----------------------------------------------------
-- Data for table `projeto`.`TIPO_MOVIMENTACAO`
-- -----------------------------------------------------
START TRANSACTION;
USE `projeto`;
INSERT INTO `projeto`.`TIPO_MOVIMENTACAO` (`idTipoMovimentacao`, `Descricao`) VALUES (1, 'TRANSFERENCIA');
INSERT INTO `projeto`.`TIPO_MOVIMENTACAO` (`idTipoMovimentacao`, `Descricao`) VALUES (2, 'SAQUE');
INSERT INTO `projeto`.`TIPO_MOVIMENTACAO` (`idTipoMovimentacao`, `Descricao`) VALUES (3, 'DEBITO AUTOMATICO');

COMMIT;


-- -----------------------------------------------------
-- Data for table `projeto`.`MOVIMENTACAO_BANCARIA`
-- -----------------------------------------------------
START TRANSACTION;
USE `projeto`;
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (1, 1, 2, 2, 100, 1000, '2016-08-13');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (2, 1, 2, 2, 10, 990, '2016-08-13');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (3, 2, 2, 2, 200, 1300, '2016-08-01');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (4, 2, 2, 2, 50, 1250, '2016-08-10');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (5, 3, 2, 2, 30, 1770, '2016-08-05');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (6, 3, 2, 2, 150, 1620, '2016-08-17');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (7, 4, 2, 2, 70, 100, '2016-08-11');
INSERT INTO `projeto`.`MOVIMENTACAO_BANCARIA` (`idMovimentacaoBancaria`, `CodCliente`, `IdTipoMovimentacao`, `TipoCredDeb`, `ValorMovimentacao`, `SaldoAtual`, `DataMovimentacao`) VALUES (8, 4, 2, 2, 80, 20, '2016-08-15');

COMMIT;


-- -----------------------------------------------------
-- Data for table `projeto`.`SAQUE`
-- -----------------------------------------------------
START TRANSACTION;
USE `projeto`;
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (1, 1, 100, 1000, '2016-08-13');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (2, 1, 10, 990, '2016-08-13');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (3, 2, 200, 1300, '2016-08-01');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (4, 2, 50, 1250, '2016-08-10');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (5, 3, 30, 1770, '2016-08-05');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (6, 3, 150, 1620, '2016-08-17');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (7, 4, 70, 100, '2016-08-11');
INSERT INTO `projeto`.`SAQUE` (`idSaque`, `CodCliente`, `ValorSaque`, `SaldoAtual`, `DataSaque`) VALUES (8, 4, 80, 20, '2016-08-15');

COMMIT;


SELECT * FROM MOVIMENTACAO_BANCARIA;
SELECT * FROM SAQUE;
SELECT * FROM CLIENTE;



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
