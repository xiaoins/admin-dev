package com.xiaoins.admin.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.order.dto.OrderQueryDTO;
import com.xiaoins.admin.order.service.OrderService;
import com.xiaoins.admin.order.vo.OrderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理控制器
 */
@Tag(name = "订单管理", description = "订单管理相关接口")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "分页查询订单列表")
    @GetMapping("/page")
    public Result<IPage<OrderVO>> getOrderPage(OrderQueryDTO queryDTO) {
        return Result.success(orderService.getOrderPage(queryDTO));
    }

    @Operation(summary = "查询订单详情")
    @GetMapping("/{orderId}")
    public Result<OrderVO> getOrderById(@Parameter(description = "订单ID") @PathVariable Long orderId) {
        return Result.success(orderService.getOrderById(orderId));
    }

    @Operation(summary = "发货")
    @PutMapping("/{orderId}/ship")
    public Result<Void> shipOrder(@Parameter(description = "订单ID") @PathVariable Long orderId) {
        orderService.shipOrder(orderId);
        return Result.success();
    }

    @Operation(summary = "完成订单")
    @PutMapping("/{orderId}/finish")
    public Result<Void> finishOrder(@Parameter(description = "订单ID") @PathVariable Long orderId) {
        orderService.finishOrder(orderId);
        return Result.success();
    }

    @Operation(summary = "取消订单")
    @PutMapping("/{orderId}/cancel")
    public Result<Void> cancelOrder(@Parameter(description = "订单ID") @PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return Result.success();
    }
}
