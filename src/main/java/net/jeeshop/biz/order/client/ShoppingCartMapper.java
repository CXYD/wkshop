package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.ShoppingCart;
import net.jeeshop.biz.order.model.ShoppingCartExample;

public interface ShoppingCartMapper extends BaseMapper<ShoppingCart, ShoppingCartExample> {
    int countByExample(ShoppingCartExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}