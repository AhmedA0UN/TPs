-- Création de la base de données
CREATE DATABASE IF NOT EXISTS notes_etudiants;
USE notes_etudiants;

-- Table ETUDIANT
CREATE TABLE ETUDIANT (
    numetu INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    datenaiss DATE,
    rue VARCHAR(100),
    cp VARCHAR(5),
    ville VARCHAR(50)
);

-- Table MATIERE
CREATE TABLE MATIERE (
    codemat VARCHAR(10) PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    coef DECIMAL(3,1) CHECK (coef > 0)
);

-- Table EPREUVE
CREATE TABLE EPREUVE (
    numepreuve INT PRIMARY KEY,
    datepreuve DATE,
    lieu VARCHAR(50),
    codemat VARCHAR(10),
    FOREIGN KEY (codemat) REFERENCES MATIERE(codemat)
);

-- Table NOTATION
CREATE TABLE NOTATION (
    numetu INT,
    numepreuve INT,
    note DECIMAL(4,2) CHECK (note BETWEEN 0 AND 20),
    PRIMARY KEY (numetu, numepreuve),
    FOREIGN KEY (numetu) REFERENCES ETUDIANT(numetu),
    FOREIGN KEY (numepreuve) REFERENCES EPREUVE(numepreuve)
);