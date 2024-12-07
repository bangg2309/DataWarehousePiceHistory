/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : control

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 04/12/2024 10:57:20
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of file_config
-- ----------------------------
INSERT INTO `file_config` VALUES (1, 'dienmayxanh', 'https://www.dienmayxanh.com/tu-lanh', 'D:/NLU/HKI-Nam4/DW/data/refrigerators_{date}.csv', 'MM-dd-yyyy');

-- ----------------------------
-- Table structure for file_logs
-- ----------------------------
DROP TABLE IF EXISTS `file_logs`;
CREATE TABLE `file_logs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_config` int NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `extract_time` datetime NULL DEFAULT NULL,
  `total_records` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `files_config_id`(`id_config` ASC) USING BTREE,
  CONSTRAINT `file_logs_ibfk_1` FOREIGN KEY (`id_config`) REFERENCES `file_config` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of file_logs
-- ----------------------------
INSERT INTO `file_logs` VALUES (1, NULL, NULL, 'PS', '2024-11-12 20:23:07', 0);
INSERT INTO `file_logs` VALUES (3, 1, 'D:\\NLU\\HKI-Nam4\\DW\\data\\refrigerators_2024-11-16.csv', 'ER', '2024-11-16 11:46:16', 20);
INSERT INTO `file_logs` VALUES (8, 1, 'D:\\NLU\\HKI-Nam4\\DW\\data\\refrigerators_2024-11-21.csv', 'ER', '2024-11-21 14:50:23', 20);
INSERT INTO `file_logs` VALUES (10, 1, 'D:/NLU/HKI-Nam4/DW/data/refrigerators_2024-11-22.csv', 'TR', '2024-11-22 18:34:47', 20);
INSERT INTO `file_logs` VALUES (11, 1, 'D:/NLU/HKI-Nam4/DW/data/refrigerators_2024-11-23.csv', 'MR', '2024-11-23 00:06:56', 20);
INSERT INTO `file_logs` VALUES (12, 1, 'D:/NLU/HKI-Nam4/DW/data/refrigerators_2024-11-29.csv', 'MR', '2024-11-29 08:43:14', 20);
INSERT INTO `file_logs` VALUES (13, 1, 'D:/NLU/HKI-Nam4/DW/data/refrigerators_2024-12-02.csv', 'MR', '2024-12-02 19:52:27', 20);
INSERT INTO `file_logs` VALUES (14, 1, 'D:/NLU/HKI-Nam4/DW/data/refrigerators_2024-12-03.csv', 'MR', '2024-12-03 08:50:32', 20);

SET FOREIGN_KEY_CHECKS = 1;
