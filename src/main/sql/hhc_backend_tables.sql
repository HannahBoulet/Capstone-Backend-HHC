CREATE TABLE Login(
    userName varchar(max) UNIQUE,
    password varchar(max),
	clientID varchar(max) UNIQUE
);

CREATE TABLE UserInfo(
	FOREIGN KEY (clientId) REFERENCES Login(clientID),
	name varchar(max),
	foodBox bit,
	medication bit,
    goalWeight int,
    currentWeight int,
    goalExercise varchar(max),
    currentExercise varchar(max),
	gymPlan varchar(max),
	clientPicture varchar(max)
);

CREATE TABLE Events(
	eventID int UNIQUE,
	eventName varchar(max),
	eventDate date,
	eventDescription varchar(max),
	eventLimit int,
	eventPicture varchar(max),
);

CREATE TABLE Registration(
    FOREIGN KEY (eventID) REFERENCES Events(eventID),
	FOREIGN KEY (clientId) REFERENCES Login(clientID),
);