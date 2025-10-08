package com.xiaoins.admin.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 角色权限分配DTO
 */
@Data
@Schema(description = "角色权限分配请求")
public class RolePermissionDTO {

    @Schema(description = "角色ID", example = "4")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @Schema(description = "权限ID列表", example = "[1, 2, 3]")
    @NotEmpty(message = "权限列表不能为空")
    private List<Long> permissionIds;
}

