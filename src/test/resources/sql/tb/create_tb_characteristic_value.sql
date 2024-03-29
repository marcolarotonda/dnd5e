CREATE TABLE `dnd5e_test`.`characteristic_value` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `character_id` int(10) unsigned NOT NULL,
  `characteristic_id` int(10) unsigned NOT NULL,
  `permanent_value` int(10) unsigned NOT NULL,
  `temporary_value` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `characteristic_value_unique` (`character_id`,`characteristic_id`),
  KEY `characteristic_value_characteristic_FK` (`characteristic_id`),
  CONSTRAINT `characteristic_value_character_FK` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  CONSTRAINT `characteristic_value_characteristic_FK` FOREIGN KEY (`characteristic_id`) REFERENCES `characteristic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;