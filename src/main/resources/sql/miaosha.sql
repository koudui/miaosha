/*
 Navicat Premium Data Transfer

 Source Server         : 慕课网
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 08/04/2019 16:43:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//商品名称',
  `price` double(10, 2) NOT NULL DEFAULT 0.00,
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES (1, '努比亚X', 3000.00, '最好看的双屏手机', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1644217443,2551725221&fm=27&gp=0.jpg', 41);
INSERT INTO `item_info` VALUES (2, '红米note7 pro', 1599.00, '小金刚手机', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4210282002,3443061240&fm=111&gp=0.jpg', 32);
INSERT INTO `item_info` VALUES (3, '华为P30 pro', 5999.00, '江湖人称“望远镜”', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=527175254,1269848454&fm=111&gp=0.jpg', 1);

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(10) NOT NULL DEFAULT 0,
  `item_id` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (1, 99, 1);
INSERT INTO `item_stock` VALUES (2, 200, 2);
INSERT INTO `item_stock` VALUES (3, 245, 3);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` double(10, 2) NOT NULL DEFAULT 0.00,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_price` double(10, 2) NOT NULL DEFAULT 0.00,
  `promo_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019040700000000', 10, 3, 0.00, 1, 0.00, 0);
INSERT INTO `order_info` VALUES ('2019040700000100', 10, 3, 0.00, 1, 0.00, 0);
INSERT INTO `order_info` VALUES ('2019040700000200', 10, 3, 5999.00, 1, 5999.00, 0);
INSERT INTO `order_info` VALUES ('2019040700000300', 10, 3, 5999.00, 1, 5999.00, 0);
INSERT INTO `order_info` VALUES ('2019040700000400', 10, 1, 2499.00, 1, 2499.00, 1);

-- ----------------------------
-- Table structure for promo_info
-- ----------------------------
DROP TABLE IF EXISTS `promo_info`;
CREATE TABLE `promo_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_date` datetime(0) NOT NULL,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_price` double(10, 2) NOT NULL DEFAULT 0.00,
  `end_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo_info
-- ----------------------------
INSERT INTO `promo_info` VALUES (1, '努比亚X 促销活动', '2019-04-07 21:30:00', 1, 2499.00, '2019-04-08 17:50:00');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 5, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` bit(1) NOT NULL DEFAULT b'1' COMMENT '//1表示男，2表示女',
  `age` int(3) NOT NULL DEFAULT 0,
  `telphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone_unique_index`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'zhangsan', b'1', 12, '123456789', 'wechat', '');
INSERT INTO `user_info` VALUES (7, '寇对', b'1', 21, '12345', 'byphone', '');
INSERT INTO `user_info` VALUES (9, '寇对', b'1', 20, '123456', 'byphone', '');
INSERT INTO `user_info` VALUES (10, 'admin', b'1', 20, '11111', 'byphone', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `encrpt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (1, '1', 'sdfdjddsfkdjfsdfj');
INSERT INTO `user_password` VALUES (5, '7', 'wgrU12/pd1mqJ6DJm/9nEA==');
INSERT INTO `user_password` VALUES (6, '9', '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO `user_password` VALUES (7, '10', 'ISMvKXpXpadDiUoOSoAfww==');

SET FOREIGN_KEY_CHECKS = 1;
