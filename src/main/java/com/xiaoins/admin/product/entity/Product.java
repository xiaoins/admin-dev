package com.xiaoins.admin.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体
 */
@Data
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(type = IdType.AUTO)
    private Long productId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 主图URL
     */
    private String mainImage;

    /**
     * 轮播图URL（JSON数组）
     */
    private String images;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 状态（0下架 1上架 2草稿）
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

