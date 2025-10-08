package com.xiaoins.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地区分布数据项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "地区分布数据项")
public class RegionDistributionItemVO {

    @Schema(description = "省份/城市名称")
    private String name;

    @Schema(description = "用户数量")
    private Long value;
}

