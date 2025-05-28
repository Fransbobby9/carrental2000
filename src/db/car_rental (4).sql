-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2025 at 02:56 AM
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
-- Table structure for table `tbl_cars`
--

CREATE TABLE `tbl_cars` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(55) NOT NULL,
  `c_model` varchar(55) NOT NULL,
  `c_price` decimal(10,2) NOT NULL,
  `c_status` varchar(55) NOT NULL,
  `c_quantity` int(11) NOT NULL,
  `c_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_cars`
--

INSERT INTO `tbl_cars` (`c_id`, `c_name`, `c_model`, `c_price`, `c_status`, `c_quantity`, `c_image`) VALUES
(1, 'Car 1', 'Model A', 25000.00, 'Available', 10, ''),
(2, 'Car 2', 'Model B', 30000.00, 'Unavailable', 5, ''),
(3, 'Car 3', 'Model C', 45000.00, 'Available', 10, ''),
(4, 'Car 4', 'Model A', 30000.00, 'Unavailable', 4, ''),
(5, 'Car 5', 'Model B', 60000.00, 'Unavailable', 10, ''),
(6, 'Car 6', 'Model D', 80000.00, 'Unavailable', 10, ''),
(7, 'Car 7', 'Model B', 30000.00, 'Available', 5, ''),
(8, 'Car 8', 'Model C', 45000.00, 'Unavailable', 5, ''),
(9, 'Car 9', 'Model C', 45000.00, 'Unavailable', 10, ''),
(10, 'Car 10', 'Model B', 30000.00, 'Available', 5, ''),
(11, 'Car 11', 'Model C', 40000.00, 'Unavailable', 10, ''),
(12, 'Civic', 'Model B', 4000.00, 'Available', 1, '');

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
(55, 31, 'daniel123', '2025-04-05 12:51:42', 'Success - User Login', 'Inactive', '2025-05-27 13:56:26', ''),
(56, 30, 'park123', '2025-04-05 13:06:54', 'Success - Admin Login', 'Inactive', '2025-04-05 13:07:30', NULL),
(57, 30, 'park123', '2025-04-05 13:10:54', 'Success - Admin Login', 'Inactive', '2025-04-05 13:13:24', NULL),
(58, 30, 'park123', '2025-04-05 13:12:44', 'Success - Admin Login', 'Inactive', '2025-04-05 13:13:24', NULL),
(59, 30, 'park123', '2025-04-05 13:16:51', 'Success - Admin Login', 'Inactive', '2025-04-05 13:26:31', NULL),
(60, 30, 'park123', '2025-04-05 13:17:27', 'Admin', 'Inactive', '2025-04-05 13:26:31', 'park123 added a new car: Car 10'),
(61, 30, 'park123', '2025-04-05 13:25:31', 'Success - Admin Login', 'Inactive', '2025-04-05 13:26:31', NULL),
(63, 31, 'daniel123', '2025-05-27 13:56:06', 'Success - User Login', 'Inactive', '2025-05-27 13:56:26', NULL),
(65, 31, 'daniel123', '2025-05-27 14:03:05', 'Success - User Login', 'Inactive', '2025-05-27 14:03:34', NULL),
(66, 31, 'daniel123', '2025-05-27 14:15:16', 'Success - User Login', 'Inactive', '2025-05-27 14:16:06', NULL),
(67, 31, 'daniel123', '2025-05-27 14:19:14', 'Success - User Login', 'Inactive', '2025-05-27 14:21:20', NULL),
(68, 31, 'daniel123', '2025-05-27 14:20:41', 'Success - User Login', 'Inactive', '2025-05-27 14:21:20', NULL),
(69, 31, 'daniel123', '2025-05-27 14:21:08', 'Success - User Login', 'Inactive', '2025-05-27 14:21:20', NULL),
(70, 31, 'daniel123', '2025-05-27 14:24:10', 'Success - User Login', 'Inactive', '2025-05-27 14:24:34', NULL),
(71, 31, 'daniel123', '2025-05-27 23:36:38', 'Success - User Login', 'Inactive', '2025-05-27 23:39:11', NULL),
(72, 31, 'daniel123', '2025-05-27 23:41:43', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(73, 31, 'daniel123', '2025-05-27 23:45:05', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(74, 31, 'daniel123', '2025-05-27 23:47:50', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(75, 31, 'daniel123', '2025-05-27 23:50:46', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(76, 31, 'daniel123', '2025-05-28 00:05:10', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(77, 31, 'daniel123', '2025-05-28 00:06:27', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(78, 31, 'daniel123', '2025-05-28 00:07:36', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(79, 31, 'daniel123', '2025-05-28 00:09:04', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(80, 31, 'daniel123', '2025-05-28 00:11:28', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(81, 31, 'daniel123', '2025-05-28 00:12:49', 'Success - User Login', 'Inactive', '2025-05-28 00:13:24', NULL),
(82, 31, 'daniel123', '2025-05-28 00:14:17', 'Success - User Login', 'Inactive', '2025-05-28 00:39:16', NULL),
(83, 31, 'daniel123', '2025-05-28 00:17:34', 'Success - User Login', 'Inactive', '2025-05-28 00:39:16', NULL),
(84, 31, 'daniel123', '2025-05-28 00:22:21', 'Success - User Login', 'Inactive', '2025-05-28 00:39:16', NULL),
(85, 31, 'daniel123', '2025-05-28 00:34:26', 'Success - User Login', 'Inactive', '2025-05-28 00:39:16', NULL),
(86, 31, 'daniel123', '2025-05-28 00:38:43', 'Success - User Login', 'Inactive', '2025-05-28 00:39:16', NULL),
(87, 31, 'daniel123', '2025-05-28 00:41:23', 'Success - User Login', 'Inactive', '2025-05-28 00:44:21', NULL),
(88, 31, 'daniel123', '2025-05-28 00:43:15', 'Success - User Login', 'Inactive', '2025-05-28 00:44:21', NULL),
(89, 31, 'daniel123', '2025-05-28 00:45:43', 'Success - User Login', 'Inactive', '2025-05-28 00:56:00', NULL),
(90, 31, 'daniel123', '2025-05-28 00:50:30', 'Success - User Login', 'Inactive', '2025-05-28 00:56:00', NULL),
(91, 31, 'daniel123', '2025-05-28 00:53:48', 'Success - User Login', 'Inactive', '2025-05-28 00:56:00', NULL),
(92, 31, 'daniel123', '2025-05-28 00:55:20', 'Success - User Login', 'Inactive', '2025-05-28 00:56:00', NULL),
(93, 30, 'park123', '2025-05-28 00:56:07', 'Success - Admin Login', 'Inactive', '2025-05-28 00:56:38', NULL),
(94, 30, 'park123', '2025-05-28 00:56:34', 'Admin', 'Inactive', '2025-05-28 00:56:38', 'park123 added a new car: Civic');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `u_id` int(11) NOT NULL,
  `u_fname` varchar(50) NOT NULL,
  `u_lname` varchar(50) NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_username` varchar(255) NOT NULL,
  `u_password` varchar(50) NOT NULL,
  `u_type` varchar(50) NOT NULL,
  `u_status` varchar(50) NOT NULL,
  `security_question` varchar(255) NOT NULL,
  `security_answer` varchar(255) NOT NULL,
  `u_image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`u_id`, `u_fname`, `u_lname`, `u_email`, `u_username`, `u_password`, `u_type`, `u_status`, `security_question`, `security_answer`, `u_image`) VALUES
(12, 'jayem', 'rosalita', 'jayemrosalita@gmail.com', 'jayem123', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'User', 'Active', '', '', ''),
(14, 'kayeshea', 'basilan', 'kaye123@gmail.com', 'kaye1233', 'NBYWrmoA/JoDM/ch9Tgq8p41ekFyon8BzFcia+U+AQc=', 'Admin', 'Active', '', '', ''),
(21, 'ross', 'sabio', 'rosssabio@gmail.com', 'ross1234', 'NBYWrmoA/JoDM/ch9Tgq8p41ekFyon8BzFcia+U+AQc=', 'Admin', 'Active', '', '', ''),
(22, 'daniel', 'failadona', 'danielfailadona@gmail.com', 'daniel1234', 'NBYWrmoA/JoDM/ch9Tgq8p41ekFyon8BzFcia+U+AQc=', 'Admin', 'Active', '', '', ''),
(24, 'benjohns', 'parans', 'benjohn@gmail.com', 'paran1234', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Admin', 'Active', '', '', ''),
(25, 'sarno', 'mamen', 'sarnomamen@gmail.com', 'mamen123', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'User', 'Active', '', '', ''),
(28, 'han', 'seo', 'hanseo@gmail.com', 'hanseo123', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'User', 'Active', '', '', ''),
(29, 'beboy', 'padriga', 'beboypadriga@gmail.com', 'beboy123', '2KkosgQ9t340C1I1R78Wy0qkg/BkX+CikO0fIKq3Ylc=', 'User', 'Active', 'What\'s the name of your first pet?', 'browny', 'C:\\Users\\milan\\OneDrive\\Desktop\\Odin\\src\\images\\462574929_1530386207674650_925214417452153602_n.jpg'),
(30, 'park', 'hanseo', 'parkhanseo@gmailcom', 'park123', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'Admin', 'Active', 'What\'s the name of your first pet?', 'clarence', 'Null'),
(31, 'daneil', 'failadona', 'danielfailadno@gmail.com', 'daniel123', 'DR6kwlbNUKKnzL/SKz2ZWfb9ML2EC5/zx8Ze5OId8G0=', 'User', 'Active', 'What\'s your favorite food?', 'hotdog', 'C:\\Users\\milan\\OneDrive\\Desktop\\carrental2000\\src\\images\\cc.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `c_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `c_name` varchar(255) NOT NULL,
  `c_model` varchar(255) NOT NULL,
  `fee_amount` decimal(10,2) NOT NULL,
  `transaction_date` datetime NOT NULL,
  `License No.` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `u_fname` varchar(100) DEFAULT NULL,
  `u_lname` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`c_id`, `u_id`, `c_name`, `c_model`, `fee_amount`, `transaction_date`, `License No.`, `email`, `u_fname`, `u_lname`) VALUES
(9, 31, 'Car 9', 'Model C', 45000.00, '2025-05-28 08:55:53', '956745634534', 'danielfailadno@gmail.com', 'daneil', 'failadona');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_cars`
--
ALTER TABLE `tbl_cars`
  ADD PRIMARY KEY (`c_id`);

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
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`c_id`),
  ADD KEY `u_id` (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_cars`
--
ALTER TABLE `tbl_cars`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD CONSTRAINT `fk_log_user_id` FOREIGN KEY (`u_id`) REFERENCES `tbl_users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `tbl_users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
