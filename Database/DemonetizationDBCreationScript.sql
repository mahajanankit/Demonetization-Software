-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Demonetization
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Demonetization
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Demonetization` DEFAULT CHARACTER SET utf8 ;
USE `Demonetization` ;

-- -----------------------------------------------------
-- Table `Demonetization`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Role` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Role` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Person` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Person` (
  `First_Name` VARCHAR(30) NOT NULL,
  `Last_Name` VARCHAR(30) NULL,
  `SSN` INT(9) NOT NULL,
  `Phone` INT(10) NULL,
  `Email` VARCHAR(70) NULL,
  `Role_ID` INT NOT NULL,
  UNIQUE INDEX `SSN_UNIQUE` (`SSN` ASC),
  PRIMARY KEY (`SSN`),
  INDEX `fk_Person_Role1_idx` (`Role_ID` ASC),
  CONSTRAINT `fk_Person_Role1`
    FOREIGN KEY (`Role_ID`)
    REFERENCES `Demonetization`.`Role` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Login_Credentials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Login_Credentials` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Login_Credentials` (
  `Password` VARCHAR(45) NOT NULL,
  `Person_SSN` INT(9) NOT NULL,
  PRIMARY KEY (`Person_SSN`),
  CONSTRAINT `fk_Login_Credentials_Person`
    FOREIGN KEY (`Person_SSN`)
    REFERENCES `Demonetization`.`Person` (`SSN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Bank` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Bank` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Balance` BIGINT NOT NULL,
  `Bank_Type_ID` INT NOT NULL,
  PRIMARY KEY (`ID`, `Bank_Type_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Bank_Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Bank_Employee` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Bank_Employee` (
  `Bank_ID` INT NOT NULL,
  `Person_SSN` INT(9) NOT NULL,
  `ID` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  INDEX `fk_Employee_Bank1_idx` (`Bank_ID` ASC),
  INDEX `fk_Employee_Person1_idx` (`Person_SSN` ASC),
  UNIQUE INDEX `Person_SSN_UNIQUE` (`Person_SSN` ASC),
  CONSTRAINT `fk_Employee_Bank1`
    FOREIGN KEY (`Bank_ID`)
    REFERENCES `Demonetization`.`Bank` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Person1`
    FOREIGN KEY (`Person_SSN`)
    REFERENCES `Demonetization`.`Person` (`SSN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Timeslot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Timeslot` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Timeslot` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Period` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Period_UNIQUE` (`Period` ASC),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Customer` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Customer` (
  `Person_SSN` INT(9) NOT NULL,
  `ID` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Person_SSN_UNIQUE` (`Person_SSN` ASC),
  CONSTRAINT `fk_table2_Person1`
    FOREIGN KEY (`Person_SSN`)
    REFERENCES `Demonetization`.`Person` (`SSN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Status` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Status` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Transaction_Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Transaction_Type` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Transaction_Type` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Type_UNIQUE` (`Type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Reservation` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Reservation` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Customer_ID` INT NOT NULL,
  `Employee_ID` INT NULL,
  `Timeslot_ID` INT NOT NULL,
  `Date` DATE NOT NULL,
  `Status_ID` INT NOT NULL,
  `Bank_ID` INT NOT NULL,
  `Transaction_Type_ID` INT NOT NULL,
  INDEX `fk_table1_Customer1_idx` (`Customer_ID` ASC),
  INDEX `fk_table1_Employee1_idx` (`Employee_ID` ASC),
  INDEX `fk_table1_Timeslot1_idx` (`Timeslot_ID` ASC),
  PRIMARY KEY (`ID`),
  INDEX `fk_Reservation_Status1_idx` (`Status_ID` ASC),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_Reservation_Bank1_idx` (`Bank_ID` ASC),
  INDEX `fk_Reservation_Transaction_Type1_idx` (`Transaction_Type_ID` ASC),
  CONSTRAINT `fk_table1_Customer1`
    FOREIGN KEY (`Customer_ID`)
    REFERENCES `Demonetization`.`Customer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Employee1`
    FOREIGN KEY (`Employee_ID`)
    REFERENCES `Demonetization`.`Bank_Employee` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Timeslot1`
    FOREIGN KEY (`Timeslot_ID`)
    REFERENCES `Demonetization`.`Timeslot` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Status1`
    FOREIGN KEY (`Status_ID`)
    REFERENCES `Demonetization`.`Status` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Bank1`
    FOREIGN KEY (`Bank_ID`)
    REFERENCES `Demonetization`.`Bank` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Transaction_Type1`
    FOREIGN KEY (`Transaction_Type_ID`)
    REFERENCES `Demonetization`.`Transaction_Type` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Notes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Notes` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Notes` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Denominations` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Denominations_UNIQUE` (`Denominations` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Reservation_Details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Reservation_Details` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Reservation_Details` (
  `Reservation_ID` INT NOT NULL,
  `Notes_ID` INT NOT NULL,
  `Number` INT NOT NULL,
  INDEX `fk_table1_Reservation1_idx` (`Reservation_ID` ASC),
  INDEX `fk_table1_Notes1_idx` (`Notes_ID` ASC),
  CONSTRAINT `fk_table1_Reservation1`
    FOREIGN KEY (`Reservation_ID`)
    REFERENCES `Demonetization`.`Reservation` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Notes1`
    FOREIGN KEY (`Notes_ID`)
    REFERENCES `Demonetization`.`Notes` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Federal_Bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Federal_Bank` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Federal_Bank` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Federal_Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Federal_Employee` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Federal_Employee` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Person_SSN` INT(9) NOT NULL,
  `Federal_Bank_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Federal_Employee_Person1_idx` (`Person_SSN` ASC),
  INDEX `fk_Federal_Employee_Federal_Bank1_idx` (`Federal_Bank_ID` ASC),
  CONSTRAINT `fk_Federal_Employee_Person1`
    FOREIGN KEY (`Person_SSN`)
    REFERENCES `Demonetization`.`Person` (`SSN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Federal_Employee_Federal_Bank1`
    FOREIGN KEY (`Federal_Bank_ID`)
    REFERENCES `Demonetization`.`Federal_Bank` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Bank_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Bank_Order` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Bank_Order` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Bank_ID` INT NOT NULL,
  `Bank_Bank_Type_ID` INT NOT NULL,
  `Federal_Bank_ID` INT NOT NULL,
  `Status_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Bank_Order_Bank1_idx` (`Bank_ID` ASC, `Bank_Bank_Type_ID` ASC),
  INDEX `fk_Bank_Order_Federal_Bank1_idx` (`Federal_Bank_ID` ASC),
  INDEX `fk_Bank_Order_Status1_idx` (`Status_ID` ASC),
  CONSTRAINT `fk_Bank_Order_Bank1`
    FOREIGN KEY (`Bank_ID` , `Bank_Bank_Type_ID`)
    REFERENCES `Demonetization`.`Bank` (`ID` , `Bank_Type_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bank_Order_Federal_Bank1`
    FOREIGN KEY (`Federal_Bank_ID`)
    REFERENCES `Demonetization`.`Federal_Bank` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bank_Order_Status1`
    FOREIGN KEY (`Status_ID`)
    REFERENCES `Demonetization`.`Status` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Demonetization`.`Bank_Order_Details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Demonetization`.`Bank_Order_Details` ;

CREATE TABLE IF NOT EXISTS `Demonetization`.`Bank_Order_Details` (
  `Bank_Order_ID` INT NOT NULL,
  `Notes_ID` INT NOT NULL,
  `Quantity` VARCHAR(45) NOT NULL,
  INDEX `fk_Bank_Order_Details_Bank_Order1_idx` (`Bank_Order_ID` ASC),
  INDEX `fk_Bank_Order_Details_Notes1_idx` (`Notes_ID` ASC),
  CONSTRAINT `fk_Bank_Order_Details_Bank_Order1`
    FOREIGN KEY (`Bank_Order_ID`)
    REFERENCES `Demonetization`.`Bank_Order` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bank_Order_Details_Notes1`
    FOREIGN KEY (`Notes_ID`)
    REFERENCES `Demonetization`.`Notes` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Demonetization`.`Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `Demonetization`;
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Federal Manager');
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Bank Manager');
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Logistic Manager');
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Federal Employee');
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Bank Employee');
INSERT INTO `Demonetization`.`Role` (`ID`, `Name`) VALUES (DEFAULT, 'Logistic Employee');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Demonetization`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `Demonetization`;
INSERT INTO `Demonetization`.`Status` (`ID`, `Name`) VALUES (DEFAULT, 'Placed');
INSERT INTO `Demonetization`.`Status` (`ID`, `Name`) VALUES (DEFAULT, 'Assigned');
INSERT INTO `Demonetization`.`Status` (`ID`, `Name`) VALUES (DEFAULT, 'Completed');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Demonetization`.`Transaction_Type`
-- -----------------------------------------------------
START TRANSACTION;
USE `Demonetization`;
INSERT INTO `Demonetization`.`Transaction_Type` (`ID`, `Type`) VALUES (DEFAULT, 'Deposit');
INSERT INTO `Demonetization`.`Transaction_Type` (`ID`, `Type`) VALUES (DEFAULT, 'Exchange');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Demonetization`.`Notes`
-- -----------------------------------------------------
START TRANSACTION;
USE `Demonetization`;
INSERT INTO `Demonetization`.`Notes` (`ID`, `Denominations`) VALUES (DEFAULT, 100);
INSERT INTO `Demonetization`.`Notes` (`ID`, `Denominations`) VALUES (DEFAULT, 500);

COMMIT;

