package net.jeeshop.biz.order.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.RefundPayment;
import net.jeeshop.biz.order.model.RefundPaymentExample;

public interface RefundPaymentMapper extends BaseMapper<RefundPayment, RefundPaymentExample> {
    int countByExample(RefundPaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundPayment record);

    int insertSelective(RefundPayment record);

    List<RefundPayment> selectByExample(RefundPaymentExample example);

    RefundPayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefundPayment record);

    int updateByPrimaryKey(RefundPayment record);
}