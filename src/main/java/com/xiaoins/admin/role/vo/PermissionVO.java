package com.xiaoins.admin.role.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限视图对象
 */
@Data
@Schema(description = "权限信息")
public class PermissionVO {

    @Schema(description = "权限ID")
    private Long permissionId;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限名称")
    private String permissionName;

    @Schema(description = "权限编码")
    private String permissionCode;

    @Schema(description = "资源类型")
    private String resourceType;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}

