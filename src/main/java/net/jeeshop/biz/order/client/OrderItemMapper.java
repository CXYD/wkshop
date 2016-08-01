package net.jeeshop.biz.order.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderItem;
import net.jeeshop.biz.order.model.OrderItemExample;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemExample> {
    int countByExample(OrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExampleWithBLOBs(OrderItemExample example);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKeyWithBLOBs(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}