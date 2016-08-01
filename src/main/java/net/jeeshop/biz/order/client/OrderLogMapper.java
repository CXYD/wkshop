package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderLog;
import net.jeeshop.biz.order.model.OrderLogExample;

public interface OrderLogMapper extends BaseMapper<OrderLog, OrderLogExample> {
    int countByExample(OrderLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderLog record);

    int insertSelective(OrderLog record);

    List<OrderLog> selectByExample(OrderLogExample example);

    OrderLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderLog record);

    int updateByPrimaryKey(OrderLog record);
}