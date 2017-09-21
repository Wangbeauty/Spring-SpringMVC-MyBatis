/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.46 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `usertable` (
	`id` double ,
	`username` varchar (30),
	`password` varchar (30),
	`addressid` double ,
	`identityId` double 
); 
insert into `usertable` (`id`, `username`, `password`, `addressid`, `identityId`) values('1','王美丽','123456','1','1');
