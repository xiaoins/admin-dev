package com.xiaoins.admin.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.product.dto.ProductCreateDTO;
import com.xiaoins.admin.product.dto.ProductQueryDTO;
import com.xiaoins.admin.product.dto.ProductStockDTO;
import com.xiaoins.admin.product.dto.ProductUpdateDTO;
import com.xiaoins.admin.product.service.ProductService;
import com.xiaoins.admin.product.vo.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理控制器
 */
@Tag(name = "商品管理", description = "商品管理相关接口")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "分页查询商品列表")
    @GetMapping("/page")
    public Result<IPage<ProductVO>> getProductPage(ProductQueryDTO queryDTO) {
        return Result.success(productService.getProductPage(queryDTO));
    }

    @Operation(summary = "查询商品详情")
    @GetMapping("/{productId}")
    public Result<ProductVO> getProductById(@Parameter(description = "商品ID") @PathVariable Long productId) {
        return Result.success(productService.getProductById(productId));
    }

    @Operation(summary = "创建商品")
    @PostMapping
    public Result<Void> createProduct(@Valid @RequestBody ProductCreateDTO createDTO) {
        productService.createProduct(createDTO);
        return Result.success();
    }

    @Operation(summary = "更新商品")
    @PutMapping
    public Result<Void> updateProduct(@Valid @RequestBody ProductUpdateDTO updateDTO) {
        productService.updateProduct(updateDTO);
        return Result.success();
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{productId}")
    public Result<Void> deleteProduct(@Parameter(description = "商品ID") @PathVariable Long productId) {
        productService.deleteProduct(productId);
        return Result.success();
    }

    @Operation(summary = "上架/下架商品")
    @PutMapping("/{productId}/status/{status}")
    public Result<Void> updateProductStatus(
            @Parameter(description = "商品ID") @PathVariable Long productId,
            @Parameter(description = "状态（0下架 1上架）") @PathVariable Integer status) {
        productService.updateProductStatus(productId, status);
        return Result.success();
    }

    @Operation(summary = "调整库存")
    @PutMapping("/stock")
    public Result<Void> adjustStock(@Valid @RequestBody ProductStockDTO stockDTO) {
        productService.adjustStock(stockDTO);
        return Result.success();
    }
}
