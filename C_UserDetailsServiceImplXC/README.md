

Set up database as given below

```mysql
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
```

Insert defaults
```mysql
INSERT INTO `rolemaster` (`id`,`name`) VALUES 
 (1,'ROLE_ADMIN'),
 (2,'ROLE_USER');
 
 INSERT INTO `userdetails` (`id`,`firstName`,`lastName`,`userName`,`password`,`email`,`gender`,`enabled`,`roleId`) VALUES 
 (1,'Praveen','Lalasangi','praveen','$2a$10$5/xhPdMowlm/auWN96l0D.E6UUXeyBqJalw.NC4bPk1RhsGDZkE9K','nlpraveennl@gmail.com','MALE',1,'1'),
 (2,'Vedantha','Lalasangi','vedanta','$2a$10$Zz4q0u6Xr3/O9gZXcoryzeuU9zBd2I7pivvqmzSWXZ5fY5PzxI7RK','vedanta@gmail.com','MALE',1,'2');
```
<hr>
**Login credentials**<br>
<hr>

*Admin*<br>
username:praveen<br>
password:praveen@123#<br>

*User*<br>
username:vedanta<br>
password:vedanta@123#<br>
<hr>
