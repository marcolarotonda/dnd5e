CREATE TABLE `dnd5e_test`.`race` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `speed` int(10) unsigned NOT NULL,
  `size` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `race_UN` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;