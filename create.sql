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
	problemID INT(20) NOT NULL AUTO_INCREMENT,
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
	PRIMARY KEY(problemID,year,serial,type,subject,large,medium,small)
);

CREATE TABLE problembody(
	problem VARCHAR(2000) NOT NULL,
	addition VARCHAR (1000),
	choice1 VARCHAR(500),
	choice2 VARCHAR(500),
	choice3 VARCHAR(500),
	choice4 VARCHAR(500),
	keyword VARCHAR(300),
	solution VARCHAR(2000),
	problemID INT(20) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(problemID)
);


/*
<ATTRIBUTES>
����⵵ year INT(4)
ȸ�� serial VARCHAR(10)
���� type VARCHAR(10)
���� subject VARCHAR(20)
���� classify VARCHAR(20)
���̵� level VARCHAR(10)
�ֿ��׸� large VARCHAR(50)
�����׸� medium VARCHAR(50)
�����׸� small VARCHAR(50)
��ȣ pNum INT(5)
����-�����ε��� problemID INT(20) NOT NULL AUTO_INCREMENT,
���� problem VARCHAR(2000) NOT NULL
�߰����� addition VARCHAR (1000)
����	choice1 VARCHAR(500)
	choice2 VARCHAR(500)
	choice3 VARCHAR(500)
	choice4 VARCHAR(500)
Ǯ��Ű���� keyword VARCHAR(300)
Ǯ�� solution VARCHAR(2000)

<RELATIONS>
basicoption1(year,serial) +ID
basicoption2(type,subject,classify,level) +ID
classification(subject,large,medium,small) +ID
problemheader(year,serial,type,subject,classify,level,large,medium,small,pNum) +problemID
problembody(problem,addition,choice1,choice2,choice3,choice4,keyword,solution) +problemID

*/