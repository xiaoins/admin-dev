package com.xiaoins.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志实体
 */
@Data
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long logId;

    private Long userId;
    private String username;
    private String operation;
    private String method;
    private String params;
    private String ip;
    private Integer executeTime;
    private LocalDateTime createTime;
}

