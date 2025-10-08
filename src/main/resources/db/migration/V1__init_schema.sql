-- ============================================================
-- 电商后台管理系统 - 数据库初始化脚本
-- Version: V1
-- Description: 创建所有表结构
-- ============================================================

-- 用户表
CREATE TABLE `sys_user` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARBINARY(100) NOT NULL COMMENT '密码（加密，使用VARBINARY避免字符集问题）',
    `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
    `role_id` BIGINT DEFAULT NULL COMMENT '角色ID',
    `status` TINYINT DEFAULT 1 COMMENT '状态（0禁用 1启用）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE `sys_role` (
    `role_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
    `status` TINYINT DEFAULT 1 COMMENT '状态（0禁用 1启用）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_role_name` (`role_name`),
    UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表
CREATE TABLE `sys_permission` (
    `permission_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `permission_name` VARCHAR(50) NOT NULL COMMENT '权限名称',
    `permission_code` VARCHAR(100) NOT NULL COMMENT '权限编码',
    `resource_type` VARCHAR(20) DEFAULT NULL COMMENT '资源类型（menu/button）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`permission_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 商品分类表
CREATE TABLE `category` (
    `category_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父级ID（0表示顶级分类）',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `level` TINYINT DEFAULT 1 COMMENT '层级',
    `status` TINYINT DEFAULT 1 COMMENT '状态（0禁用 1启用）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`category_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 商品表
CREATE TABLE `product` (
    `product_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `product_no` VARCHAR(50) NOT NULL COMMENT '商品编号',
    `product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
    `category_id` BIGINT NOT NULL COMMENT '分类ID',
    `brand` VARCHAR(50) DEFAULT NULL COMMENT '品牌',
    `main_image` VARCHAR(200) DEFAULT NULL COMMENT '主图URL',
    `images` TEXT DEFAULT NULL COMMENT '轮播图URL（JSON数组）',
    `description` TEXT DEFAULT NULL COMMENT '商品描述',
    `original_price` DECIMAL(10,2) DEFAULT 0.00 COMMENT '原价',
    `current_price` DECIMAL(10,2) NOT NULL COMMENT '现价',
    `stock` INT DEFAULT 0 COMMENT '库存',
    `sales` INT DEFAULT 0 COMMENT '销量',
    `status` TINYINT DEFAULT 2 COMMENT '状态（0下架 1上架 2草稿）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`product_id`),
    UNIQUE KEY `uk_product_no` (`product_no`),
    KEY `idx_category_status_time` (`category_id`, `status`, `create_time`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 订单表
CREATE TABLE `order` (
    `order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总额',
    `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',
    `freight` DECIMAL(10,2) DEFAULT 0.00 COMMENT '运费',
    `coupon_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠金额',
    `pay_type` TINYINT DEFAULT NULL COMMENT '支付方式（1微信 2支付宝）',
    `status` TINYINT DEFAULT 0 COMMENT '订单状态（0待付款 1待发货 2已发货 3已完成 4已取消）',
    `receiver_name` VARCHAR(50) DEFAULT NULL COMMENT '收货人',
    `receiver_phone` VARCHAR(20) DEFAULT NULL COMMENT '收货电话',
    `receiver_address` VARCHAR(200) DEFAULT NULL COMMENT '收货地址',
    `logistics_company` VARCHAR(50) DEFAULT NULL COMMENT '物流公司',
    `logistics_no` VARCHAR(50) DEFAULT NULL COMMENT '物流单号',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `pay_time` DATETIME DEFAULT NULL COMMENT '付款时间',
    `send_time` DATETIME DEFAULT NULL COMMENT '发货时间',
    `finish_time` DATETIME DEFAULT NULL COMMENT '完成时间',
    PRIMARY KEY (`order_id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_status_time` (`user_id`, `status`, `create_time`),
    KEY `idx_status_time` (`status`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单明细表
CREATE TABLE `order_item` (
    `item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '明细ID',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `product_id` BIGINT NOT NULL COMMENT '商品ID',
    `product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
    `product_image` VARCHAR(200) DEFAULT NULL COMMENT '商品图片',
    `product_price` DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    `quantity` INT NOT NULL COMMENT '购买数量',
    `total_price` DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`item_id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 优惠券表
CREATE TABLE `coupon` (
    `coupon_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '优惠券ID',
    `coupon_name` VARCHAR(50) NOT NULL COMMENT '优惠券名称',
    `coupon_type` TINYINT NOT NULL COMMENT '类型（1满减 2折扣 3无门槛）',
    `coupon_value` DECIMAL(10,2) NOT NULL COMMENT '优惠金额/折扣',
    `min_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '最低消费',
    `total_count` INT NOT NULL COMMENT '发放总量',
    `receive_count` INT DEFAULT 0 COMMENT '已领取数量',
    `use_count` INT DEFAULT 0 COMMENT '已使用数量',
    `limit_count` INT DEFAULT 1 COMMENT '每人限领',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME NOT NULL COMMENT '结束时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`coupon_id`),
    KEY `idx_type_time` (`coupon_type`, `start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

-- 用户优惠券表
CREATE TABLE `coupon_user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `coupon_id` BIGINT NOT NULL COMMENT '优惠券ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `order_id` BIGINT DEFAULT NULL COMMENT '订单ID',
    `status` TINYINT DEFAULT 0 COMMENT '状态（0未使用 1已使用 2已过期）',
    `receive_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
    `use_time` DATETIME DEFAULT NULL COMMENT '使用时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_status_coupon` (`user_id`, `status`, `coupon_id`),
    KEY `idx_coupon_id` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

-- 营销活动表
CREATE TABLE `activity` (
    `activity_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `activity_name` VARCHAR(100) NOT NULL COMMENT '活动名称',
    `activity_type` TINYINT NOT NULL COMMENT '活动类型（1限时折扣 2满减活动 3新人专享 4会员专享）',
    `activity_rule` TEXT DEFAULT NULL COMMENT '活动规则（JSON）',
    `status` TINYINT DEFAULT 0 COMMENT '状态（0未开始 1进行中 2已结束）',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME NOT NULL COMMENT '结束时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`activity_id`),
    KEY `idx_status_time` (`status`, `start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动表';

-- 活动商品表
CREATE TABLE `activity_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID',
    `product_id` BIGINT NOT NULL COMMENT '商品ID',
    `discount_price` DECIMAL(10,2) DEFAULT NULL COMMENT '活动价格',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动商品表';

-- 退款表
CREATE TABLE `refund` (
    `refund_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '退款ID',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `refund_no` VARCHAR(50) NOT NULL COMMENT '退款单号',
    `refund_type` TINYINT NOT NULL COMMENT '退款类型（1仅退款 2退货退款）',
    `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',
    `refund_reason` VARCHAR(200) DEFAULT NULL COMMENT '退款原因',
    `status` TINYINT DEFAULT 0 COMMENT '状态（0待审核 1已同意 2已拒绝 3已完成）',
    `apply_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    `success_time` DATETIME DEFAULT NULL COMMENT '完成时间',
    PRIMARY KEY (`refund_id`),
    UNIQUE KEY `uk_refund_no` (`refund_no`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款表';

-- 操作日志表
CREATE TABLE `sys_log` (
    `log_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `user_id` BIGINT DEFAULT NULL COMMENT '用户ID',
    `username` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
    `operation` VARCHAR(100) DEFAULT NULL COMMENT '操作内容',
    `method` VARCHAR(200) DEFAULT NULL COMMENT '请求方法',
    `params` TEXT DEFAULT NULL COMMENT '请求参数',
    `ip` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `execute_time` INT DEFAULT 0 COMMENT '执行时长（ms）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`log_id`),
    KEY `idx_user_time` (`user_id`, `create_time`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

