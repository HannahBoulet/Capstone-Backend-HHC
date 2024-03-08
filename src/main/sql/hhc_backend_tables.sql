CREATE TABLE Login(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userName varchar(50) UNIQUE,
    password varchar(50),
	clientID varchar(50) UNIQUE
);

CREATE TABLE UserInfo(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
	eventID int UNIQUE AUTO_INCREMENT,
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

#---------------------------------------------------------------------------------------------------------------------------------------
#delte all data

delete from login where id = id;
delete from Events where eventName = eventName;


#---------------------------------------------------------------------------------------------------------------------------------------
#select statments

SELECT
        id,
    userName,
    password,
	clientID
from Login;

SELECT
   id,
    clientFirst,
    clientLast,
	foodBox,
	medication ,
    goalWeight ,
    currentWeight ,
    goalExercise ,
    currentExercise ,
	clientPicture
from UserInfo;

SELECT
	eventID ,
	eventName,
	eventDate,
	eventDescription,
	eventLimit,
	eventPicture
from Events;

SELECT
    eventID,
    id
from Registration;

#---------------------------------------------------------------------------------------------------------------------------------------
#Insert test data

INSERT INTO Login (userName, password, clientID) VALUES
('user1', 'password1', 'client123'),
('user2', 'password2', 'client456');

INSERT INTO UserInfo (ID,clientFirst, clientLast, foodBox, medication, goalWeight, currentWeight, goalExercise, currentExercise, clientPicture) VALUES
(1,'John', 'Doe', 1, 0, 180, 175, '3 times a week gym', 'Walking daily', 'picture.jpg'),
(2,'Jane', 'Smith', 0, 1, 150, 155, 'Yoga twice a week', 'Pilates once a week', 'profile_pic.png');

INSERT INTO Events (eventName, eventDate, eventDescription, eventLimit, eventPicture) VALUES
('Community Potluck', '2024-03-15', 'Bring your favorite dish to share!', 20, 'potluck.jpg'),
('Fitness Workshop', '2024-04-20', 'Learn new exercise routines', 15, 'workshop.jpg');


INSERT INTO Registration (eventID, id) VALUES
(1, 1), -- John Doe registers for Community Potluck
(2, 2); -- Jane Smith registers for Fitness Workshop

#---------------------------------------------------------------------------------------------------------------------------------------
#drop tables
drop table Login;
drop table UserInfo;
drop table registration;
drop table events;

#---------------------------------------------------------------------------------------------------------------------------------------
#reset auto increament

alter table login AUTO_INCREMENT = 1;


#---------------------------------------------------------------------------------------------------------------------------------------
#procedure to make a login
DELIMITER //

CREATE PROCEDURE AddUserLogin(
    IN userName2 VARCHAR(50),
    IN password2 VARCHAR(50),
    IN clientID2 VARCHAR(50)
)

BEGIN

    DECLARE errorMessage VARCHAR(255);
    DECLARE usernameExists INT DEFAULT 0;
    DECLARE clientExists INT DEFAULT 0;


    IF userName2 IS NULL OR userName2 = '' THEN
        SET errorMessage = 'Username cannot be empty.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMessage;
    ELSEIF password2 IS NULL OR password2 = '' THEN
        SET errorMessage = 'Password cannot be empty.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMessage;
    ELSEIF clientID2 IS NULL OR clientID2 = '' THEN
        SET errorMessage = 'ClientID cannot be empty.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMessage;
    END IF;



    SELECT COUNT(*) INTO usernameExists FROM Login WHERE userName = userName2;
    SELECT COUNT(*) INTO clientExists FROM Login WHERE clientID = clientID2;

    IF usernameExists > 0 THEN
        SET errorMessage = 'Username already exists.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMessage;
    ELSEIF clientExists > 0 THEN
        SET errorMessage = 'ClientID already exists.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMessage;
    END IF;


    INSERT INTO Login (userName, password, clientID) VALUES (userName2, password2, clientID2);


    SELECT 'User created successfully.';

END //

DELIMITER ;
#---------------------------------------------------------------------------------------------------------------------------------------
#testing procedure + other usfull tools to modify it

CALL AddUserLogin ('user3','password3', 'client789');

SHOW PROCEDURE STATUS WHERE db = 'hhc';
DROP PROCEDURE AddUserLogin;

#---------------------------------------------------------------------------------------------------------------------------------------
#procedure to make a login and enter userinfo
DELIMITER //

CREATE PROCEDURE CreateUserAndUserInfo(
    IN userName2 VARCHAR(50),
    IN password2 VARCHAR(50),
    IN clientID2 VARCHAR(50),
    clientFirst2 varchar(50),
    clientLast2 varchar(50),
	foodBox2 bit,
	medication2 bit,
    goalWeight2 int,
    currentWeight2 int,
    goalExercise2 text,
    currentExercise2 text,
	clientPicture2 varchar(100)
)

begin
    CALL AddUserLogin (userName2,password2, clientID2);

    INSERT INTO UserInfo (clientFirst, clientLast, foodBox, medication, goalWeight, currentWeight, goalExercise, currentExercise, clientPicture) VALUES
    (clientFirst2,clientLast2, foodBox2, medication2, goalWeight2, currentWeight2, goalExercise2, currentExercise2, clientPicture2 );
END //

DELIMITER ;

#---------------------------------------------------------------------------------------------------------------------------------------
#some testing calls for entering users+data
CALL CreateUserAndUserInfo('user1','password1', 'client123', 'John', 'Doe', 1, 0, 180, 175, '3 times a week gym', 'Walking daily', 'picture.jpg');

CALL CreateUserAndUserInfo('user2','password2', 'client456', 'Jane', 'Smith', 0, 1, 150, 155, 'Yoga twice a week', 'Pilates once a week', 'profile_pic.png');

