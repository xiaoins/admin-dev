package com.xiaoins.admin.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.product.dto.ProductCreateDTO;
import com.xiaoins.admin.product.dto.ProductQueryDTO;
import com.xiaoins.admin.product.dto.ProductStockDTO;
import com.xiaoins.admin.product.dto.ProductUpdateDTO;
import com.xiaoins.admin.product.vo.ProductVO;

/**
 * 商品服务接口
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     */
    IPage<ProductVO> getProductPage(ProductQueryDTO queryDTO);

    /**
     * 查询商品详情
     */
    ProductVO getProductById(Long productId);

    /**
     * 创建商品
     */
    void createProduct(ProductCreateDTO createDTO);

    /**
     * 更新商品
     */
    void updateProduct(ProductUpdateDTO updateDTO);

    /**
     * 删除商品
     */
    void deleteProduct(Long productId);

    /**
     * 上架/下架商品
     */
    void updateProductStatus(Long productId, Integer status);

    /**
     * 调整库存
     */
    void adjustStock(ProductStockDTO stockDTO);
}
