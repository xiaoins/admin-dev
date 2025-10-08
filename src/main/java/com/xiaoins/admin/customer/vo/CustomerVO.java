package com.xiaoins.admin.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 消费者用户VO
 */
@Data
public class CustomerVO {
    
    /**
     * 消费者ID
     */
    private Long customerId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 性别（0未知 1男 2女）
     */
    private Integer gender;
    
    /**
     * 性别文本
     */
    private String genderText;
    
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
     * 完整地址
     */
    private String fullAddress;
    
    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;
    
    /**
     * 状态文本
     */
    private String statusText;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

