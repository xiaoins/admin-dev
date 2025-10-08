package com.xiaoins.admin.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类查询DTO
 */
@Data
@Schema(description = "分类查询条件")
public class CategoryQueryDTO {

    @Schema(description = "分类名称", example = "数码电器")
    private String categoryName;

    @Schema(description = "父级ID（0表示查询一级分类）", example = "0")
    private Long parentId;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status;
}

