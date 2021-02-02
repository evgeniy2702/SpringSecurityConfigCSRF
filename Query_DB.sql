CREATE SCHEMA `books` ;

USE books;

CREATE TABLE `books`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `author` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `year` INT NOT NULL DEFAULT 0,
  `stile` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `num_pages` INT NOT NULL DEFAULT 0,
  `description` VARCHAR(45) NOT NULL DEFAULT 'Null',
  PRIMARY KEY (`id`));
  
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`) VALUES ('Name_1', 'Author_1', '1989', 'stile_1', '100', 'desc_1');
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`) VALUES ('Name_2', 'Author_2', '1990', 'stile_2', '200', 'desc_2');
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`) VALUES ('Name_3', 'Author_3', '1991', 'stile_3', '300', 'desc_3');

SELECT * FROM books;