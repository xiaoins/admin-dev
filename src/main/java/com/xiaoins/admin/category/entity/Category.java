package com.xiaoins.admin.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品分类实体
 */
@Data
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父级ID（0表示顶级分类）
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

