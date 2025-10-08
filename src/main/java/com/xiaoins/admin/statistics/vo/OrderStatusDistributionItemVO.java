package com.xiaoins.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单状态分布数据项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "订单状态分布数据项")
public class OrderStatusDistributionItemVO {

    @Schema(description = "状态名称")
    private String name;

    @Schema(description = "订单数量")
    private Long value;
}

