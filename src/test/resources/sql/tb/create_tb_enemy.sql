CREATE TABLE `dnd5e_test`.`enemy` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT 'generic_enemy',
  `initiative_bonus` int(10) unsigned NOT NULL DEFAULT 0,
  `damage_taken` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;