package com.xiaoins.admin.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 更新分类DTO
 */
@Data
@Schema(description = "更新分类请求")
public class CategoryUpdateDTO {

    @Schema(description = "分类ID", example = "6")
    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    @Schema(description = "分类名称", example = "智能穿戴")
    @NotBlank(message = "分类名称不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,20}$", message = "分类名称为2-20位中文、字母或数字")
    private String categoryName;

    @Schema(description = "排序", example = "1")
    private Integer sortOrder;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status;
}

