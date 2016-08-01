package net.jeeshop.biz.order.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderPay;
import net.jeeshop.biz.order.model.OrderPayExample;

import java.util.List;

public interface OrderPayMapper extends BaseMapper<OrderPay, OrderPayExample> {
    int countByExample(OrderPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    List<OrderPay> selectByExample(OrderPayExample example);

    OrderPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}