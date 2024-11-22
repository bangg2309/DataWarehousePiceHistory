/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : data_warehouse

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 11/11/2024 15:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for date_dim
-- ----------------------------
DROP TABLE IF EXISTS `date_dim`;
CREATE TABLE `date_dim`  (
  `id` int NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `year` int NULL DEFAULT NULL,
  `quarter` int NULL DEFAULT NULL,
  `month` int NULL DEFAULT NULL,
  `day_of_week` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of date_dim
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
