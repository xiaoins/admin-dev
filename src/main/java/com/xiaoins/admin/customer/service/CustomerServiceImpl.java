package com.xiaoins.admin.customer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.customer.dto.CustomerDTO;
import com.xiaoins.admin.customer.dto.CustomerQueryDTO;
import com.xiaoins.admin.customer.entity.Customer;
import com.xiaoins.admin.customer.mapper.CustomerMapper;
import com.xiaoins.admin.customer.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消费者用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Page<CustomerVO> getCustomerPage(CustomerQueryDTO queryDTO) {
        Page<Customer> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getUsername()), Customer::getUsername, queryDTO.getUsername())
                .like(StringUtils.hasText(queryDTO.getRealName()), Customer::getRealName, queryDTO.getRealName())
                .like(StringUtils.hasText(queryDTO.getPhone()), Customer::getPhone, queryDTO.getPhone())
                .eq(StringUtils.hasText(queryDTO.getProvince()), Customer::getProvince, queryDTO.getProvince())
                .eq(StringUtils.hasText(queryDTO.getCity()), Customer::getCity, queryDTO.getCity())
                .eq(queryDTO.getStatus() != null, Customer::getStatus, queryDTO.getStatus())
                .eq(queryDTO.getGender() != null, Customer::getGender, queryDTO.getGender())
                .orderByDesc(Customer::getCreateTime);
        
        Page<Customer> customerPage = customerMapper.selectPage(page, wrapper);
        
        List<CustomerVO> customerVOList = customerPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        
        Page<CustomerVO> voPage = new Page<>(customerPage.getCurrent(), customerPage.getSize(), customerPage.getTotal());
        voPage.setRecords(customerVOList);
        
        return voPage;
    }
    
    @Override
    public CustomerVO getCustomerById(Long customerId) {
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException("消费者用户不存在");
        }
        return convertToVO(customer);
    }
    
    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Customer::getUsername, customerDTO.getUsername());
        if (customerMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (StringUtils.hasText(customerDTO.getPhone())) {
            wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Customer::getPhone, customerDTO.getPhone());
            if (customerMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("手机号已被注册");
            }
        }
        
        // 检查邮箱是否已存在
        if (StringUtils.hasText(customerDTO.getEmail())) {
            wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Customer::getEmail, customerDTO.getEmail());
            if (customerMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("邮箱已被注册");
            }
        }
        
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        
        // 加密密码
        if (StringUtils.hasText(customerDTO.getPassword())) {
            customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        }
        
        // 默认值
        if (customer.getStatus() == null) {
            customer.setStatus(1);
        }
        if (customer.getGender() == null) {
            customer.setGender(0);
        }
        
        customerMapper.insert(customer);
    }
    
    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        Customer existingCustomer = customerMapper.selectById(customerDTO.getCustomerId());
        if (existingCustomer == null) {
            throw new BusinessException("消费者用户不存在");
        }
        
        // 检查用户名是否被其他用户使用
        if (!existingCustomer.getUsername().equals(customerDTO.getUsername())) {
            LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Customer::getUsername, customerDTO.getUsername())
                    .ne(Customer::getCustomerId, customerDTO.getCustomerId());
            if (customerMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("用户名已存在");
            }
        }
        
        // 检查手机号是否被其他用户使用
        if (StringUtils.hasText(customerDTO.getPhone()) 
                && !customerDTO.getPhone().equals(existingCustomer.getPhone())) {
            LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Customer::getPhone, customerDTO.getPhone())
                    .ne(Customer::getCustomerId, customerDTO.getCustomerId());
            if (customerMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("手机号已被注册");
            }
        }
        
        // 检查邮箱是否被其他用户使用
        if (StringUtils.hasText(customerDTO.getEmail()) 
                && !customerDTO.getEmail().equals(existingCustomer.getEmail())) {
            LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Customer::getEmail, customerDTO.getEmail())
                    .ne(Customer::getCustomerId, customerDTO.getCustomerId());
            if (customerMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("邮箱已被注册");
            }
        }
        
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        
        // 更新时不修改密码
        customer.setPassword(null);
        
        customerMapper.updateById(customer);
    }
    
    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException("消费者用户不存在");
        }
        
        // TODO: 检查是否有关联订单，如果有则不允许删除
        
        customerMapper.deleteById(customerId);
    }
    
    @Override
    public void resetPassword(Long customerId, String newPassword) {
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null) {
            throw new BusinessException("消费者用户不存在");
        }
        
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerMapper.updateById(customer);
    }
    
    /**
     * 转换为VO对象
     */
    private CustomerVO convertToVO(Customer customer) {
        CustomerVO vo = new CustomerVO();
        BeanUtils.copyProperties(customer, vo);
        
        // 性别文本
        if (customer.getGender() != null) {
            switch (customer.getGender()) {
                case 1:
                    vo.setGenderText("男");
                    break;
                case 2:
                    vo.setGenderText("女");
                    break;
                default:
                    vo.setGenderText("未知");
            }
        }
        
        // 状态文本
        vo.setStatusText(customer.getStatus() == 1 ? "启用" : "禁用");
        
        // 完整地址
        StringBuilder fullAddress = new StringBuilder();
        if (StringUtils.hasText(customer.getProvince())) {
            fullAddress.append(customer.getProvince());
        }
        if (StringUtils.hasText(customer.getCity())) {
            fullAddress.append(customer.getCity());
        }
        if (StringUtils.hasText(customer.getDistrict())) {
            fullAddress.append(customer.getDistrict());
        }
        if (StringUtils.hasText(customer.getAddress())) {
            fullAddress.append(customer.getAddress());
        }
        vo.setFullAddress(fullAddress.toString());
        
        return vo;
    }
}

