CREATE TABLE Login(
    userName text UNIQUE,
    password text,
	clientID text UNIQUE
);

CREATE TABLE UserInfo(
	FOREIGN KEY (clientId) REFERENCES Login(clientID),
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
    FOREIGN KEY (eventID) REFERENCES Events(eventID),
	FOREIGN KEY (clientId) REFERENCES Login(clientID)
);