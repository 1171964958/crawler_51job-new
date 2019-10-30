/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-21 15:41:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `company_addr` varchar(255) DEFAULT NULL,
  `company_info` text,
  `job_name` varchar(255) DEFAULT NULL,
  `job_addr` varchar(255) DEFAULT NULL,
  `job_info` text,
  `salary_min` int(10) DEFAULT NULL,
  `salary_max` int(10) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sy_01` (`url`,`time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4219 DEFAULT CHARSET=utf8;
