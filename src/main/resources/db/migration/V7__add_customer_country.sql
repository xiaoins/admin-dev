-- 给 customer 表添加 country（国家）字段
ALTER TABLE `customer` ADD COLUMN `country` VARCHAR(50) NULL COMMENT '国家' AFTER `email`;

-- 给现有数据设置默认值（中国）
UPDATE `customer` SET `country` = '中国' WHERE `country` IS NULL;

