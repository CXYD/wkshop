package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PaymentItem;
import net.jeeshop.biz.finance.model.PaymentItemExample;

public interface PaymentItemMapper extends BaseMapper<PaymentItem, PaymentItemExample> {
    int countByExample(PaymentItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentItem record);

    int insertSelective(PaymentItem record);

    List<PaymentItem> selectByExample(PaymentItemExample example);

    PaymentItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentItem record);

    int updateByPrimaryKey(PaymentItem record);
}