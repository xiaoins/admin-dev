package com.xiaoins.admin.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限实体
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long permissionId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 资源类型（menu/button）
     */
    private String resourceType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

