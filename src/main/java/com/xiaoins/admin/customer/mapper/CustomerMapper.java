package com.xiaoins.admin.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoins.admin.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消费者用户Mapper接口
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}

