-- 添加测试数据：商品和订单

-- 插入测试商品数据（注意：product表没有category_name字段，只有category_id）
INSERT INTO product (product_id, product_no, product_name, category_id, brand, main_image, images, description, original_price, current_price, stock, sales, status, create_time, update_time) VALUES
(1, 'P202510001', 'iPhone 15 Pro Max 256GB 深空黑色', 101, 'Apple', 'https://img.example.com/iphone15.jpg', '["https://img.example.com/iphone15-1.jpg","https://img.example.com/iphone15-2.jpg"]', 'Apple iPhone 15 Pro Max，搭载A17 Pro芯片，钛金属设计，全新操作按钮。', 9999.00, 8999.00, 50, 15, 1, NOW(), NOW()),
(2, 'P202510002', '华为Mate 60 Pro 512GB 雅川青', 101, '华为', 'https://img.example.com/mate60.jpg', '["https://img.example.com/mate60-1.jpg"]', '华为Mate 60 Pro，搭载麒麟9000S芯片，支持卫星通话。', 7999.00, 6999.00, 80, 25, 1, NOW(), NOW()),
(3, 'P202510003', '小米14 Ultra 16GB+1TB 钛金属', 101, '小米', 'https://img.example.com/mi14.jpg', '["https://img.example.com/mi14-1.jpg"]', '小米14 Ultra，徕卡光学镜头，骁龙8 Gen3芯片。', 6999.00, 5999.00, 100, 30, 1, NOW(), NOW()),
(4, 'P202510004', 'MacBook Pro 14英寸 M3 Pro', 102, 'Apple', 'https://img.example.com/macbook.jpg', '["https://img.example.com/macbook-1.jpg"]', 'MacBook Pro 14，搭载M3 Pro芯片，18GB内存，512GB存储。', 15999.00, 14999.00, 30, 8, 1, NOW(), NOW()),
(5, 'P202510005', '联想ThinkPad X1 Carbon', 102, '联想', 'https://img.example.com/thinkpad.jpg', '["https://img.example.com/thinkpad-1.jpg"]', 'ThinkPad X1 Carbon Gen 11，Intel 13代酷睿，轻薄商务本。', 12999.00, 11999.00, 25, 5, 1, NOW(), NOW()),
(6, 'P202510006', '戴尔XPS 15 9530', 102, 'Dell', 'https://img.example.com/xps15.jpg', '["https://img.example.com/xps15-1.jpg"]', 'Dell XPS 15，4K OLED屏幕，RTX 4050独显。', 13999.00, 12999.00, 20, 3, 1, NOW(), NOW()),
(7, 'P202510007', '海尔冰箱 BCD-500WDPF', 103, '海尔', 'https://img.example.com/haier.jpg', '["https://img.example.com/haier-1.jpg"]', '海尔500升十字对开门冰箱，变频风冷无霜。', 3999.00, 3599.00, 15, 12, 1, NOW(), NOW()),
(8, 'P202510008', '格力空调 KFR-35GW', 103, '格力', 'https://img.example.com/gree.jpg', '["https://img.example.com/gree-1.jpg"]', '格力1.5匹变频空调，一级能效，智能控制。', 3299.00, 2999.00, 40, 20, 1, NOW(), NOW()),
(9, 'P202510009', '耐克Air Max 270 男鞋', 203, 'Nike', 'https://img.example.com/nike.jpg', '["https://img.example.com/nike-1.jpg"]', '耐克Air Max 270气垫运动鞋，舒适透气。', 1299.00, 899.00, 200, 50, 1, NOW(), NOW()),
(10, 'P202510010', '阿迪达斯Ultra Boost 22', 203, 'Adidas', 'https://img.example.com/adidas.jpg', '["https://img.example.com/adidas-1.jpg"]', '阿迪达斯Ultra Boost 22跑鞋，Boost中底科技。', 1499.00, 1099.00, 150, 35, 1, NOW(), NOW()),
(11, 'P202510011', '三只松鼠每日坚果大礼包', 301, '三只松鼠', 'https://img.example.com/squirrel.jpg', '["https://img.example.com/squirrel-1.jpg"]', '三只松鼠每日坚果，30包混合装，营养健康。', 199.00, 149.00, 500, 120, 1, NOW(), NOW()),
(12, 'P202510012', '农夫山泉天然水 550ml*24瓶', 302, '农夫山泉', 'https://img.example.com/nongfu.jpg', '["https://img.example.com/nongfu-1.jpg"]', '农夫山泉天然水，整箱装，家庭常备。', 45.00, 39.00, 1000, 200, 1, NOW(), NOW()),
(13, 'P202510013', '雀巢咖啡1+2原味 100条', 302, '雀巢', 'https://img.example.com/nestle.jpg', '["https://img.example.com/nestle-1.jpg"]', '雀巢1+2速溶咖啡，香浓顺滑，办公室必备。', 89.00, 69.00, 300, 80, 1, NOW(), NOW()),
(14, 'P202510014', '金龙鱼食用油5L', 303, '金龙鱼', 'https://img.example.com/arowana.jpg', '["https://img.example.com/arowana-1.jpg"]', '金龙鱼调和油，营养均衡，适合中式烹饪。', 89.00, 79.00, 200, 45, 1, NOW(), NOW()),
(15, 'P202510015', '李锦记蚝油 2.27kg', 303, '李锦记', 'https://img.example.com/ljj.jpg', '["https://img.example.com/ljj-1.jpg"]', '李锦记蚝油，鲜美味浓，烹饪好帮手。', 59.00, 49.00, 150, 60, 1, NOW(), NOW()),
(16, 'P202510016', 'iPhone 14 128GB 午夜色', 101, 'Apple', 'https://img.example.com/iphone14.jpg', '["https://img.example.com/iphone14-1.jpg"]', 'Apple iPhone 14，A15芯片，超视网膜XDR显示屏。', 5999.00, 4999.00, 5, 40, 1, NOW(), NOW()),
(17, 'P202510017', '华为MateBook X Pro', 102, '华为', 'https://img.example.com/matebook.jpg', '["https://img.example.com/matebook-1.jpg"]', '华为MateBook X Pro，3K触控屏，英特尔酷睿处理器。', 9999.00, 8999.00, 3, 2, 1, NOW(), NOW()),
(18, 'P202510018', '美的洗衣机 MG100V31D', 103, '美的', 'https://img.example.com/midea.jpg', '["https://img.example.com/midea-1.jpg"]', '美的10公斤滚筒洗衣机，变频节能，除菌洗。', 2999.00, 2599.00, 8, 5, 1, NOW(), NOW()),
(19, 'P202510019', '索尼WH-1000XM5降噪耳机', 101, 'Sony', 'https://img.example.com/sony.jpg', '["https://img.example.com/sony-1.jpg"]', '索尼WH-1000XM5，行业领先的降噪技术。', 2799.00, 2399.00, 2, 18, 1, NOW(), NOW()),
(20, 'P202510020', 'AirPods Pro 2代', 101, 'Apple', 'https://img.example.com/airpods.jpg', '["https://img.example.com/airpods-1.jpg"]', 'Apple AirPods Pro 第二代，自适应主动降噪。', 1899.00, 1699.00, 1, 25, 1, NOW(), NOW());

-- 插入测试订单数据
INSERT INTO `order` (order_id, order_no, user_id, total_amount, pay_amount, freight, coupon_amount, pay_type, status, receiver_name, receiver_phone, receiver_address, logistics_company, logistics_no, create_time, pay_time, send_time, finish_time) VALUES
(1, 'O20251006000001', 1, 8999.00, 9009.00, 10.00, 0.00, 1, 3, '张三', '13800138001', '北京市朝阳区建国路88号SOHO现代城A座1001室', '顺丰速运', 'SF1234567890', '2025-10-05 09:30:00', '2025-10-05 09:35:00', '2025-10-05 14:00:00', '2025-10-06 10:00:00'),
(2, 'O20251006000002', 2, 6999.00, 7009.00, 10.00, 0.00, 2, 2, '李四', '13800138002', '上海市浦东新区陆家嘴环路1000号恒生银行大厦20楼', '中通快递', 'ZTO9876543210', '2025-10-05 10:15:00', '2025-10-05 10:20:00', '2025-10-05 15:30:00', NULL),
(3, 'O20251006000003', 3, 5999.00, 6009.00, 10.00, 0.00, 1, 2, '王五', '13800138003', '广州市天河区珠江新城花城大道123号', '申通快递', 'STO1122334455', '2025-10-05 11:00:00', '2025-10-05 11:05:00', '2025-10-05 16:00:00', NULL),
(4, 'O20251006000004', 1, 14999.00, 15009.00, 10.00, 0.00, 1, 1, '张三', '13800138001', '北京市朝阳区建国路88号SOHO现代城A座1001室', NULL, NULL, '2025-10-06 08:30:00', '2025-10-06 08:35:00', NULL, NULL),
(5, 'O20251006000005', 2, 11999.00, 12009.00, 10.00, 0.00, 2, 1, '李四', '13800138002', '上海市浦东新区陆家嘴环路1000号恒生银行大厦20楼', NULL, NULL, '2025-10-06 09:15:00', '2025-10-06 09:20:00', NULL, NULL),
(6, 'O20251006000006', 3, 3599.00, 3609.00, 10.00, 0.00, 1, 1, '王五', '13800138003', '广州市天河区珠江新城花城大道123号', NULL, NULL, '2025-10-06 10:00:00', '2025-10-06 10:05:00', NULL, NULL),
(7, 'O20251006000007', 1, 2999.00, 3009.00, 10.00, 0.00, 1, 0, '张三', '13800138001', '北京市朝阳区建国路88号SOHO现代城A座1001室', NULL, NULL, '2025-10-06 11:30:00', NULL, NULL, NULL),
(8, 'O20251006000008', 2, 899.00, 909.00, 10.00, 0.00, 2, 0, '李四', '13800138002', '上海市浦东新区陆家嘴环路1000号恒生银行大厦20楼', NULL, NULL, '2025-10-06 12:00:00', NULL, NULL, NULL),
(9, 'O20251006000009', 3, 1099.00, 1109.00, 10.00, 0.00, 1, 4, '王五', '13800138003', '广州市天河区珠江新城花城大道123号', NULL, NULL, '2025-10-04 14:00:00', '2025-10-04 14:05:00', NULL, NULL),
(10, 'O20251006000010', 1, 149.00, 159.00, 10.00, 0.00, 1, 3, '张三', '13800138001', '北京市朝阳区建国路88号SOHO现代城A座1001室', '顺丰速运', 'SF2233445566', '2025-10-04 15:30:00', '2025-10-04 15:35:00', '2025-10-05 09:00:00', '2025-10-06 09:00:00'),
(11, 'O20251006000011', 2, 39.00, 49.00, 10.00, 0.00, 2, 3, '李四', '13800138002', '上海市浦东新区陆家嘴环路1000号恒生银行大厦20楼', '中通快递', 'ZTO5566778899', '2025-10-03 10:00:00', '2025-10-03 10:05:00', '2025-10-03 16:00:00', '2025-10-05 10:00:00'),
(12, 'O20251006000012', 3, 69.00, 79.00, 10.00, 0.00, 1, 3, '王五', '13800138003', '广州市天河区珠江新城花城大道123号', '申通快递', 'STO6677889900', '2025-10-02 14:20:00', '2025-10-02 14:25:00', '2025-10-03 10:00:00', '2025-10-05 14:00:00');