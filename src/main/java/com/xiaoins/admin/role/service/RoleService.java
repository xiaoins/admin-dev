package com.xiaoins.admin.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.role.dto.RoleCreateDTO;
import com.xiaoins.admin.role.dto.RolePermissionDTO;
import com.xiaoins.admin.role.dto.RoleQueryDTO;
import com.xiaoins.admin.role.dto.RoleUpdateDTO;
import com.xiaoins.admin.role.vo.PermissionVO;
import com.xiaoins.admin.role.vo.RoleVO;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService {

    /**
     * 分页查询角色列表
     */
    IPage<RoleVO> getRolePage(RoleQueryDTO queryDTO);

    /**
     * 查询角色详情
     */
    RoleVO getRoleById(Long roleId);

    /**
     * 创建角色
     */
    void createRole(RoleCreateDTO createDTO);

    /**
     * 更新角色
     */
    void updateRole(RoleUpdateDTO updateDTO);

    /**
     * 删除角色
     */
    void deleteRole(Long roleId);

    /**
     * 启用/禁用角色
     */
    void updateRoleStatus(Long roleId, Integer status);

    /**
     * 分配角色权限
     */
    void assignPermissions(RolePermissionDTO permissionDTO);

    /**
     * 查询角色的权限列表
     */
    List<PermissionVO> getRolePermissions(Long roleId);

    /**
     * 查询所有权限列表
     */
    List<PermissionVO> getAllPermissions();
}

