-- Adminer 4.8.1 MySQL 8.1.0 dump

INSERT INTO `actor` (`id`, `first_name`, `last_name`) VALUES
(1,	'Ewan',	'McGregor'),
(2,	'Liam',	'Neeson'),
(3,	'Natalie',	'Portman'),
(4,	'Hayden',	'Christensen'),
(5,	'Jimmy',	'Smits'),
(6,	'Anthony',	'Daniels'),
(7,	'Alden',	'Ehrenreich'),
(8,	'Woody',	'Harrelson'),
(9,	'Diego',	'Luna'),
(10,	'Ben',	'Mendelsohn'),
(11,	'Mark',	'Hamill'),
(12,	'Harrison',	'Ford'),
(13,	'Carrie',	'Fisher'),
(14,	'Kenny',	'Baker'),
(15,	'David',	'Prowse'),
(16,	'Frank',	'Oz'),
(17,	'Daisy',	'Ridley'),
(18,	'Adam',	'Driver');

INSERT INTO `regisseur` (`id`, `first_name`, `last_name`) VALUES
(1,	'George',	'Lucas'),
(2,	'Irvin',	'Kershner'),
(3,	'Richard',	'Marquand'),
(4,	'J.J.',	'Abrams'),
(5,	'Rian',	'Johnson');

INSERT INTO `movie` (`id`, `title`, `length`, `episodes`, `age_restriction`, `regisseur_id`) VALUES
(1,	'Star Wars: Krieg der Sterne',	'03:00:00',	4,	12,	1),
(2,	'Star Wars: Das Imperium schlägt zurück',	'03:00:00',	5,	12,	2),
(3,	'Star Wars: Rückkehr der Jedi-Ritter',	'03:00:00',	6,	12,	3),
(4,	'Star Wars: Die dunkle Bedrohung',	'03:00:00',	1,	12,	1),
(5,	'Star Wars: Angriff der Klonkrieger',	'03:00:00',	2,	12,	1),
(6,	'Star Wars: Die Rache der Sith',	'03:00:00',	3,	12,	1),
(7,	'Star Wars: Das Erwachen der Macht',	'03:00:00',	7,	12,	4),
(8,	'Star Wars: Die letzten Jedi',	'03:00:00',	8,	12,	5),
(9,	'Star Wars: Der Aufstieg Skywalkers',	'03:00:00',	9,	16,	4);

INSERT INTO `movie-actors` (`movie_id`, `actor_id`) VALUES
(4,	1),
(4,	2),
(5,	3),
(5,	4),
(6,	5),
(6,	6),
(1,	7),
(1,	8),
(2,	9),
(2,	10),
(3,	11),
(3,	12),
(7,	13),
(7,	14),
(8,	15),
(8,	16),
(9,	17),
(9,	18);



INSERT INTO `review` (`id`, `comment`, `movie_id`) VALUES
(1,	'Good movie!',	1),
(2,	'Very cool scenes',	2),
(3,	'Well playing actors',	3),
(4,	'Nice one!',	4),
(5,	'Cool and amazing',	5),
(6,	'Best of all',	6),
(7,	'Not so good',	7),
(8,	'Not like the others',	8),
(9,	'Good but the others were better',	9);


-- 2024-02-07 11:51:42