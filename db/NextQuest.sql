SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE `NextQuest` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci;
USE `NextQuest`;

CREATE TABLE IF NOT EXISTS `AbilityList` (
  `idAbility` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) COLLATE utf8_czech_ci NOT NULL,
  `Description` varchar(250) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`idAbility`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `Projects` (
  `idProject` int(11) NOT NULL,
  `idUserCreatedBy` int(11) NOT NULL,
  `Name` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`idProject`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

CREATE TABLE IF NOT EXISTS `TaskAbility` (
  `idTask` int(11) NOT NULL,
  `idAbility` int(11) NOT NULL,
  `Level` int(5) NOT NULL,
  KEY `idTask` (`idTask`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

CREATE TABLE IF NOT EXISTS `Tasks` (
  `idTask` int(11) NOT NULL AUTO_INCREMENT,
  `idProject` int(11) NOT NULL,
  `idUserCreatedBy` int(11) NOT NULL,
  `idUserAssignedTo` int(11) NOT NULL,
  `idParentTask` int(11) NOT NULL,
  `TaskStatus` enum('Created','Assigned','InProgress','Rejected','ApprovedWaiting','Completed') COLLATE utf8_czech_ci NOT NULL DEFAULT 'Created',
  `Title` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  `Description` varchar(1000) COLLATE utf8_czech_ci NOT NULL,
  `Priority` int(11) NOT NULL,
  `CreationDate` date NOT NULL,
  `DeadlineDate` date NOT NULL,
  `MaxHours` int(11) NOT NULL,
  `isSubTask` tinyint(1) NOT NULL DEFAULT '0',
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`idTask`),
  KEY `idProject` (`idProject`,`idUserAssignedTo`,`idParentTask`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `UserAbility` (
  `idUser` int(11) NOT NULL,
  `idAbility` int(11) NOT NULL,
  `Level` int(5) NOT NULL,
  KEY `idUser` (`idUser`,`idAbility`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

CREATE TABLE IF NOT EXISTS `Users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `idUserCreatedBy` int(11) NOT NULL,
  `LoginName` varchar(20) COLLATE utf8_czech_ci NOT NULL,
  `Name` varchar(40) COLLATE utf8_czech_ci NOT NULL,
  `Password` varchar(32) COLLATE utf8_czech_ci NOT NULL,
  `permAdmin` tinyint(1) NOT NULL DEFAULT '0',
  `permLeader` tinyint(1) NOT NULL DEFAULT '0',
  `permPersonalist` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`),
  KEY `LoginName` (`LoginName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
