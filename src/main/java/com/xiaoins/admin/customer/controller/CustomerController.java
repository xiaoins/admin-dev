package com.xiaoins.admin.customer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.customer.dto.CustomerDTO;
import com.xiaoins.admin.customer.dto.CustomerQueryDTO;
import com.xiaoins.admin.customer.service.CustomerService;
import com.xiaoins.admin.customer.vo.CustomerVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 消费者用户管理控制器
 */
@Tag(name = "消费者用户管理")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService customerService;
    
    /**
     * 分页查询消费者用户列表
     */
    @Operation(summary = "分页查询消费者用户列表")
    @GetMapping("/page")
    public Result<Page<CustomerVO>> getCustomerPage(CustomerQueryDTO queryDTO) {
        Page<CustomerVO> page = customerService.getCustomerPage(queryDTO);
        return Result.success(page);
    }
    
    /**
     * 根据ID查询消费者用户详情
     */
    @Operation(summary = "根据ID查询消费者用户详情")
    @GetMapping("/{customerId}")
    public Result<CustomerVO> getCustomerById(@PathVariable Long customerId) {
        CustomerVO customerVO = customerService.getCustomerById(customerId);
        return Result.success(customerVO);
    }
    
    /**
     * 新增消费者用户
     */
    @Operation(summary = "新增消费者用户")
    @PostMapping
    public Result<Void> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return Result.success();
    }
    
    /**
     * 更新消费者用户
     */
    @Operation(summary = "更新消费者用户")
    @PutMapping("/{customerId}")
    public Result<Void> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(customerId);
        customerService.updateCustomer(customerDTO);
        return Result.success();
    }
    
    /**
     * 删除消费者用户
     */
    @Operation(summary = "删除消费者用户")
    @DeleteMapping("/{customerId}")
    public Result<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return Result.success();
    }
    
    /**
     * 重置密码
     */
    @Operation(summary = "重置密码")
    @PutMapping("/{customerId}/reset-password")
    public Result<Void> resetPassword(@PathVariable Long customerId, @RequestBody String newPassword) {
        customerService.resetPassword(customerId, newPassword);
        return Result.success();
    }
}

