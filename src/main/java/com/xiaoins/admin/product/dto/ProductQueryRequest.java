package com.xiaoins.admin.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品查询请求
 */
@Data
@Schema(description = "商品查询请求")
public class ProductQueryRequest {

    @Schema(description = "商品名称（模糊搜索）")
    private String productName;

    @Schema(description = "商品编号（精确搜索）")
    private String productNo;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "状态（0下架 1上架 2草稿）")
    private Integer status;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "页码", example = "1")
    private Long page = 1L;

    @Schema(description = "每页大小", example = "10")
    private Long size = 10L;
}

