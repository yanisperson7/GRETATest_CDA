-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 24 août 2026 à 09:14
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_testcda`
--

-- --------------------------------------------------------

--
-- Structure de la table `archivereservation`
--

DROP TABLE IF EXISTS `archivereservation`;
CREATE TABLE IF NOT EXISTS `archivereservation` (
  `idArchive` int NOT NULL AUTO_INCREMENT,
  `idReservation` int NOT NULL,
  `idUtilisateur` int NOT NULL,
  `idMateriel` int NOT NULL,
  `dateReservation` date NOT NULL,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `action` varchar(50) NOT NULL,
  `dateAction` date NOT NULL,
  PRIMARY KEY (`idArchive`),
  KEY `fk_archive_reservation` (`idReservation`),
  KEY `fk_archive_materiel` (`idMateriel`),
  KEY `fk_archive_utilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
CREATE TABLE IF NOT EXISTS `materiel` (
  `idMateriel` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `categorie` enum('Ordinateur','Vidéoprojecteur','Webcam') NOT NULL,
  `quantite` int NOT NULL,
  `etat` varchar(50) NOT NULL,
  PRIMARY KEY (`idMateriel`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`idMateriel`, `nom`, `categorie`, `quantite`, `etat`) VALUES
(1, 'Vidéoprojecteur Epson', 'Vidéoprojecteur', 5, 'disponible'),
(2, 'Ordinateur portable HP', 'Ordinateur', 10, 'prêté'),
(5, 'PlayStation 5', 'Ordinateur', 3, 'prêté');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `idReservation` int NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int NOT NULL,
  `idMateriel` int NOT NULL,
  `dateReservation` date NOT NULL,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `fk_reservation_utilisateur` (`idUtilisateur`),
  KEY `fk_reservation_materiel` (`idMateriel`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `idUtilisateur`, `idMateriel`, `dateReservation`, `heureDebut`, `heureFin`) VALUES
(1, 2, 1, '2026-08-25', '09:00:00', '11:00:00'),
(2, 1, 1, '2026-08-24', '10:29:40', '10:29:40'),
(3, 1, 2, '2026-08-30', '13:00:00', '15:00:00'),
(4, 1, 1, '2026-08-23', '13:41:51', '13:41:51'),
(5, 1, 2, '2026-08-30', '13:00:00', '15:00:00'),
(6, 1, 2, '2026-08-23', '13:20:52', '13:20:52'),
(7, 1, 1, '2026-08-24', '10:38:45', '10:38:45'),
(8, 2, 2, '2026-08-30', '13:00:00', '15:00:00'),
(9, 3, 2, '2026-08-25', '14:48:00', '12:48:23'),
(10, 3, 2, '2026-08-25', '14:48:00', '12:48:23'),
(11, 1, 1, '2026-08-23', '13:30:53', '13:30:53'),
(12, 1, 1, '2026-08-23', '13:23:43', '13:23:43'),
(13, 1, 1, '2026-08-23', '13:41:51', '13:41:51'),
(14, 3, 1, '2026-08-24', '09:57:08', '09:57:08'),
(15, 1, 1, '2026-08-24', '10:29:40', '10:29:40'),
(16, 1, 1, '2026-08-24', '10:38:45', '10:38:45'),
(17, 1, 2, '2026-08-24', '11:04:45', '11:04:45'),
(18, 2, 1, '2026-08-24', '11:06:51', '11:06:51');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `idRole` varchar(5) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `libelle`) VALUES
('ADM', 'Administrateur'),
('ENS', 'Enseignant');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int NOT NULL AUTO_INCREMENT,
  `idRole` varchar(5) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`),
  KEY `fk_utilisateur_role` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `idRole`, `nom`, `prenom`, `login`, `password`) VALUES
(1, 'ADM', 'Person', 'Yanis', 'yanis', '123'),
(2, 'ENS', 'Person', 'Lisa', 'lisa', '456'),
(3, 'ENS', 'Person', 'Maïou', 'maiou', '789');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `archivereservation`
--
ALTER TABLE `archivereservation`
  ADD CONSTRAINT `fk_archive_materiel` FOREIGN KEY (`idMateriel`) REFERENCES `materiel` (`idMateriel`),
  ADD CONSTRAINT `fk_archive_utilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_materiel` FOREIGN KEY (`idMateriel`) REFERENCES `materiel` (`idMateriel`),
  ADD CONSTRAINT `fk_reservation_utilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_utilisateur_role` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
