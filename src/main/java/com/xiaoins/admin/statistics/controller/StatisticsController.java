package com.xiaoins.admin.statistics.controller;

import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.statistics.service.StatisticsService;
import com.xiaoins.admin.statistics.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计管理控制器
 */
@Tag(name = "数据统计", description = "数据统计相关接口")
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "获取仪表盘概览")
    @GetMapping("/dashboard")
    public Result<DashboardVO> getDashboard() {
        return Result.success(statisticsService.getDashboard());
    }

    @Operation(summary = "获取用户地区分布统计")
    @GetMapping("/customer-region")
    public Result<List<RegionDistributionItemVO>> getCustomerRegionDistribution() {
        return Result.success(statisticsService.getCustomerRegionDistribution());
    }

    @Operation(summary = "获取销售趋势数据")
    @GetMapping("/sales-trend")
    public Result<List<SalesTrendItemVO>> getSalesTrend(
            @Parameter(description = "统计天数，默认7天") @RequestParam(required = false, defaultValue = "7") Integer days
    ) {
        return Result.success(statisticsService.getSalesTrend(days));
    }

    @Operation(summary = "获取热销商品排行")
    @GetMapping("/hot-products")
    public Result<List<HotProductItemVO>> getHotProducts(
            @Parameter(description = "分类ID（可选，不传则查询所有分类）") @RequestParam(value = "categoryId", required = false) Long categoryId,
            @Parameter(description = "显示数量，默认10") @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit
    ) {
        return Result.success(statisticsService.getHotProducts(categoryId, limit));
    }

    @Operation(summary = "获取订单状态分布统计")
    @GetMapping("/order-status")
    public Result<List<OrderStatusDistributionItemVO>> getOrderStatusDistribution() {
        return Result.success(statisticsService.getOrderStatusDistribution());
    }
}

