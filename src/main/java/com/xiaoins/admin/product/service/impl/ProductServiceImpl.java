package com.xiaoins.admin.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.category.entity.Category;
import com.xiaoins.admin.category.mapper.CategoryMapper;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.product.dto.ProductCreateDTO;
import com.xiaoins.admin.product.dto.ProductQueryDTO;
import com.xiaoins.admin.product.dto.ProductStockDTO;
import com.xiaoins.admin.product.dto.ProductUpdateDTO;
import com.xiaoins.admin.product.entity.Product;
import com.xiaoins.admin.product.mapper.ProductMapper;
import com.xiaoins.admin.product.service.ProductService;
import com.xiaoins.admin.product.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public IPage<ProductVO> getProductPage(ProductQueryDTO queryDTO) {
        // 构建分页对象
        Page<Product> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(queryDTO.getProductName()), Product::getProductName, queryDTO.getProductName())
                .like(StrUtil.isNotBlank(queryDTO.getProductNo()), Product::getProductNo, queryDTO.getProductNo())
                .eq(queryDTO.getCategoryId() != null, Product::getCategoryId, queryDTO.getCategoryId())
                .like(StrUtil.isNotBlank(queryDTO.getBrand()), Product::getBrand, queryDTO.getBrand())
                .eq(queryDTO.getStatus() != null, Product::getStatus, queryDTO.getStatus())
                .orderByDesc(Product::getCreateTime);

        // 执行查询
        IPage<Product> productPage = productMapper.selectPage(page, wrapper);

        // 转换为VO
        IPage<ProductVO> voPage = productPage.convert(this::convertToVO);

        return voPage;
    }

    @Override
    public ProductVO getProductById(Long productId) {
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
        }

        return convertToVO(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductCreateDTO createDTO) {
        // 如果没有提供商品编号，自动生成
        if (StrUtil.isBlank(createDTO.getProductNo())) {
            createDTO.setProductNo(generateProductNo());
        }
        
        // 检查商品编号是否已存在
        Long count = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().eq(Product::getProductNo, createDTO.getProductNo())
        );
        if (count > 0) {
            throw new BusinessException(ResultCode.PRODUCT_NO_EXISTS);
        }

        // 检查分类是否存在
        Category category = categoryMapper.selectById(createDTO.getCategoryId());
        if (category == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        // 检查分类状态
        if (category.getStatus() == 0) {
            throw new BusinessException(ResultCode.OPERATION_ERROR.getCode(), "该分类已禁用，无法添加商品");
        }

        // 创建商品
        Product product = BeanUtil.copyProperties(createDTO, Product.class);
        productMapper.insert(product);

        log.info("创建商品成功，商品ID: {}, 商品编号: {}, 商品名称: {}", product.getProductId(), product.getProductNo(), product.getProductName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductUpdateDTO updateDTO) {
        // 检查商品是否存在
        Product existProduct = productMapper.selectById(updateDTO.getProductId());
        if (existProduct == null) {
            throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
        }

        // 检查分类是否存在
        Category category = categoryMapper.selectById(updateDTO.getCategoryId());
        if (category == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        // 检查分类状态
        if (category.getStatus() == 0) {
            throw new BusinessException(ResultCode.OPERATION_ERROR.getCode(), "该分类已禁用");
        }

        // 更新商品
        Product product = BeanUtil.copyProperties(updateDTO, Product.class);
        productMapper.updateById(product);

        log.info("更新商品成功，商品ID: {}, 商品名称: {}", product.getProductId(), product.getProductName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long productId) {
        // 检查商品是否存在
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
        }

        // 检查商品是否已上架
        if (product.getStatus() == 1) {
            throw new BusinessException(ResultCode.OPERATION_ERROR.getCode(), "商品已上架，请先下架再删除");
        }

        // 删除商品
        productMapper.deleteById(productId);

        log.info("删除商品成功，商品ID: {}, 商品名称: {}", productId, product.getProductName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductStatus(Long productId, Integer status) {
        // 检查商品是否存在
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
        }

        // 如果要上架，检查库存
        if (status == 1 && product.getStock() <= 0) {
            throw new BusinessException(ResultCode.OPERATION_ERROR.getCode(), "库存不足，无法上架");
        }

        // 更新状态
        product.setStatus(status);
        productMapper.updateById(product);

        log.info("更新商品状态成功，商品ID: {}, 状态: {}", productId, status == 1 ? "上架" : "下架");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adjustStock(ProductStockDTO stockDTO) {
        // 检查商品是否存在
        Product product = productMapper.selectById(stockDTO.getProductId());
        if (product == null) {
            throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
        }

        // 计算新库存
        int newStock = product.getStock() + stockDTO.getQuantity();
        if (newStock < 0) {
            throw new BusinessException(ResultCode.PRODUCT_STOCK_NOT_ENOUGH);
        }

        // 更新库存
        product.setStock(newStock);
        productMapper.updateById(product);

        log.info("调整库存成功，商品ID: {}, 调整数量: {}, 当前库存: {}, 备注: {}", 
                stockDTO.getProductId(), stockDTO.getQuantity(), newStock, stockDTO.getRemark());
    }

    /**
     * 转换为VO
     */
    private ProductVO convertToVO(Product product) {
        ProductVO vo = BeanUtil.copyProperties(product, ProductVO.class);
        
        // 设置状态描述
        vo.setStatusDesc(getStatusDesc(product.getStatus()));

        // 设置分类名称
        Category category = categoryMapper.selectById(product.getCategoryId());
        if (category != null) {
            vo.setCategoryName(category.getCategoryName());
        }

        return vo;
    }

    /**
     * 获取状态描述
     */
    private String getStatusDesc(Integer status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case 0:
                return "下架";
            case 1:
                return "上架";
            case 2:
                return "草稿";
            default:
                return "未知";
        }
    }

    /**
     * 生成商品编号
     * 格式：P + yyyyMMdd + 4位递增序列号
     */
    private String generateProductNo() {
        // 获取当前日期（yyyyMMdd格式）
        String dateStr = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        // 查询当天已有的最大编号
        String prefix = "P" + dateStr;
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(Product::getProductNo, prefix)
                .orderByDesc(Product::getProductNo)
                .last("LIMIT 1");
        
        Product lastProduct = productMapper.selectOne(wrapper);
        
        int sequence = 1;
        if (lastProduct != null && lastProduct.getProductNo() != null) {
            // 提取序列号（最后4位）
            String lastNo = lastProduct.getProductNo();
            if (lastNo.length() >= 13) {
                String lastSeq = lastNo.substring(9); // P(1) + yyyyMMdd(8) = 9
                try {
                    sequence = Integer.parseInt(lastSeq) + 1;
                } catch (NumberFormatException e) {
                    log.warn("解析商品编号序列失败: {}", lastNo);
                }
            }
        }
        
        // 生成新编号：P + yyyyMMdd + 4位序列号（不足4位补0）
        String productNo = prefix + String.format("%04d", sequence);
        log.info("生成新商品编号: {}", productNo);
        
        return productNo;
    }
}

