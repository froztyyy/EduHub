-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2023 at 05:30 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eduhub_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `UserID` int(11) NOT NULL,
  `Username` int(11) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `RoleID` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`UserID`, `Username`, `Password`, `RoleID`) VALUES
(1, 20220057, 'Pw57Wd20', 3),
(2, 20220058, 'Wo58Rd20', 3),
(3, 20220059, 'Co92De20', 2),
(4, 20220060, '6I202020', 2),
(5, 20220061, 'Id6120K1', 3),
(6, 20220078, '82R00Y20', 3),
(7, 20220081, '8I20220D', 3),
(8, 20220082, '20T2W0D8', 3),
(9, 20220097, '7T20D920', 3),
(10, 20220119, '2O19E020', 3),
(11, 20220185, '852O21W0', 3),
(12, 20220255, '520K2022', 2),
(13, 20220256, '2W56I020', 2),
(14, 20220260, '620E20D2', 3),
(15, 20220262, '620262Q0', 3),
(16, 20220287, '7R20W822', 3),
(17, 20220302, '3O202022', 2),
(18, 20220313, '1320W2D0', 3),
(19, 20220336, '36C2O220', 3),
(20, 20220367, '6O72K20D', 3),
(21, 20220377, '7K20D322', 3),
(22, 20220404, '40204W22', 3),
(23, 20220456, '6E20W456', 3),
(24, 20220457, '752022R4', 3),
(25, 20220464, '46202W48', 3),
(26, 20220471, '1W72R042', 3),
(27, 20220475, '57R2K420', 3),
(28, 20220507, '79K20W42', 3),
(29, 20220527, '7C205K27', 3),
(30, 20220571, '72202O57', 3),
(31, 20220583, '1720522R', 3),
(32, 20220629, '3R8W52K0', 2),
(33, 20220657, '9W2O6220', 3),
(34, 20220661, '75O22D57', 3),
(35, 20220666, '1K6W0220', 3),
(36, 20220679, '66K0O222', 3),
(37, 20220683, '9O76W822', 3),
(38, 20220715, '386022W8', 3),
(39, 20220815, '517022R0', 2),
(40, 20220844, '518O2I22', 3),
(41, 20231583, '4480D2W2', 3),
(42, 10000000, 'EduHubOOP', 1),
(43, 10000001, 'mikazuki125', 1),
(44, 10000002, 'EHBSCS2A', 2),
(45, 10000003, 'studLeader', 3),
(46, 10000004, 'studMember', 3);

-- --------------------------------------------------------

--
-- Table structure for table `announcement`
--

CREATE TABLE `announcement` (
  `AnnouncementID` int(11) NOT NULL,
  `PostID` int(11) DEFAULT NULL,
  `Title` varchar(150) NOT NULL,
  `Body` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `audience`
--

CREATE TABLE `audience` (
  `AudienceID` int(11) NOT NULL,
  `AudienceName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `audience`
--

INSERT INTO `audience` (`AudienceID`, `AudienceName`) VALUES
(1, 'School'),
(2, 'Homeroom'),
(3, 'Officers'),
(4, 'Course'),
(5, 'Group'),
(6, 'Everyone');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryID` int(11) NOT NULL,
  `CategoryName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryID`, `CategoryName`) VALUES
(1, 'Assignment'),
(2, 'Activity'),
(3, 'Project'),
(4, 'Task');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(255) NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `UpdatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`GroupID`, `GroupName`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'Group 3', '2023-11-01 20:26:39', '2023-11-01 20:26:39'),
(2, 'Test Group', '2023-11-01 20:27:48', '2023-11-01 20:27:48');

-- --------------------------------------------------------

--
-- Table structure for table `leader_groups`
--

CREATE TABLE `leader_groups` (
  `ID` int(11) NOT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `is_leader` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `leader_groups`
--

INSERT INTO `leader_groups` (`ID`, `GroupID`, `StudentID`, `is_leader`) VALUES
(1, 1, 20220629, 1),
(2, 2, 10000003, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `PostID` int(11) NOT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `SubjectID` int(11) DEFAULT NULL,
  `AudienceID` int(11) NOT NULL,
  `PriorityID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `PostDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `priority_level`
--

CREATE TABLE `priority_level` (
  `PriorityID` int(11) NOT NULL,
  `PriorityName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `priority_level`
--

INSERT INTO `priority_level` (`PriorityID`, `PriorityName`) VALUES
(1, 'Urgent'),
(2, 'High'),
(3, 'Medium'),
(4, 'Low'),
(5, 'Recurring'),
(6, 'Unknown');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `RoleID` int(1) NOT NULL,
  `RoleName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`RoleID`, `RoleName`) VALUES
(1, 'Admin'),
(2, 'Officer'),
(3, 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `StudentID` int(11) NOT NULL,
  `Surname` varchar(80) DEFAULT NULL,
  `FirstName` varchar(80) DEFAULT NULL,
  `MiddleInitial` varchar(80) DEFAULT NULL,
  `Suffix` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`StudentID`, `Surname`, `FirstName`, `MiddleInitial`, `Suffix`) VALUES
(10000000, 'ADMINISTRATOR', 'ALPHA', NULL, NULL),
(10000001, 'ADMINISTRATOR', 'BETA', NULL, NULL),
(10000002, 'OFFICER', 'CHARLIE', NULL, NULL),
(10000003, 'STUDENT', 'DELTA', 'L.', NULL),
(10000004, 'STUDENT', 'FOXTROT', 'S.', NULL),
(20220057, 'SAMIS', 'LAIRA COLEEN', 'W.', NULL),
(20220058, 'PALOMARES', 'RAYMOND', 'R.', NULL),
(20220059, 'DANTES', 'JOSHUA GABRIEL', 'P.', NULL),
(20220060, 'ACEDO', 'MARK RELAN GERCEE', NULL, NULL),
(20220061, 'SALUPADO', 'SEIFFER CHARLES', 'S.', NULL),
(20220078, 'LONOZA', 'JOSHUA', 'O.', NULL),
(20220081, 'SERRANO', 'CHRISTIAN', 'P.', NULL),
(20220082, 'BERNARDO', 'JOHN MICHAEL', 'D.', NULL),
(20220097, 'LISTANGCO', 'MARK LAURENZ', 'R.', NULL),
(20220119, 'VILLANUEVA', 'JASPER', 'P.', NULL),
(20220185, 'ORIAS', 'FRANXINE', 'C.', NULL),
(20220255, 'CAPALAC', 'GARVY REN', 'V.', NULL),
(20220256, 'FUNGO', 'GIAN HIGINO', 'O.', NULL),
(20220260, 'ESTREMADURA', 'RASHLY', 'R.', NULL),
(20220262, 'VALENCIA', 'RONAN RENZ', 'T.', NULL),
(20220287, 'BUA', 'PAUL VINCENT', 'G.', NULL),
(20220302, 'CARATAO', 'JACELYN', 'A.', NULL),
(20220313, 'NAMIAS', 'JHON KENETH RYAN', 'B.', NULL),
(20220336, 'CANO', 'KURT DANIEL', 'S.', NULL),
(20220367, 'SANTOS', 'ALYSSA', 'I.', NULL),
(20220377, 'CABUDSAN', 'YVEZ LAWRENCE', 'M.', NULL),
(20220404, 'AYA-AY', 'LYNIEL CRIS', 'S.', NULL),
(20220456, 'CRISTOBAL', 'GENREY', 'O.', NULL),
(20220457, 'SUSTIGUER', 'RODEL', 'DV.', 'JR'),
(20220464, 'MIRANDA', 'KARL MATHEW', 'L.', NULL),
(20220471, 'JULIANE', 'CARL JAMES', 'A.', NULL),
(20220475, 'UY', 'GWYNETH', 'F.', NULL),
(20220497, 'PABUA', 'EMANNUEL', 'L.', NULL),
(20220507, 'MONTE', 'MARTIN LORENCE', 'S.', NULL),
(20220527, 'LUGTU', 'BEA ALYSSA', 'G.', NULL),
(20220571, 'RAMBOYONG', 'JHEDIAEL CALVIN', 'T.', NULL),
(20220583, 'PERALTA', 'JUSTINE BRYAN', 'M.', NULL),
(20220629, 'LLANES', 'KEVIN', 'A.', NULL),
(20220657, 'DALUGDOG', 'ERVHYNE', 'R.', NULL),
(20220661, 'ERMINO', 'MARK RANNIELE', 'I.', NULL),
(20220666, 'CAMIQUE', 'JAMES IVAN', 'B.', NULL),
(20220679, 'CARAM', 'MIKE RUFINO', 'J.', 'II'),
(20220683, 'BENIG', 'EZEDREX JO', 'A.', NULL),
(20220715, 'TORRES', 'CEDRIC', NULL, NULL),
(20220815, 'ALCAIDE', 'KRISHA MAE', 'P.', NULL),
(20220844, 'PEREZ', 'CHRISTIAN', 'D.', NULL),
(20231583, 'GRIFALDEA', 'MARK JOHN', 'D.', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student_groups`
--

CREATE TABLE `student_groups` (
  `ID` int(11) NOT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `StudentID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_groups`
--

INSERT INTO `student_groups` (`ID`, `GroupID`, `StudentID`) VALUES
(1, 1, 20220471),
(2, 1, 20220097),
(3, 1, 20220629),
(4, 1, 20220078),
(5, 1, 20220527),
(6, 1, 20220464),
(7, 1, 20220256),
(8, 1, 20220260),
(9, 2, 10000003),
(10, 2, 10000004);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `SubjectID` int(11) NOT NULL,
  `SubjectName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectID`, `SubjectName`) VALUES
(1, 'CS101'),
(2, 'CS102'),
(3, 'CS103'),
(4, 'CCS104'),
(5, 'CCS105'),
(6, 'PR002'),
(7, 'PATHFIT3');

-- --------------------------------------------------------

--
-- Table structure for table `subject_groups`
--

CREATE TABLE `subject_groups` (
  `ID` int(11) NOT NULL,
  `GroupID` int(11) DEFAULT NULL,
  `SubjectID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject_groups`
--

INSERT INTO `subject_groups` (`ID`, `GroupID`, `SubjectID`) VALUES
(1, 1, 1),
(2, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `todo`
--

CREATE TABLE `todo` (
  `ToDoID` int(11) NOT NULL,
  `StudentID` int(11) DEFAULT NULL,
  `AudienceID` int(11) DEFAULT NULL,
  `DatePostID` int(11) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `RemindMe` datetime DEFAULT NULL,
  `Deadline` datetime DEFAULT NULL,
  `PriorityID` int(11) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `SubTitle` varchar(255) DEFAULT NULL,
  `Note` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `StudentID` (`Username`),
  ADD KEY `RoleID` (`RoleID`);

--
-- Indexes for table `announcement`
--
ALTER TABLE `announcement`
  ADD PRIMARY KEY (`AnnouncementID`),
  ADD KEY `PostID` (`PostID`) USING BTREE;

--
-- Indexes for table `audience`
--
ALTER TABLE `audience`
  ADD PRIMARY KEY (`AudienceID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`GroupID`);

--
-- Indexes for table `leader_groups`
--
ALTER TABLE `leader_groups`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GroupID` (`GroupID`),
  ADD KEY `StudentID` (`StudentID`) USING BTREE;

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`PostID`),
  ADD KEY `SubjectID` (`SubjectID`),
  ADD KEY `post_ibfk_2` (`StudentID`),
  ADD KEY `CategoryID` (`CategoryID`) USING BTREE,
  ADD KEY `AudienceID` (`AudienceID`),
  ADD KEY `PriorityID` (`PriorityID`);

--
-- Indexes for table `priority_level`
--
ALTER TABLE `priority_level`
  ADD PRIMARY KEY (`PriorityID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`RoleID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `student_groups`
--
ALTER TABLE `student_groups`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GroupID` (`GroupID`),
  ADD KEY `student_groups_ibfk_2` (`StudentID`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`SubjectID`);

--
-- Indexes for table `subject_groups`
--
ALTER TABLE `subject_groups`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `GroupID` (`GroupID`),
  ADD KEY `SubjectID` (`SubjectID`);

--
-- Indexes for table `todo`
--
ALTER TABLE `todo`
  ADD PRIMARY KEY (`ToDoID`),
  ADD KEY `CategoryID` (`CategoryID`),
  ADD KEY `PriorityID` (`PriorityID`),
  ADD KEY `AudienceID` (`AudienceID`),
  ADD KEY `StudentID` (`StudentID`),
  ADD KEY `todo_ibfk_6` (`DatePostID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `GroupID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `leader_groups`
--
ALTER TABLE `leader_groups`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `RoleID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `StudentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20231585;

--
-- AUTO_INCREMENT for table `student_groups`
--
ALTER TABLE `student_groups`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `subject_groups`
--
ALTER TABLE `subject_groups`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `student` (`StudentID`),
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`);

--
-- Constraints for table `announcement`
--
ALTER TABLE `announcement`
  ADD CONSTRAINT `announcement_ibfk_2` FOREIGN KEY (`PostID`) REFERENCES `post` (`PostID`);

--
-- Constraints for table `leader_groups`
--
ALTER TABLE `leader_groups`
  ADD CONSTRAINT `leader_groups_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`),
  ADD CONSTRAINT `leader_groups_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  ADD CONSTRAINT `post_ibfk_3` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`);

--
-- Constraints for table `student_groups`
--
ALTER TABLE `student_groups`
  ADD CONSTRAINT `student_groups_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`),
  ADD CONSTRAINT `student_groups_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`);

--
-- Constraints for table `subject_groups`
--
ALTER TABLE `subject_groups`
  ADD CONSTRAINT `subject_groups_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`GroupID`),
  ADD CONSTRAINT `subject_groups_ibfk_2` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`);

--
-- Constraints for table `todo`
--
ALTER TABLE `todo`
  ADD CONSTRAINT `todo_ibfk_1` FOREIGN KEY (`DatePostID`) REFERENCES `post` (`PostID`),
  ADD CONSTRAINT `todo_ibfk_2` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  ADD CONSTRAINT `todo_ibfk_3` FOREIGN KEY (`PriorityID`) REFERENCES `priority_level` (`PriorityID`),
  ADD CONSTRAINT `todo_ibfk_4` FOREIGN KEY (`AudienceID`) REFERENCES `audience` (`AudienceID`),
  ADD CONSTRAINT `todo_ibfk_5` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  ADD CONSTRAINT `todo_ibfk_6` FOREIGN KEY (`DatePostID`) REFERENCES `post` (`PostID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
