package com.xiaoins.admin.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单视图对象
 */
@Data
@Schema(description = "订单信息")
public class OrderVO {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "收货人姓名")
    private String receiverName;

    @Schema(description = "收货人电话")
    private String receiverPhone;

    @Schema(description = "收货地址")
    private String receiverAddress;

    @Schema(description = "商品总金额")
    private BigDecimal totalAmount;

    @Schema(description = "运费")
    private BigDecimal freight;

    @Schema(description = "实付金额")
    private BigDecimal payAmount;

    @Schema(description = "支付类型")
    private Integer payType;

    @Schema(description = "优惠券金额")
    private BigDecimal couponAmount;

    @Schema(description = "订单状态（0待付款 1已付款 2已发货 3已完成 4已取消）")
    private Integer status;

    @Schema(description = "订单状态描述")
    private String statusDesc;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "付款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @Schema(description = "发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @Schema(description = "完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
}

