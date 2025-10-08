package com.xiaoins.admin.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品库存调整DTO
 */
@Data
@Schema(description = "商品库存调整请求")
public class ProductStockDTO {

    @Schema(description = "商品ID", example = "1")
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    @Schema(description = "调整数量（正数增加，负数减少）", example = "10")
    @NotNull(message = "调整数量不能为空")
    private Integer quantity;

    @Schema(description = "备注", example = "采购入库")
    private String remark;
}

