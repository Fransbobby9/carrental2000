-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2025 at 03:45 PM
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
-- Database: `car_rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE `tbl_log` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `u_type` varchar(50) NOT NULL,
  `log_status` enum('Pending','Active','Inactive','') NOT NULL,
  `logout_time` timestamp NULL DEFAULT NULL,
  `log_description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`log_id`, `u_id`, `u_username`, `login_time`, `u_type`, `log_status`, `logout_time`, `log_description`) VALUES
(1, 28, 'hanseo123', '2025-03-29 12:45:06', 'Success - User Login', 'Active', NULL, ''),
(2, 24, 'paran1234', '2025-03-29 12:45:19', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(3, 28, 'hanseo123', '2025-03-29 12:50:24', 'Success - User Login', 'Active', NULL, ''),
(4, 24, 'paran1234', '2025-03-29 12:50:35', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(5, 24, 'paran1234', '2025-03-29 12:59:13', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(6, 24, 'paran1234', '2025-03-29 12:59:58', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(7, 24, 'paran1234', '2025-03-29 13:00:25', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(8, 24, 'paran1234', '2025-03-29 13:05:20', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(9, 24, 'paran1234', '2025-03-30 10:49:16', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(10, 24, 'paran1234', '2025-03-30 10:54:26', 'Success - Admin Login', 'Inactive', '2025-03-30 10:54:31', ''),
(12, 29, 'beboy123', '2025-03-30 11:57:38', 'Success - User Login', 'Active', NULL, ''),
(13, 29, 'beboy123', '2025-03-30 12:48:21', 'Success - User Login', 'Active', NULL, ''),
(14, 29, 'beboy123', '2025-03-30 12:50:04', 'Success - User Login', 'Active', NULL, ''),
(15, 29, 'beboy123', '2025-03-30 12:53:56', 'Success - User Login', 'Active', NULL, ''),
(16, 29, 'beboy123', '2025-03-30 13:02:29', 'Success - User Login', 'Active', NULL, ''),
(17, 29, 'beboy123', '2025-03-30 13:02:39', 'Success - User Login', 'Active', NULL, ''),
(18, 29, 'beboy123', '2025-03-30 13:05:06', 'Success - User Login', 'Active', NULL, ''),
(19, 30, 'park123', '2025-04-02 06:43:11', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(23, 31, 'daniel123', '2025-04-02 06:55:07', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(25, 31, 'daniel123', '2025-04-02 06:59:05', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(26, 31, 'daniel123', '2025-04-02 07:01:18', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(27, 31, 'daniel123', '2025-04-02 07:02:25', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(30, 31, 'daniel123', '2025-04-05 11:34:57', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(31, 30, 'park123', '2025-04-05 11:35:15', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(34, 31, 'daniel123', '2025-04-05 11:35:44', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(35, 31, 'daniel123', '2025-04-05 11:36:54', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(36, 30, 'park123', '2025-04-05 11:47:39', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(37, 31, 'daniel123', '2025-04-05 11:48:28', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(38, 31, 'daniel123', '2025-04-05 11:51:51', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(39, 30, 'park123', '2025-04-05 11:52:04', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(40, 31, 'daniel123', '2025-04-05 11:52:51', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(41, 30, 'park123', '2025-04-05 11:54:35', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(42, 30, 'park123', '2025-04-05 11:57:25', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(43, 31, 'daniel123', '2025-04-05 11:58:12', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(44, 30, 'park123', '2025-04-05 12:11:41', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(45, 30, 'park123', '2025-04-05 12:13:09', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(47, 31, 'daniel123', '2025-04-05 12:21:20', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(48, 30, 'park123', '2025-04-05 12:21:34', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(49, 30, 'park123', '2025-04-05 12:22:32', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(50, 31, 'daniel123', '2025-04-05 12:27:10', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(51, 31, 'daniel123', '2025-04-05 12:27:50', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(52, 30, 'park123', '2025-04-05 12:30:00', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(53, 31, 'daniel123', '2025-04-05 12:48:38', 'Success - User Login', 'Inactive', '2025-04-05 12:51:43', ''),
(54, 30, 'park123', '2025-04-05 12:48:45', 'Success - Admin Login', 'Inactive', '2025-04-05 12:48:55', ''),
(55, 31, 'daniel123', '2025-04-05 12:51:42', 'Success - User Login', 'Active', NULL, ''),
(56, 30, 'park123', '2025-04-05 13:06:54', 'Success - Admin Login', 'Inactive', '2025-04-05 13:07:30', NULL),
(57, 30, 'park123', '2025-04-05 13:10:54', 'Success - Admin Login', 'Inactive', '2025-04-05 13:13:24', NULL),
(58, 30, 'park123', '2025-04-05 13:12:44', 'Success - Admin Login', 'Inactive', '2025-04-05 13:13:24', NULL),
(59, 30, 'park123', '2025-04-05 13:16:51', 'Success - Admin Login', 'Inactive', '2025-04-05 13:26:31', NULL),
(60, 30, 'park123', '2025-04-05 13:17:27', 'Admin', 'Inactive', '2025-04-05 13:26:31', 'park123 added a new car: Car 10'),
(61, 30, 'park123', '2025-04-05 13:25:31', 'Success - Admin Login', 'Inactive', '2025-04-05 13:26:31', NULL),
(62, 30, 'park123', '2025-04-09 12:37:36', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(63, 30, 'park123', '2025-04-09 12:39:26', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(64, 30, 'park123', '2025-04-09 12:42:59', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(65, 30, 'park123', '2025-04-09 12:44:02', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(66, 30, 'park123', '2025-04-09 13:00:14', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(67, 30, 'park123', '2025-04-09 13:02:06', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(68, 30, 'park123', '2025-04-09 13:11:05', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(69, 30, 'park123', '2025-04-09 13:12:25', 'Success - Admin Login', 'Inactive', '2025-04-09 13:13:27', NULL),
(70, 30, 'park123', '2025-04-09 13:23:42', 'Success - Admin Login', 'Inactive', '2025-04-09 13:51:48', NULL),
(71, 30, 'park123', '2025-04-09 13:24:33', 'Success - Admin Login', 'Inactive', '2025-04-09 13:51:48', NULL),
(72, 30, 'park123', '2025-04-09 13:31:31', 'Success - Admin Login', 'Inactive', '2025-04-09 13:51:48', NULL),
(73, 30, 'park123', '2025-04-09 13:40:43', 'Success - Admin Login', 'Inactive', '2025-04-09 13:51:48', NULL),
(74, 30, 'park123', '2025-04-09 13:41:17', 'Admin', 'Inactive', '2025-04-09 13:51:48', 'Admin Added Account: jay123'),
(75, 30, 'park123', '2025-04-09 13:50:25', 'Success - Admin Login', 'Inactive', '2025-04-09 13:51:48', NULL),
(76, 30, 'park123', '2025-04-09 13:51:20', 'Admin', 'Inactive', '2025-04-09 13:51:48', 'Admin Updated user account: jay123'),
(77, 30, 'park123', '2025-04-09 13:54:03', 'Success - Admin Login', 'Active', NULL, NULL),
(78, 30, 'park123', '2025-04-09 13:54:09', 'Admin', 'Active', NULL, 'Deleted user account with ID: 34'),
(79, 30, 'park123', '2025-04-09 13:54:17', 'Admin', 'Active', NULL, 'Deleted user account with ID: 35'),
(80, 30, 'park123', '2025-04-09 13:54:20', 'Admin', 'Active', NULL, 'Deleted user account with ID: 33'),
(81, 1, 'frans123', '2025-04-10 13:26:51', 'Success - User Login', 'Inactive', '2025-04-10 13:28:19', NULL),
(82, 1, 'frans123', '2025-04-10 13:27:58', 'Success - User Login', 'Inactive', '2025-04-10 13:28:19', NULL),
(83, 1, 'frans123', '2025-04-10 13:28:16', 'User Changed Their Details', 'Inactive', '2025-04-10 13:28:19', NULL),
(84, 1, 'frans123', '2025-04-10 13:30:48', 'Success - User Login', 'Inactive', '2025-04-10 13:31:21', NULL),
(85, 1, 'frans123', '2025-04-10 13:39:46', 'Success - User Login', 'Inactive', '2025-04-10 13:40:23', NULL),
(86, 1, 'frans123', '2025-04-10 13:39:58', 'User Changed Their Details', 'Inactive', '2025-04-10 13:40:23', NULL),
(87, 1, 'frans123', '2025-04-10 13:43:31', 'Success - User Login', 'Inactive', '2025-04-10 13:44:04', NULL),
(88, 1, 'frans123', '2025-04-10 13:43:43', 'User', 'Inactive', '2025-04-10 13:44:04', 'User Changed Their Details');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `u_id` int(11) NOT NULL,
  `u_fname` varchar(255) NOT NULL,
  `u_lname` varchar(255) NOT NULL,
  `u_username` varchar(255) NOT NULL,
  `u_email` varchar(255) NOT NULL,
  `u_type` varchar(255) NOT NULL,
  `u_status` varchar(255) NOT NULL,
  `u_password` varchar(255) NOT NULL,
  `u_image` varchar(255) NOT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`u_id`, `u_fname`, `u_lname`, `u_username`, `u_email`, `u_type`, `u_status`, `u_password`, `u_image`, `security_question`, `security_answer`) VALUES
(1, 'frans', 'ababa', 'frans123', 'fransababa@gmail.com', 'User', 'Active', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'src/images/cc.jpg', 'What\'s the name of your first pet?', 'kai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `fk_log_user_id` (`u_id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
