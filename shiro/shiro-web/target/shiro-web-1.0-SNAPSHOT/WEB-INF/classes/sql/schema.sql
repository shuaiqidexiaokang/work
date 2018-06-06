DROP DATABASE IF EXISTS `shiro`;
CREATE DATABASE shiro;
USE shiro;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';


#INSERT INTO users(username, password) VALUES('Mark', '123456');
INSERT INTO users(username, password)
VALUES ( 'Mark', '283538989cef48f3d7d8a1c1bdf2008f');

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

INSERT INTO user_roles(username, role_name)
VALUES
  ( 'Mark', 'admin'),
  ('Mark', 'user');

CREATE TABLE `roles_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名',
  `permission` varchar(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

INSERT INTO `roles_permissions`(role_name, permission) VALUES ('admin', 'user:select');
