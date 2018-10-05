/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `demo`;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfn44sntic2k93auag97juyij` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1),
	(1),
	(1),
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `t_products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_id` int(11) NOT NULL,
  `product_sub_category_id` int(11) NOT NULL,
  `product_product_sku` varchar(20) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_brand` varchar(30) DEFAULT NULL,
  `product_color` varchar(20) DEFAULT NULL,
  `product_size` varchar(50) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_image` varchar(100) DEFAULT NULL,
  `product_quantity` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int(11) DEFAULT '0',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int(11) DEFAULT '0',
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`),
  KEY `p_c_id` (`product_category_id`),
  KEY `FK_t_products_t_product_sub_category` (`product_sub_category_id`),
  CONSTRAINT `FK_t_products_t_product_sub_category` FOREIGN KEY (`product_sub_category_id`) REFERENCES `t_product_sub_category` (`sub_category_id`),
  CONSTRAINT `p_c_id` FOREIGN KEY (`product_category_id`) REFERENCES `t_product_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `t_products` DISABLE KEYS */;
INSERT INTO `t_products` (`product_id`, `product_category_id`, `product_sub_category_id`, `product_product_sku`, `product_name`, `product_brand`, `product_color`, `product_size`, `product_price`, `product_image`, `product_quantity`, `create_date`, `created_by`, `update_date`, `updated_by`, `is_active`) VALUES
	(1, 1, 1, 'FRD00F1', 'Sangria Collar 3/4Th Sleeves Embroidered Flared Anarkali', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(2, 2, 2, 'FRD00F3', 'Aurelia Navy Blue Printed Cotton Kurta', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(3, 2, 3, 'RO100', 'Biba White Printed Viscose Kurta', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(4, 3, 4, 'HND0E1', 'Aurelia Yellow Printed Cotton Kurta', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(5, 4, 5, 'MST0G1', 'Biba Blue Printed Kurta', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(6, 5, 6, 'NS0A1', 'Aurelia Navy Blue Printed Cotton Kurta', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0),
	(7, 3, 1, 'NS2S1', 'Sangria Round Neck 3/4Th Sleeves Anarkali', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0);
/*!40000 ALTER TABLE `t_products` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `t_product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_desc` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int(11) DEFAULT '0',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int(11) DEFAULT '0',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `UK_tod8mswk49xbq0klxxxgivgpg` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `t_product_category` DISABLE KEYS */;
INSERT INTO `t_product_category` (`category_id`, `category_name`, `category_desc`, `create_date`, `created_by`, `update_date`, `updated_by`) VALUES
	(1, 'Sofa', '', '2018-10-03 23:46:46', 1, '2018-10-03 18:16:58', 1),
	(2, 'Living Space Storage', NULL, '2018-10-03 18:55:29', 0, '2018-10-03 18:55:41', 1),
	(3, 'Study Tables', NULL, '2018-10-03 18:55:57', 0, '2018-10-03 18:56:03', 1),
	(4, 'Kids Beds', NULL, '2018-10-03 18:56:17', 0, '2018-10-03 18:56:21', 1),
	(5, 'Tables', NULL, '2018-10-03 18:56:37', 0, '2018-10-03 18:56:42', 1),
	(6, 'Dining Table', NULL, '2018-10-03 18:56:54', 0, '2018-10-03 18:56:58', 1);
/*!40000 ALTER TABLE `t_product_category` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `t_product_sub_category` (
  `sub_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `sub_category_name` varchar(255) NOT NULL,
  `sub_category_desc` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT '0',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(255) DEFAULT '0',
  PRIMARY KEY (`sub_category_id`),
  UNIQUE KEY `UK_oxo4jc3iqvnylalc0poxxkcbk` (`sub_category_name`),
  KEY `c_id` (`category_id`),
  CONSTRAINT `c_id` FOREIGN KEY (`category_id`) REFERENCES `t_product_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `t_product_sub_category` DISABLE KEYS */;
INSERT INTO `t_product_sub_category` (`sub_category_id`, `category_id`, `sub_category_name`, `sub_category_desc`, `image_url`, `create_date`, `created_by`, `update_date`, `updated_by`) VALUES
	(1, 1, 'Divans', NULL, 'dummy', '2018-10-03 18:58:12', '0', '2018-10-03 19:02:25', '0'),
	(2, 2, 'Pooja Units', NULL, 'dummy', '2018-10-03 18:58:25', '0', '2018-10-03 19:02:29', '0'),
	(3, 1, 'Study Tables', NULL, 'dummy', '2018-10-03 18:58:36', '0', '2018-10-03 19:02:31', '0'),
	(4, 3, 'Compact Crib', NULL, 'dummy', '2018-10-03 18:59:26', '0', '2018-10-03 19:02:44', '0'),
	(5, 4, 'Nested Tables', NULL, 'dummy', '2018-10-03 18:59:36', '0', '2018-10-03 19:02:45', '0'),
	(6, 5, 'Designer Dine Tables', NULL, 'dummy', '2018-10-03 18:59:48', '0', '2018-10-03 19:02:47', '0');
/*!40000 ALTER TABLE `t_product_sub_category` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
