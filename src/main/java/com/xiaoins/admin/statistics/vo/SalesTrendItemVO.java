package com.xiaoins.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 销售趋势数据项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "销售趋势数据项")
public class SalesTrendItemVO {

    @Schema(description = "日期")
    private String date;

    @Schema(description = "订单数量")
    private Long orderCount;

    @Schema(description = "销售额")
    private BigDecimal salesAmount;
}

