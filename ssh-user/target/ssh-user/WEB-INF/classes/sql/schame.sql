drop database if exists ssh-user;
create database ssh-user;
use ssh-user;

create table user(
  id int auto_increment comment 'id',
  usename varchar(255) comment '用户名',
  password varchar(255) comment '密码',
  address varchar(255) comment '地址',
  phone varchar(255) comment '电话',
  primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8 comment='用户表';