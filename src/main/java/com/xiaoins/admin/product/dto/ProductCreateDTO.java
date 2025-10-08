package com.xiaoins.admin.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 创建商品DTO
 */
@Data
@Schema(description = "创建商品请求")
public class ProductCreateDTO {

    @Schema(description = "商品编号（可选，不提供则自动生成）", example = "P202510001")
    @Pattern(regexp = "^[A-Z0-9]{6,20}$", message = "商品编号为6-20位大写字母或数字")
    private String productNo;

    @Schema(description = "商品名称", example = "iPhone 15 Pro Max 256GB")
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @Schema(description = "分类ID", example = "101")
    @NotNull(message = "分类不能为空")
    private Long categoryId;

    @Schema(description = "品牌", example = "Apple")
    private String brand;

    @Schema(description = "主图URL", example = "https://example.com/images/product.jpg")
    private String mainImage;

    @Schema(description = "轮播图URL（JSON数组）", example = "[\"url1\", \"url2\"]")
    private String images;

    @Schema(description = "商品描述", example = "全新一代iPhone")
    private String description;

    @Schema(description = "原价", example = "9999.00")
    @NotNull(message = "原价不能为空")
    private BigDecimal originalPrice;

    @Schema(description = "现价", example = "8999.00")
    @NotNull(message = "现价不能为空")
    private BigDecimal currentPrice;

    @Schema(description = "库存", example = "100")
    @NotNull(message = "库存不能为空")
    private Integer stock;

    @Schema(description = "销量", example = "0")
    private Integer sales = 0;

    @Schema(description = "状态（0下架 1上架）", example = "1")
    private Integer status = 1;
}

