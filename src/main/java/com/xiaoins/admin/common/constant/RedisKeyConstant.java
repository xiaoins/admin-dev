package com.xiaoins.admin.common.constant;

/**
 * Redis Key常量
 */
public interface RedisKeyConstant {

    /**
     * 登录验证码前缀
     */
    String CAPTCHA_PREFIX = "captcha:";

    /**
     * 登录失败次数前缀
     */
    String LOGIN_FAIL_COUNT_PREFIX = "login:fail:count:";

    /**
     * 用户锁定前缀
     */
    String USER_LOCK_PREFIX = "user:lock:";

    /**
     * Token黑名单前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "token:blacklist:";

    /**
     * 分类缓存前缀
     */
    String CATEGORY_TREE_PREFIX = "category:tree";

    /**
     * 商品缓存前缀
     */
    String PRODUCT_PREFIX = "product:";

    /**
     * 分布式锁前缀
     */
    String LOCK_PREFIX = "lock:";

    /**
     * 幂等键前缀
     */
    String IDEMPOTENT_PREFIX = "idempotent:";
}

