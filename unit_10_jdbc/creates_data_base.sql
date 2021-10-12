SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema unit_10_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema unit_10_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `unit_10_db` DEFAULT CHARACTER SET utf8 ;
USE `unit_10_db` ;

-- -----------------------------------------------------
-- Table `unit_10_db`.`locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `unit_10_db`.`locations` ;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`locations` (
                                                        `id` INT NOT NULL,
                                                        `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_of_location_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `unit_10_db`.`routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `unit_10_db`.`routes` ;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`routes` (
                                                     `id` INT NOT NULL,
                                                     `from_id` INT NOT NULL,
                                                     `to_id` INT NOT NULL,
                                                     `cost` INT UNSIGNED NULL,
                                                     PRIMARY KEY (`id`),
    INDEX `FK_route_from_location_idx` (`from_id` ASC) VISIBLE,
    INDEX `FK_route_to_location_idx` (`to_id` ASC) INVISIBLE,
    INDEX `test` (`from_id` ASC, `to_id` ASC) INVISIBLE,
    CONSTRAINT `FK_route_from_location`
    FOREIGN KEY (`from_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `FK_route_to_location`
    FOREIGN KEY (`to_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `unit_10_db`.`problems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `unit_10_db`.`problems` ;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`problems` (
                                                       `id` INT NOT NULL,
                                                       `from_id` INT NOT NULL,
                                                       `to_id` INT NOT NULL,
                                                       PRIMARY KEY (`id`),
    INDEX `FK_problem_from_location_idx` (`from_id` ASC) VISIBLE,
    INDEX `FK_problem_to_location_idx` (`to_id` ASC) VISIBLE,
    CONSTRAINT `FK_problem_from_location`
    FOREIGN KEY (`from_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `FK_problem_to_location`
    FOREIGN KEY (`to_id`)
    REFERENCES `unit_10_db`.`locations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `unit_10_db`.`solutions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `unit_10_db`.`solutions` ;

CREATE TABLE IF NOT EXISTS `unit_10_db`.`solutions` (
                                                        `problem_id` INT NOT NULL,
                                                        `cost` INT NULL,
                                                        PRIMARY KEY (`problem_id`),
    INDEX `FK_solution_problem_from_to_idx` (`problem_id` ASC) VISIBLE,
    CONSTRAINT `FK_solution_problem`
    FOREIGN KEY (`problem_id`)
    REFERENCES `unit_10_db`.`problems` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO unit_10_db.locations (id, name) VALUES (5, 'Berlin');
INSERT INTO unit_10_db.locations (id, name) VALUES (2, 'London');
INSERT INTO unit_10_db.locations (id, name) VALUES (4, 'Madrid');
INSERT INTO unit_10_db.locations (id, name) VALUES (3, 'Paris');
INSERT INTO unit_10_db.locations (id, name) VALUES (1, 'Vienna');

INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (1, 1, 2, 1000);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (2, 1, 3, 9000);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (3, 2, 4, 8000);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (4, 2, 5, 3000);
INSERT INTO unit_10_db.routes (id, from_id, to_id, cost) VALUES (5, 1, 5, 7000);

INSERT INTO unit_10_db.problems (id, from_id, to_id) VALUES (1, 1, 2);
