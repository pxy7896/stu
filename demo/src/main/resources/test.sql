create database demo;

use demo;

create table student(
	id int(11) not null auto_increment,
	name varchar(255),
	primary key(id)
);
create table course(
	id int(11) not null auto_increment,
	name varchar(255),
	weekday int(3) not null,
	start int(3) not null,
	primary key(id)
);
create table enroll(
	id int(11) not null auto_increment,
	sid int(11),
	cid int(11),
	primary key(id)
);