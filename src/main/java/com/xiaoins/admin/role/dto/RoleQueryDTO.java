package com.xiaoins.admin.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色查询DTO
 */
@Data
@Schema(description = "角色查询条件")
public class RoleQueryDTO {

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize = 10;

    @Schema(description = "角色名称", example = "管理员")
    private String roleName;

    @Schema(description = "角色编码", example = "ROLE_ADMIN")
    private String roleCode;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status;
}

