package com.xiaoins.admin.category.service;

import com.xiaoins.admin.category.dto.CategoryCreateDTO;
import com.xiaoins.admin.category.dto.CategoryQueryDTO;
import com.xiaoins.admin.category.dto.CategoryUpdateDTO;
import com.xiaoins.admin.category.vo.CategoryVO;

import java.util.List;

/**
 * 分类服务接口
 */
public interface CategoryService {

    /**
     * 查询分类列表（扁平结构）
     */
    List<CategoryVO> getCategoryList(CategoryQueryDTO queryDTO);

    /**
     * 查询分类树（所有分类，树形结构）
     */
    List<CategoryVO> getCategoryTree(CategoryQueryDTO queryDTO);

    /**
     * 查询分类详情
     */
    CategoryVO getCategoryById(Long categoryId);

    /**
     * 创建分类
     */
    void createCategory(CategoryCreateDTO createDTO);

    /**
     * 更新分类
     */
    void updateCategory(CategoryUpdateDTO updateDTO);

    /**
     * 删除分类
     */
    void deleteCategory(Long categoryId);

    /**
     * 启用/禁用分类
     */
    void updateCategoryStatus(Long categoryId, Integer status);
}
