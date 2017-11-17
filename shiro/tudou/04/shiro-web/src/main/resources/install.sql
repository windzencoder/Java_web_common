CREATE SCHEMA `shiro_web` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `shiro_web`.`t_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `nickname` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `shiro_web`.`t_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `shiro_web`.`t_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `url` VARCHAR(255) NULL,
  `permission` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `shiro_web`.`t_role_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NULL,
  `res_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `shiro_web`.`t_user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `role_id` INT NULL,
  PRIMARY KEY (`id`));
