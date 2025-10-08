package com.xiaoins.admin.customer.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 消费者用户实体类
 */
@Data
@TableName("customer")
public class Customer {
    
    /**
     * 消费者ID
     */
    @TableId(type = IdType.AUTO)
    private Long customerId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码（加密）
     */
    private String password;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 性别（0未知 1男 2女）
     */
    private Integer gender;
    
    /**
     * 出生日期
     */
    private LocalDate birthDate;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 国家
     */
    private String country;
    
    /**
     * 头像URL
     */
    private String avatarUrl;
    
    /**
     * 省份/州
     */
    private String province;
    
    /**
     * 城市
     */
    private String city;
    
    /**
     * 区县
     */
    private String district;
    
    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

