package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.RefundOrderItem;
import net.jeeshop.biz.order.model.RefundOrderItemExample;

public interface RefundOrderItemMapper extends BaseMapper<RefundOrderItem, RefundOrderItemExample> {
    int countByExample(RefundOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundOrderItem record);

    int insertSelective(RefundOrderItem record);

    List<RefundOrderItem> selectByExample(RefundOrderItemExample example);

    RefundOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefundOrderItem record);

    int updateByPrimaryKey(RefundOrderItem record);
}