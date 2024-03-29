CREATE TABLE `dnd5e_test`.`character` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(10) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `race_id` int(10) unsigned NOT NULL,
  `competence_bonus` int(10) unsigned NOT NULL,
  `speed` int(10) unsigned NOT NULL,
  `initiative_bonus` int(11) NOT NULL,
  `passive_perception` int(10) unsigned NOT NULL,
  `class_armor` int(10) unsigned NOT NULL,
  `alive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `character_UN` (`player_id`),
  KEY `character_FK_3` (`race_id`),
  CONSTRAINT `character_FK` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `character_FK_3` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;