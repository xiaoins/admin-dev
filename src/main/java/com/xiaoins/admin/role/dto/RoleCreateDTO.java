package com.xiaoins.admin.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 创建角色DTO
 */
@Data
@Schema(description = "创建角色请求")
public class RoleCreateDTO {

    @Schema(description = "角色名称", example = "产品经理")
    @NotBlank(message = "角色名称不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9_]{2,20}$", message = "角色名称为2-20位中文、字母、数字或下划线")
    private String roleName;

    @Schema(description = "角色编码", example = "ROLE_PRODUCT_MANAGER")
    @NotBlank(message = "角色编码不能为空")
    @Pattern(regexp = "^[A-Z_]{2,50}$", message = "角色编码必须是2-50位大写字母或下划线")
    private String roleCode;

    @Schema(description = "角色描述", example = "负责产品规划和需求管理")
    private String description;

    @Schema(description = "状态（0禁用 1启用）", example = "1")
    private Integer status = 1;
}

