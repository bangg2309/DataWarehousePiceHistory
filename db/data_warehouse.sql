/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100428
 Source Host           : localhost:3306
 Source Schema         : data_warehouse

 Target Server Type    : MySQL
 Target Server Version : 100428
 File Encoding         : 65001

 Date: 04/11/2024 14:41:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dim_date
-- ----------------------------
DROP TABLE IF EXISTS `dim_date`;
CREATE TABLE `dim_date`  (
  `id` int NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `year` int NULL DEFAULT NULL,
  `quarter` int NULL DEFAULT NULL,
  `month` int NULL DEFAULT NULL,
  `day_of_week` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dim_date
-- ----------------------------

-- ----------------------------
-- Table structure for dim_product
-- ----------------------------
DROP TABLE IF EXISTS `dim_product`;
CREATE TABLE `dim_product`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(15, 4) NULL DEFAULT NULL,
  `discount` decimal(10, 2) NULL DEFAULT NULL,
  `price_sale` decimal(15, 4) NULL DEFAULT NULL,
  `effective_date` datetime NULL DEFAULT NULL,
  `expiry_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dim_product
-- ----------------------------

-- ----------------------------
-- Table structure for fact_price_history
-- ----------------------------
DROP TABLE IF EXISTS `fact_price_history`;
CREATE TABLE `fact_price_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `price` decimal(15, 2) NULL DEFAULT NULL,
  `effective_date` datetime NULL DEFAULT NULL,
  `expiry_date` datetime NULL DEFAULT NULL,
  `price_sale` decimal(15, 2) NULL DEFAULT NULL,
  `discount` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `fact_price_history_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `dim_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fact_price_history
-- ----------------------------

-- ----------------------------
-- Table structure for product_temp
-- ----------------------------
DROP TABLE IF EXISTS `product_temp`;
CREATE TABLE `product_temp`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(15, 4) NULL DEFAULT NULL,
  `discount` decimal(10, 2) NULL DEFAULT NULL,
  `price_sale` decimal(15, 4) NULL DEFAULT NULL,
  `created_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_temp
-- ----------------------------
INSERT INTO `product_temp` VALUES (1, 'Tủ lạnh Panasonic Inverter 326 lít NR-TL351GVKV', 'NR-TL351GVKV', 'https://cdn.tgdd.vn/Products/Images/1943/319851/TimerThumb/319851--1--600x600.jpg', 'Panasonic', 14.6900, 0.25, 10.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (2, 'Tủ lạnh Samsung Inverter 280 lít RB27N4010BU/SV', 'RB27N4010BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/225858/samsung-rb27n4010bu-sv-300x300.jpg', 'Samsung', 12.5900, 0.28, 8.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (3, 'Tủ lạnh Toshiba Inverter 460 lít Side By Side GR-RS600WI-PMV(37)-SG', 'GR-RS600WI-PMV(37)-SG', 'https://cdn.tgdd.vn/Products/Images/1943/303228/tu-lanh-toshiba-inverter-460-lit-gr-rs600wi-pmv-37-sg-16-600x600.jpg', 'Toshiba', 14.4900, 0.12, 12.6400, '0000-00-00');
INSERT INTO `product_temp` VALUES (4, 'Tủ lạnh Panasonic Inverter 366 lít NR-TL381GVKV', 'NR-TL381GVKV', 'https://cdn.tgdd.vn/Products/Images/1943/319852/TimerThumb/319852--1--600x600.jpg', 'Panasonic', 16.3900, 0.26, 11.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (5, 'Tủ lạnh Panasonic Inverter 170 lít NR-BA190PPVN', 'NR-BA190PPVN', 'https://cdn.tgdd.vn/Products/Images/1943/230195/panasonic-nr-ba190ppvn-300x300.jpg', 'Panasonic', 6.7900, 0.26, 4.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (6, 'Tủ lạnh LG Inverter 519 lít Side By Side GR-B256JDS', 'GR-B256JDS', 'https://cdn.tgdd.vn/Products/Images/1943/307871/lg-gr-b256jds-600x600.jpg', 'LG', 19.9900, 0.37, 12.4900, '0000-00-00');
INSERT INTO `product_temp` VALUES (7, 'Tủ lạnh Samsung Inverter 307 lít RB30N4190BU/SV', 'RB30N4190BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/240183/tu-lanh-samsung-inverter-307-lit-rb30n4190bu-sv-201221-103443-600x600.jpg', 'Samsung', 17.7900, 0.24, 13.4900, '0000-00-00');
INSERT INTO `product_temp` VALUES (8, 'Tủ lạnh Samsung Inverter 236 lít RT22M4032BU/SV', 'RT22M4032BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220323/samsung-rt22m4032bu-sv-300x300.jpg', 'Samsung', 9.5900, 0.26, 7.0900, '0000-00-00');
INSERT INTO `product_temp` VALUES (9, 'Tủ lạnh Panasonic Inverter 405 lít NR-TX461GPKV', 'NR-TX461GPKV', 'https://cdn.tgdd.vn/Products/Images/1943/249097/panasonic-inverter-405-lit-nr-tx461gpkv-300x300.jpg', 'Panasonic', 14.5900, 0.13, 12.6900, '0000-00-00');
INSERT INTO `product_temp` VALUES (10, 'Tủ lạnh Samsung Inverter 310 lít RB30N4010BU/SV', 'RB30N4010BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220319/samsung-rb30n4010bu-sv-300x300.jpg', 'Samsung', 16.0900, 0.31, 11.0900, '0000-00-00');
INSERT INTO `product_temp` VALUES (11, 'Tủ lạnh Aqua Inverter 646 lít AQR-S682XA(SLB)', 'AQR-S682XA(SLB)', 'https://cdn.tgdd.vn/Products/Images/1943/316167/tu-lanh-aqua-inverter-646-lit-aqr-s682xa-slb-171023-094656-600x600.jpg', 'Aqua', 0.0000, 0.00, 17.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (12, 'Tủ lạnh Samsung Inverter 276 lít RB27N4190BU/SV', 'RB27N4190BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/236993/samsung-rb27n4190bu-sv-1.-600x600.jpg', 'Samsung', 14.5900, 0.17, 11.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (13, 'Tủ lạnh Aqua Inverter 550 lít Side By Side AQR-S612XA(WCB)', 'AQR-S612XA(WCB)', 'https://cdn.tgdd.vn/Products/Images/1943/328725/tu-lanh-aqua-inverter-550-lit-side-by-side-aqr-s612xa-wcb-thumb-1-600x600.jpg', 'Aqua', 15.9900, 0.12, 13.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (14, 'Tủ lạnh Toshiba Inverter 180 lít GR-B22VU UKG', 'UKG', 'https://cdn.tgdd.vn/Products/Images/1943/202857/toshiba-gr-b22vu-ukg-220823-015828-600x600.jpg', 'Toshiba', 5.6900, 0.08, 5.1900, '0000-00-00');
INSERT INTO `product_temp` VALUES (15, 'Tủ lạnh Samsung Inverter 488 lít Multi Door RF48A4000B4/SV', 'RF48A4000B4/SV', 'https://cdn.tgdd.vn/Products/Images/1943/236999/samsung-rf48a4000b4-sv-1.-600x600.jpg', 'Samsung', 23.9900, 0.15, 20.3400, '0000-00-00');
INSERT INTO `product_temp` VALUES (16, 'Tủ lạnh Toshiba Inverter 474 lít Multi Door GR-RF611WI-PGV(22)-XK', 'GR-RF611WI-PGV(22)-XK', 'https://cdn.tgdd.vn/Products/Images/1943/324574/gr-rf611wi-pgv-22-xk-thumb-1-600x600.jpg', 'Toshiba', 17.9900, 0.05, 16.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (17, 'Tủ lạnh Samsung Inverter 307 lít RB30N4190BY/SV', 'RB30N4190BY/SV', 'https://cdn.tgdd.vn/Products/Images/1943/240188/samsung-inverter-307-lit-rb30n4190by-sv-1.-600x600.jpg', 'Samsung', 17.4900, 0.25, 12.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (18, 'Tủ lạnh Aqua Inverter 550 lít Side By Side AQR-S612XA(CBC)', 'AQR-S612XA(CBC)', 'https://cdn.tgdd.vn/Products/Images/1943/328724/tu-lanh-aqua-inverter-550-lit-side-by-side-aqr-s612xa-cbc-thumb-1-600x600.jpg', 'Aqua', 15.4900, 0.16, 12.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (19, 'Tủ lạnh Aqua Inverter 189 lít AQR-T220FA(FB)', 'AQR-T220FA(FB)', 'https://cdn.tgdd.vn/Products/Images/1943/304189/tu-lanh-aqua-aqr-t220fa-fb-5-600x600.jpg', 'Aqua', 5.4900, 0.09, 4.9900, '0000-00-00');
INSERT INTO `product_temp` VALUES (20, 'Tủ lạnh Samsung Inverter 236 lít RT22M4032BY/SV', 'RT22M4032BY/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220325/samsung-rt22m4032by-sv-300x300.jpg', 'Samsung', 9.3900, 0.24, 7.0900, '0000-00-00');

-- ----------------------------
-- Procedure structure for transfer_refrigerator_data
-- ----------------------------
DROP PROCEDURE IF EXISTS `transfer_refrigerator_data`;
delimiter ;;
CREATE PROCEDURE `transfer_refrigerator_data`()
BEGIN
    INSERT INTO data_warehouse.product_temp (name, product_code, image_url, brand, price, discount, price_sale, created_date)
    SELECT name, product_code, image_url, brand, price, discount, price_sale, created_date
    FROM staging.refrigerators;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
