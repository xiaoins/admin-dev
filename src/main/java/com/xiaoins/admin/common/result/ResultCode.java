package com.xiaoins.admin.common.result;

import lombok.Getter;

/**
 * 错误码枚举
 */
@Getter
public enum ResultCode {

    // ============ 通用错误码 ============
    SUCCESS("0", "成功"),
    SYSTEM_ERROR("A0001", "系统错误"),
    PARAM_ERROR("A0002", "参数错误"),
    PARAM_MISSING("A0003", "参数缺失"),
    DATA_NOT_FOUND("A0004", "数据不存在"),
    PARAM_VALID_ERROR("A0005", "参数校验失败"),
    OPERATION_ERROR("A0006", "操作失败"),
    
    // ============ 认证授权 ============
    UNAUTHORIZED("A0401", "未认证，请先登录"),
    TOKEN_INVALID("A0402", "Token无效或已过期"),
    TOKEN_EXPIRED("A0403", "Token已过期"),
    FORBIDDEN("A0301", "无权限访问"),
    
    // ============ 用户模块 U**** ============
    USER_NOT_FOUND("U1001", "用户不存在"),
    USER_ALREADY_EXISTS("U1002", "用户名已存在"),
    USER_PASSWORD_ERROR("U1003", "密码错误"),
    USER_DISABLED("U1004", "用户已被禁用"),
    USER_LOCKED("U1005", "用户已被锁定"),
    ROLE_NOT_FOUND("U2001", "角色不存在"),
    ROLE_ALREADY_EXISTS("U2002", "角色已存在"),
    PERMISSION_NOT_FOUND("U3001", "权限不存在"),
    
    // ============ 商品模块 P**** ============
    CATEGORY_NOT_FOUND("P0001", "分类不存在"),
    CATEGORY_HAS_CHILDREN("P0002", "分类下存在子分类，无法删除"),
    CATEGORY_HAS_PRODUCTS("P0003", "分类下存在商品，无法删除"),
    PRODUCT_NOT_FOUND("P1001", "商品不存在"),
    PRODUCT_NO_EXISTS("P1002", "商品编号已存在"),
    PRODUCT_STOCK_NOT_ENOUGH("P1003", "商品库存不足"),
    PRODUCT_OFF_SHELF("P1004", "商品已下架"),
    
    // ============ 订单模块 O**** ============
    ORDER_NOT_FOUND("O1001", "订单不存在"),
    ORDER_STATUS_ERROR("O1002", "订单状态错误"),
    ORDER_CANNOT_CANCEL("O1003", "订单无法取消"),
    ORDER_ALREADY_PAID("O1004", "订单已支付"),
    ORDER_ALREADY_SHIPPED("O1005", "订单已发货"),
    
    // ============ 退款模块 R**** ============
    REFUND_NOT_FOUND("R1001", "退款单不存在"),
    REFUND_STATUS_ERROR("R1002", "退款状态错误"),
    REFUND_AMOUNT_ERROR("R1003", "退款金额错误"),
    
    // ============ 营销模块 M**** ============
    COUPON_NOT_FOUND("M1001", "优惠券不存在"),
    COUPON_NOT_AVAILABLE("M1002", "优惠券不可用"),
    COUPON_EXPIRED("M1003", "优惠券已过期"),
    COUPON_OUT_OF_STOCK("M1004", "优惠券已领完"),
    COUPON_LIMIT_EXCEEDED("M1005", "超出领取限制"),
    ACTIVITY_NOT_FOUND("M2001", "活动不存在"),
    ACTIVITY_NOT_STARTED("M2002", "活动未开始"),
    ACTIVITY_ENDED("M2003", "活动已结束"),
    
    // ============ 文件上传 ============
    FILE_UPLOAD_ERROR("F0001", "文件上传失败"),
    FILE_TYPE_ERROR("F0002", "文件类型不支持"),
    FILE_SIZE_ERROR("F0003", "文件大小超出限制"),
    
    // ============ 数据库操作 ============
    DB_INSERT_ERROR("D0001", "数据插入失败"),
    DB_UPDATE_ERROR("D0002", "数据更新失败"),
    DB_DELETE_ERROR("D0003", "数据删除失败"),
    DB_QUERY_ERROR("D0004", "数据查询失败");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

