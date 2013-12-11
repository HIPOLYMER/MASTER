drop database p2w;
create database p2w;
use p2w;

CREATE TABLE basicoption1(
	ID INT(20) NOT NULL AUTO_INCREMENT,
	year INT(4),
	serial VARCHAR(10),
	PRIMARY KEY(ID,year,serial)
);
CREATE TABLE basicoption2(
	ID INT(20) NOT NULL AUTO_INCREMENT,
	type VARCHAR(10),
	subject VARCHAR(20),
	PRIMARY KEY(ID,type,subject)
);
CREATE TABLE classification(
	ID INT(20) NOT NULL AUTO_INCREMENT,
	subject VARCHAR(20),
	large VARCHAR(200),
	medium VARCHAR(200),
	small VARCHAR(200),
	PRIMARY KEY(ID,subject,large,medium,small)
);
CREATE TABLE problemheader(
	problemID INT(20) NOT NULL,
	year INT(4),
	serial VARCHAR(10),
	type VARCHAR(10),
	subject VARCHAR(20),
	classify VARCHAR(20),
	level VARCHAR(10),
	large VARCHAR(200),
	medium VARCHAR(200),
	small VARCHAR(200),
	pNum INT(5),
	PRIMARY KEY(year,serial,type,subject,pNum)
);

CREATE TABLE problembody(
	problem VARCHAR(3000) NOT NULL,
	addition VARCHAR (3000),
	choice1 VARCHAR(1000),
	choice2 VARCHAR(1000),
	choice3 VARCHAR(1000),
	choice4 VARCHAR(1000),
	keyword VARCHAR(500),
	solution VARCHAR(2000),
	answer INT(10),
	problemID INT(200) NOT NULL,
	PRIMARY KEY(problemID)
);

