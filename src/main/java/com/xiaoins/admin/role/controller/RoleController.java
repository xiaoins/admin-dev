package com.xiaoins.admin.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.common.result.Result;
import com.xiaoins.admin.role.dto.RoleCreateDTO;
import com.xiaoins.admin.role.dto.RolePermissionDTO;
import com.xiaoins.admin.role.dto.RoleQueryDTO;
import com.xiaoins.admin.role.dto.RoleUpdateDTO;
import com.xiaoins.admin.role.service.RoleService;
import com.xiaoins.admin.role.vo.PermissionVO;
import com.xiaoins.admin.role.vo.RoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 */
@Tag(name = "角色管理", description = "角色管理相关接口")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "分页查询角色列表")
    @GetMapping("/page")
    public Result<IPage<RoleVO>> getRolePage(RoleQueryDTO queryDTO) {
        return Result.success(roleService.getRolePage(queryDTO));
    }

    @Operation(summary = "查询角色详情")
    @GetMapping("/{roleId}")
    public Result<RoleVO> getRoleById(@Parameter(description = "角色ID") @PathVariable Long roleId) {
        return Result.success(roleService.getRoleById(roleId));
    }

    @Operation(summary = "创建角色")
    @PostMapping
    public Result<Void> createRole(@Valid @RequestBody RoleCreateDTO createDTO) {
        roleService.createRole(createDTO);
        return Result.success();
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public Result<Void> updateRole(@Valid @RequestBody RoleUpdateDTO updateDTO) {
        roleService.updateRole(updateDTO);
        return Result.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{roleId}")
    public Result<Void> deleteRole(@Parameter(description = "角色ID") @PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return Result.success();
    }

    @Operation(summary = "启用/禁用角色")
    @PutMapping("/{roleId}/status/{status}")
    public Result<Void> updateRoleStatus(
            @Parameter(description = "角色ID") @PathVariable Long roleId,
            @Parameter(description = "状态（0禁用 1启用）") @PathVariable Integer status) {
        roleService.updateRoleStatus(roleId, status);
        return Result.success();
    }

    @Operation(summary = "分配角色权限")
    @PostMapping("/permissions")
    public Result<Void> assignPermissions(@Valid @RequestBody RolePermissionDTO permissionDTO) {
        roleService.assignPermissions(permissionDTO);
        return Result.success();
    }

    @Operation(summary = "查询角色权限列表")
    @GetMapping("/{roleId}/permissions")
    public Result<List<PermissionVO>> getRolePermissions(
            @Parameter(description = "角色ID") @PathVariable Long roleId) {
        return Result.success(roleService.getRolePermissions(roleId));
    }

    @Operation(summary = "查询所有权限列表")
    @GetMapping("/permissions/all")
    public Result<List<PermissionVO>> getAllPermissions() {
        return Result.success(roleService.getAllPermissions());
    }
}

