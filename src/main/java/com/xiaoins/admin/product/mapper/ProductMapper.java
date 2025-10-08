package com.xiaoins.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoins.admin.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}

