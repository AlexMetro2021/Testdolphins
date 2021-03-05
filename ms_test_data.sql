-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2021 at 03:34 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `omdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `ms_test_data`
--

CREATE TABLE `ms_test_data` (
  `id` int(6) NOT NULL,
  `native_name` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `year_made` year(4) NOT NULL,
  `title` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `execution_status` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `ms_test_data`
--

INSERT INTO `ms_test_data` (`id`, `native_name`, `year_made`, `title`, `execution_status`) VALUES
(1, 'Frozen', 2013, '\"Frozen Heart\"', 'Standby'),
(2, 'Frozen', 2013, '\"Do You Want to Build a Snowman?\"', 'Standby'),
(3, 'Frozen', 2013, '\"For the First Time in Forever\"', 'Standby'),
(4, 'Frozen', 2013, '\"Love Is an Open Door\"', 'Standby'),
(5, 'Frozen', 2013, '\"Let It Go\"', 'Standby'),
(6, 'Frozen', 2013, '\"Reindeer(s) Are Better Than People\"', 'Standby'),
(7, 'Frozen', 2013, '\"In Summer\"', 'Standby'),
(8, 'Frozen', 2013, '\"For the First Time in Forever (Reprise)\"', 'Standby'),
(9, 'Frozen', 2013, '\"Fixer Upper\"', 'Standby'),
(10, 'Frozen', 2013, '\"Let It Go\" (single version)', 'Standby'),
(11, 'Frozen', 2013, '\"Vuelie\" (featuring Cantus)', 'Standby'),
(12, 'Frozen', 2013, '\"Elsa and Anna\"', 'Standby'),
(13, 'Frozen', 2013, '\"The Trolls\"', 'Standby'),
(14, 'Frozen', 2013, '\"Coronation Day\"', 'Standby'),
(15, 'Frozen', 2013, '\"Heimr Àrnadalr\"', 'Standby'),
(16, 'Frozen', 2013, '\"Winter\'s Waltz\"', 'Standby'),
(17, 'Frozen', 2013, '\"Sorcery\"', 'Standby'),
(18, 'Frozen', 2013, '\"Royal Pursuit\"', 'Standby'),
(19, 'Frozen', 2013, '\"Onward and Upward\"', 'Standby'),
(20, 'Frozen', 2013, '\"Wolves\"', 'Standby'),
(21, 'Frozen', 2013, '\"The North Mountain\"', 'Standby'),
(22, 'Frozen', 2013, '\"We Were So Close\"', 'Standby'),
(23, 'Frozen', 2013, '\"Marshmallow Attack!\"', 'Standby'),
(24, 'Frozen', 2013, '\"Conceal, Don\'t Feel\"', 'Standby'),
(25, 'Frozen', 2013, '\"Only an Act of True Love\"', 'Standby'),
(26, 'Frozen', 2013, '\"Summit Siege\"', 'Standby'),
(27, 'Frozen', 2013, '\"Return to Arendelle\"', 'Standby'),
(28, 'Frozen', 2013, '\"Treason\"', 'Standby'),
(29, 'Frozen', 2013, '\"Some People Are Worth Melting For\"', 'Standby'),
(30, 'Frozen', 2013, '\"Whiteout\"', 'Standby'),
(31, 'Frozen', 2013, '\"The Great Thaw (Vuelie Reprise)\"', 'Standby'),
(32, 'Frozen', 2013, '\"Epilogue\"', 'Standby');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ms_test_data`
--
ALTER TABLE `ms_test_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ms_test_data`
--
ALTER TABLE `ms_test_data`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
