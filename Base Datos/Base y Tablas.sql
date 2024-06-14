CREATE DATABASE `angular_grupo8` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `progress` char(25) DEFAULT 'PENDING',
  `priority` char(15) DEFAULT 'LOW',
  `photo_url` varchar(250) DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `is_ready` char(1) DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task_user` (`user_id`),
  CONSTRAINT `fk_task_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_active` char(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsers`()
BEGIN
    SELECT id, user_name, email, is_active 
    FROM users 
    WHERE is_active = 1;
END$$

DELIMITER ;
