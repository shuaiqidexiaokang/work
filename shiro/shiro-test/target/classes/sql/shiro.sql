DROP DATABASE IF EXISTS `shiro`;
CREATE DATABASE shiro;
USE shiro;
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  `permission` varchar(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES ('1', 'admin', 'user:select');

-- ----------------------------
-- Table structure for test_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `test_roles_permissions`;
CREATE TABLE `test_roles_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  `permission` varchar(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of test_roles_permissions
-- ----------------------------
INSERT INTO `test_roles_permissions` VALUES ('1', 'admin', 'user:select');

-- ----------------------------
-- Table structure for test_users
-- ----------------------------
DROP TABLE IF EXISTS `test_users`;
CREATE TABLE `test_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of test_users
-- ----------------------------
INSERT INTO `test_users` VALUES ('1', 'Tony', '654321');

-- ----------------------------
-- Table structure for test_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `test_user_roles`;
CREATE TABLE `test_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of test_user_roles
-- ----------------------------
INSERT INTO `test_user_roles` VALUES ('1', 'Tony', 'admin');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'Mark', '283538989cef48f3d7d8a1c1bdf2008f');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', 'Mark', 'admin');
