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
    coef DECIMAL(3,1),
    CONSTRAINT chk_coef_positif CHECK (coef > 0)
);

-- Table EPREUVE
CREATE TABLE EPREUVE (
    numepreuve INT PRIMARY KEY,
    datepreuve DATE,
    lieu VARCHAR(50),
    codemat VARCHAR(10),
    CONSTRAINT fk_epreuve_matiere 
        FOREIGN KEY (codemat) REFERENCES MATIERE(codemat)
);

-- Table NOTATION
CREATE TABLE NOTATION (
    numetu INT,
    numepreuve INT,
    note DECIMAL(4,2),
    PRIMARY KEY (numetu, numepreuve),
    CONSTRAINT fk_notation_etudiant 
        FOREIGN KEY (numetu) REFERENCES ETUDIANT(numetu),
    CONSTRAINT fk_notation_epreuve 
        FOREIGN KEY (numepreuve) REFERENCES EPREUVE(numepreuve),
    CONSTRAINT chk_note_intervalle CHECK (note BETWEEN 0 AND 20)
);