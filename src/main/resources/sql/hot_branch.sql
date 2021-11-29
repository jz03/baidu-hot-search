/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 29/11/2021 16:58:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hot_branch
-- ----------------------------
DROP TABLE IF EXISTS `hot_branch`;
CREATE TABLE `hot_branch`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` double(15, 0) NOT NULL COMMENT '批次id',
  `index` int(11) NULL DEFAULT NULL COMMENT '排名',
  `hot_info_id` int(11) NULL DEFAULT NULL COMMENT '热点信息id',
  `hot_score` int(10) NULL DEFAULT NULL COMMENT '热点指数',
  `create_date` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_id`(`id`) USING BTREE,
  INDEX `index_branch_id`(`branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28741 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
