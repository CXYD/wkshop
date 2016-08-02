package net.jeeshop.biz.order.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.Order;
import net.jeeshop.biz.order.model.OrderExample;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderBean, OrderExample> {
    int countByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<OrderBean> selectByExample(OrderExample example);

    OrderBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderBean> selectBalPay(OrderExample example);

    List<OrderBean> queryOrder(OrderExample orderExample);

    List<OrderBean> querySettleRecords(OrderExample orderExample);
}