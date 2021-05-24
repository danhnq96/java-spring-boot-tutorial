USE `whoami_db`;

-------------------------------------------- BEGIN CREATE TABLE STATEMENTS. --------------------------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(256) NOT NULL,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob DEFAULT NULL,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
);

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob DEFAULT NULL,
  `authentication` blob DEFAULT NULL
);

CREATE TABLE `h01dt_users` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h04dt_groups` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `group_description` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `group_owner` varchar(255) DEFAULT NULL,
  `group_type` varchar(255) DEFAULT NULL,
  `group_url` varchar(255) DEFAULT NULL,
  `is_closed` char(1) DEFAULT NULL,
  `is_lock` char(1) DEFAULT NULL,
  `is_private` char(1) DEFAULT NULL,
  `is_publish` char(1) DEFAULT NULL,
  `parent_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfo711su05cvda6wyvgtm10jn5` (`parent_group`),
  CONSTRAINT `FKfo711su05cvda6wyvgtm10jn5` FOREIGN KEY (`parent_group`) REFERENCES `h04dt_groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h05dt_user_group` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2nq2l1xrxuhqt3di39qljce62` (`group_id`),
  KEY `FK6o1blpgp6ojchmofth1h0809y` (`user_id`),
  CONSTRAINT `FK2nq2l1xrxuhqt3di39qljce62` FOREIGN KEY (`group_id`) REFERENCES `h04dt_groups` (`id`),
  CONSTRAINT `FK6o1blpgp6ojchmofth1h0809y` FOREIGN KEY (`user_id`) REFERENCES `h01dt_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h06dt_question` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `answers_optional` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  `lock_status` varchar(1) DEFAULT NULL,
  `multiple_choice` varchar(1) DEFAULT NULL,
  `private_status` varchar(1) DEFAULT NULL,
  `publish_status` varchar(1) DEFAULT NULL,
  `super_size` varchar(1) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `random_answer` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtlii1urpcdqs8f91xyo5euww` (`owner`),
  CONSTRAINT `FKjtlii1urpcdqs8f91xyo5euww` FOREIGN KEY (`owner`) REFERENCES `h01dt_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h07dt_quiz_test` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  `private_status` varchar(255) DEFAULT NULL,
  `publish_status` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `question_time` int(11) DEFAULT NULL,
  `quiz_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4hmfs9ntnau43kejk26ypfu05` (`group_id`),
  KEY `FKi3hee0fos8h4a6um9pl0lnm0f` (`owner`),
  CONSTRAINT `FK4hmfs9ntnau43kejk26ypfu05` FOREIGN KEY (`group_id`) REFERENCES `h04dt_groups` (`id`),
  CONSTRAINT `FKi3hee0fos8h4a6um9pl0lnm0f` FOREIGN KEY (`owner`) REFERENCES `h01dt_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h08dt_user_answer` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `question_id` varchar(255) DEFAULT NULL,
  `time_finish` int(11) DEFAULT NULL,
  `user_answer` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdx0obt0meuy4ten3bs3nfw8wq` (`question_id`),
  KEY `FK3dbaswueuw7y2p93c3pq5bc00` (`user_id`),
  CONSTRAINT `FK3dbaswueuw7y2p93c3pq5bc00` FOREIGN KEY (`user_id`) REFERENCES `h01dt_users` (`id`),
  CONSTRAINT `FKdx0obt0meuy4ten3bs3nfw8wq` FOREIGN KEY (`question_id`) REFERENCES `h06dt_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h09dt_user_quiz` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `finish_status` varchar(255) DEFAULT NULL,
  `quiz_test` tinyblob,
  `quiz_id` varchar(255) DEFAULT NULL,
  `time_end` date DEFAULT NULL,
  `time_start` date DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi977pfjxpuy4m56x5xvif3cvt` (`user_id`),
  CONSTRAINT `FKi977pfjxpuy4m56x5xvif3cvt` FOREIGN KEY (`user_id`) REFERENCES `h01dt_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h11dt_quiz_question` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `question_id` varchar(255) DEFAULT NULL,
  `quiz_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk13dkrpm3pgs4bbbxwgaayg8w` (`question_id`),
  KEY `FKs3c4nts3q4ufs9aautveamt9r` (`quiz_id`),
  CONSTRAINT `FKk13dkrpm3pgs4bbbxwgaayg8w` FOREIGN KEY (`question_id`) REFERENCES `h06dt_question` (`id`),
  CONSTRAINT `FKs3c4nts3q4ufs9aautveamt9r` FOREIGN KEY (`quiz_id`) REFERENCES `h07dt_quiz_test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h12dt_notifications` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `invite_from` varchar(255) DEFAULT NULL,
  `invite_to` varchar(255) DEFAULT NULL,
  `notify_link` varchar(255) DEFAULT NULL,
  `notify_status` varchar(255) DEFAULT NULL,
  `notify_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK366a9vd7l590wmqh6w4iuelc5` (`invite_to`),
  KEY `FKbepmnf4ux6d17x8aetku6htr9` (`invite_from`),
  CONSTRAINT `FK366a9vd7l590wmqh6w4iuelc5` FOREIGN KEY (`invite_to`) REFERENCES `h01dt_users` (`id`),
  CONSTRAINT `FKbepmnf4ux6d17x8aetku6htr9` FOREIGN KEY (`invite_from`) REFERENCES `h01dt_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `s02st_role` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `s03dt_user_role` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKyyjjp6y3q69sa3ur4vahi9bk` (`role_id`),
  KEY `FKl7ge4s7apcy9ha7vsl7ivvmqd` (`user_id`),
  CONSTRAINT `FKl7ge4s7apcy9ha7vsl7ivvmqd` FOREIGN KEY (`user_id`) REFERENCES `h01dt_users` (`id`),
  CONSTRAINT `FKyyjjp6y3q69sa3ur4vahi9bk` FOREIGN KEY (`role_id`) REFERENCES `s02st_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `h13dt_invites` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delflg` char(1) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `invite_type` varchar(255) NULL,
  `owner` varchar(255) NULL,
  `be_invited` varchar(255) NULL,
  `expire_date` date NULL,
  `message` varchar(255) NULL,
  `detail_id` varchar(255) NULL,
  `condition` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-------------------------------------------- END CREATE TABLE STATEMENTS. --------------------------------------------

-------------------------------------------- BEGIN CREATE ADDITIONAL TABLE STATEMENTS. --------------------------------------------

CREATE TABLE `about_us` (
  `id_about_us` int(11) NOT NULL AUTO_INCREMENT,
  `happy_tour` int(11) DEFAULT NULL,
  `new_tour` int(11) DEFAULT NULL,
  `sold_tour` int(11) DEFAULT NULL,
  `tour_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_about_us`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `article` (
  `id_article` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `description` text,
  `quote` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `image` (
  `id_image` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `id_article` bigint(20) DEFAULT NULL,
  `id_tour` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_image`),
  KEY `FKrlb42sw2curqik3vh380y29xw` (`id_article`),
  KEY `FKceta33w9vvtiam0kax6ap2nsb` (`id_tour`),
  CONSTRAINT `FKceta33w9vvtiam0kax6ap2nsb` FOREIGN KEY (`id_tour`) REFERENCES `tour` (`id_tour`),
  CONSTRAINT `FKrlb42sw2curqik3vh380y29xw` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `invoice` (
  `id_invoice` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) NOT NULL,
  `create_at` date NOT NULL,
  `invoice_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `update_by` varchar(255) NOT NULL,
  `update_at` date NOT NULL,
  `id_invoice_category` int(11) DEFAULT NULL,
  `id_tour_detail` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_invoice`),
  KEY `FK5nsnva6ta77ga8y8iqq4n5rfa` (`id_invoice_category`),
  KEY `FKnkpnj370igo2nyx93bvj5m118` (`id_tour_detail`),
  KEY `FKbub7c17bl8pbefd91y21ovrr3` (`id_user`),
  CONSTRAINT `FK5nsnva6ta77ga8y8iqq4n5rfa` FOREIGN KEY (`id_invoice_category`) REFERENCES `invoice_category` (`id_invoice_category`),
  CONSTRAINT `FKbub7c17bl8pbefd91y21ovrr3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKnkpnj370igo2nyx93bvj5m118` FOREIGN KEY (`id_tour_detail`) REFERENCES `tour_detail` (`id_tour_detail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `invoice_category` (
  `id_invoice_category` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_invoice_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(256) NOT NULL,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rating` (
  `id_rating` bigint(20) NOT NULL AUTO_INCREMENT,
  `rate` int(11) DEFAULT NULL,
  `id_tour` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_rating`),
  KEY `FK9n3ejomuovli7x6oumhiob9qb` (`id_tour`),
  KEY `FK19w7ioj0t787iykijq6yb6gx0` (`id_user`),
  CONSTRAINT `FK19w7ioj0t787iykijq6yb6gx0` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FK9n3ejomuovli7x6oumhiob9qb` FOREIGN KEY (`id_tour`) REFERENCES `tour` (`id_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `role` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tour` (
  `id_tour` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `destination_location` varchar(45) NOT NULL,
  `is_promotion` tinyint(4) NOT NULL,
  `tour_name` varchar(255) NOT NULL,
  `program` text,
  `quote` text,
  `start_location` varchar(45) NOT NULL,
  `total_time` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tour_detail` (
  `id_tour_detail` bigint(20) NOT NULL AUTO_INCREMENT,
  `adult_price` int(11) NOT NULL,
  `begin_date` date NOT NULL,
  `child_price` int(11) NOT NULL,
  `create_by` varchar(255) NOT NULL,
  `create_at` date NOT NULL,
  `end_date` date NOT NULL,
  `tour_detail` varchar(255) DEFAULT NULL,
  `number_of_ticket` int(11) DEFAULT NULL,
  `update_by` varchar(255) NOT NULL,
  `update_at` date NOT NULL,
  `id_tour` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_tour_detail`),
  KEY `FKcwmjgcsy76i0frdo4asp81dv` (`id_tour`),
  CONSTRAINT `FKcwmjgcsy76i0frdo4asp81dv` FOREIGN KEY (`id_tour`) REFERENCES `tour` (`id_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `gender` varchar(15) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(80) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `id_user` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`),
  CONSTRAINT `FK2aam9nt2tv8vcfymi3jo9c314` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-------------------------------------------- END CREATE ADDITIONAL TABLE STATEMENTS. --------------------------------------------

-------------------------------------------- BEGIN INSERT DATA STATEMENTS. --------------------------------------------
INSERT INTO `s02st_role` values('acedccc0-b583-11e9-b623-0242ac140002', NOW(), '0', null, 'USER', 'USER');
INSERT INTO `s02st_role` values('aceec988-b583-11e9-b623-0242ac140002', NOW(), '0', null, 'ADMIN', 'ADMIN');
INSERT INTO `s02st_role` values('acf056ad-b583-11e9-b623-0242ac140002', NOW(), '0', null, 'EDITOR', 'EDITOR');

INSERT INTO `whoami_db`.`h04dt_groups`
(`id`,
`create_date`,
`group_description`,
`group_name`,
`parent_group`)
VALUES
(
'963f3c19-c160-11e9-a2e9-0242ac140002',
'2019-08-18 02:29:16',
'Demo channel description',
'Child channel',
'24a5adb9-e001-4e13-929a-9862de05fe3b'
);


UPDATE `whoami_db`.`h04dt_groups`
SET
`delflg` = '0',
`is_lock` = '0',
`IS_CLOSED` ='0',
`IS_PRIVATE` ='0',
`IS_PUBLISH` ='0'
WHERE `id` = '963f3c19-c160-11e9-a2e9-0242ac140002';


INSERT INTO `whoami_db`.`h05dt_user_group`
(`user_group_id`,
`create_date`,
`delflg`,
`group_id`,
`user_id`)
VALUES
(
'252a27d1-c161-11e9-a2e9-0242ac140002',
'2019-08-17 04:49:01',
'0',
'963f3c19-c160-11e9-a2e9-0242ac140002',
'b6f47b1f-7cf0-4993-b47e-0bce576ed8bb'
);







-------------------------------------------- BEGIN CREATE MEN-WORLD TABLE STATEMENTS. --------------------------------------------
CREATE TABLE `whoami_db`.`MW_TYPE_INFO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TYPE_INFO_NAME` VARCHAR(45) NOT NULL,
  `TYPE_INFO_CODE` VARCHAR(20) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NULL,
  `IS_ACTIVE` BIT(1) NOT NULL DEFAULT 0,
  `CREATE_DATE` DATETIME NULL,
  `CREATE_BY` INT NULL,
  `UPDATE_DATE` DATETIME NULL,
  `UPDATE_BY` INT NULL,
  `DELFLG` BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);
ALTER TABLE `whoami_db`.`MW_TYPE_INFO` AUTO_INCREMENT=1;

CREATE TABLE `whoami_db`.`MW_INFORMATION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(200) NOT NULL,
  `CONTENT` TEXT NOT NULL,
  `ISAPPROVED` BIT(1) NOT NULL,
  `CREATE_DATE` DATETIME NULL,
  `CREATE_BY` INT NULL,
  `UPDATE_DATE` DATETIME NULL,
  `UPDATE_BY` INT NULL,
  `DELFLG` BIT(1) NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `whoami_db`.`MW_INFORMATION` AUTO_INCREMENT=1;


CREATE TABLE `whoami_db`.`MW_INFOMATION_TYPE_INFO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `INFORMATION_ID` INT NOT NULL,
  `TYPE_INFO_ID` INT NOT NULL,
  `IS_ACTIVE` BIT(1) NULL,
  `CREATE_DATE` DATETIME NULL,
  `CREATE_BY` INT NULL,
  `UPDATE_DATE` DATETIME NULL,
  `UPDATE_BY` INT NULL,
  `DELFLG` BIT(1) NOT NULL,
  PRIMARY KEY (`ID`));
ALTER TABLE `whoami_db`.`MW_INFOMATION_TYPE_INFO` AUTO_INCREMENT=1;

ALTER TABLE `whoami_db`.`MW_INFOMATION_TYPE_INFO` 
DROP FOREIGN KEY `MW_INFORMATION_KEY`,
DROP FOREIGN KEY `MW_TYPE_INFO_KEY`;
ALTER TABLE `whoami_db`.`MW_INFOMATION_TYPE_INFO` 
ADD CONSTRAINT `MW_INFORMATION_KEY`
  FOREIGN KEY (`INFORMATION_ID`)
  REFERENCES `whoami_db`.`MW_INFORMATION` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `MW_TYPE_INFO_KEY`
  FOREIGN KEY (`TYPE_INFO_ID`)
  REFERENCES `whoami_db`.`MW_TYPE_INFO` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

  -------------------------------------------- END CREATE MEN-WORLD TABLE STATEMENTS. --------------------------------------------