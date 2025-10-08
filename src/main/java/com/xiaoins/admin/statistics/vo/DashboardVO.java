package com.xiaoins.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 仪表盘概览VO
 */
@Data
@Schema(description = "仪表盘概览数据")
public class DashboardVO {

    @Schema(description = "今日订单数")
    private Long todayOrders;

    @Schema(description = "今日销售额")
    private BigDecimal todaySales;

    @Schema(description = "总用户数")
    private Long totalUsers;

    @Schema(description = "总商品数")
    private Long totalProducts;

    @Schema(description = "待付款订单数")
    private Long pendingOrders;

    @Schema(description = "已付款订单数")
    private Long paidOrders;

    @Schema(description = "已发货订单数")
    private Long shippedOrders;

    @Schema(description = "库存告警商品数")
    private Long lowStockProducts;
}

