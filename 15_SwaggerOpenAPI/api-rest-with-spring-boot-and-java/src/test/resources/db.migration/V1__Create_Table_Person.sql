CREATE TABLE IF NOT EXISTS `person` (
      `id` bigint NOT NULL,
      `first_name` varchar(255) NOT NULL,
      `last_name` varchar(255) NOT NULL,
      `address` varchar(255) NOT NULL,
      `gender` varchar(255) NOT NULL,
      PRIMARY KEY (`id`)
);