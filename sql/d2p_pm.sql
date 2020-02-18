/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : d2p_pm

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-02-18 20:08:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pm_resource
-- ----------------------------
DROP TABLE IF EXISTS `pm_resource`;
CREATE TABLE `pm_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `title` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限代码',
  `path` varchar(255) DEFAULT NULL COMMENT '前端路径',
  `component` varchar(255) DEFAULT NULL COMMENT '前端组件路径',
  `icon` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT '100' COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '菜单类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of pm_resource
-- ----------------------------
INSERT INTO `pm_resource` VALUES ('1', 'aside', '侧边菜单', null, null, null, null, '100', '1', null, '0', null, null);
INSERT INTO `pm_resource` VALUES ('2', 'header', '顶部菜单', null, null, null, null, '100', '1', null, '0', null, null);
INSERT INTO `pm_resource` VALUES ('7', 'permission', '权限管理', '', '', '', '', '1', '1', '1', '0', '2020-02-14 22:05:01', '2020-02-14 22:05:01');
INSERT INTO `pm_resource` VALUES ('8', 'resource', '资源管理', 'permission:resource:view', '/permission/resource', '/permission/views/resource', 'address-book', '0', '1', '7', '0', '2020-02-14 22:13:00', '2020-02-14 22:13:00');
INSERT INTO `pm_resource` VALUES ('9', 'role', '角色管理', 'permission:role:view', '/permission/role', '/permission/views/role', null, '1', '1', '7', '0', '2020-02-15 17:26:39', '2020-02-15 17:26:39');
INSERT INTO `pm_resource` VALUES ('10', 'usersphere', '用户中心', null, null, null, null, '1', '1', '1', '0', '2020-02-15 18:46:44', '2020-02-15 18:46:44');
INSERT INTO `pm_resource` VALUES ('11', 'user', '用户管理', 'permission:user:view', '/usersphere/user', '/usersphere/views/user', null, null, '1', '10', '0', '2020-02-15 18:47:32', '2020-02-15 18:47:32');
INSERT INTO `pm_resource` VALUES ('12', 'addResource', '添加资源', 'permission:resource:add', null, null, null, '100', '2', '8', '0', '2020-02-15 18:53:48', '2020-02-15 18:53:48');
INSERT INTO `pm_resource` VALUES ('13', 'editResource', '修改资源', 'permission:resource:edit', null, null, null, '100', '2', '8', '0', '2020-02-15 18:54:18', '2020-02-15 18:54:18');
INSERT INTO `pm_resource` VALUES ('14', 'delResource', '删除资源', 'permission:resource:del', null, null, null, '100', '2', '8', '0', '2020-02-15 18:54:50', '2020-02-15 18:54:50');
INSERT INTO `pm_resource` VALUES ('15', 'addResource', '查看资源', 'permission:resource:view', null, null, 'eye', '90', '2', '8', '0', '2020-02-18 18:09:44', '2020-02-18 18:09:44');

-- ----------------------------
-- Table structure for pm_role
-- ----------------------------
DROP TABLE IF EXISTS `pm_role`;
CREATE TABLE `pm_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) NOT NULL COMMENT '角色代码',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父角色id',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pm_role
-- ----------------------------
INSERT INTO `pm_role` VALUES ('1', '管理员', 'admin', '0', '0', '2020-02-15 17:36:18', '2020-02-15 17:36:18');
INSERT INTO `pm_role` VALUES ('3', '菜单管理员', 'menuManager', null, '0', '2020-02-17 23:31:05', '2020-02-17 23:31:05');

-- ----------------------------
-- Table structure for pm_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `pm_role_resource`;
CREATE TABLE `pm_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pm_role_resource
-- ----------------------------
INSERT INTO `pm_role_resource` VALUES ('1', '1', '7', '1', '2020-02-15 18:36:22', '2020-02-15 18:36:25');
INSERT INTO `pm_role_resource` VALUES ('2', '1', '8', '1', '2020-02-15 18:36:53', '2020-02-15 18:36:55');
INSERT INTO `pm_role_resource` VALUES ('3', '1', '3', '1', '2020-02-15 18:37:02', '2020-02-15 18:37:04');
INSERT INTO `pm_role_resource` VALUES ('4', '1', '1', '0', '2020-02-15 19:41:47', '2020-02-15 19:41:47');
INSERT INTO `pm_role_resource` VALUES ('5', '1', '2', '0', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('6', '1', '12', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('7', '1', '13', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('8', '1', '14', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('9', '1', '3', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('10', '1', '10', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('11', '1', '11', '1', '2020-02-15 19:41:48', '2020-02-15 19:41:48');
INSERT INTO `pm_role_resource` VALUES ('12', '1', '1', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('13', '1', '2', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('14', '1', '12', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('15', '1', '13', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('16', '1', '14', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('17', '1', '3', '1', '2020-02-15 19:43:07', '2020-02-15 19:43:07');
INSERT INTO `pm_role_resource` VALUES ('18', '1', '1', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('19', '1', '2', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('20', '1', '12', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('21', '1', '13', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('22', '1', '14', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('23', '1', '3', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('24', '1', '10', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('25', '1', '11', '1', '2020-02-15 19:43:10', '2020-02-15 19:43:10');
INSERT INTO `pm_role_resource` VALUES ('26', '1', '7', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('27', '1', '8', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('28', '1', '12', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('29', '1', '13', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('30', '1', '14', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('31', '1', '9', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('32', '1', '10', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('33', '1', '11', '0', '2020-02-15 19:43:23', '2020-02-15 19:43:23');
INSERT INTO `pm_role_resource` VALUES ('34', '3', '7', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('35', '3', '8', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('36', '3', '12', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('37', '3', '13', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('38', '3', '14', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('39', '3', '9', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('40', '3', '1', '1', '2020-02-17 23:31:13', '2020-02-17 23:31:13');
INSERT INTO `pm_role_resource` VALUES ('41', '3', '7', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('42', '3', '8', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('43', '3', '12', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('44', '3', '13', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('45', '3', '14', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('46', '3', '9', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('47', '3', '2', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('48', '3', '1', '1', '2020-02-17 23:41:48', '2020-02-17 23:41:48');
INSERT INTO `pm_role_resource` VALUES ('49', '3', '7', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('50', '3', '8', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('51', '3', '12', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('52', '3', '13', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('53', '3', '14', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('54', '3', '9', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('55', '3', '2', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');
INSERT INTO `pm_role_resource` VALUES ('56', '3', '1', '0', '2020-02-17 23:42:09', '2020-02-17 23:42:09');

-- ----------------------------
-- Table structure for pm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pm_user_role`;
CREATE TABLE `pm_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色';

-- ----------------------------
-- Records of pm_user_role
-- ----------------------------
INSERT INTO `pm_user_role` VALUES ('4', '2', '3', '0', '2020-02-17 23:31:24', '2020-02-17 23:31:24');
INSERT INTO `pm_user_role` VALUES ('5', '1', '1', '0', '2020-02-18 15:27:30', '2020-02-18 15:27:30');
INSERT INTO `pm_user_role` VALUES ('6', '1', '3', '0', '2020-02-18 15:27:30', '2020-02-18 15:27:30');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `calling_code` varchar(10) DEFAULT NULL COMMENT '电话区号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `del_flag` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$CvMJvWsWWl9miFggFjRhOuj3nQeUuhoW48M.SeAFCfYsp0lSmOscW', '管理员', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/2/16/30428108036833.jpg', '1', '111@qq.com', '86', '18603046467', '0', null, '2020-02-15 20:49:15');
INSERT INTO `sys_user` VALUES ('2', 'test', '$2a$10$tfeLZESgZYMTKcI5FEuij.pmDuDH1ThyqX/BIMW3/QQ46UeYapcBq', 'test', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/2/16/94104782868855.jpg', '1', '2@qq.com', '86', '18603046467', '0', '2020-02-15 20:23:15', '2020-02-15 20:23:15');
