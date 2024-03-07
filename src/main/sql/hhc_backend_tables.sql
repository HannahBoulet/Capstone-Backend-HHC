CREATE TABLE Login(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userName varchar(50) UNIQUE,
    password varchar(50),
	clientID varchar(50) UNIQUE
);

CREATE TABLE UserInfo(
    id int NOT NULL PRIMARY KEY,
    clientFirst varchar(50),
    clientLast varchar(50),
	foodBox bit,
	medication bit,
    goalWeight int,
    currentWeight int,
    goalExercise text,
    currentExercise text,
	clientPicture varchar(100),
    FOREIGN KEY(id) REFERENCES Login(id)
);

CREATE TABLE Events(
	eventID int UNIQUE,
	eventName varchar(50),
	eventDate date,
	eventDescription text,
	eventLimit int,
	eventPicture varchar(100)
);

CREATE TABLE Registration(
    eventID int,
    id int,
    FOREIGN KEY(eventID) REFERENCES Events(eventID),
    FOREIGN KEY(id) REFERENCES UserInfo(id)
);


drop table Login;