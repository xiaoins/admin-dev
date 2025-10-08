package com.xiaoins.admin.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoins.admin.order.dto.OrderQueryDTO;
import com.xiaoins.admin.order.vo.OrderVO;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 分页查询订单列表
     */
    IPage<OrderVO> getOrderPage(OrderQueryDTO queryDTO);

    /**
     * 查询订单详情
     */
    OrderVO getOrderById(Long orderId);

    /**
     * 更新订单状态（发货）
     */
    void shipOrder(Long orderId);

    /**
     * 完成订单
     */
    void finishOrder(Long orderId);

    /**
     * 取消订单
     */
    void cancelOrder(Long orderId);
}
