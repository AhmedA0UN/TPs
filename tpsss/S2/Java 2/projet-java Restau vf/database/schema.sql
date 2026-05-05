CREATE DATABASE IF NOT EXISTS mydatabase
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE mydatabase;

CREATE TABLE IF NOT EXISTS menu (
  idMenu INT PRIMARY KEY AUTO_INCREMENT,
  nomMenu VARCHAR(100) NOT NULL,
  description TEXT
);

CREATE TABLE IF NOT EXISTS utilisateur (
  idU INT auto_increment  PRIMARY KEY,
  nomU VARCHAR(100) NOT NULL,
  mdpU VARCHAR(255) NOT NULL,
  role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS plat (
  idPlat INT PRIMARY KEY AUTO_INCREMENT,
  nomPlat VARCHAR(120) NOT NULL,
  prix DECIMAL(10,2) NOT NULL,
  idMenu INT NULL,
  typeMenu VARCHAR(50) NOT NULL,
  typePlat VARCHAR(50) NOT NULL,
  description TEXT,
  CONSTRAINT fk_plat_menu
    FOREIGN KEY (idMenu) REFERENCES menu(idMenu)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS commande (
  idC INT PRIMARY KEY AUTO_INCREMENT,
  etat VARCHAR(30) NOT NULL,
  idU INT NOT NULL,
  date_commande DATETIME NOT NULL,
  prixC DECIMAL(10,2) DEFAULT 0,
  CONSTRAINT fk_commande_utilisateur
    FOREIGN KEY (idU) REFERENCES utilisateur(idU)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS facture (
  idFacture INT PRIMARY KEY AUTO_INCREMENT,
  idC INT NOT NULL,
  date_facture DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  montantTotal DECIMAL(10,2) NOT NULL DEFAULT 0,
  CONSTRAINT fk_facture_commande
    FOREIGN KEY (idC) REFERENCES commande(idC)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS lignecommande (
  idLigne INT PRIMARY KEY AUTO_INCREMENT,
  idC INT NOT NULL,
  idPlat INT NOT NULL,
  quantite INT NOT NULL,
  prixUnitaire DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_lignecommande_commande
    FOREIGN KEY (idC) REFERENCES commande(idC)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_lignecommande_plat
    FOREIGN KEY (idPlat) REFERENCES plat(idPlat)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT chk_lignecommande_quantite_positive
    CHECK (quantite > 0)
);

CREATE TABLE IF NOT EXISTS platscommande (
  idPlat INT NOT NULL auto_increment,
  nomPlat VARCHAR(120) NOT NULL,
  quantite INT NOT NULL,
  idU INT NOT NULL,
  PRIMARY KEY (idPlat, idU),
  CONSTRAINT fk_platscommande_plat
    FOREIGN KEY (idPlat) REFERENCES plat(idPlat)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_platscommande_utilisateur
    FOREIGN KEY (idU) REFERENCES utilisateur(idU)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT chk_quantite_positive
    CHECK (quantite > 0)
);

-- Donnees minimales de test

INSERT INTO utilisateur (idU, nomU, mdpU, role)
VALUES
  (1, 'admin', 'admin123', 'chef'),
  (2, 'serveuse1', 'serveuse123', 'serveuse'),
  (3, 'client1', 'client123', 'client')

ON DUPLICATE KEY UPDATE nomU = VALUES(nomU);

INSERT INTO plat (nomPlat, prix, typeMenu, typePlat, description)
VALUES
  ('Salade Cesar', 12.50, 'Menu Sale', 'Entree', 'Salade avec poulet grille'),
  ('Tarte aux pommes', 9.00, 'Menu Sucre', 'Dessert', 'Tarte maison')
ON DUPLICATE KEY UPDATE nomPlat = VALUES(nomPlat);
