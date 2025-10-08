package com.xiaoins.admin.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分类请求
 */
@Data
@Schema(description = "分类请求")
public class CategoryRequest {

    @NotBlank(message = "分类名称不能为空")
    @Schema(description = "分类名称", example = "数码电器")
    private String categoryName;

    @NotNull(message = "父级ID不能为空")
    @Schema(description = "父级ID（0表示顶级分类）", example = "0")
    private Long parentId;

    @Schema(description = "排序", example = "1")
    private Integer sortOrder;

    @Schema(description = "层级", example = "1")
    private Integer level;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status;
}

