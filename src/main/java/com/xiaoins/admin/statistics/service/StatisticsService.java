package com.xiaoins.admin.statistics.service;

import com.xiaoins.admin.statistics.vo.*;

import java.util.List;

/**
 * 统计服务接口
 */
public interface StatisticsService {

    /**
     * 获取仪表盘概览数据
     */
    DashboardVO getDashboard();

    /**
     * 获取用户地区分布统计（省份维度）
     */
    List<RegionDistributionItemVO> getCustomerRegionDistribution();

    /**
     * 获取销售趋势数据（最近7天）
     */
    List<SalesTrendItemVO> getSalesTrend(Integer days);

    /**
     * 获取热销商品排行（Top 10）
     * @param categoryId 分类ID（可选，不传则查询所有分类）
     * @param limit 显示数量
     */
    List<HotProductItemVO> getHotProducts(Long categoryId, Integer limit);

    /**
     * 获取订单状态分布统计
     */
    List<OrderStatusDistributionItemVO> getOrderStatusDistribution();
}

