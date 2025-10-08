package com.xiaoins.admin.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoins.admin.customer.entity.Customer;
import com.xiaoins.admin.customer.mapper.CustomerMapper;
import com.xiaoins.admin.order.entity.Order;
import com.xiaoins.admin.order.mapper.OrderMapper;
import com.xiaoins.admin.product.entity.Product;
import com.xiaoins.admin.product.mapper.ProductMapper;
import com.xiaoins.admin.statistics.service.StatisticsService;
import com.xiaoins.admin.statistics.vo.*;
import com.xiaoins.admin.user.entity.SysUser;
import com.xiaoins.admin.user.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 统计服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderMapper orderMapper;
    private final SysUserMapper sysUserMapper;
    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;

    @Override
    public DashboardVO getDashboard() {
        DashboardVO dashboard = new DashboardVO();

        // 今日订单数和销售额
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Order> todayOrders = orderMapper.selectList(
                new LambdaQueryWrapper<Order>()
                        .ge(Order::getCreateTime, todayStart)
                        .le(Order::getCreateTime, todayEnd)
        );

        dashboard.setTodayOrders((long) todayOrders.size());

        BigDecimal todaySales = todayOrders.stream()
                .filter(order -> order.getStatus() >= 1) // 已付款及以后的状态
                .map(Order::getPayAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dashboard.setTodaySales(todaySales);

        // 总用户数
        Long totalUsers = sysUserMapper.selectCount(null);
        dashboard.setTotalUsers(totalUsers);

        // 总商品数
        Long totalProducts = productMapper.selectCount(null);
        dashboard.setTotalProducts(totalProducts);

        // 待付款订单数
        Long pendingOrders = orderMapper.selectCount(
                new LambdaQueryWrapper<Order>().eq(Order::getStatus, 0)
        );
        dashboard.setPendingOrders(pendingOrders);

        // 已付款订单数
        Long paidOrders = orderMapper.selectCount(
                new LambdaQueryWrapper<Order>().eq(Order::getStatus, 1)
        );
        dashboard.setPaidOrders(paidOrders);

        // 已发货订单数
        Long shippedOrders = orderMapper.selectCount(
                new LambdaQueryWrapper<Order>().eq(Order::getStatus, 2)
        );
        dashboard.setShippedOrders(shippedOrders);

        // 库存告警商品数（库存小于10）
        Long lowStockProducts = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().lt(Product::getStock, 10)
        );
        dashboard.setLowStockProducts(lowStockProducts);

        return dashboard;
    }

    @Override
    public List<RegionDistributionItemVO> getCustomerRegionDistribution() {
        // 查询所有消费者用户
        List<Customer> customers = customerMapper.selectList(
                new LambdaQueryWrapper<Customer>()
                        .select(Customer::getProvince)
                        .eq(Customer::getStatus, 1)
        );

        // 按省份分组统计
        Map<String, Long> provinceCountMap = new HashMap<>();
        for (Customer customer : customers) {
            String province = customer.getProvince();
            if (province != null && !province.isEmpty()) {
                provinceCountMap.put(province, provinceCountMap.getOrDefault(province, 0L) + 1);
            }
        }

        // 转换为VO列表并按数量降序排序
        return provinceCountMap.entrySet().stream()
                .map(entry -> new RegionDistributionItemVO(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesTrendItemVO> getSalesTrend(Integer days) {
        if (days == null || days <= 0) {
            days = 7; // 默认7天
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SalesTrendItemVO> trendList = new ArrayList<>();

        // 生成最近N天的数据
        for (int i = days - 1; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            LocalDateTime dayStart = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime dayEnd = LocalDateTime.of(date, LocalTime.MAX);

            // 查询当天订单
            List<Order> dayOrders = orderMapper.selectList(
                    new LambdaQueryWrapper<Order>()
                            .ge(Order::getCreateTime, dayStart)
                            .le(Order::getCreateTime, dayEnd)
            );

            // 计算订单数和销售额（已付款及以后的状态）
            long orderCount = dayOrders.size();
            BigDecimal salesAmount = dayOrders.stream()
                    .filter(order -> order.getStatus() >= 1)
                    .map(Order::getPayAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            trendList.add(new SalesTrendItemVO(
                    date.format(dateFormatter),
                    orderCount,
                    salesAmount
            ));
        }

        return trendList;
    }

    @Override
    public List<HotProductItemVO> getHotProducts(Long categoryId, Integer limit) {
        log.info("获取热销商品排行 - categoryId: {}, limit: {}", categoryId, limit);
        
        if (limit == null || limit <= 0) {
            limit = 10; // 默认Top 10
        }

        // 构建查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<Product>()
                .select(Product::getProductId, Product::getProductName, Product::getSales, Product::getCategoryId)
                .eq(Product::getStatus, 1); // 只查上架商品

        // 如果传入了分类ID，则按分类过滤（支持一级和二级分类）
        if (categoryId != null && categoryId > 0) {
            log.info("开始按分类过滤 - categoryId: {}", categoryId);
            // 如果是一级分类（ID小于100），需要查询所有子分类的商品
            if (categoryId < 100) {
                // 一级分类ID范围：1, 2, 3, 4, 5
                // 对应二级分类ID范围：101-199, 201-299, 301-399, 401-499, 501-599
                long minSubCategoryId = categoryId * 100 + 1;
                long maxSubCategoryId = categoryId * 100 + 99;
                log.info("一级分类 - 查询范围: {} - {}", minSubCategoryId, maxSubCategoryId);
                queryWrapper.between(Product::getCategoryId, minSubCategoryId, maxSubCategoryId);
            } else {
                // 二级分类，直接按 category_id 过滤
                log.info("二级分类 - 精确匹配: {}", categoryId);
                queryWrapper.eq(Product::getCategoryId, categoryId);
            }
        } else {
            log.info("查询所有分类的热销商品");
        }

        // 查询商品并按销量降序排序
        List<Product> products = productMapper.selectList(
                queryWrapper
                        .orderByDesc(Product::getSales)
                        .last("LIMIT " + limit)
        );

        return products.stream()
                .map(product -> new HotProductItemVO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getSales()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderStatusDistributionItemVO> getOrderStatusDistribution() {
        List<OrderStatusDistributionItemVO> distributionList = new ArrayList<>();

        // 订单状态：0待付款 1待发货 2已发货 3已完成 4已取消
        String[] statusNames = {"待付款", "待发货", "已发货", "已完成", "已取消"};

        for (int status = 0; status < statusNames.length; status++) {
            Long count = orderMapper.selectCount(
                    new LambdaQueryWrapper<Order>().eq(Order::getStatus, status)
            );
            distributionList.add(new OrderStatusDistributionItemVO(statusNames[status], count));
        }

        return distributionList;
    }
}

