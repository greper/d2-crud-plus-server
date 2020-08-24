UPDATE `pm_resource` SET  permission = 'usersphere:user:view' WHERE (`id`='11');

INSERT INTO `pm_resource` VALUES ('28', '1', 'usersphere_user_view', '用户查看', 'usersphere:user:view', null, null, null, '100', '2', '11', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('29', '1', 'usersphere_user_add', '用户新增', 'usersphere:user:add', null, null, null, '100', '2', '11', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('30', '1', 'usersphere_user_edit', '用户修改', 'usersphere:user:edit', null, null, null, '100', '2', '11', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');
INSERT INTO `pm_resource` VALUES ('31', '1', 'usersphere_user_del', '用户删除', 'usersphere:user:del', null, null, null, '100', '2', '11', '0', '2020-02-28 13:27:39', '2020-02-28 13:27:39');

INSERT INTO `pm_role_resource` VALUES ('252', '1', '1', '28', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('253', '1', '1', '29', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('254', '1', '1', '30', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
INSERT INTO `pm_role_resource` VALUES ('255', '1', '1', '31', '0', '2020-03-02 18:06:13', '2020-03-02 18:06:13');
