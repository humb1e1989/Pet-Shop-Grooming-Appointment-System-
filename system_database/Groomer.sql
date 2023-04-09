CREATE TABLE `Appointment`  (
  `id` int NOT NULL,
  `time` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `finish_time` datetime NOT NULL,
  `cancel_time` datetime NULL,
  `groomer_id` int NOT NULL,
  `user_id` int NOT NULL,
  `service_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Groomer`  (
  `id` int NOT NULL,
  `name` varchar(255) NULL,
  `gender` tinyint NULL,
  `image_url` varchar(255) NULL,
  `rank` int NULL,
  `phone_num` int NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Pet`  (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `size` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image_url` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `User`  (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_num` int NULL,
  `e-mail` varchar(255) NULL,
  `type` tinyint NOT NULL,
  `image_url` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `Appointment` ADD CONSTRAINT `app_user_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);
ALTER TABLE `Appointment` ADD CONSTRAINT `app_groomer_foreign_key` FOREIGN KEY (`groomer_id`) REFERENCES `Groomer` (`id`);
ALTER TABLE `Pet` ADD CONSTRAINT `pet_user_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

