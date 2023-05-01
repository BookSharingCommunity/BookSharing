CREATE DATABASE `booksharing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booksharing`;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `booksharing`.`role` (`role_id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `booksharing`.`role` (`role_id`, `name`) VALUES ('2', 'ROLE_ADMIN');

