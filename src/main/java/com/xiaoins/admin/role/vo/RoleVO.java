package com.xiaoins.admin.role.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色视图对象
 */
@Data
@Schema(description = "角色信息")
public class RoleVO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色描述")
    private String description;

    @Schema(description = "状态（0禁用 1启用）")
    private Integer status;

    @Schema(description = "状态描述")
    private String statusDesc;

    @Schema(description = "权限数量")
    private Integer permissionCount;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}

