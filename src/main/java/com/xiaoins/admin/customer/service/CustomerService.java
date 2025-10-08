package com.xiaoins.admin.customer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.customer.dto.CustomerDTO;
import com.xiaoins.admin.customer.dto.CustomerQueryDTO;
import com.xiaoins.admin.customer.vo.CustomerVO;

/**
 * 消费者用户服务接口
 */
public interface CustomerService {
    
    /**
     * 分页查询消费者用户
     */
    Page<CustomerVO> getCustomerPage(CustomerQueryDTO queryDTO);
    
    /**
     * 根据ID查询消费者用户
     */
    CustomerVO getCustomerById(Long customerId);
    
    /**
     * 新增消费者用户
     */
    void createCustomer(CustomerDTO customerDTO);
    
    /**
     * 更新消费者用户
     */
    void updateCustomer(CustomerDTO customerDTO);
    
    /**
     * 删除消费者用户
     */
    void deleteCustomer(Long customerId);
    
    /**
     * 重置密码
     */
    void resetPassword(Long customerId, String newPassword);
}

