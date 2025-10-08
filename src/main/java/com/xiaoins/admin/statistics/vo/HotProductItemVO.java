package com.xiaoins.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 热销商品数据项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "热销商品数据项")
public class HotProductItemVO {

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "销售数量")
    private Integer sales;
}

