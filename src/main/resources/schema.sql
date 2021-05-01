CREATE TABLE IF NOT EXISTS `chess_game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `running` TINYINT NOT NULL,
  `pieces` VARCHAR(192) NOT NULL,
  `next_turn` VARCHAR(15) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );

CREATE TABLE IF NOT EXISTS `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

-- CREATE TABLE `chess_game` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `running` tinyint(4) NOT NULL,
--   `pieces` varchar(192) NOT NULL,
--   `next_turn` varchar(15) NOT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `id_UNIQUE` (`id`));
--
-- CREATE TABLE `room` (
--   `id` bigint NOT NULL,
--   `title` varchar(45) NOT NULL,
--   `password` varchar(100) NOT NULL,
--   `chess_game_id` bigint(20) NOT NULL,
--   `user_id` bigint(20) NOT NULL,
--   PRIMARY KEY (`id`));
--
-- CREATE TABLE `user` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `name` varchar(45) NOT NULL,
--   `password` varchar(45) NOT NULL,
--   PRIMARY KEY (`id`));
--
-- alter table room
--     add constraint fk_room_to_chess_game
--         foreign key (chess_game_id) references chess_game (id);
