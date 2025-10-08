package com.xiaoins.admin.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 创建分类DTO
 */
@Data
@Schema(description = "创建分类请求")
public class CategoryCreateDTO {

    @Schema(description = "分类名称", example = "智能穿戴")
    @NotBlank(message = "分类名称不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,20}$", message = "分类名称为2-20位中文、字母或数字")
    private String categoryName;

    @Schema(description = "父级ID（0表示一级分类）", example = "1")
    @NotNull(message = "父级ID不能为空")
    private Long parentId;

    @Schema(description = "排序", example = "1")
    private Integer sortOrder = 0;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status = 1;
}

