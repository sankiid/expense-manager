drop database IF EXISTS `expesne_manager`;
create database `expesne_manager`;
use `expesne_manager`;

DROP TABLE IF EXISTS  `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `created_at` date NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
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
  `user_account_id` int(11) NOT NULL,
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
  `user_account_id` int(11) NOT NULL,
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
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('others', now(), 'income');

INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('rent', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('eletricity bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('mobile bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('internet bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('maid', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('water bill', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('car', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('loan emi', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('construction', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('travel', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('shoppings', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('grocery', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('food', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('health care', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('dish tv', now(), 'expense');
INSERT INTO `category` (`name`, `created_at`, `type`) VALUES ('others', now(), 'expense');

drop table bank;
CREATE TABLE `bank` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `bank` (`id`, `name`, `created_at`, `updated_at`)
VALUES
	(1, 'sbi', '2018-05-19', '2018-05-19'),
	(2, 'icici', '2018-05-19', '2018-05-19'),
	(3, 'citi', '2018-05-19', '2018-05-19'),
	(4, 'paytm', '2018-05-19', '2018-05-19'),
	(5, 'airtel wallet', '2018-05-19', '2018-05-19'),
	(6, 'phonepe', '2018-05-19', '2018-05-19'),
	(7, 'kotak bank', '2018-05-19', '2018-05-19'),
	(8, 'wallet', '2018-05-19', '2018-05-19'),
	(9, 'kotak', '2018-05-19', '2018-05-19'),
    (10, 'HDFC', '2018-05-19', '2018-05-19'),
    (11, 'Yes bank', '2018-05-19', '2018-05-19'),
    (12, 'others', '2018-05-19', '2018-05-19');

drop table user_account_info;
CREATE TABLE `user_account_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `account_number` varchar(30) default null,
  `amount` float NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT bank_acct_idx UNIQUE (bank_id,account_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;