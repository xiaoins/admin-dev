package com.xiaoins.admin.marketing.controller;

import com.xiaoins.admin.marketing.service.CouponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券控制器
 */
@Tag(name = "优惠券管理", description = "优惠券的创建、发放、查询等操作")
@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    // 优惠券相关接口
}

