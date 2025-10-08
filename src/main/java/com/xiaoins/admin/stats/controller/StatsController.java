package com.xiaoins.admin.stats.controller;

import com.xiaoins.admin.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计控制器
 */
@Tag(name = "数据统计", description = "概览统计、订单统计、商品统计等")
@RestController
@RequestMapping("/stats")
public class StatsController {

    /**
     * 概览统计
     */
    @Operation(summary = "概览统计")
    @GetMapping("/overview")
    public Result<Map<String, Object>> overview() {
        Map<String, Object> data = new HashMap<>();
        data.put("todayOrderCount", 0);
        data.put("todaySales", 0);
        data.put("pendingOrderCount", 0);
        data.put("lowStockCount", 0);
        return Result.success(data);
    }
}

