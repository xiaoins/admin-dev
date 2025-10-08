package com.xiaoins.admin.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品视图对象
 */
@Data
@Schema(description = "商品信息")
public class ProductVO {

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品编号")
    private String productNo;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "品牌")
    private String brand;

    @Schema(description = "主图URL")
    private String mainImage;

    @Schema(description = "轮播图URL（JSON数组）")
    private String images;

    @Schema(description = "商品描述")
    private String description;

    @Schema(description = "原价")
    private BigDecimal originalPrice;

    @Schema(description = "现价")
    private BigDecimal currentPrice;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "销量")
    private Integer sales;

    @Schema(description = "状态（0下架 1上架）")
    private Integer status;

    @Schema(description = "状态描述")
    private String statusDesc;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
