CREATE TABLE `dnd5e_test`.`initiative_item` (
	id INTEGER UNSIGNED auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	tag varchar(100) NOT NULL,
	initiative_bonus INTEGER NOT NULL,
	damage_taken INTEGER NOT NULL,
	CONSTRAINT initiative_item_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;