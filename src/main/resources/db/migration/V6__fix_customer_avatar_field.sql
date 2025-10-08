-- V6: 修复customer表的avatar字段名
-- 将 avatar 重命名为 avatar_url，以匹配Java实体类

ALTER TABLE `customer` CHANGE COLUMN `avatar` `avatar_url` VARCHAR(200) DEFAULT NULL COMMENT '头像URL';

