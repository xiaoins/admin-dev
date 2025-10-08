-- ============================================================
-- 为sys_user表添加头像字段
-- Version: V8
-- Description: 添加avatar_url字段用于存储系统用户头像
-- ============================================================

ALTER TABLE `sys_user`
ADD COLUMN `avatar_url` VARCHAR(255) DEFAULT NULL COMMENT '头像URL' AFTER `email`;

