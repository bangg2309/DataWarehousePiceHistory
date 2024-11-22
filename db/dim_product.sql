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

 Date: 11/11/2024 15:43:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dim_product
-- ----------------------------
DROP TABLE IF EXISTS `dim_product`;
CREATE TABLE `dim_product`  (
  `SK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(15, 4) NULL DEFAULT NULL,
  `discount` decimal(10, 2) NULL DEFAULT NULL,
  `price_sale` decimal(15, 4) NULL DEFAULT NULL,
  `effective_date` datetime NULL DEFAULT NULL,
  `expiry_date` datetime NULL DEFAULT NULL,
  `date_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`SK`, `product_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dim_product
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
