# QUALITEDEVS5

Ce projet, intitulé QUALITEDEVS5, a été développé dans le cadre du semestre 5 du Brevet Universitaire de Technologie (BUT) Informatique. L'objectif principal de ce projet est d'améliorer et faire évoluer une application existante, mettant en œuvre des principes de qualité de développement logiciel.

## Membres de l'équipe :
- GRAIRI Naël
- BERTON Lucas
- ALOUI Mohamed
- DIALLO Mamadou

Le projet met l'accent sur l'application de bonnes pratiques de développement, la qualité du code, la gestion de configuration, les tests unitaires et d'intégration, ainsi que d'autres aspects liés à l'amélioration continue du logiciel. L'équipe travaille de concert pour garantir la qualité du code, la robustesse de l'application, et pour répondre aux besoins des utilisateurs finaux. Les membres de l'équipe sont engagés dans le processus de développement et s'efforcent de fournir un logiciel fiable et performant.
# Explication du Projet

# Documentation d'installation et de configuration

## Table des matières
1. [Introduction](#introduction)
2. [Prérequis](#prérequis)
3. [Récupération du projet](#récupération-du-projet)
4. [Maven](#maven)
5. [TOMCAT](#tomcat)
6. [Configuration de la base de données](#configuration-de-la-base-de-données)
7. [Lancement de l’application](#lancement-de-lapplication)
8. [Lancer le build et la compilation sans l’option DSKIPTEST](#lancer-le-build-et-la-compilation-sans-loption-dskiptest)
9. [Migration des Versions](#migration-des-versions)
10. [Hachage du Mot de passe](#hachage-du-mot-de-passe)

## Introduction
Bienvenue dans la documentation d’installation du projet Bank IUT ! Nous sommes ravis que vous ayez choisi notre application pour votre gestion financière personnelle.

Cette documentation a pour objectif de vous guider à travers le processus d’installation de Bank IUT à partir de notre référentiel GIT, afin que vous puissiez rapidement commencer à profiter de ses fonctionnalités. Bank IUT est un projet informatique conçu pour simplifier la gestion de vos finances, en vous offrant un moyen pratique de suivre vos comptes, vos dépenses et vos revenus.

Bank IUT est un projet développé en Java par notre équipe d’étudiants de l’IUT de Metz. Ils été créé dans le cadre de notre cursus académique pour répondre aux besoins en gestion financière, tant personnelle que professionnelle. L’application est conçue pour être facile à utiliser, tout en offrant des fonctionnalités puissantes pour vous aider à mieux comprendre et gérer vos finances.

Dans cette documentation, nous vous expliquerons en détail comment installer Bank IUT à partir de notre référentiel GIT. Cette méthode d’installation vous permettra d’obtenir la version la plus récente du projet et de bénéficier des mises à jours ultérieures.

Nous vous guiderons pas à pas à travers le processus d’installation, en vous fournissant des instructions claires, des captures d’écran et des exemples.

## Prérequis
Avant de commencer le processus d’installation, assurez-vous de disposer des éléments suivants sur votre système :

- **Java Development Kit (JDK) :** Bank IUT est un projet Java, donc vous aurez besoin d'avoir le JDK installé sur votre machine. Assurez-vous d'avoir la version recommandée dans les prérequis du projet.

                             <img src="../QUALITEDEVS5/Indicateurs/Images/java-version.png" width="400" height="200">

- **Apache Maven :** Bank IUT utilise Maven comme système de gestion de projet. Vous devez installer Maven sur votre système pour pouvoir construire et compiler le projet.

- **Apache Tomcat :** Bank IUT utilise Tomcat comme serveur d'application. Vous devez avoir Tomcat installé sur votre machine pour déployer et exécuter l'application.

- **Base de données :** Assurez-vous d'avoir une base de données compatible avec le projet (mentionnée dans la section de configuration de la base de données).

## Récupération du projet
Sur le Git que nous vous transmis, veuillez récupérer l’entièreté du dossier _00_ASBank2018 (dossier comprenant le code de l’application).

    <img src="../QUALITEDEVS5/Indicateurs /java-version.png" width="400" height="400">

           Veuillez ensuite ouvrir le dossier dans l’IDE IntelliJ IDEA.

## Maven
Maven est l’outil qui nous permettra de build notre application. Pour cela, nous allons devoir le configurer.
Pour configurer Maven, cliquez sur les 3 points à côté du logo Maven : 

                                     <img src="../QUALITEDEVS5/Indicateurs /java-version.png" width="400" height="400">

Cliquez ensuite sur édit :
 
                                   <img src="../QUALITEDEVS5/Indicateurs /java-version.png" width="400" height="400">


Reprenez bien les mêmes paramètre de configuration que la capture d’écran suivante :

                                   <img src="../QUALITEDEVS5/Indicateurs /java-version.png" width="400" height="400">

    Pour cette version d’installation nous lancerons l’application sans les tests. Veuillez donc à ne pas oublier l’option « Skip Tests ».

