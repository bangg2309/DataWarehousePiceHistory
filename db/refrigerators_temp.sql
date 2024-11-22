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

 Date: 22/11/2024 23:50:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for refrigerators_temp
-- ----------------------------
DROP TABLE IF EXISTS `refrigerators_temp`;
CREATE TABLE `refrigerators_temp`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price_sale` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of refrigerators_temp
-- ----------------------------
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Toshiba Inverter 180 lít GR-B22VU UKG', 'UKG', 'https://cdn.tgdd.vn/Products/Images/1943/202857/toshiba-gr-b22vu-ukg-220823-015828-600x600.jpg', 'Toshiba', '5.690.000₫', '0.08', '5.190.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 280 lít RB27N4010BU/SV', 'RB27N4010BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/225858/samsung-rb27n4010bu-sv-300x300.jpg', 'Samsung', '10.890.000₫', '0.12', '9.490.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Toshiba Inverter 460 lít Side By Side GR-RS600WI-PMV(37)-SG', 'GR-RS600WI-PMV(37)-SG', 'https://cdn.tgdd.vn/Products/Images/1943/303228/tu-lanh-toshiba-inverter-460-lit-gr-rs600wi-pmv-37-sg-16-600x600.jpg', 'Toshiba', '14.490.000₫', '0.19', '11.690.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Panasonic Inverter 326 lít NR-TL351GVKV', 'NR-TL351GVKV', 'https://cdn.tgdd.vn/Products/Images/1943/319851/TimerThumb/319851--1--600x600.jpg', 'Panasonic', '14.690.000₫', '0.09', '13.290.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh LG Inverter 519 lít Side By Side GR-B256JDS', 'GR-B256JDS', 'https://cdn.tgdd.vn/Products/Images/1943/307871/lg-gr-b256jds-600x600.jpg', 'LG', '19.990.000₫', '0.37', '12.490.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 307 lít RB30N4190BU/SV', 'RB30N4190BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/240183/tu-lanh-samsung-inverter-307-lit-rb30n4190bu-sv-201221-103443-600x600.jpg', 'Samsung', '15.290.000₫', '0.11', '13.490.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 236 lít RT22M4032BU/SV', 'RT22M4032BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220323/samsung-rt22m4032bu-sv-300x300.jpg', 'Samsung', '8.990.000₫', '0.15', '7.590.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Panasonic Inverter 366 lít NR-TL381GVKV', 'NR-TL381GVKV', 'https://cdn.tgdd.vn/Products/Images/1943/319852/TimerThumb/319852--1--600x600.jpg', 'Panasonic', '16.390.000₫', '0.12', '14.290.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Panasonic Inverter 405 lít NR-TX461GPKV', 'NR-TX461GPKV', 'https://cdn.tgdd.vn/Products/Images/1943/249097/panasonic-inverter-405-lit-nr-tx461gpkv-300x300.jpg', 'Panasonic', '', '0.0', '14.590.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Aqua Inverter 646 lít Side By Side AQR-S682XA(SLB)', 'AQR-S682XA(SLB)', 'https://cdn.tgdd.vn/Products/Images/1943/316167/tu-lanh-aqua-inverter-646-lit-aqr-s682xa-slb-171023-094656-600x600.jpg', 'Aqua', '17.990.000₫', '0.06', '16.790.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 276 lít RB27N4190BU/SV', 'RB27N4190BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/236993/samsung-rb27n4190bu-sv-1.-600x600.jpg', 'Samsung', '13.690.000₫', '0.12', '11.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Aqua Inverter 550 lít Side By Side AQR-S612XA(WCB)', 'AQR-S612XA(WCB)', 'https://cdn.tgdd.vn/Products/Images/1943/328725/tu-lanh-aqua-inverter-550-lit-side-by-side-aqr-s612xa-wcb-thumb-1-600x600.jpg', 'Aqua', '15.990.000₫', '0.12', '13.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 488 lít Multi Door RF48A4000B4/SV', 'RF48A4000B4/SV', 'https://cdn.tgdd.vn/Products/Images/1943/236999/TimerThumb/samsung-rf48a4000b4-sv-(5).jpg', 'Samsung', '21.690.000₫', '0.0', '20.890.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Toshiba Inverter 474 lít Multi Door GR-RF611WI-PGV(22)-XK', 'GR-RF611WI-PGV(22)-XK', 'https://cdn.tgdd.vn/Products/Images/1943/324574/gr-rf611wi-pgv-22-xk-thumb-1-600x600.jpg', 'Toshiba', '17.990.000₫', '0.16', '14.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 307 lít RB30N4190BY/SV', 'RB30N4190BY/SV', 'https://cdn.tgdd.vn/Products/Images/1943/240188/samsung-inverter-307-lit-rb30n4190by-sv-1.-600x600.jpg', 'Samsung', '14.390.000₫', '0.09', '12.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Aqua Inverter 550 lít Side By Side AQR-S612XA(CBC)', 'AQR-S612XA(CBC)', 'https://cdn.tgdd.vn/Products/Images/1943/328724/tu-lanh-aqua-inverter-550-lit-side-by-side-aqr-s612xa-cbc-thumb-1-600x600.jpg', 'Aqua', '15.490.000₫', '0.13', '13.390.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 236 lít RT22M4032BY/SV', 'RT22M4032BY/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220325/samsung-rt22m4032by-sv-300x300.jpg', 'Samsung', '8.190.000₫', '0.09', '7.390.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Toshiba Inverter 233 lít GR-RT303WE-PMV(52)', 'GR-RT303WE-PMV(52)', 'https://cdn.tgdd.vn/Products/Images/1943/310651/tu-lanh-toshiba-inverter-233-lit-gr-rt303we-pmv-52-270723-113836-600x600.jpg', 'Toshiba', '6.590.000₫', '0.09', '5.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Aqua Inverter 189 lít AQR-T220FA(FB)', 'AQR-T220FA(FB)', 'https://cdn.tgdd.vn/Products/Images/1943/304189/tu-lanh-aqua-aqr-t220fa-fb-5-600x600.jpg', 'Aqua', '5.490.000₫', '0.09', '4.990.000₫', '2024-11-22');
INSERT INTO `refrigerators_temp` VALUES ('Tủ lạnh Samsung Inverter 256 lít RT25M4032BU/SV', 'RT25M4032BU/SV', 'https://cdn.tgdd.vn/Products/Images/1943/220326/samsung-rt25m4032bu-sv-300x300.jpg', 'Samsung', '9.190.000₫', '0.14', '7.890.000₫', '2024-11-22');

SET FOREIGN_KEY_CHECKS = 1;
