-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2024 at 07:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cardx`
--

-- --------------------------------------------------------

--
-- Table structure for table `carddesigns`
--

CREATE TABLE `carddesigns` (
  `design_id` int(10) NOT NULL,
  `designName` varchar(40) NOT NULL,
  `image` varchar(400) NOT NULL,
  `collection` varchar(40) NOT NULL,
  `dmyUserName` varchar(40) NOT NULL,
  `designAmount` int(40) NOT NULL,
  `designType` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `carddesigns`
--

INSERT INTO `carddesigns` (`design_id`, `designName`, `image`, `collection`, `dmyUserName`, `designAmount`, `designType`) VALUES
(1, 'Dark Moon', 'assets/card-design/weekly/card1.jpg', 'weekly', 'Mark Wood', 5, 'standard'),
(2, 'And Breathe', 'assets/card-design/weekly/card2.jpg', 'weekly', 'Mark zark', 11, 'advanced'),
(3, 'Live work create', 'assets/card-design/weekly/card3.jpg', 'weekly', 'Wood', 25, 'advanced'),
(4, 'who i am', 'assets/card-design/weekly/card4.jpg', 'weekly', 'Will', 40, 'premium'),
(5, 'dogs and line', 'assets/card-design/weekly/card5.jpg', 'weekly', 'Charles', 45, 'premium'),
(6, 'math shapes', 'assets/card-design/weekly/card6.jpg', 'weekly', 'steve jobs', 2, 'standard'),
(7, 'Ironman', 'assets/card-design/alltime/card1.jpg', 'all-time', 'Wood', 20, 'advanced'),
(8, 'Cat girl', 'assets/card-design/alltime/card2.jpg', 'all-time', 'zark', 3, 'standard'),
(9, 'I am thanos', 'assets/card-design/alltime/card3.jpg', 'all-time', 'Steve', 50, 'premium');

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `feature_id` int(11) NOT NULL,
  `icon` varchar(40) NOT NULL,
  `title` varchar(40) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`feature_id`, `icon`, `title`, `description`) VALUES
(1, 'design_services', 'Customizable Templates', 'Choose from a variety of professional and stylish templates to create a card that reflects your personality and brand.'),
(3, 'qr_code', 'QR Code Integration', 'Each CardX comes with a unique QR code that can be scanned by any smartphone.'),
(4, 'fast_forward', 'Instant Access', 'Your details are instantly accessible via a dedicated webpage, eliminating the need for physical cards.'),
(5, 'lock_outline', 'Secure and Private', 'Your information is secure with our state-of-the-art encryption and privacy controls.'),
(6, 'share', 'Easy Sharing', 'Share your digital card effortlessly via email, social media, or a direct link, ensuring your contacts always have your latest information.'),
(7, 'update', 'Real-Time Updates', 'Update your contact details in real-time. Any changes you make are instantly reflected on your card, keeping your network always up-to-date.'),
(8, 'analytics', 'Analytics and Insights', 'Track how often your card is viewed and shared. Gain valuable insights into how your digital card is performing and whos engaging with your content.');

-- --------------------------------------------------------

--
-- Table structure for table `help`
--

CREATE TABLE `help` (
  `help_id` int(40) NOT NULL,
  `userName` varchar(35) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNumber` varchar(40) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `question` text NOT NULL,
  `help_image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `helpmessage`
--

CREATE TABLE `helpmessage` (
  `help_ref_id` varchar(40) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `subject` varchar(400) DEFAULT NULL,
  `question` text DEFAULT NULL,
  `help_image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `user` text NOT NULL,
  `time` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carddesigns`
--
ALTER TABLE `carddesigns`
  ADD PRIMARY KEY (`design_id`);

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`feature_id`);

--
-- Indexes for table `help`
--
ALTER TABLE `help`
  ADD PRIMARY KEY (`help_id`);

--
-- Indexes for table `helpmessage`
--
ALTER TABLE `helpmessage`
  ADD PRIMARY KEY (`help_ref_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carddesigns`
--
ALTER TABLE `carddesigns`
  MODIFY `design_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `feature_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `help`
--
ALTER TABLE `help`
  MODIFY `help_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
