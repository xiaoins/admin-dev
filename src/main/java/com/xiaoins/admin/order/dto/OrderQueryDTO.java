package com.xiaoins.admin.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单查询DTO
 */
@Data
@Schema(description = "订单查询条件")
public class OrderQueryDTO {

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "订单号", example = "O202510060001")
    private String orderNo;

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "订单状态（0待付款 1已付款 2已发货 3已完成 4已取消）", example = "1")
    private Integer status;

    @Schema(description = "开始时间", example = "2025-10-01 00:00:00")
    private String startTime;

    @Schema(description = "结束时间", example = "2025-10-31 23:59:59")
    private String endTime;
}

