package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.RefundOrder;
import net.jeeshop.biz.order.model.RefundOrderExample;

public interface RefundOrderMapper extends BaseMapper<RefundOrder, RefundOrderExample> {
    int countByExample(RefundOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    List<RefundOrder> selectByExample(RefundOrderExample example);

    RefundOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}