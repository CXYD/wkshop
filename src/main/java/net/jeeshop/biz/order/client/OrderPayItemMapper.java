package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderPayItem;
import net.jeeshop.biz.order.model.OrderPayItemExample;

public interface OrderPayItemMapper extends BaseMapper<OrderPayItem, OrderPayItemExample> {
    int countByExample(OrderPayItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPayItem record);

    int insertSelective(OrderPayItem record);

    List<OrderPayItem> selectByExample(OrderPayItemExample example);

    OrderPayItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPayItem record);

    int updateByPrimaryKey(OrderPayItem record);
}