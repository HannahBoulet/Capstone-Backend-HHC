CREATE TABLE Login(
    userName text UNIQUE,
    password text,
	clientID text UNIQUE
);

CREATE TABLE UserInfo(
    clientID text FOREIGN KEY REFERENCES UserInfo(clientID)
	name text,
	foodBox bit,
	medication bit,
    goalWeight int,
    currentWeight int,
    goalExercise text,
    currentExercise text,
	clientPicture text
);

CREATE TABLE Events(
	eventID int UNIQUE,
	eventName text,
	eventDate date,
	eventDescription text,
	eventLimit int,
	eventPicture text
);

CREATE TABLE Registration(
    eventID text FOREIGN KEY REFERENCES Events(eventID),
    clientID text FOREIGN KEY REFERENCES UserInfo(clientID)
);