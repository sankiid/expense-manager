drop database IF EXISTS `expesne_manager`;
create database `expesne_manager`;
use `expesne_manager`;

DROP TABLE IF EXISTS  `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `created_at` date NOT NULL,
  `password` varchar(100) NOT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`),
  UNIQUE `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `roles`;
CREATE TABLE `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11),
  `role_id` int(11),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `roles` (`name`, `created_at`) VALUES ('USER', now());
INSERT INTO `roles` (`name`, `created_at`) VALUES ('ADMIN', now());


DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `created_at` date NOT NULL,
  `category_id` int(11) NOT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  -- FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
  KEY `created_at_idx` (`user_id`, `created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `created_at` date NOT NULL,
  `category_id` int(11) NOT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  -- FOREIGN KEY exp_user_idx (`user_id`) REFERENCES `users`(`id`),
  KEY `created_at_idx2` (`user_id`, `created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `category`;
CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `created_at` date DEFAULT NULL,
  `type` ENUM('income', 'expense', 'investment') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('salary', now(), 'income');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('gift', now(), 'income');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('investment', now(), 'income');

INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('rent', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('eletricity bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('mobile bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('internet bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('maid', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('water bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('car fuel', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('car maintainace', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('travel', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('shoppings', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('grocery', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('food', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('health care', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('dish tv', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('others', now(), 'expense');
