package com.xiaoins.admin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户查询条件DTO
 */
@Data
@Schema(description = "用户查询条件")
public class UserQueryDTO {

    @Schema(description = "用户名（模糊查询）")
    private String username;

    @Schema(description = "真实姓名（模糊查询）")
    private String realName;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "状态（0禁用 1启用）")
    private Integer status;

    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页大小", example = "10")
    private Integer pageSize = 10;
}

