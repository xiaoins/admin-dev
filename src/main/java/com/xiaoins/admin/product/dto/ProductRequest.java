package com.xiaoins.admin.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品请求
 */
@Data
@Schema(description = "商品请求")
public class ProductRequest {

    @NotBlank(message = "商品编号不能为空")
    @Schema(description = "商品编号", example = "P001")
    private String productNo;

    @NotBlank(message = "商品名称不能为空")
    @Schema(description = "商品名称", example = "iPhone 15 Pro")
    private String productName;

    @NotNull(message = "分类ID不能为空")
    @Schema(description = "分类ID", example = "101")
    private Long categoryId;

    @Schema(description = "品牌", example = "Apple")
    private String brand;

    @Schema(description = "主图URL")
    private String mainImage;

    @Schema(description = "轮播图URL（JSON数组）")
    private String images;

    @Schema(description = "商品描述")
    private String description;

    @Schema(description = "原价", example = "9999.00")
    private BigDecimal originalPrice;

    @NotNull(message = "现价不能为空")
    @Schema(description = "现价", example = "8999.00")
    private BigDecimal currentPrice;

    @NotNull(message = "库存不能为空")
    @Schema(description = "库存", example = "100")
    private Integer stock;

    @Schema(description = "状态（0下架 1上架 2草稿）", example = "1")
    private Integer status;
}

