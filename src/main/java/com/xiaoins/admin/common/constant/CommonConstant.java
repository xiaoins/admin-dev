package com.xiaoins.admin.common.constant;

/**
 * 通用常量
 */
public interface CommonConstant {

    /**
     * 状态：启用
     */
    Integer STATUS_ENABLE = 1;

    /**
     * 状态：禁用
     */
    Integer STATUS_DISABLE = 0;

    /**
     * 删除标志：未删除
     */
    Integer NOT_DELETED = 0;

    /**
     * 删除标志：已删除
     */
    Integer DELETED = 1;

    /**
     * 顶级分类父ID
     */
    Long TOP_CATEGORY_PARENT_ID = 0L;

    /**
     * UTF-8编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON内容类型
     */
    String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";
}

