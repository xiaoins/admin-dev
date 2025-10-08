-- ============================================================
-- 电商后台管理系统 - 初始数据脚本
-- Version: V2
-- Description: 插入基础数据（角色、管理员账号、分类等）
-- ============================================================

-- 插入角色数据
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_code`, `description`, `status`) VALUES
(1, '超级管理员', 'ROLE_SUPER_ADMIN', '拥有所有权限', 1),
(2, '运营管理员', 'ROLE_OPERATOR', '负责商品、订单、营销管理', 1),
(3, '数据分析员', 'ROLE_ANALYST', '仅查看权限，可访问数据统计模块', 1);

-- 插入权限数据
INSERT INTO `sys_permission` (`role_id`, `permission_name`, `permission_code`, `resource_type`) VALUES
-- 超级管理员权限
(1, '用户管理', 'user:*', 'menu'),
(1, '角色管理', 'role:*', 'menu'),
(1, '权限管理', 'permission:*', 'menu'),
(1, '商品管理', 'product:*', 'menu'),
(1, '订单管理', 'order:*', 'menu'),
(1, '营销管理', 'marketing:*', 'menu'),
(1, '数据统计', 'stats:*', 'menu'),
(1, '系统管理', 'system:*', 'menu'),

-- 运营管理员权限
(2, '商品分类管理', 'category:*', 'menu'),
(2, '商品管理', 'product:*', 'menu'),
(2, '订单管理', 'order:*', 'menu'),
(2, '退款管理', 'refund:*', 'menu'),
(2, '营销管理', 'marketing:*', 'menu'),
(2, '概览统计', 'stats:view', 'menu'),

-- 数据分析员权限
(3, '数据统计', 'stats:*', 'menu');

-- 插入管理员账号（密码：admin123，使用BCrypt加密）
-- 密码字段使用 VARBINARY 类型，直接插入字符串即可
INSERT INTO `sys_user` (`username`, `password`, `real_name`, `phone`, `email`, `role_id`, `status`) VALUES
('admin', '$2a$10$GnBuN8qUNZR15oq0m0a18N-.pLs.x/3oeKo1oU1/QZbvuDWecnNyVvC', '系统管理员', '13800138000', 'admin@example.com', 1, 1),
('operator', '$2a$10$GnBuN8qUNZR15oq0m0a18N-.pLs.x/3oeKo1oU1/QZbvuDWecnNyVvC', '运营人员', '13800138001', 'operator@example.com', 2, 1),
('analyst', '$2a$10$GnBuN8qUNZR15oq0m0a18N-.pLs.x/3oeKo1oU1/QZbvuDWecnNyVvC', '数据分析员', '13800138002', 'analyst@example.com', 3, 1);

-- 插入商品分类数据
INSERT INTO `category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `level`, `status`) VALUES
-- 一级分类
(1, '数码电器', 0, 1, 1, 1),
(2, '服装鞋包', 0, 2, 1, 1),
(3, '食品饮料', 0, 3, 1, 1),
(4, '家居家纺', 0, 4, 1, 1),
(5, '美妆护肤', 0, 5, 1, 1),

-- 二级分类（数码电器）
(101, '手机通讯', 1, 1, 2, 1),
(102, '电脑办公', 1, 2, 2, 1),
(103, '家用电器', 1, 3, 2, 1),

-- 二级分类（服装鞋包）
(201, '男装', 2, 1, 2, 1),
(202, '女装', 2, 2, 2, 1),
(203, '运动鞋', 2, 3, 2, 1),

-- 二级分类（食品饮料）
(301, '休闲零食', 3, 1, 2, 1),
(302, '饮料冲调', 3, 2, 2, 1),
(303, '粮油调味', 3, 3, 2, 1);

