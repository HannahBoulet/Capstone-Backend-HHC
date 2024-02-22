CREATE TABLE Login(
    userName text UNIQUE,
    password text,
	clientID text UNIQUE
);

CREATE TABLE UserInfo(
    clientFirst text,
    clientLast text,
	foodBox bit,
	medication bit,
    goalWeight int,
    currentWeight int,
    goalExercise text,
    currentExercise text,
	clientPicture text,
    clientID text,
    FOREIGN KEY(clientID) REFERENCES Login(clientID)

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
    eventID text,
    clientID text,
    FOREIGN KEY(eventID) REFERENCES Events(eventID),
    FOREIGN KEY(clientID) REFERENCES UserInfo(clientID)
);