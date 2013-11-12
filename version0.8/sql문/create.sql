/*	mysql -u root -p p2w
	\. create.sql
*/
drop database p2w;
create database p2w;
use p2w

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
	large VARCHAR(50),
	medium VARCHAR(50),
	small VARCHAR(50),
	PRIMARY KEY(ID,subject,large,medium,small)
);
CREATE TABLE problemheader(
	problemID INT(20) NOT NULL AUTO_INCREMENT UNIQUE,
	year INT(4),
	serial VARCHAR(10),
	type VARCHAR(10),
	subject VARCHAR(20),
	classify VARCHAR(20),
	level VARCHAR(10),
	large VARCHAR(50),
	medium VARCHAR(50),
	small VARCHAR(50),
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
	problemID INT(200) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(problemID)
);


/*
<ATTRIBUTES>
기출년도 year INT(4)
회차 serial VARCHAR(10)
유형 type VARCHAR(10)
과목 subject VARCHAR(20)
구분 classify VARCHAR(20)
난이도 level VARCHAR(10)
주요항목 large VARCHAR(50)
세부항목 medium VARCHAR(50)
세세항목 small VARCHAR(50)
번호 pNum INT(5)
문제-연결인덱스 problemID INT(20) NOT NULL AUTO_INCREMENT,
문제 problem VARCHAR(3000) NOT NULL
추가사항 addition VARCHAR (3000)
보기	choice1 VARCHAR(1000)
	choice2 VARCHAR(1000)
	choice3 VARCHAR(1000)
	choice4 VARCHAR(1000)
풀이키워드 keyword VARCHAR(500)
풀이 solution VARCHAR(2000)
답 answer INT(10)

<RELATIONS>
basicoption1(year,serial) +ID
basicoption2(type,subject,classify,level) +ID
classification(subject,large,medium,small) +ID
problemheader(year,serial,type,subject,classify,level,large,medium,small,pNum) +problemID
problembody(problem,addition,choice1,choice2,choice3,choice4,keyword,solution) +problemID

*/