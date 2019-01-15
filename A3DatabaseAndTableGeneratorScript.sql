drop database if exists A3;
create database A3;
use A3;
create table RockStars(
	id integer primary key auto_increment,
    firstName varchar(50) ,
    lastName varchar(50) ,
    salary double
);
insert into RockStars(id, firstName, lastName, salary) VALUES
	(5, 'Michael', 'Jackson', 75000000.00),
    (10, 'Adam', 'Levine', 35000000.00),
    (12, 'Jennifer', 'Lopez', 20000000.00),
    (13, 'Simon', 'Cowell', 95000000.00);
