package com.xiaoins.admin.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.user.dto.UserCreateDTO;
import com.xiaoins.admin.user.dto.UserPasswordDTO;
import com.xiaoins.admin.user.dto.UserQueryDTO;
import com.xiaoins.admin.user.dto.UserUpdateDTO;
import com.xiaoins.admin.user.service.UserService;
import com.xiaoins.admin.user.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 */
@Tag(name = "用户管理", description = "用户增删改查等操作")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "分页查询用户列表")
    @GetMapping("/page")
    public Result<Page<UserVO>> getUserPage(@Valid UserQueryDTO query) {
        return Result.success(userService.getUserPage(query));
    }

    @Operation(summary = "根据ID获取用户详情")
    @GetMapping("/{userId}")
    public Result<UserVO> getUserById(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId) {
        return Result.success(userService.getUserById(userId));
    }

    @Operation(summary = "创建用户")
    @PostMapping
    public Result<Long> createUser(@Valid @RequestBody UserCreateDTO dto) {
        return Result.success(userService.createUser(dto));
    }

    @Operation(summary = "更新用户")
    @PutMapping
    public Result<Boolean> updateUser(@Valid @RequestBody UserUpdateDTO dto) {
        return Result.success(userService.updateUser(dto));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteUser(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId) {
        return Result.success(userService.deleteUser(userId));
    }

    @Operation(summary = "启用/禁用用户")
    @PutMapping("/{userId}/status/{status}")
    public Result<Boolean> updateUserStatus(
            @Parameter(description = "用户ID", example = "1")
            @PathVariable Long userId,
            @Parameter(description = "状态（0禁用 1启用）", example = "1")
            @PathVariable Integer status) {
        return Result.success(userService.updateUserStatus(userId, status));
    }

    @Operation(summary = "重置用户密码")
    @PutMapping("/reset-password")
    public Result<Boolean> resetPassword(@Valid @RequestBody UserPasswordDTO dto) {
        return Result.success(userService.resetPassword(dto));
    }
}

