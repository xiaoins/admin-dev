package com.xiaoins.admin.customer.dto;

import lombok.Data;

/**
 * 消费者用户查询DTO
 */
@Data
public class CustomerQueryDTO {
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 省份
     */
    private String province;
    
    /**
     * 城市
     */
    private String city;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
     * 性别
     */
    private Integer gender;
    
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}

