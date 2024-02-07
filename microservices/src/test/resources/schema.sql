-- Adminer 4.8.1 MySQL 8.1.0 dump

--SET NAMES utf8;
--SET time_zone = `+00:00`;
--SET foreign_key_checks = 0;

--SET NAMES utf8mb4;

--CREATE DATABASE `watchlist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION=`N` */;
--USE `watchlist`;

DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `movie-actors`;
DROP TABLE IF EXISTS `movie`;
DROP TABLE IF EXISTS `regisseur`;
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60), -- CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_name` varchar(60), -- CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
);
--ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `regisseur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `length` varchar(60) NOT NULL,
  `episodes` int NOT NULL,
  `age_restriction` int NOT NULL,
  `regisseur_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `regisseur_id` (`regisseur_id`),
  CONSTRAINT `movie_ibfk_1` FOREIGN KEY (`regisseur_id`) REFERENCES `regisseur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `movie-actors` (
  `movie_id` int NOT NULL,
  `actor_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`actor_id`),
  KEY `FK_ACTOR_idx` (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `movie_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_id` (`movie_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--DROP TABLE IF EXISTS `role`;
--CREATE TABLE `role` (
  --`user_id` int NOT NULL,
  --`role` varchar(60) NOT NULL,
  --KEY `user_id` (`user_id`),
 -- CONSTRAINT `role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--DROP TABLE IF EXISTS `user`;
--CREATE TABLE `user` (
  --`id` int NOT NULL AUTO_INCREMENT,
  --`user_name` varchar(60) NOT NULL,
  --`email` varchar(60) NOT NULL,
 -- `password` varchar(60) NOT NULL,
  --`age` int NOT NULL,
  --`user_id` int DEFAULT NULL,
 -- PRIMARY KEY (`id`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2024-01-30 12:43:52
