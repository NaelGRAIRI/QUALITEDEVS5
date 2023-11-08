-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 08 nov. 2023 à 14:39
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `iutbank`
--

-- --------------------------------------------------------

--
-- Structure de la table `Compte`
--

CREATE TABLE `Compte` (
  `num_compte` varchar(50) NOT NULL,
  `num_client` varchar(50) NOT NULL,
  `solde` float NOT NULL,
  `avecDecouvert` tinyint(1) NOT NULL,
  `decouvertAutorise` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Compte`
--

INSERT INTO `Compte` (`num_compte`, `num_client`, `solde`, `avecDecouvert`, `decouvertAutorise`) VALUES
('12912837401', '123456788', -1289, 1, 1500),
('BD4242424242', '123456789', 150, 0, NULL),
('FF5050500202', '123456789', 705.96, 1, 150);

-- --------------------------------------------------------

--
-- Structure de la table `Genre`
--

CREATE TABLE `Genre` (
  `id_genre` int(11) NOT NULL,
  `genre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Genre`
--

INSERT INTO `Genre` (`id_genre`, `genre`) VALUES
(1, 'Homme'),
(2, 'Femme'),
(3, 'Non Binaire');

-- --------------------------------------------------------

--
-- Structure de la table `type_Client`
--

CREATE TABLE `type_Client` (
  `id_type` int(11) NOT NULL,
  `type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_Client`
--

INSERT INTO `type_Client` (`id_type`, `type`) VALUES
(1, 'Manager'),
(2, 'Client');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `num_user` varchar(45) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prénom` varchar(45) NOT NULL,
  `adresse` varchar(45) NOT NULL,
  `userPwd` varchar(256) NOT NULL,
  `genre` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`num_user`, `nom`, `prénom`, `adresse`, `userPwd`, `genre`, `type`) VALUES
('123456788', 'client2', 'Jude', '10 rue du moulin rouge, tournant en brie', '699a3eec2c9f94989c141222ea8083e02e3d8174a749816e789fcd583878aa75', 1, 2),
('123456789', 'client1', 'Jane', '45, grand boulevard, Brest', '11fa5aade33a4f8ea1cbf309f610092bdf7e50526ffdc08f17b0ea046f97961f', 2, 2),
('admin1', 'Smith', 'Joe', '123, grande rue, Metz', '713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca', 1, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD PRIMARY KEY (`num_compte`),
  ADD KEY `num_client` (`num_client`);

--
-- Index pour la table `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id_genre`);

--
-- Index pour la table `type_Client`
--
ALTER TABLE `type_Client`
  ADD PRIMARY KEY (`id_type`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`num_user`),
  ADD KEY `genre` (`genre`),
  ADD KEY `type` (`type`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Genre`
--
ALTER TABLE `Genre`
  MODIFY `id_genre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `type_Client`
--
ALTER TABLE `type_Client`
  MODIFY `id_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`num_client`) REFERENCES `Utilisateur` (`num_user`);

--
-- Contraintes pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`genre`) REFERENCES `Genre` (`id_genre`),
  ADD CONSTRAINT `utilisateur_ibfk_2` FOREIGN KEY (`type`) REFERENCES `type_Client` (`id_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
