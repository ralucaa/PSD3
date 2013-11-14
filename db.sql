-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 14, 2013 at 02:30 PM
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
  `SessionID` varchar(25) NOT NULL,
  `Status` varchar(10) NOT NULL,
  PRIMARY KEY (`StudentID`,`SessionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Attendance`
--

INSERT INTO `Attendance` (`StudentID`, `SessionID`, `Status`) VALUES
('1007100b', '1', 'present'),
('1007100b', '2', 'absent'),
('1007100b', '3', 'present'),
('1106695s', '1', 'absent'),
('1106695s', '2', 'present'),
('1106695s', '3', 'mv');

-- --------------------------------------------------------

--
-- Table structure for table `Course`
--

CREATE TABLE IF NOT EXISTS `Course` (
  `ID` varchar(25) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
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
-- Table structure for table `Session`
--

CREATE TABLE IF NOT EXISTS `Session` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CourseID` varchar(25) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CourseID` (`CourseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Session`
--

INSERT INTO `Session` (`ID`, `CourseID`, `Name`, `StartTime`, `EndTime`) VALUES
(1, 'COMPSCI 4009', 'First Algorithmics Lab', '2013-11-29 13:00:00', '2013-11-29 15:00:00'),
(2, 'COMPSCI 4009', 'Second Algorithmics Lab', '2013-12-06 11:00:00', '2013-12-06 15:00:00'),
(3, 'COMPSCI 4010', 'C tutorial', '2013-11-27 11:00:00', '2013-11-27 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE IF NOT EXISTS `Student` (
  `ID` varchar(8) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Barcode` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Student`
--

INSERT INTO `Student` (`ID`, `FirstName`, `LastName`, `Barcode`) VALUES
('1007100b', 'Martynas', 'Buivys', '12345678912345'),
('1106695s', 'Vlad', 'Schnakovszki', '12345123451234');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Attendance`
--
ALTER TABLE `Attendance`
  ADD CONSTRAINT `Attendance_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`ID`);

--
-- Constraints for table `Session`
--
ALTER TABLE `Session`
  ADD CONSTRAINT `Session_ibfk_1` FOREIGN KEY (`CourseID`) REFERENCES `Course` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
