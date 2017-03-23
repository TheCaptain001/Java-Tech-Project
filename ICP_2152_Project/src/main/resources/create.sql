/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Dion
 * Created: 23-Mar-2017
 */

CREATE TABLE IF NOT EXISTS words
(
    welshWord VARCHAR(20) NOT NULL,
    englishWord VARCHAR(20) NOT NULL,
    gender CHAR(1) NOT NULL,
    meaning VARCHAR(40) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS students
(
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    dateOfBirth DATE NOT NULL,
    email VARCHAR (30) NOT NULL,
    PRIMARY KEY (username)
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS instructor 
(
    instructNo VARCHAR(6) NOT NULL, 
    email VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    Primary Key(instructNo)
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS sysAdmin
(
    adminNo VARCHAR(6),
    email VARCHAR(30),
    password VARCHAR(20) NOT NULL,
    Primary Key(adminNo)
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS access
(
    username VARCHAR(20),
    instructNo VARCHAR(6),
    adminNo VARCHAR(6),
    accessLevel CHAR(1),
    primary key (acceSsLevel),
    foreign key (username) references students(username) ON UPDATE CASCADE,
    foreign key (instructNo) references instructor(instructNo) ON UPDATE CASCADE,
    foreign key (adminNo) references sysAdmin(adminNo) ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS results
(
    username VARCHAR(20) NOT NULL,
    genderTest INT(3),
    meaningTest INT(3),
    englishTest INT(3),
    foreign key (username) references students(username) ON UPDATE CASCADE
)ENGINE=InnoDB;