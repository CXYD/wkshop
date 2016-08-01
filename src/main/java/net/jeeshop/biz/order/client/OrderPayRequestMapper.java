package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderPayRequest;
import net.jeeshop.biz.order.model.OrderPayRequestExample;

public interface OrderPayRequestMapper extends BaseMapper<OrderPayRequest, OrderPayRequestExample> {
    int countByExample(OrderPayRequestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPayRequest record);

    int insertSelective(OrderPayRequest record);

    List<OrderPayRequest> selectByExample(OrderPayRequestExample example);

    OrderPayRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPayRequest record);

    int updateByPrimaryKey(OrderPayRequest record);
}