CREATE TABLE  `UserDetails` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL DEFAULT '',
  `lastName` VARCHAR(45) DEFAULT NULL,
  `userName` VARCHAR(45) NOT NULL DEFAULT '',
  `password` VARCHAR(45) NOT NULL DEFAULT '',
  `email` VARCHAR(45) NOT NULL DEFAULT '',
  `gender` ENUM('MALE','FEMALE') NOT NULL DEFAULT 'MALE',
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `roleId` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `RoleMaster` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Project (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  `description` VARCHAR(512) NOT NULL DEFAULT '',
  `mgr` INTEGER UNSIGNED NOT NULL DEFAULT NULL,
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;


CREATE TABLE  Issue (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `issueType` enum('EPIC','NEW_FEATURE','TASK','BUG') NOT NULL DEFAULT 'NEW_FEATURE',
  `assignee` int(10) unsigned DEFAULT NULL,
  `reporter` int(10) unsigned NOT NULL DEFAULT '0',
  `projectId` int(10) unsigned NOT NULL DEFAULT '0',
  `description` varchar(512) DEFAULT NULL,
  `summary` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;


CREATE TABLE  IssueComment (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `issueId` int(10) unsigned NOT NULL DEFAULT '0',
  `userId` int(10) unsigned NOT NULL DEFAULT '0',
  `message` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;