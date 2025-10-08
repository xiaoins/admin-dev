package com.xiaoins.admin.marketing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券实体
 */
@Data
@TableName("coupon")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long couponId;

    private String couponName;
    private Integer couponType;
    private BigDecimal couponValue;
    private BigDecimal minAmount;
    private Integer totalCount;
    private Integer receiveCount;
    private Integer useCount;
    private Integer limitCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
}

