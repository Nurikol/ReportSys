CREATE DATABASE `reportsys`;

USE `reportsys`;

/*Table structure for table `groups` */

CREATE TABLE `groups` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `leader` varchar(20) DEFAULT NULL,
  `member` varchar(255) DEFAULT NULL,
  `ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `groups` */

insert into `groups` (`id`,`name`,`leader`,`member`,`ids`) values
  ('1','测试小组1','测试学生','测试老师','2,3'),
  ('2','测试小组2','测试老师','测试学生','3,2');

/*Table structure for table `notice` */

CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createTime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

insert  into `notice`(`id`,`content`,`createTime`) values ('1','2017-03-12 提交周报表的截止时间是： 23:55:33','2017-03-06 08:29:00'),('2','2017-03-03 提交日报表的截止时间是： 23:55:22','2017-03-03 08:15:00');


/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `wechatID` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account`,`username`,`password`,`wechatID`) values 
  (1,'111111','admin','e10adc3949ba59abbe56e057f20f883e',''),
  (2,'222','测试老师','e10adc3949ba59abbe56e057f20f883e',''),
  (3,'333','测试学生','e10adc3949ba59abbe56e057f20f883e','');


/*Table structure for table `report` */

CREATE TABLE `report` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `userId` int(5) DEFAULT NULL,
  `downloadUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

CREATE TABLE `role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'管理员'),(2,'老师'),(3,'学生'),(4,'项目组长');


/*Table structure for table `user_role` */

CREATE TABLE `user_role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `userId` int(5) DEFAULT NULL,
  `roleId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values (1,1,1),(2,2,2),(3,3,3),(4,2,4),(5,3,4);

/*Table structure for table `project` */

CREATE TABLE `project`(
  `projectID` int(5) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(100) NOT NULL,
  `GroupID` int(5) NOT NULL,
  PRIMARY KEY(`projectID`),
  CONSTRAINT `GroupID_fk` FOREIGN KEY(`GroupID`) REFERENCES `groups`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert into `project`(`projectID`,`projectName`,`GroupID`) values (1,'嘻嘻',1),(2,'哈哈',2),(3,'嘿嘿',1);

/*Table structure for table `weeklyReport` */

CREATE TABLE `weeklyReport`(
  `weekID` int(3) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `weekDate` varchar(255) NOT NULL,
  `userID` int(5) NOT NULL,
  `projectID` int(5) NOT NULL,
  `problem` varchar(255) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `solution` varchar(255) NOT NULL,
  `nextPlan` varchar(255) NOT NULL,
  `futureSolution` varchar(255) NOT NULL,
  CONSTRAINT `userID_fk1` FOREIGN KEY(`userID`) REFERENCES `user`(`id`) ,
  CONSTRAINT `projectID_fk1` FOREIGN KEY(`projectID`) REFERENCES `project`(`projectID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dailyReport` */

CREATE TABLE `dailyReport`(
  `DayID` int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `userID` int(5) NOT NULL,
  `projectID` int(5) NOT NULL,
  `finishedContent` varchar(255) NOT NULL,
  `arrangement` varchar(255) NOT NULL,
  `Date` date NOT NULL,
  CONSTRAINT `userID_fk2` FOREIGN KEY(`userID`) REFERENCES `user`(`id`),
  CONSTRAINT `projectID_fk2` FOREIGN KEY(`projectID`) REFERENCES `project`(`projectID`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `meeting` */

CREATE TABLE `meeting`(
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `Theme` varchar(100) NOT NULL,
  `meetingContent` text NOT NULL,
  `meetingMember` varchar(255),
  `recorder` varchar(60),
  `meetingDate` date NOT NULL,
  `otherMember` varchar(255) NOT NULL,
  `createTime` date NOT NULL,
  PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
