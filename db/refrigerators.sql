/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : staging

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 22/11/2024 23:50:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for refrigerators
-- ----------------------------
DROP TABLE IF EXISTS `refrigerators`;
CREATE TABLE `refrigerators`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `SK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(15, 4) NULL DEFAULT NULL,
  `created_date` date NULL DEFAULT NULL,
  `date_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `SK`) USING BTREE,
  INDEX `date_id`(`date_id` ASC) USING BTREE,
  CONSTRAINT `refrigerators_ibfk_1` FOREIGN KEY (`date_id`) REFERENCES `date_dim` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 331 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of refrigerators
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
