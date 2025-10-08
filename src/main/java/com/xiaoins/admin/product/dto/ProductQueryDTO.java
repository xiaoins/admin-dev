package com.xiaoins.admin.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品查询DTO
 */
@Data
@Schema(description = "商品查询条件")
public class ProductQueryDTO {

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "商品名称", example = "iPhone")
    private String productName;

    @Schema(description = "商品编号", example = "P001")
    private String productNo;

    @Schema(description = "分类ID", example = "101")
    private Long categoryId;

    @Schema(description = "品牌", example = "Apple")
    private String brand;

    @Schema(description = "状态（0下架 1上架）", example = "1")
    private Integer status;
}

