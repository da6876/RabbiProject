-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2022 at 05:59 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `soc_oaa`
--

-- --------------------------------------------------------

--
-- Table structure for table `oaa_location_table`
--

CREATE TABLE `oaa_location_table` (
  `location_code` int(15) NOT NULL,
  `location_name` varchar(50) DEFAULT NULL,
  `location_status` varchar(25) DEFAULT NULL,
  `create_data` varchar(25) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_data` varchar(25) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `oaa_location_table`
--

INSERT INTO `oaa_location_table` (`location_code`, `location_name`, `location_status`, `create_data`, `create_by`, `update_data`, `update_by`) VALUES
(1, 'Muscat', 'A', '04/01/2022', 'ADMIN', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oaa_service_table`
--

CREATE TABLE `oaa_service_table` (
  `service_id` int(15) DEFAULT NULL,
  `token_no` varchar(50) DEFAULT NULL,
  `user_info_id` int(15) DEFAULT NULL,
  `mobile_num` varchar(50) DEFAULT NULL,
  `service_type_id` varchar(50) DEFAULT NULL,
  `user_info_id_sp` varchar(50) DEFAULT NULL,
  `service_status` varchar(50) DEFAULT NULL,
  `remarks` varchar(150) DEFAULT NULL,
  `user_address` varchar(150) DEFAULT NULL,
  `user_latitude` varchar(100) DEFAULT NULL,
  `user_longitude` varchar(100) DEFAULT NULL,
  `create_data` varchar(25) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_data` varchar(25) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `location_code` int(15) DEFAULT NULL,
  `user_latitude_sp` varchar(100) DEFAULT NULL,
  `user_longitude_sp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `oaa_service_type`
--

CREATE TABLE `oaa_service_type` (
  `service_type_id` int(15) DEFAULT NULL,
  `service_type_name` varchar(50) DEFAULT NULL,
  `service_charge` varchar(50) DEFAULT NULL,
  `service_type_status` varchar(25) DEFAULT NULL,
  `create_data` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_data` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `oaa_user_info`
--

CREATE TABLE `oaa_user_info` (
  `user_info_id` int(15) NOT NULL,
  `location_code` int(15) DEFAULT NULL,
  `user_info_name` varchar(50) DEFAULT NULL,
  `user_fast_name` varchar(50) DEFAULT NULL,
  `user_last_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_refer_no` varchar(50) DEFAULT NULL,
  `user_point` int(15) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_latitude` varchar(100) DEFAULT NULL,
  `user_longitude` varchar(100) DEFAULT NULL,
  `user_status` varchar(25) DEFAULT NULL,
  `create_data` varchar(25) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_data` varchar(25) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `oaa_user_info`
--

INSERT INTO `oaa_user_info` (`user_info_id`, `location_code`, `user_info_name`, `user_fast_name`, `user_last_name`, `user_password`, `user_phone`, `user_email`, `user_refer_no`, `user_point`, `user_address`, `user_latitude`, `user_longitude`, `user_status`, `create_data`, `create_by`, `update_data`, `update_by`) VALUES
(5, 110021, 'Dhali Abir', 'Dhali', 'Abir', '81dc9bdb52d04dc20036dbd8313ed055', '01684924439', 'dhaliabir@gmail.com', '123222', 10, 'Dhaka,Bangladesh', '22.3300', '93.2222', 'A', '10/20/2022', 'DhaliAbir', NULL, NULL),
(7, 110021, 'IT Rabbi', 'IT', 'Rabbi', '81dc9bdb52d04dc20036dbd8313ed055', '01955375749', 'itrabbi@gmail.com', '123222', 10, 'Dhaka,Bangladesh', '22.3300', '93.2222', 'A', '10/20/2022', 'DhaliAbir', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oaa_user_info_sp`
--

CREATE TABLE `oaa_user_info_sp` (
  `user_info_id_sp` int(15) NOT NULL,
  `location_code` int(15) DEFAULT NULL,
  `user_info_name_sp` varchar(50) DEFAULT NULL,
  `user_fast_name_sp` varchar(50) DEFAULT NULL,
  `user_last_name_sp` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_phone_sp` varchar(50) DEFAULT NULL,
  `user_email_sp` varchar(50) DEFAULT NULL,
  `user_refer_no_sp` varchar(50) DEFAULT NULL,
  `user_point_sp` int(15) DEFAULT NULL,
  `user_address_sp` varchar(255) DEFAULT NULL,
  `user_latitude_sp` varchar(100) DEFAULT NULL,
  `user_longitude_sp` varchar(100) DEFAULT NULL,
  `user_status_sp` varchar(25) DEFAULT NULL,
  `create_data_sp` varchar(25) DEFAULT NULL,
  `create_by_sp` varchar(50) DEFAULT NULL,
  `update_data_sp` varchar(50) DEFAULT NULL,
  `update_by_sp` varchar(50) DEFAULT NULL,
  `sev_pro_name` varchar(50) DEFAULT NULL,
  `sev_delail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `oaa_user_info_sp`
--

INSERT INTO `oaa_user_info_sp` (`user_info_id_sp`, `location_code`, `user_info_name_sp`, `user_fast_name_sp`, `user_last_name_sp`, `user_password`, `user_phone_sp`, `user_email_sp`, `user_refer_no_sp`, `user_point_sp`, `user_address_sp`, `user_latitude_sp`, `user_longitude_sp`, `user_status_sp`, `create_data_sp`, `create_by_sp`, `update_data_sp`, `update_by_sp`, `sev_pro_name`, `sev_delail`) VALUES
(2, 110021, 'Dhali Abir', 'Dhali', 'Abir', '123456', '01955375749', 'dhaliabi1r@gmail.com', '10', 50, NULL, NULL, NULL, 'A', '21/02/2022', 'ADMIN', NULL, NULL, 'MA Service', 'Service Details');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `oaa_location_table`
--
ALTER TABLE `oaa_location_table`
  ADD PRIMARY KEY (`location_code`);

--
-- Indexes for table `oaa_user_info`
--
ALTER TABLE `oaa_user_info`
  ADD PRIMARY KEY (`user_info_id`),
  ADD UNIQUE KEY `user_email` (`user_email`);

--
-- Indexes for table `oaa_user_info_sp`
--
ALTER TABLE `oaa_user_info_sp`
  ADD PRIMARY KEY (`user_info_id_sp`),
  ADD UNIQUE KEY `user_email_sp` (`user_email_sp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `oaa_location_table`
--
ALTER TABLE `oaa_location_table`
  MODIFY `location_code` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `oaa_user_info`
--
ALTER TABLE `oaa_user_info`
  MODIFY `user_info_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `oaa_user_info_sp`
--
ALTER TABLE `oaa_user_info_sp`
  MODIFY `user_info_id_sp` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
