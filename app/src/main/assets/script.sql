DROP TABLE Patient;
DROP TABLE Medecin;
DROP TABLE Rendezvous;

CREATE TABLE Patient(
    prenom TEXT NOT NULL,
    nom TEXT NOT NULL,
    numtelephone INTEGER NOT NULL,
    adresse TEXT NOT NULL,
    email TEXT NOT NULL,
    motpasse TEXT NOT NULL,
    PRIMARY KEY (email),
    UNIQUE(email)

);

CREATE TABLE Medecin(
    nom TEXT NOT NULL,
    adresse TEXT NOT NULL,
    email TEXT NOT NULL,
    motpasse TEXT NOT NULL,
    PRIMARY KEY (email),
    UNIQUE(email)

);

CREATE TABLE Rendezvous(
    daterdv TEXT NOT NULL,
    emailMedecin TEXT NOT NULL,
    emailPatient TEXT,
    PRIMARY KEY (daterdv,emailMedecin),
    UNIQUE(daterdv,emailMedecin),
    FOREIGN KEY (emailPatient) REFERENCES Patient (email),
    FOREIGN KEY (emailMedecin) REFERENCES Medecin (email)

);

INSERT INTO Patient VALUES
("Françoise","Dupont",0611111111,"31 Rue de la Paix 69003 Lyon","francoise.dupont@gmail.com","Azerty123"),
("Ignace","Aubé",0609740582,"83, rue des Chaligny 69005 Lyon","ignace.aube@gmail.com","Azerty123"),
("Claudette","Courtois",0750963223,"97, Rue St Ferréol 94200 Ivry-sur-Seine","claudette.courtois@gmail.com","Azerty123");


INSERT INTO Medecin VALUES
("Smith","12 Rue de la Paix 69006 Lyon","dr.smith@gmail.com","Azerty123"),
("Couture","79, rue Gontier-Patin 69006 Lyon","dr.couture@gmail.com","Azerty123"),
("Vermande","77, Avenue des Tuileries 69006 Lyon","dr.vermande@gmail.com","Azerty123"),
("Brisette","24, rue Charles Corbeau 94200 Ivry-sur-Seine","dr.brisette@gmail.com","Azerty123"),
("Caisse","34, avenue du Marechal Juin 94200 Ivry-sur-Seine","dr.caisse@gmail.com","Azerty123"),
("Bureau","87, rue Ernest Renan 94200 Ivry-sur-Seine","dr.bureau@gmail.com","Azerty123");


INSERT INTO Rendezvous VALUES
("08:00 8/12/2021","dr.smith@gmail.com","francoise.dupont@gmail.com"),
("11:00 8/12/2021","dr.vermande@gmail.com","francoise.dupont@gmail.com"),
("16:00 8/6/2021","dr.smith@gmail.com","francoise.dupont@gmail.com"),
("15:00 3/6/2021","dr.smith@gmail.com","ignace.aube@gmail.com"),
("17:00 4/6/2021","dr.caisse@gmail.com","claudette.courtois@gmail.com");

