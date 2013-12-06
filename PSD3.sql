-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 06, 2013 at 01:46 PM
-- Server version: 5.5.34
-- PHP Version: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `PSD3`
--

-- --------------------------------------------------------

--
-- Table structure for table `Attendance`
--

CREATE TABLE IF NOT EXISTS `Attendance` (
  `StudentID` varchar(8) NOT NULL,
  `SessionID` int(10) unsigned NOT NULL,
  `Status` varchar(10) NOT NULL DEFAULT 'absent',
  PRIMARY KEY (`StudentID`,`SessionID`),
  KEY `FK_Attendance_Session_idx` (`SessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Attendance`
--

INSERT INTO `Attendance` (`StudentID`, `SessionID`, `Status`) VALUES
('0904275f', 1, ''),
('1007100b', 1, ''),
('1007100b', 2, 'present'),
('1007100b', 3, 'present'),
('1102430c', 1, ''),
('1106113s', 1, ''),
('1106695s', 1, ''),
('1106695s', 2, 'absent'),
('1106695s', 3, 'mv');

-- --------------------------------------------------------

--
-- Table structure for table `Course`
--

CREATE TABLE IF NOT EXISTS `Course` (
  `ID` varchar(25) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Course`
--

INSERT INTO `Course` (`ID`, `Name`) VALUES
('COMPSCI 4009', 'Algorithmics'),
('COMPSCI 4010', 'Advanced Programming'),
('COMPSCI 4015', 'Professional Software Development'),
('COMPSCI 4016', 'Programming Languages');

-- --------------------------------------------------------

--
-- Table structure for table `Registration`
--

CREATE TABLE IF NOT EXISTS `Registration` (
  `StudentID` varchar(8) NOT NULL,
  `SessionID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`StudentID`,`SessionID`),
  KEY `FK_Registration_Session_idx` (`SessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Registration`
--

INSERT INTO `Registration` (`StudentID`, `SessionID`) VALUES
('0904275f', 1),
('1007100b', 1),
('1102430c', 1),
('1106113s', 1),
('1106695s', 1),
('1007100b', 2),
('1106695s', 2),
('1106695s', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Session`
--

CREATE TABLE IF NOT EXISTS `Session` (
  `ID` int(10) unsigned NOT NULL,
  `Course` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  `Frequency` int(10) NOT NULL DEFAULT '7',
  `Staff` varchar(50) NOT NULL,
  `MaxAttendance` int(10) NOT NULL,
  `Compulsory` tinyint(1) NOT NULL,
  `Venue` varchar(50) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Session_Venue_idx` (`Venue`),
  KEY `FK_Session_Staff_idx` (`Staff`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Session`
--

INSERT INTO `Session` (`ID`, `Course`, `Name`, `StartTime`, `EndTime`, `Frequency`, `Staff`, `MaxAttendance`, `Compulsory`, `Venue`, `StartDate`, `EndDate`) VALUES
(1, 'COMPSCI 4009', 'First Algorithmics Lab', '13:00:00', '15:00:00', 7, 'gnorman', 50, 1, '', '2013-09-22', '2013-12-22'),
(2, 'COMPSCI 4009', 'Second Algorithmics Lab', '11:00:00', '15:00:00', 14, 'gnorman', 120, 0, '', '2013-09-21', '2013-12-22'),
(3, 'COMPSCI 4010', 'C tutorial', '11:00:00', '12:00:00', 28, 'jsventek', 99, 1, '', '2013-09-25', '2013-12-22');

-- --------------------------------------------------------

--
-- Table structure for table `Sprint1Session`
--

CREATE TABLE IF NOT EXISTS `Sprint1Session` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CourseID` varchar(25) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CourseID` (`CourseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Sprint1Session`
--

INSERT INTO `Sprint1Session` (`ID`, `CourseID`, `Name`, `StartTime`, `EndTime`) VALUES
(1, 'COMPSCI 4009', 'First Algorithmics Lab', '2013-11-29 13:00:00', '2013-11-29 15:00:00'),
(2, 'COMPSCI 4009', 'Second Algorithmics Lab', '2013-12-06 11:00:00', '2013-12-06 15:00:00'),
(3, 'COMPSCI 4010', 'C tutorial', '2013-11-27 11:00:00', '2013-11-27 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `Staff`
--

CREATE TABLE IF NOT EXISTS `Staff` (
  `ID` varchar(10) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Staff`
--

INSERT INTO `Staff` (`ID`, `FirstName`, `LastName`) VALUES
('gnorman', 'Gethin', 'Norman'),
('jsinger', 'Jeremy', 'Singer'),
('jsventek', 'Joe', 'Sventek');

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE IF NOT EXISTS `Student` (
  `ID` varchar(8) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Barcode` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Barcode_UNIQUE` (`Barcode`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Student`
--

INSERT INTO `Student` (`ID`, `FirstName`, `LastName`, `Barcode`) VALUES
('0904275f', 'Helen', 'Foster', '54321543215432'),
('1007100b', 'Martynas', 'Buivys', '12345678912345'),
('1102430c', 'Raluca', 'Criste', '54321543212345'),
('1106113s', 'Tomasz', 'Sadowski', '98765432198765'),
('1106695s', 'Vlad', 'Schnakovszki', '12345123451234');

-- --------------------------------------------------------

--
-- Table structure for table `Venue`
--

CREATE TABLE IF NOT EXISTS `Venue` (
  `ID` varchar(10) NOT NULL,
  `Building` varchar(50) NOT NULL,
  `Room` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Venue`
--

INSERT INTO `Venue` (`ID`, `Building`, `Room`) VALUES
('BO507', 'Boyd Orr', '507'),
('BO513', 'Boyd Orr', '513'),
('BO720', 'Boyd Orr', '720');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Attendance`
--
ALTER TABLE `Attendance`
  ADD CONSTRAINT `FK_Attendance_Session` FOREIGN KEY (`SessionID`) REFERENCES `Session` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Attendance_Student` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Registration`
--
ALTER TABLE `Registration`
  ADD CONSTRAINT `FK_Registration_Session` FOREIGN KEY (`SessionID`) REFERENCES `Session` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
