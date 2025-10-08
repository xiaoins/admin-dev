package com.xiaoins.admin.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 */
@Data
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long orderId;

    private String orderNo;
    private Long userId;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private BigDecimal freight;
    private BigDecimal couponAmount;
    private Integer payType;
    private Integer status;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String logisticsCompany;
    private String logisticsNo;
    private LocalDateTime createTime;
    private LocalDateTime payTime;
    private LocalDateTime sendTime;
    private LocalDateTime finishTime;
}

