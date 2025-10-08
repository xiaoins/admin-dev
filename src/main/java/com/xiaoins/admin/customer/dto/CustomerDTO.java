package com.xiaoins.admin.customer.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 消费者用户DTO
 */
@Data
public class CustomerDTO {
    
    /**
     * 消费者ID（编辑时需要）
     */
    private Long customerId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码（新增时需要）
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
     * 省份
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
}

