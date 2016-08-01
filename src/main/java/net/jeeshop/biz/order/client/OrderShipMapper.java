package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderShip;
import net.jeeshop.biz.order.model.OrderShipExample;

public interface OrderShipMapper extends BaseMapper<OrderShip, OrderShipExample> {
    int countByExample(OrderShipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderShip record);

    int insertSelective(OrderShip record);

    List<OrderShip> selectByExample(OrderShipExample example);

    OrderShip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderShip record);

    int updateByPrimaryKey(OrderShip record);
}