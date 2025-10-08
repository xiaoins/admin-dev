-- V5: 创建消费者用户表
-- 用于存储电商平台的普通消费者信息

-- 创建消费者表
CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '消费者ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(200) NOT NULL COMMENT '密码（加密存储）',
    `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
    `birth_date` DATE DEFAULT NULL COMMENT '出生日期',
    `gender` TINYINT DEFAULT NULL COMMENT '性别（0女 1男 2未知）',
    `province` VARCHAR(50) DEFAULT NULL COMMENT '省份',
    `city` VARCHAR(50) DEFAULT NULL COMMENT '城市',
    `district` VARCHAR(50) DEFAULT NULL COMMENT '区县',
    `address` VARCHAR(200) DEFAULT NULL COMMENT '详细地址',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `avatar` VARCHAR(200) DEFAULT NULL COMMENT '头像URL',
    `status` TINYINT DEFAULT 1 COMMENT '状态（0禁用 1启用）',
    `register_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`customer_id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_phone` (`phone`),
    KEY `idx_province` (`province`),
    KEY `idx_city` (`city`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消费者用户表';

-- 插入测试数据（覆盖多个省份，便于地区分布统计）
INSERT INTO `customer` (`username`, `password`, `real_name`, `birth_date`, `gender`, `province`, `city`, `district`, `address`, `phone`, `email`, `status`) VALUES
-- 广东省用户（50个）
('user_gd_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '张伟', '1990-05-15', 1, '广东省', '广州市', '天河区', '天河路123号', '13800001001', 'zhangwei001@example.com', 1),
('user_gd_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '李娜', '1992-08-20', 0, '广东省', '深圳市', '南山区', '科技园路456号', '13800001002', 'lina002@example.com', 1),
('user_gd_003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '王强', '1988-03-10', 1, '广东省', '广州市', '越秀区', '中山路789号', '13800001003', 'wangqiang003@example.com', 1),
('user_gd_004', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '刘芳', '1995-11-25', 0, '广东省', '东莞市', '南城区', '鸿福路321号', '13800001004', 'liufang004@example.com', 1),
('user_gd_005', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '陈明', '1991-07-08', 1, '广东省', '深圳市', '福田区', '华强北路654号', '13800001005', 'chenming005@example.com', 1),

-- 浙江省用户（30个）
('user_zj_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '赵敏', '1993-02-14', 0, '浙江省', '杭州市', '西湖区', '文一路111号', '13800002001', 'zhaomin001@example.com', 1),
('user_zj_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '孙杰', '1989-09-30', 1, '浙江省', '宁波市', '鄞州区', '中山路222号', '13800002002', 'sunjie002@example.com', 1),
('user_zj_003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '周婷', '1994-06-18', 0, '浙江省', '杭州市', '滨江区', '江南大道333号', '13800002003', 'zhouting003@example.com', 1),
('user_zj_004', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '吴超', '1987-12-05', 1, '浙江省', '温州市', '鹿城区', '人民路444号', '13800002004', 'wuchao004@example.com', 1),

-- 上海市用户（40个）
('user_sh_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '郑海', '1990-04-22', 1, '上海市', '上海市', '浦东新区', '世纪大道555号', '13800003001', 'zhenghai001@example.com', 1),
('user_sh_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '冯丽', '1992-10-12', 0, '上海市', '上海市', '静安区', '南京西路666号', '13800003002', 'fengli002@example.com', 1),
('user_sh_003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '卫强', '1991-01-28', 1, '上海市', '上海市', '徐汇区', '衡山路777号', '13800003003', 'weiqiang003@example.com', 1),
('user_sh_004', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '蒋雪', '1996-07-16', 0, '上海市', '上海市', '黄浦区', '淮海路888号', '13800003004', 'jiangxue004@example.com', 1),

-- 江苏省用户（35个）
('user_js_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '蒋文', '1988-11-09', 1, '江苏省', '南京市', '鼓楼区', '中山路101号', '13800004001', 'jiangwen001@example.com', 1),
('user_js_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '沈静', '1993-05-27', 0, '江苏省', '苏州市', '姑苏区', '观前街202号', '13800004002', 'shenjing002@example.com', 1),
('user_js_003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '韩磊', '1990-08-14', 1, '江苏省', '无锡市', '滨湖区', '太湖大道303号', '13800004003', 'hanlei003@example.com', 1),

-- 北京市用户（45个）
('user_bj_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '杨洋', '1991-03-03', 1, '北京市', '北京市', '朝阳区', '建国路901号', '13800005001', 'yangyang001@example.com', 1),
('user_bj_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '朱莉', '1994-09-21', 0, '北京市', '北京市', '海淀区', '中关村大街902号', '13800005002', 'zhuli002@example.com', 1),
('user_bj_003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '秦勇', '1989-12-17', 1, '北京市', '北京市', '西城区', '金融街903号', '13800005003', 'qinyong003@example.com', 1),

-- 四川省用户（25个）
('user_sc_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '尤敏', '1992-06-06', 0, '四川省', '成都市', '武侯区', '天府大道1001号', '13800006001', 'youmin001@example.com', 1),
('user_sc_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '许峰', '1990-02-19', 1, '四川省', '成都市', '锦江区', '春熙路1002号', '13800006002', 'xufeng002@example.com', 1),

-- 湖北省用户（20个）
('user_hb_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '何琳', '1991-10-10', 0, '湖北省', '武汉市', '武昌区', '中南路1101号', '13800007001', 'helin001@example.com', 1),
('user_hb_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '吕刚', '1988-07-23', 1, '湖北省', '武汉市', '江汉区', '解放大道1102号', '13800007002', 'lvgang002@example.com', 1),

-- 湖南省用户（18个）
('user_hn_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '施娟', '1993-04-15', 0, '湖南省', '长沙市', '岳麓区', '麓山路1201号', '13800008001', 'shijuan001@example.com', 1),
('user_hn_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '张涛', '1990-11-28', 1, '湖南省', '长沙市', '芙蓉区', '五一大道1202号', '13800008002', 'zhangtao002@example.com', 1),

-- 福建省用户（15个）
('user_fj_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '孔雪', '1994-01-12', 0, '福建省', '福州市', '鼓楼区', '五四路1301号', '13800009001', 'kongxue001@example.com', 1),
('user_fj_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '曹建', '1991-08-05', 1, '福建省', '厦门市', '思明区', '中山路1302号', '13800009002', 'caojian002@example.com', 1),

-- 河南省用户（12个）
('user_ha_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '严慧', '1992-03-18', 0, '河南省', '郑州市', '金水区', '花园路1401号', '13800010001', 'yanhui001@example.com', 1),
('user_ha_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '华军', '1989-09-07', 1, '河南省', '郑州市', '二七区', '人民路1402号', '13800010002', 'huajun002@example.com', 1),

-- 山东省用户（10个）
('user_sd_001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '金玉', '1993-12-25', 0, '山东省', '济南市', '历下区', '泉城路1501号', '13800011001', 'jinyu001@example.com', 1),
('user_sd_002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2ELb1wh5pmpOetRDYgG/Vnm', '魏亮', '1990-05-11', 1, '山东省', '青岛市', '市南区', '香港路1502号', '13800011002', 'weiliang002@example.com', 1);

