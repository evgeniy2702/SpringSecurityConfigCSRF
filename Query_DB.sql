CREATE SCHEMA `books` ;

USE books;

CREATE TABLE `books`.`books` (
  `id` BIGINT(100) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `author` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `year` INT NOT NULL DEFAULT 0,
  `stile` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `num_pages` INT NOT NULL DEFAULT 0,
  `description` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `buyer_id` BIGINT(100) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));



CREATE TABLE `books`.`buyers` (
  `buyer_id` BIGINT(100) NOT NULL  AUTO_INCREMENT,
  `nameBuyer` VARCHAR(45) NOT NULL DEFAULT 'Null',
  PRIMARY KEY (`buyer_id`));

ALTER TABLE `books`.`buyers`
ADD CONSTRAINT `bookKey`
  FOREIGN KEY (`buyer_id`)
  REFERENCES `books`.`books` (`buyer_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

 ALTER TABLE `books`.`books`
DROP FOREIGN KEY `buyerKey`;
ALTER TABLE `books`.`books`
ADD CONSTRAINT `buyerKey`
  FOREIGN KEY (`buyer_id`)
  REFERENCES `books`.`buyers` (`buyer_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


  CREATE TABLE `books`.`owners` (
  `id_owner` BIGINT(100) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT 'Null',
  `email` VARCHAR (45) NOT NULL,
  `password` VARCHAR (100) NOT NULL,
  PRIMARY KEY (`id_owner`));

  CREATE TABLE `books`.`owner_books` (
  `id`INT NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT(100) NOT NULL,
  `owner_id` BIGINT(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `owner_fk_idx` (`owner_id` ASC),
  INDEX `book_fk_idx` (`book_id` ASC),
  CONSTRAINT `owner_fk`
    FOREIGN KEY (`owner_id`)
    REFERENCES `books`.`owners` (`id_owner`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `book_fk`
    FOREIGN KEY (`book_id`)
    REFERENCES `books`.`books` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

    CREATE TABLE `books`.`role` (
  `id` BIGINT(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `books`.`owners` (`name`) VALUES ('Owner_1');
INSERT INTO `books`.`owners` (`name`) VALUES ('Owner_2');
INSERT INTO `books`.`owners` (`name`) VALUES ('Owner_3');

INSERT INTO `books`.`buyers` (`nameBuyer`) VALUES ('Buyer_1');
INSERT INTO `books`.`buyers` (`nameBuyer`) VALUES ('Buyer_2');
INSERT INTO `books`.`buyers` (`nameBuyer`) VALUES ('Buyer_3');

INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('1', '1');
INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('1', '2');
INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('1', '3');
INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('2', '2');
INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('2', '3');
INSERT INTO `books`.`owner_books` (`book_id`, `owner_id`) VALUES ('3', '3');

INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`, `buyer_id`) VALUES ('Name_1', 'Author_1', '1989', 'stile_1', '100', 'desc_1', '1');
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`, `buyer_id`) VALUES ('Name_2', 'Author_2', '1990', 'stile_2', '200', 'desc_2', '2');
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`, `buyer_id`) VALUES ('Name_3', 'Author_3', '1991', 'stile_3', '300', 'desc_3', '3');
INSERT INTO `books`.`books` (`name`, `author`, `year`, `stile`, `num_pages`, `description`, `buyer_id`) VALUES ('Name_9', 'Author_9', '2021', 'stile_9', '400', 'desc_9', '2');

SELECT * FROM books;

SELECT * FROM buyers;

SELECT * FROM owners;

DELETE FROM books WHERE id = 12;

UPDATE `books`.`books` SET `name`='Name_55' WHERE `id`='5';
UPDATE `books`.`books` SET `name`='Name_55', `author`='Author_55', `year`='1993', `stile`='stile_55', `num_pages`='3000', `description`='desc_55' WHERE `id`='5';