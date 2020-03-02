/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : d2p_pm

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-03-02 19:06:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pm_platform
-- ----------------------------
DROP TABLE IF EXISTS `pm_platform`;
CREATE TABLE `pm_platform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '平台名称',
  `code` varchar(255) DEFAULT NULL COMMENT '平台代码',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='平台';

-- ----------------------------
-- Records of pm_platform
-- ----------------------------
INSERT INTO `pm_platform` VALUES ('1', '系统管理平台', 'admin', '0', '2020-02-28 15:06:38', '2020-02-28 15:06:40');
INSERT INTO `pm_platform` VALUES ('2', '商家管理平台', 'seller', '0', '2020-02-28 15:06:55', '2020-02-28 15:06:58');

-- ----------------------------
-- Table structure for pm_resource
-- ----------------------------
DROP TABLE IF EXISTS `pm_resource`;
CREATE TABLE `pm_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_id` bigint(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of pm_resource
-- ----------------------------
INSERT INTO `pm_resource` VALUES ('1', '1', 'aside', '侧边菜单', null, null, null, null, '-10001', '1', null, '0', null, null);
INSERT INTO `pm_resource` VALUES ('2', '1', 'header', '顶部菜单', null, null, null, null, '-10000', '1', null, '0', null, null);
INSERT INTO `pm_resource` VALUES ('7', '1', 'permission', '权限管理', '', '', '', '', '1', '1', '1', '0', '2020-02-14 22:05:01', '2020-02-14 22:05:01');
INSERT INTO `pm_resource` VALUES ('8', '1', 'resource', '资源管理', 'permission:resource:view', '/permission/resource', '/permission/views/resource', 'address-book', '90', '1', '7', '0', '2020-02-14 22:13:00', '2020-02-14 22:13:00');
INSERT INTO `pm_resource` VALUES ('9', '1', 'role', '角色管理', 'permission:role:view', '/permission/role', '/permission/views/role', null, '100', '1', '7', '0', '2020-02-15 17:26:39', '2020-02-15 17:26:39');
INSERT INTO `pm_resource` VALUES ('10', '1', 'usersphere', '用户中心', null, null, null, null, '1', '1', '1', '0', '2020-02-15 18:46:44', '2020-02-15 18:46:44');
INSERT INTO `pm_resource` VALUES ('11', '1', 'user', '用户管理', 'permission:user:view', '/usersphere/user', '/usersphere/views/user', null, null, '1', '10', '0', '2020-02-15 18:47:32', '2020-02-15 18:47:32');
INSERT INTO `pm_resource` VALUES ('12', '1', 'addResource', '添加资源', 'permission:resource:add', null, null, null, '100', '2', '8', '0', '2020-02-15 18:53:48', '2020-02-15 18:53:48');
INSERT INTO `pm_resource` VALUES ('13', '1', 'editResource', '修改资源', 'permission:resource:edit', null, null, null, '100', '2', '8', '0', '2020-02-15 18:54:18', '2020-02-15 18:54:18');
INSERT INTO `pm_resource` VALUES ('14', '1', 'delResource', '删除资源', 'permission:resource:del', null, null, null, '100', '2', '8', '0', '2020-02-15 18:54:50', '2020-02-15 18:54:50');
INSERT INTO `pm_resource` VALUES ('15', '1', 'viewResource', '查看资源', 'permission:resource:view', null, null, 'eye', '90', '2', '8', '0', '2020-02-18 18:09:44', '2020-02-18 18:09:44');
INSERT INTO `pm_resource` VALUES ('17', '1', 'platform_manager', '平台管理', 'permission:platform:view', '/permission/platform', '/permission/views/platform', null, '50', '1', '7', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('18', '1', 'permission_platform_view', '平台查看', 'permission:platform:view', null, null, null, '100', '2', '17', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('19', '1', 'permission_platform_add', '平台新增', 'permission:platform:add', null, null, null, '100', '2', '17', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('20', '1', 'permission_platform_edit', '平台修改', 'permission:platform:edit', null, null, null, '100', '2', '17', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('21', '1', 'permission_platform_del', '平台删除', 'permission:platform:del', null, null, null, '100', '2', '17', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('22', '2', 'seller_index', '商家首页', 'seller:index:view', null, null, 'home', '100', '1', '0', '0', '2020-02-29 13:04:14', '2020-02-29 13:04:14');
INSERT INTO `pm_resource` VALUES ('23', '1', 'addRole', '添加角色', 'permission:role:add', '', '', '', '100', '2', '9', '0', '2020-02-15 18:53:48', '2020-02-15 18:53:48');
INSERT INTO `pm_resource` VALUES ('24', '1', 'editRole', '修改角色', 'permission:role:edit', '', '', '', '100', '2', '9', '0', '2020-02-15 18:54:18', '2020-02-15 18:54:18');
INSERT INTO `pm_resource` VALUES ('25', '1', 'delRole', '删除角色', 'permission:role:del', '', '', '', '100', '2', '9', '0', '2020-02-15 18:54:50', '2020-02-15 18:54:50');
INSERT INTO `pm_resource` VALUES ('26', '1', 'viewRole', '查看角色', 'permission:role:view', '', '', 'eye', '90', '2', '9', '0', '2020-02-18 18:09:44', '2020-02-18 18:09:44');
INSERT INTO `pm_resource` VALUES ('27', '1', 'roleAuthz', '角色授权', 'permission:role:authz', null, null, null, '100', '2', '9', '0', '2020-03-02 18:04:52', '2020-03-02 18:04:52');

-- ----------------------------
-- Table structure for pm_role
-- ----------------------------
DROP TABLE IF EXISTS `pm_role`;
CREATE TABLE `pm_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) NOT NULL COMMENT '角色代码',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父角色id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pm_role
-- ----------------------------
INSERT INTO `pm_role` VALUES ('1', '1', '管理员', 'admin', '0', '0', '2020-02-15 17:36:18', '2020-02-15 17:36:18');
INSERT INTO `pm_role` VALUES ('3', '1', '菜单管理员', 'menuManager', '0', '0', '2020-02-17 23:31:05', '2020-02-17 23:31:05');
INSERT INTO `pm_role` VALUES ('4', '2', '卖家管理员', 'seller', '0', '0', '2020-02-29 12:58:28', '2020-02-29 12:58:28');
INSERT INTO `pm_role` VALUES ('5', '1', '我只能查看', 'read_only', '0', '0', '2020-03-02 18:02:39', '2020-03-02 18:02:39');
INSERT INTO `pm_role` VALUES ('6', '1', '授权管理员', 'authz', '0', '0', '2020-03-02 18:16:47', '2020-03-02 18:16:47');

-- ----------------------------
-- Table structure for pm_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `pm_role_resource`;
CREATE TABLE `pm_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `platform_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pm_role_resource
-- ----------------------------
INSERT INTO `pm_role_resource` VALUES ('139', '2', '4', '22', '0', '2020-03-02 11:02:37', '2020-03-02 11:02:37');
INSERT INTO `pm_role_resource` VALUES ('189', '1', '1', '1', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('190', '1', '1', '7', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('191', '1', '1', '17', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('192', '1', '1', '18', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('193', '1', '1', '19', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('194', '1', '1', '20', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('195', '1', '1', '21', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('196', '1', '1', '8', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('197', '1', '1', '15', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('198', '1', '1', '12', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('199', '1', '1', '13', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('200', '1', '1', '14', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('201', '1', '1', '9', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('202', '1', '1', '26', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('203', '1', '1', '23', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('204', '1', '1', '24', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('205', '1', '1', '25', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('206', '1', '1', '27', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('207', '1', '1', '10', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('208', '1', '1', '11', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('209', '1', '1', '2', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('210', '1', '5', '18', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('211', '1', '5', '15', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('212', '1', '5', '26', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('213', '1', '5', '10', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('214', '1', '5', '11', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('215', '1', '5', '2', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('216', '1', '5', '1', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('217', '1', '5', '7', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('218', '1', '5', '17', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('219', '1', '5', '8', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('220', '1', '5', '9', '0', '2020-03-02 18:06:38', '2020-03-02 18:06:38');
INSERT INTO `pm_role_resource` VALUES ('221', '1', '3', '7', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('222', '1', '3', '17', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('223', '1', '3', '18', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('224', '1', '3', '19', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('225', '1', '3', '20', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('226', '1', '3', '21', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('227', '1', '3', '8', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('228', '1', '3', '15', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('229', '1', '3', '12', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('230', '1', '3', '13', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('231', '1', '3', '14', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('232', '1', '3', '9', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('233', '1', '3', '26', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('234', '1', '3', '23', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('235', '1', '3', '24', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('236', '1', '3', '25', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('237', '1', '3', '27', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('238', '1', '3', '2', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('239', '1', '3', '1', '0', '2020-03-02 18:16:15', '2020-03-02 18:16:15');
INSERT INTO `pm_role_resource` VALUES ('240', '1', '6', '18', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('241', '1', '6', '15', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('242', '1', '6', '26', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('243', '1', '6', '27', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('244', '1', '6', '10', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('245', '1', '6', '11', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('246', '1', '6', '2', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('247', '1', '6', '1', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('248', '1', '6', '7', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('249', '1', '6', '17', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('250', '1', '6', '8', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');
INSERT INTO `pm_role_resource` VALUES ('251', '1', '6', '9', '0', '2020-03-02 18:17:08', '2020-03-02 18:17:08');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色';

-- ----------------------------
-- Records of pm_user_role
-- ----------------------------
INSERT INTO `pm_user_role` VALUES ('10', '2', '3', '0', '2020-03-02 10:41:01', '2020-03-02 10:41:01');
INSERT INTO `pm_user_role` VALUES ('11', '2', '4', '0', '2020-03-02 10:41:01', '2020-03-02 10:41:01');
INSERT INTO `pm_user_role` VALUES ('28', '1', '1', '0', '2020-03-02 15:32:28', '2020-03-02 15:32:28');
INSERT INTO `pm_user_role` VALUES ('29', '1', '3', '0', '2020-03-02 15:32:28', '2020-03-02 15:32:28');
INSERT INTO `pm_user_role` VALUES ('30', '3', '5', '0', '2020-03-02 18:08:31', '2020-03-02 18:08:31');
INSERT INTO `pm_user_role` VALUES ('31', '4', '6', '0', '2020-03-02 18:19:54', '2020-03-02 18:19:54');

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
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$irJN827gfkpwdHJl9FbV4u71m9GN8b0qOsefpd1A22m2.PtQIvDaC', '管理员', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/2/16/30428108036833.jpg', '1', 'admin@qq.com', '86', '18603046467', '0', '2020-02-18 21:55:02', '2020-02-15 20:49:15');
INSERT INTO `sys_user` VALUES ('2', 'test', '$2a$10$tfeLZESgZYMTKcI5FEuij.pmDuDH1ThyqX/BIMW3/QQ46UeYapcBq', '菜单管理员', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/2/16/94104782868855.jpg', '1', 'test@qq.com', '86', '18603046467', '0', '2020-02-15 20:23:15', '2020-02-15 20:23:15');
INSERT INTO `sys_user` VALUES ('3', 'readonly', '$2a$10$VTVgONkb9qJcHISCkgZY9.Lj7NgUxxeXEkAeUzwmoMaiv3eI3lFVq', '只读', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/3/2/66510814578909.jpg', '2', 'readonly@qq.com', '86', '18603046468', '0', '2020-03-02 18:08:23', '2020-03-02 18:08:23');
INSERT INTO `sys_user` VALUES ('4', 'authz', '$2a$10$H.ZJj01TkbGNNkbBu7uJhuCQ1lwkgqiavb6ZeKg.2VqpyBMftnZLO', '授权管理员', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/3/2/53903789949792.jpg', '2', 'authz@qq.com', '86', '18603046469', '0', '2020-03-02 18:17:52', '2020-03-02 18:17:52');
