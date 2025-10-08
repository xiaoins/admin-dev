package com.xiaoins.admin.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoins.admin.common.exception.BusinessException;
import com.xiaoins.admin.common.result.ResultCode;
import com.xiaoins.admin.order.dto.OrderQueryDTO;
import com.xiaoins.admin.order.entity.Order;
import com.xiaoins.admin.order.mapper.OrderMapper;
import com.xiaoins.admin.order.service.OrderService;
import com.xiaoins.admin.order.vo.OrderVO;
import com.xiaoins.admin.user.entity.SysUser;
import com.xiaoins.admin.user.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 订单服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final SysUserMapper sysUserMapper;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public IPage<OrderVO> getOrderPage(OrderQueryDTO queryDTO) {
        // 构建分页对象
        Page<Order> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(queryDTO.getOrderNo()), Order::getOrderNo, queryDTO.getOrderNo())
                .eq(queryDTO.getUserId() != null, Order::getUserId, queryDTO.getUserId())
                .eq(queryDTO.getStatus() != null, Order::getStatus, queryDTO.getStatus());

        // 时间范围查询
        if (StrUtil.isNotBlank(queryDTO.getStartTime())) {
            LocalDateTime startTime = LocalDateTime.parse(queryDTO.getStartTime(), DATE_TIME_FORMATTER);
            wrapper.ge(Order::getCreateTime, startTime);
        }
        if (StrUtil.isNotBlank(queryDTO.getEndTime())) {
            LocalDateTime endTime = LocalDateTime.parse(queryDTO.getEndTime(), DATE_TIME_FORMATTER);
            wrapper.le(Order::getCreateTime, endTime);
        }

        wrapper.orderByDesc(Order::getCreateTime);

        // 执行查询
        IPage<Order> orderPage = orderMapper.selectPage(page, wrapper);

        // 转换为VO
        IPage<OrderVO> voPage = orderPage.convert(this::convertToVO);

        return voPage;
    }

    @Override
    public OrderVO getOrderById(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_FOUND);
        }

        return convertToVO(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shipOrder(Long orderId) {
        // 检查订单是否存在
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_FOUND);
        }

        // 检查订单状态（只有已付款的订单才能发货）
        if (order.getStatus() != 1) {
            throw new BusinessException(ResultCode.ORDER_STATUS_ERROR.getCode(), "只有已付款的订单才能发货");
        }

        // 更新订单状态
        order.setStatus(2);
        order.setSendTime(LocalDateTime.now());
        orderMapper.updateById(order);

        log.info("订单发货成功，订单ID: {}, 订单号: {}", orderId, order.getOrderNo());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishOrder(Long orderId) {
        // 检查订单是否存在
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_FOUND);
        }

        // 检查订单状态（只有已发货的订单才能完成）
        if (order.getStatus() != 2) {
            throw new BusinessException(ResultCode.ORDER_STATUS_ERROR.getCode(), "只有已发货的订单才能完成");
        }

        // 更新订单状态
        order.setStatus(3);
        order.setFinishTime(LocalDateTime.now());
        orderMapper.updateById(order);

        log.info("订单完成成功，订单ID: {}, 订单号: {}", orderId, order.getOrderNo());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long orderId) {
        // 检查订单是否存在
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException(ResultCode.ORDER_NOT_FOUND);
        }

        // 检查订单状态（已发货和已完成的订单不能取消）
        if (order.getStatus() >= 2) {
            throw new BusinessException(ResultCode.ORDER_CANNOT_CANCEL);
        }

        // 更新订单状态
        order.setStatus(4);
        orderMapper.updateById(order);

        log.info("订单取消成功，订单ID: {}, 订单号: {}", orderId, order.getOrderNo());
    }

    /**
     * 转换为VO
     */
    private OrderVO convertToVO(Order order) {
        OrderVO vo = BeanUtil.copyProperties(order, OrderVO.class);

        // 设置状态描述
        vo.setStatusDesc(getStatusDesc(order.getStatus()));

        // 设置用户名
        SysUser user = sysUserMapper.selectById(order.getUserId());
        if (user != null) {
            vo.setUsername(user.getUsername());
        }

        return vo;
    }

    /**
     * 获取状态描述
     */
    private String getStatusDesc(Integer status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case 0:
                return "待付款";
            case 1:
                return "已付款";
            case 2:
                return "已发货";
            case 3:
                return "已完成";
            case 4:
                return "已取消";
            default:
                return "未知";
        }
    }
}

