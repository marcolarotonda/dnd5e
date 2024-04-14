CREATE TABLE `dnd5e_test`.`enemy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enemy_type_id` int(10) unsigned NOT NULL,
  `damage_taken` int(10) unsigned NOT NULL DEFAULT 0,
  `alive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `enemy_enemy_type_FK` (`enemy_type_id`),
  CONSTRAINT `enemy_enemy_type_FK` FOREIGN KEY (`enemy_type_id`) REFERENCES `enemy_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;