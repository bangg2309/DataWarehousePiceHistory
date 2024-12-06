/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100428
 Source Host           : localhost:3306
 Source Schema         : control

 Target Server Type    : MySQL
 Target Server Version : 100428
 File Encoding         : 65001

 Date: 04/11/2024 14:41:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_config
-- ----------------------------
DROP TABLE IF EXISTS `file_config`;
CREATE TABLE `file_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `source_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `date_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_config
-- ----------------------------
INSERT INTO `file_config` VALUES (1, 'dienmayxanh', 'https://www.dienmayxanh.com/tu-lanh', 'D:\\DW\\data\\refrigerators_{date}.csv', 'MM-dd-yyyy');

-- ----------------------------
-- Table structure for file_logs
-- ----------------------------
DROP TABLE IF EXISTS `file_logs`;
CREATE TABLE `file_logs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `files_config_id` int NULL DEFAULT NULL,
  `file_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `extract_time` datetime NULL DEFAULT NULL,
  `total_record` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `files_config_id`(`files_config_id` ASC) USING BTREE,
  CONSTRAINT `file_logs_ibfk_1` FOREIGN KEY (`files_config_id`) REFERENCES `file_config` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_logs
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
