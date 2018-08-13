-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2018 at 06:15 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ganeshdarshan2018`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `ccategory_name` varchar(150) DEFAULT NULL,
  `ncategory_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ncategory_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ccategory_name`, `ncategory_id`) VALUES
('Sarvajanik', 1),
('Home', 2),
('Preparation', 3);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(250) DEFAULT NULL,
  `ccomment` text,
  `is_approved` bit(1) DEFAULT NULL,
  `dcurrent_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `nimg_id` int(11) NOT NULL AUTO_INCREMENT,
  `cimage_name` varchar(150) DEFAULT NULL,
  `caddress` varchar(150) DEFAULT NULL,
  `ccreated_by` varchar(150) DEFAULT NULL,
  `cshared_by` varchar(150) DEFAULT NULL,
  `cimg_caption` varchar(120) DEFAULT NULL,
  `ncategory_id` int(10) DEFAULT NULL,
  `ctitle` varchar(150) DEFAULT NULL,
  `narea_id` int(10) DEFAULT NULL,
  `ncity_id` int(10) DEFAULT '1',
  `cimg_path` varchar(256) DEFAULT NULL,
  `byear` int(1) NOT NULL DEFAULT '2018',
  PRIMARY KEY (`nimg_id`),
  KEY `ncategory_id` (`ncategory_id`),
  KEY `narea_id` (`narea_id`),
  KEY `ncity_id` (`ncity_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`nimg_id`, `cimage_name`, `caddress`, `ccreated_by`, `cshared_by`, `cimg_caption`, `ncategory_id`, `ctitle`, `narea_id`, `ncity_id`, `cimg_path`, `byear`) VALUES
(1, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(2, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(3, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(4, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(5, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(6, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(7, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(8, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(9, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(10, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(11, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(12, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(13, 'Ganapati', 'Vadagaon Belagavi', 'Navin', 'Navin Sutar', 'Mere Bappa', 2, 'Mere Bappa', 12, 1, 'http://bcdn.newshunt.com/cmd/resize/400x400_60/fetchdata13/images/6b/ee/12/6bee12ec2f5203397994dfaf4658bdf4.jpg', 2018),
(14, 'Ganapati', 'Shahapur', 'Nitin', 'Nitin Sutar', 'Moraya', 2, 'Moraya', 10, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(15, 'Ganapati Preparation', 'Gondhali Galli', 'J J Patil', 'Navin', 'Ganesha Festival Preparation at our place Ganesha Festival Preparation at our place Ganesha Festival Preparation at ourG', 3, 'Moraya', 9, 1, 'http://www.hdnicewallpapers.com/Walls/Big/God%20and%20Lord/Ganapati_Bappa_on_Ganesh_Chaturthi_Wallpaper.jpg', 2018),
(16, 'Ganapati Preparation', 'Shahapur', 'Nitin', 'Naveen Sutar', 'Ganesha Festival Preparation at our place Ganesha Festival Preparation at our place Ganesha Festival Preparation at ourG', 3, 'Moraya', 11, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ2gIKBDoLOFpjExZn4Q74sY-bDSeexOX-Hb-E3yrb1m_34N50HA', 2018),
(17, 'Ganapati Preparation', 'Shahapur', 'Nitin', 'Naveen Sutar', 'Ganesha Festival Preparation at our place Ganesha Festival Preparation at our place Ganesha Festival Preparation at ourG', 3, 'Moraya', 11, 1, 'https://comps.canstockphoto.com/ganapati-lord-ganesha-vector-clipart_csp52579509.jpg', 2018),
(18, 'Ganapati Preparation', 'Shahapur', 'Nitin', 'Naveen Sutar', 'ohjh', 3, 'Moraya', 11, 1, 'https://comps.canstockphoto.com/ganapati-lord-ganesha-vector-clipart_csp52579509.jpg', 2018),
(19, 'Ganapati Preparation', 'Shahapur', 'Nitin', 'Naveen Sutar', 'ohjh', 3, 'Moraya', 11, 1, 'https://comps.canstockphoto.com/ganapati-lord-ganesha-vector-clipart_csp52579509.jpg', 2018),
(20, 'Ganapati Preparation', 'Shahapur', 'Nitin', 'Naveen Sutar', 'ohjh', 3, 'Moraya', 11, 1, 'https://comps.canstockphoto.com/ganapati-lord-ganesha-vector-clipart_csp52579509.jpg', 2018),
(21, NULL, NULL, NULL, NULL, 'Ganesha Festival Preparation at our place Ganesha Festival Preparation at our place Ganesha Festival Preparation at ourG', 3, NULL, 11, 1, NULL, 2018);

-- --------------------------------------------------------

--
-- Table structure for table `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `nplace_id` int(11) NOT NULL AUTO_INCREMENT,
  `cplace_name` varchar(150) DEFAULT NULL,
  `nparent_id` int(10) DEFAULT NULL,
  `cplace_type` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`nplace_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`nplace_id`, `cplace_name`, `nparent_id`, `cplace_type`) VALUES
(1, 'Belgaum', 0, 'city'),
(2, 'Honaga', 1, 'area'),
(3, 'Kangrali', 1, 'area'),
(4, 'Nehru Nagar', 1, 'area'),
(5, 'Auto Nagar', 1, 'area'),
(6, 'Hindalga', 1, 'area'),
(7, 'Chennamma', 1, 'area'),
(8, 'Fort Road', 1, 'area'),
(9, 'Bogarves', 1, 'area'),
(10, 'Shahapur', 1, 'area'),
(11, 'Tilakwadi', 1, 'area'),
(12, 'Vadgaon', 1, 'area'),
(13, 'Angol', 1, 'area'),
(14, 'Khanapur', 1, 'area'),
(15, 'Camp', 1, 'area'),
(16, 'none', 1, 'area');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`ncategory_id`) REFERENCES `category` (`ncategory_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `images_ibfk_2` FOREIGN KEY (`narea_id`) REFERENCES `place` (`nplace_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `images_ibfk_3` FOREIGN KEY (`ncity_id`) REFERENCES `place` (`nplace_id`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
