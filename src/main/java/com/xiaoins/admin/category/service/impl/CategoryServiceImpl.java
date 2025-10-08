package com.xiaoins.admin.category.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoins.admin.category.dto.CategoryCreateDTO;
import com.xiaoins.admin.category.dto.CategoryQueryDTO;
import com.xiaoins.admin.category.dto.CategoryUpdateDTO;
import com.xiaoins.admin.category.entity.Category;
import com.xiaoins.admin.category.mapper.CategoryMapper;
import com.xiaoins.admin.category.service.CategoryService;
import com.xiaoins.admin.category.vo.CategoryVO;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.product.entity.Product;
import com.xiaoins.admin.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public List<CategoryVO> getCategoryList(CategoryQueryDTO queryDTO) {
        // 构建查询条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(queryDTO.getCategoryName()), Category::getCategoryName, queryDTO.getCategoryName())
                .eq(queryDTO.getStatus() != null, Category::getStatus, queryDTO.getStatus())
                .orderByAsc(Category::getSortOrder, Category::getCategoryId);

        // 查询所有分类
        List<Category> allCategories = categoryMapper.selectList(wrapper);

        // 转换为VO（扁平结构）
        return allCategories.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryVO> getCategoryTree(CategoryQueryDTO queryDTO) {
        // 构建查询条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(queryDTO.getCategoryName()), Category::getCategoryName, queryDTO.getCategoryName())
                .eq(queryDTO.getStatus() != null, Category::getStatus, queryDTO.getStatus())
                .orderByAsc(Category::getSortOrder, Category::getCategoryId);

        // 查询所有分类
        List<Category> allCategories = categoryMapper.selectList(wrapper);

        // 转换为VO
        List<CategoryVO> allCategoryVOs = allCategories.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        // 构建树形结构
        return buildTree(allCategoryVOs, 0L);
    }

    @Override
    public CategoryVO getCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        return convertToVO(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCategory(CategoryCreateDTO createDTO) {
        // 检查父分类是否存在（如果不是创建一级分类）
        if (createDTO.getParentId() != 0) {
            Category parentCategory = categoryMapper.selectById(createDTO.getParentId());
            if (parentCategory == null) {
                throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND.getCode(), "父分类不存在");
            }

            // 检查父分类层级，最多支持两级
            if (parentCategory.getLevel() >= 2) {
                throw new BusinessException(ResultCode.OPERATION_ERROR.getCode(), "最多支持两级分类");
            }
        }

        // 检查同级分类名称是否已存在
        Long count = categoryMapper.selectCount(
                new LambdaQueryWrapper<Category>()
                        .eq(Category::getParentId, createDTO.getParentId())
                        .eq(Category::getCategoryName, createDTO.getCategoryName())
        );
        if (count > 0) {
            throw new BusinessException(ResultCode.PARAM_VALID_ERROR.getCode(), "同级分类名称已存在");
        }

        // 创建分类
        Category category = BeanUtil.copyProperties(createDTO, Category.class);
        
        // 设置层级
        if (createDTO.getParentId() == 0) {
            category.setLevel(1);
        } else {
            Category parentCategory = categoryMapper.selectById(createDTO.getParentId());
            category.setLevel(parentCategory.getLevel() + 1);
        }

        categoryMapper.insert(category);

        log.info("创建分类成功，分类ID: {}, 分类名称: {}", category.getCategoryId(), category.getCategoryName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(CategoryUpdateDTO updateDTO) {
        // 检查分类是否存在
        Category existCategory = categoryMapper.selectById(updateDTO.getCategoryId());
        if (existCategory == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        // 检查同级分类名称是否已被其他分类使用
        Long count = categoryMapper.selectCount(
                new LambdaQueryWrapper<Category>()
                        .eq(Category::getParentId, existCategory.getParentId())
                        .eq(Category::getCategoryName, updateDTO.getCategoryName())
                        .ne(Category::getCategoryId, updateDTO.getCategoryId())
        );
        if (count > 0) {
            throw new BusinessException(ResultCode.PARAM_VALID_ERROR.getCode(), "同级分类名称已存在");
        }

        // 更新分类
        Category category = BeanUtil.copyProperties(updateDTO, Category.class);
        categoryMapper.updateById(category);

        log.info("更新分类成功，分类ID: {}, 分类名称: {}", category.getCategoryId(), category.getCategoryName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Long categoryId) {
        // 检查分类是否存在
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        // 检查是否有子分类
        Long childCount = categoryMapper.selectCount(
                new LambdaQueryWrapper<Category>().eq(Category::getParentId, categoryId)
        );
        if (childCount > 0) {
            throw new BusinessException(ResultCode.CATEGORY_HAS_CHILDREN);
        }

        // 检查是否有商品关联此分类
        Long productCount = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().eq(Product::getCategoryId, categoryId)
        );
        if (productCount > 0) {
            throw new BusinessException(ResultCode.CATEGORY_HAS_PRODUCTS);
        }

        // 删除分类
        categoryMapper.deleteById(categoryId);

        log.info("删除分类成功，分类ID: {}, 分类名称: {}", categoryId, category.getCategoryName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategoryStatus(Long categoryId, Integer status) {
        // 检查分类是否存在
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) {
            throw new BusinessException(ResultCode.CATEGORY_NOT_FOUND);
        }

        // 更新状态
        category.setStatus(status);
        categoryMapper.updateById(category);

        log.info("更新分类状态成功，分类ID: {}, 状态: {}", categoryId, status == 1 ? "启用" : "禁用");
    }

    /**
     * 转换为VO
     */
    private CategoryVO convertToVO(Category category) {
        CategoryVO vo = BeanUtil.copyProperties(category, CategoryVO.class);
        vo.setStatusDesc(category.getStatus() == 1 ? "启用" : "禁用");

        // 查询商品数量
        Long count = productMapper.selectCount(
                new LambdaQueryWrapper<Product>().eq(Product::getCategoryId, category.getCategoryId())
        );
        vo.setProductCount(count.intValue());

        return vo;
    }

    /**
     * 构建树形结构
     */
    private List<CategoryVO> buildTree(List<CategoryVO> allCategories, Long parentId) {
        List<CategoryVO> tree = new ArrayList<>();

        for (CategoryVO category : allCategories) {
            if (category.getParentId().equals(parentId)) {
                // 递归查找子节点
                List<CategoryVO> children = buildTree(allCategories, category.getCategoryId());
                category.setChildren(children);
                tree.add(category);
            }
        }

        return tree;
    }
}

