package com.xiaoins.admin.category.controller;

import com.xiaoins.admin.category.dto.CategoryCreateDTO;
import com.xiaoins.admin.category.dto.CategoryQueryDTO;
import com.xiaoins.admin.category.dto.CategoryUpdateDTO;
import com.xiaoins.admin.category.service.CategoryService;
import com.xiaoins.admin.category.vo.CategoryVO;
import com.xiaoins.admin.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理控制器
 */
@Tag(name = "分类管理", description = "分类管理相关接口")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "查询分类列表（扁平结构）")
    @GetMapping("/list")
    public Result<List<CategoryVO>> getCategoryList(CategoryQueryDTO queryDTO) {
        return Result.success(categoryService.getCategoryList(queryDTO));
    }

    @Operation(summary = "查询分类树")
    @GetMapping("/tree")
    public Result<List<CategoryVO>> getCategoryTree(CategoryQueryDTO queryDTO) {
        return Result.success(categoryService.getCategoryTree(queryDTO));
    }

    @Operation(summary = "查询分类详情")
    @GetMapping("/{categoryId:\\d+}")
    public Result<CategoryVO> getCategoryById(@Parameter(description = "分类ID") @PathVariable Long categoryId) {
        return Result.success(categoryService.getCategoryById(categoryId));
    }

    @Operation(summary = "创建分类")
    @PostMapping
    public Result<Void> createCategory(@Valid @RequestBody CategoryCreateDTO createDTO) {
        categoryService.createCategory(createDTO);
        return Result.success();
    }

    @Operation(summary = "更新分类")
    @PutMapping
    public Result<Void> updateCategory(@Valid @RequestBody CategoryUpdateDTO updateDTO) {
        categoryService.updateCategory(updateDTO);
        return Result.success();
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{categoryId:\\d+}")
    public Result<Void> deleteCategory(@Parameter(description = "分类ID") @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return Result.success();
    }

    @Operation(summary = "启用/禁用分类")
    @PutMapping("/{categoryId:\\d+}/status/{status}")
    public Result<Void> updateCategoryStatus(
            @Parameter(description = "分类ID") @PathVariable Long categoryId,
            @Parameter(description = "状态（0禁用 1启用）") @PathVariable Integer status) {
        categoryService.updateCategoryStatus(categoryId, status);
        return Result.success();
    }
}
