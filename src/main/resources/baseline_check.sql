/*
 Navicat Premium Data Transfer

 Source Server         : charlesyang.info
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 82.156.7.201:3306
 Source Schema         : baseline_check

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 11/06/2022 23:10:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_baseline
-- ----------------------------
DROP TABLE IF EXISTS `account_baseline`;
CREATE TABLE `account_baseline`  (
  `caption` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mac` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `domain` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `passwordRequired` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`caption`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_baseline
-- ----------------------------
INSERT INTO `account_baseline` VALUES ('DESKTOP-FE7KNRO\\\\11036', '2c:ea:7f:fd:d9:7f', '', 'DESKTOP-FE7KNRO', '11036', 'TRUE', 'S-1-5-21-2326478685-1907645411-1776621248-1001', 'OK');
INSERT INTO `account_baseline` VALUES ('DESKTOP-FE7KNRO\\\\Administrator', '2c:ea:7f:fd:d9:7f', '管理计算机(域)的内置帐户', 'DESKTOP-FE7KNRO', 'Administrator', 'TRUE', 'S-1-5-21-2326478685-1907645411-1776621248-500', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-FE7KNRO\\\\DefaultAccount', '2c:ea:7f:fd:d9:7f', '系统管理的用户帐户。', 'DESKTOP-FE7KNRO', 'DefaultAccount', 'FALSE', 'S-1-5-21-2326478685-1907645411-1776621248-503', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-FE7KNRO\\\\Guest', '2c:ea:7f:fd:d9:7f', '供来宾访问计算机或访问域的内置帐户', 'DESKTOP-FE7KNRO', 'Guest', 'FALSE', 'S-1-5-21-2326478685-1907645411-1776621248-501', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-FE7KNRO\\\\WDAGUtilityAccount', '2c:ea:7f:fd:d9:7f', '系统为WindowsDefender应用程序防护方案管理和使用的用户帐户。', 'DESKTOP-FE7KNRO', 'WDAGUtilityAccount', 'TRUE', 'S-1-5-21-2326478685-1907645411-1776621248-504', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-ROSGR3H\\\\Administrator', '90:78:41:d0:61:7e', '管理计算机(域)的内置帐户', 'DESKTOP-ROSGR3H', 'Administrator', 'TRUE', 'S-1-5-21-1840277091-3356531036-3940763011-500', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-ROSGR3H\\\\DefaultAccount', '90:78:41:d0:61:7e', '系统管理的用户帐户。', 'DESKTOP-ROSGR3H', 'DefaultAccount', 'FALSE', 'S-1-5-21-1840277091-3356531036-3940763011-503', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-ROSGR3H\\\\Guest', '90:78:41:d0:61:7e', '供来宾访问计算机或访问域的内置帐户', 'DESKTOP-ROSGR3H', 'Guest', 'FALSE', 'S-1-5-21-1840277091-3356531036-3940763011-501', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-ROSGR3H\\\\WDAGUtilityAccount', '90:78:41:d0:61:7e', '系统为WindowsDefender应用程序防护方案管理和使用的用户帐户。', 'DESKTOP-ROSGR3H', 'WDAGUtilityAccount', 'TRUE', 'S-1-5-21-1840277091-3356531036-3940763011-504', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-ROSGR3H\\\\憨猪', '90:78:41:d0:61:7e', NULL, 'DESKTOP-ROSGR3H', '憨猪', 'FALSE', 'S-1-5-21-1840277091-3356531036-3940763011-1001', 'OK');
INSERT INTO `account_baseline` VALUES ('DESKTOP-TJBUD85\\\\86186', 'f8:0d:ac:09:8c:51', '', 'DESKTOP-TJBUD85', '86186', 'TRUE', 'S-1-5-21-704209790-3325597098-3725864796-1001', 'OK');
INSERT INTO `account_baseline` VALUES ('DESKTOP-TJBUD85\\\\Administrator', 'f8:0d:ac:09:8c:51', '管理计算机(域)的内置帐户', 'DESKTOP-TJBUD85', 'Administrator', 'FALSE', 'S-1-5-21-704209790-3325597098-3725864796-500', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-TJBUD85\\\\DefaultAccount', 'f8:0d:ac:09:8c:51', '系统管理的用户帐户。', 'DESKTOP-TJBUD85', 'DefaultAccount', 'FALSE', 'S-1-5-21-704209790-3325597098-3725864796-503', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-TJBUD85\\\\Guest', 'f8:0d:ac:09:8c:51', '供来宾访问计算机或访问域的内置帐户', 'DESKTOP-TJBUD85', 'Guest', 'FALSE', 'S-1-5-21-704209790-3325597098-3725864796-501', 'Degraded');
INSERT INTO `account_baseline` VALUES ('DESKTOP-TJBUD85\\\\WDAGUtilityAccount', 'f8:0d:ac:09:8c:51', '系统为WindowsDefender应用程序防护方案管理和使用的用户帐户。', 'DESKTOP-TJBUD85', 'WDAGUtilityAccount', 'TRUE', 'S-1-5-21-704209790-3325597098-3725864796-504', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-GU9A069G\\\\Administrator', '34:cf:f6:c6:71:50', '管理计算机(域)的内置帐户', 'LAPTOP-GU9A069G', 'Administrator', 'TRUE', 'S-1-5-21-1775014870-2167040157-533444449-500', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-GU9A069G\\\\DefaultAccount', '34:cf:f6:c6:71:50', '系统管理的用户帐户。', 'LAPTOP-GU9A069G', 'DefaultAccount', 'FALSE', 'S-1-5-21-1775014870-2167040157-533444449-503', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-GU9A069G\\\\DPP', '34:cf:f6:c6:71:50', '', 'LAPTOP-GU9A069G', 'DPP', 'FALSE', 'S-1-5-21-1775014870-2167040157-533444449-1001', 'OK');
INSERT INTO `account_baseline` VALUES ('LAPTOP-GU9A069G\\\\Guest', '34:cf:f6:c6:71:50', '供来宾访问计算机或访问域的内置帐户', 'LAPTOP-GU9A069G', 'Guest', 'FALSE', 'S-1-5-21-1775014870-2167040157-533444449-501', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-GU9A069G\\\\WDAGUtilityAccount', '34:cf:f6:c6:71:50', '系统为WindowsDefender应用程序防护方案管理和使用的用户帐户。', 'LAPTOP-GU9A069G', 'WDAGUtilityAccount', 'TRUE', 'S-1-5-21-1775014870-2167040157-533444449-504', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-IMDN6FVM\\\\26603', '00:50:56:c0:00:08', '', 'LAPTOP-IMDN6FVM', '26603', 'TRUE', 'S-1-5-21-2521086035-2996767343-3965285143-1001', 'OK');
INSERT INTO `account_baseline` VALUES ('LAPTOP-IMDN6FVM\\\\Administrator', '00:50:56:c0:00:08', '管理计算机(域)的内置帐户', 'LAPTOP-IMDN6FVM', 'Administrator', 'TRUE', 'S-1-5-21-2521086035-2996767343-3965285143-500', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-IMDN6FVM\\\\DefaultAccount', '00:50:56:c0:00:08', '系统管理的用户帐户。', 'LAPTOP-IMDN6FVM', 'DefaultAccount', 'FALSE', 'S-1-5-21-2521086035-2996767343-3965285143-503', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-IMDN6FVM\\\\Guest', '00:50:56:c0:00:08', '供来宾访问计算机或访问域的内置帐户', 'LAPTOP-IMDN6FVM', 'Guest', 'FALSE', 'S-1-5-21-2521086035-2996767343-3965285143-501', 'Degraded');
INSERT INTO `account_baseline` VALUES ('LAPTOP-IMDN6FVM\\\\WDAGUtilityAccount', '00:50:56:c0:00:08', '系统为WindowsDefender应用程序防护方案管理和使用的用户帐户。', 'LAPTOP-IMDN6FVM', 'WDAGUtilityAccount', 'TRUE', 'S-1-5-21-2521086035-2996767343-3965285143-504', 'Degraded');

-- ----------------------------
-- Table structure for hardware_baseline
-- ----------------------------
DROP TABLE IF EXISTS `hardware_baseline`;
CREATE TABLE `hardware_baseline`  (
  `mac` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info_os` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_version` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_fullname` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_os_architecture` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_mu_languages` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_SerialNumber` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_cpu_count` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_mainboard` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_board_model` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_systemtype` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_physical_memory` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_cpu_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_clock_speed` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_number_core` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_data_width` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_socket_desigination` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_l2_cache` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info_l3_cache` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hardware_baseline
-- ----------------------------
INSERT INTO `hardware_baseline` VALUES ('90:78:41:d0:61:7e', 'Microsoft Windows 10 专业版', 'None', 'DESKTOP-ROSGR3H', '64 位', 'zh-CN', '00330-80000-00000-AA319', '1', 'Dell Inc.', 'Inspiron 7591', 'x64-based PC', '16G', 'Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz', '2400', '4', '64', 'U3E1', '1024', '8192');
INSERT INTO `hardware_baseline` VALUES ('2c:ea:7f:fd:d9:7f', 'Microsoft Windows 10 家庭中文版', 'None', 'DESKTOP-FE7KNRO', '64 位', 'zh-CN', '00326-40000-00000-AAOEM', '1', 'Dell Inc.', 'G3 3590', 'x64-based PC', '20G', 'Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz', '2592', '6', '64', 'U3E1', '1536', '12288');
INSERT INTO `hardware_baseline` VALUES ('00:50:56:c0:00:08', 'Microsoft Windows 11 家庭中文版', 'None', 'LAPTOP-IMDN6FVM', '64 位', 'zh-CN', '00342-35490-55245-AAOEM', '1', 'LENOVO', '81T0', 'x64-based PC', '16G', 'Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz', '2400', '4', '64', 'U3E1', '1024', '8192');
INSERT INTO `hardware_baseline` VALUES ('34:cf:f6:c6:71:50', 'Microsoft Windows 10 家庭中文版', 'None', 'LAPTOP-GU9A069G', '64 位', 'zh-CN', '00342-35900-76786-AAOEM', '1', 'LENOVO', '20UF0008CD', 'x64-based PC', '16G', 'AMD Ryzen 5 PRO 4650U with Radeon Graphics', '2100', '6', '64', 'FP6', '3072', '8192');
INSERT INTO `hardware_baseline` VALUES ('f8:0d:ac:09:8c:51', 'Microsoft Windows 10 家庭中文版', 'None', 'DESKTOP-TJBUD85', '64 位', 'zh-CN', '00342-36015-89401-AAOEM', '1', 'HP', 'HP ProBook 450 G8 Notebook PC', 'x64-based PC', '16G', '11th Gen Intel(R) Core(TM) i5-1135G7 @ 2.40GHz', '2419', '4', '64', 'U3E1', '5120', '8192');

-- ----------------------------
-- Table structure for system_baseline
-- ----------------------------
DROP TABLE IF EXISTS `system_baseline`;
CREATE TABLE `system_baseline`  (
  `mac` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OSCaption` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `OSVersion` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DiskCaption` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `InterfaceType` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NetworkCaption` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IPAddress` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MACAddress` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UpdateHotFixID` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `InstalledOn` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_column` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`mac`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_baseline
-- ----------------------------
INSERT INTO `system_baseline` VALUES ('00:50:56:c0:00:08', 'MicrosoftWindows11家庭中文版', '10.0.22621', 'WDCPCSN720SDAPNTW-512G-1101', 'SCSI', 'VMwareVirtualEthernetAdapterforVMnet1', '{192.168.213.1,fe80::a80c:30dc:9e1e:dc89}', '00:50:56:C0:00:01', 'KB5007297', '5/23/2022', NULL);
INSERT INTO `system_baseline` VALUES ('2c:ea:7f:fd:d9:7f', 'MicrosoftWindows10家庭中文版', '10.0.19044', 'KBG30ZMS512GNVMeTOSHIBA512GB', 'SCSI', 'Intel(R)Wireless-AC9560160MHz', '{10.134.40.74,fe80::60f5:f0dc:e894:6e33}', 'DC:FB:48:D0:39:87', 'KB5013624', '5/11/2022', NULL);
INSERT INTO `system_baseline` VALUES ('34:cf:f6:c6:71:50', 'MicrosoftWindows10家庭中文版', '10.0.19044', 'SAMSUNGMZVLB512HBJQ-000L7', 'SCSI', 'Intel(R)Wi-Fi6AX200160MHz', '{10.134.161.29,fe80::988e:9637:ea20:ecc6}', '34:CF:F6:C6:71:4F', 'KB5013887', '6/9/2022', NULL);
INSERT INTO `system_baseline` VALUES ('90:78:41:d0:61:7e', 'MicrosoftWindows10专业版', '10.0.19041', 'NVMePCSN520NVMeWD', 'SCSI', 'Intel(R)Wireless-AC9560160MHz', '10.133.60.226,fe80::1096:776b:2b05:33f3', '22:F7:BD:D2:8D:59', 'KB4569745', '8/8/2020', NULL);
INSERT INTO `system_baseline` VALUES ('f8:0d:ac:09:8c:51', 'MicrosoftWindows10家庭中文版', '10.0.19044', 'KBG40ZNV512GKIOXIA', 'SCSI', 'Intel(R)Wi-Fi6AX201160MHz', '{10.132.243.118,fe80::98b8:fed6:7043:366d}', '98:8D:46:B5:22:C8', 'KB5013624', '5/12/2022', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '7d7159a7eff8c160a1842db9b0301de3', 'admin');
INSERT INTO `user` VALUES (2, 'user', 'cb60490c9d6dbe232a8fbe153d87f2ed', NULL);
INSERT INTO `user` VALUES (4, 'Charles', '26ce0610ff02cfbc59d236aa64963a2a', NULL);
INSERT INTO `user` VALUES (5, 'qwer', 'c75fd8efbe49d25925e694c4d8e4bdae', NULL);
INSERT INTO `user` VALUES (6, 'asdf', '97af499380b912efd682254c81c93269', NULL);
INSERT INTO `user` VALUES (7, 'leex', '8d9576f0db443b4a7682990833bc062a', NULL);

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `id` int(11) NOT NULL,
  `username` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mac` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_profile_mac_uindex`(`mac`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES (1, 'admin', NULL, NULL);
INSERT INTO `user_profile` VALUES (2, 'user', '90:78:41:d0:61:7e', NULL);
INSERT INTO `user_profile` VALUES (4, 'Charles', '2C:EA:7F:FD:D9:7F', NULL);
INSERT INTO `user_profile` VALUES (5, 'qwer', '00:50:56:c0:00:08', NULL);
INSERT INTO `user_profile` VALUES (6, 'asdf', '34:cf:f6:c6:71:50', NULL);
INSERT INTO `user_profile` VALUES (7, 'leex', 'F8:0D:AC:09:8C:51', NULL);

SET FOREIGN_KEY_CHECKS = 1;
