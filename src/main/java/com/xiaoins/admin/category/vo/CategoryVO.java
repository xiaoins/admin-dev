package com.xiaoins.admin.category.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类视图对象
 */
@Data
@Schema(description = "分类信息")
public class CategoryVO {

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sortOrder;

    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "状态（0禁用 1启用）")
    private Integer status;

    @Schema(description = "状态描述")
    private String statusDesc;

    @Schema(description = "商品数量")
    private Integer productCount;

    @Schema(description = "子分类列表")
    private List<CategoryVO> children;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
