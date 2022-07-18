/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_root
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : dormitory_system

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 07/09/2021 22:04:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_num` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `password` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  PRIMARY KEY (`admin_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1001', '123456', '张三', '男', '13610011234');
INSERT INTO `admin` VALUES ('1002', '123456', '李四', '男', '13610022345');
INSERT INTO `admin` VALUES ('1003', '123456', '王五', '女', '13610033456');
INSERT INTO `admin` VALUES ('1004', '123456', '赵六', '男', '13610044567');

-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice`  (
  `stu_num` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细信息',
  INDEX `advice_FK_stunum`(`stu_num`) USING BTREE,
  CONSTRAINT `advice_FK_stunum` FOREIGN KEY (`stu_num`) REFERENCES `student` (`stu_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advice
-- ----------------------------
INSERT INTO `advice` VALUES ('20192019101', '建议全天24小时提供热水。');

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `floor_num` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼号',
  `layer` int NOT NULL COMMENT '楼层',
  `room_num` int NOT NULL COMMENT '宿舍号',
  `bed_total` int NOT NULL COMMENT '总床位数',
  `bed_surplus` int NOT NULL COMMENT '剩余床位数',
  `price` int NOT NULL COMMENT '单价',
  PRIMARY KEY (`floor_num`, `layer`, `room_num`) USING BTREE,
  INDEX `floor_num`(`floor_num`) USING BTREE,
  INDEX `layer`(`layer`) USING BTREE,
  INDEX `room_num`(`room_num`) USING BTREE,
  CONSTRAINT `dormitory_FK_floornum` FOREIGN KEY (`floor_num`) REFERENCES `floor` (`floor_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('C16', 2, 263, 4, 2, 150);
INSERT INTO `dormitory` VALUES ('C16', 2, 264, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C16', 2, 265, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C16', 3, 321, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C16', 3, 322, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C17', 1, 101, 4, 3, 150);
INSERT INTO `dormitory` VALUES ('C17', 1, 102, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C17', 1, 103, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C17', 2, 201, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C17', 3, 301, 4, 4, 150);
INSERT INTO `dormitory` VALUES ('C20', 5, 501, 2, 2, 450);
INSERT INTO `dormitory` VALUES ('C20', 5, 520, 2, 0, 450);

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor`  (
  `floor_num` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼号',
  `layer_amount` int NOT NULL COMMENT '层数',
  `room_amount` int NOT NULL COMMENT '房间数',
  `category` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `sex` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '居住性别',
  `admin_num` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宿管编号',
  PRIMARY KEY (`floor_num`) USING BTREE,
  UNIQUE INDEX `admin_num`(`admin_num`) USING BTREE,
  CONSTRAINT `floor_FK_adminnum` FOREIGN KEY (`admin_num`) REFERENCES `admin` (`admin_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of floor
-- ----------------------------
INSERT INTO `floor` VALUES ('C16', 7, 140, '普通', '男', '1001');
INSERT INTO `floor` VALUES ('C17', 7, 140, '普通', '女', '1003');
INSERT INTO `floor` VALUES ('C20', 5, 50, '豪华', '混合', '1002');

-- ----------------------------
-- Table structure for in_out
-- ----------------------------
DROP TABLE IF EXISTS `in_out`;
CREATE TABLE `in_out`  (
  `stu_num` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `floor_num` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼号',
  `category` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别（出/入）',
  `time` datetime NOT NULL COMMENT '时间',
  INDEX `inout_FK_stunum`(`stu_num`) USING BTREE,
  INDEX `inout_FK_floornum`(`floor_num`) USING BTREE,
  CONSTRAINT `inout_FK_stunum` FOREIGN KEY (`stu_num`) REFERENCES `student` (`stu_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `inout_FK_floornum` FOREIGN KEY (`floor_num`) REFERENCES `floor` (`floor_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of in_out
-- ----------------------------
INSERT INTO `in_out` VALUES ('20192019101', 'C16', '出', '2019-09-30 15:30:21');
INSERT INTO `in_out` VALUES ('20192019101', 'C16', '入', '2019-10-07 10:45:01');
INSERT INTO `in_out` VALUES ('20192019103', 'C17', '出', '2020-07-13 09:25:00');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `stu_num` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `floor_num` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼号',
  `layer` int NOT NULL COMMENT '楼层',
  `room_num` int NOT NULL COMMENT '宿舍号',
  `info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报修详细',
  `yes_no` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否处理（是/否）',
  INDEX `repair_FK_stunum`(`stu_num`) USING BTREE,
  INDEX `repair_FK_floornum`(`floor_num`) USING BTREE,
  INDEX `repair_FK_layer`(`layer`) USING BTREE,
  INDEX `repair_FK_dormitory`(`room_num`, `floor_num`, `layer`) USING BTREE,
  CONSTRAINT `repair_FK_stunum` FOREIGN KEY (`stu_num`) REFERENCES `student` (`stu_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `repair_FK_dormitory` FOREIGN KEY (`room_num`, `floor_num`, `layer`) REFERENCES `dormitory` (`room_num`, `floor_num`, `layer`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('20192019101', 'C16', 2, 263, '宿舍门锁损坏。', '是');
INSERT INTO `repair` VALUES ('20192019101', 'C16', 2, 263, '厕所堵塞。', '否');
INSERT INTO `repair` VALUES ('20192019103', 'C17', 1, 101, '空调损坏。', '是');
INSERT INTO `repair` VALUES ('20192019102', 'C16', 2, 263, '窗户破损。', '否');
INSERT INTO `repair` VALUES ('20202020321', 'C20', 5, 520, '水龙头损坏。', '否');

-- ----------------------------
-- Table structure for stayinfo
-- ----------------------------
DROP TABLE IF EXISTS `stayinfo`;
CREATE TABLE `stayinfo`  (
  `stu_num` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `floor_num` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼号',
  `layer` int NOT NULL COMMENT '楼层',
  `room_num` int NOT NULL COMMENT '宿舍号',
  `time` date NOT NULL COMMENT '入住时间',
  PRIMARY KEY (`stu_num`) USING BTREE,
  INDEX `stayinfo_FK_layer`(`layer`) USING BTREE,
  INDEX `stayinfo_FK_roomnum`(`room_num`) USING BTREE,
  INDEX `stayinfo_FK_dormitory`(`floor_num`, `layer`, `room_num`) USING BTREE,
  CONSTRAINT `stayinfo_FK_stunum` FOREIGN KEY (`stu_num`) REFERENCES `student` (`stu_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stayinfo_FK_dormitory` FOREIGN KEY (`floor_num`, `layer`, `room_num`) REFERENCES `dormitory` (`floor_num`, `layer`, `room_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stayinfo
-- ----------------------------
INSERT INTO `stayinfo` VALUES ('20192019101', 'C16', 2, 263, '2019-09-01');
INSERT INTO `stayinfo` VALUES ('20192019102', 'C16', 2, 263, '2019-09-01');
INSERT INTO `stayinfo` VALUES ('20192019103', 'C17', 1, 101, '2019-09-01');
INSERT INTO `stayinfo` VALUES ('20202020321', 'C20', 5, 520, '2020-09-01');
INSERT INTO `stayinfo` VALUES ('20202020322', 'C20', 5, 520, '2020-09-01');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_num` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `password` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birth` int NOT NULL COMMENT '出生日期（用于计算年龄）',
  `grade` int NOT NULL COMMENT '年级',
  `faculty` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '院系',
  `class` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `yes_no` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否入住（是/否）',
  PRIMARY KEY (`stu_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20192019101', '12345678', '小明', '男', 2000, 2019, '计算机工程学院', '网络工程2班', '13692871011', '是');
INSERT INTO `student` VALUES ('20192019102', '12345678', '小刚', '男', 2000, 2019, '计算机工程学院', '网络工程2班', '13692871022', '是');
INSERT INTO `student` VALUES ('20192019103', '12345678', '小红', '女', 2001, 2019, '计算机工程学院', '网络工程2班', '13692871033', '是');
INSERT INTO `student` VALUES ('20192019510', '12345678', '小橙', '女', 2000, 2019, '管理学院', '会计学1班', '13692875100', '否');
INSERT INTO `student` VALUES ('20192019511', '12345678', '小黄', '女', 2001, 2019, '管理学院', '会计学1班', '13692875111', '否');
INSERT INTO `student` VALUES ('20202020321', '12345678', '小绿', '男', 2002, 2020, '电气学院', '电气工程1班', '13692873211', '是');
INSERT INTO `student` VALUES ('20202020322', '12345678', '小蓝', '男', 2001, 2020, '电气学院', '电气工程1班', '13692873222', '是');
INSERT INTO `student` VALUES ('20212021303', '12345678', '小靛', '男', 2002, 2021, '计算机工程学院', '软件工程1班', '13692873033', '否');
INSERT INTO `student` VALUES ('20212021304', '12345678', '小紫', '女', 2003, 2021, '计算机工程学院', '软件工程1班', '13692873044', '否');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `test` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------

-- ----------------------------
-- View structure for adminview_advice
-- ----------------------------
DROP VIEW IF EXISTS `adminview_advice`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `adminview_advice` AS select `advice`.`stu_num` AS `stu_num`,`student`.`name` AS `name`,`advice`.`info` AS `info`,`floor`.`admin_num` AS `admin_num` from (((`advice` join `student` on((`advice`.`stu_num` = `student`.`stu_num`))) join `stayinfo` on((`advice`.`stu_num` = `stayinfo`.`stu_num`))) join `floor` on((`stayinfo`.`floor_num` = `floor`.`floor_num`)));

-- ----------------------------
-- View structure for adminview_student
-- ----------------------------
DROP VIEW IF EXISTS `adminview_student`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `adminview_student` AS select `student`.`stu_num` AS `stu_num`,`student`.`password` AS `password`,`student`.`name` AS `name`,`student`.`sex` AS `sex`,`student`.`birth` AS `birth`,`student`.`grade` AS `grade`,`student`.`faculty` AS `faculty`,`student`.`class` AS `class`,`student`.`phone` AS `phone`,`student`.`yes_no` AS `yes_no`,`stayinfo`.`floor_num` AS `floor_num` from (`student` join `stayinfo` on((`student`.`stu_num` = `stayinfo`.`stu_num`)));

-- ----------------------------
-- View structure for student_advice
-- ----------------------------
DROP VIEW IF EXISTS `student_advice`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_advice` AS select `advice`.`stu_num` AS `stu_num`,`student`.`name` AS `name`,`advice`.`info` AS `info` from (`student` join `advice` on((`student`.`stu_num` = `advice`.`stu_num`)));

-- ----------------------------
-- View structure for student_inout
-- ----------------------------
DROP VIEW IF EXISTS `student_inout`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_inout` AS select `in_out`.`stu_num` AS `stu_num`,`student`.`name` AS `name`,`in_out`.`floor_num` AS `floor_num`,`in_out`.`category` AS `category`,`in_out`.`time` AS `time` from (`student` join `in_out` on((`student`.`stu_num` = `in_out`.`stu_num`)));

-- ----------------------------
-- View structure for student_repair
-- ----------------------------
DROP VIEW IF EXISTS `student_repair`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_repair` AS select `repair`.`stu_num` AS `stu_num`,`student`.`name` AS `name`,`repair`.`floor_num` AS `floor_num`,`repair`.`layer` AS `layer`,`repair`.`room_num` AS `room_num`,`repair`.`info` AS `info`,`repair`.`yes_no` AS `yes_no` from (`student` join `repair` on((`student`.`stu_num` = `repair`.`stu_num`)));

-- ----------------------------
-- View structure for student_stayinfo
-- ----------------------------
DROP VIEW IF EXISTS `student_stayinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_stayinfo` AS select `student`.`stu_num` AS `stu_num`,`student`.`name` AS `name`,`stayinfo`.`floor_num` AS `floor_num`,`stayinfo`.`layer` AS `layer`,`stayinfo`.`room_num` AS `room_num`,`stayinfo`.`time` AS `time` from (`student` join `stayinfo` on((`student`.`stu_num` = `stayinfo`.`stu_num`)));

SET FOREIGN_KEY_CHECKS = 1;
