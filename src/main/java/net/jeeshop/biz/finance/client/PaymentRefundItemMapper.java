package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PaymentRefundItem;
import net.jeeshop.biz.finance.model.PaymentRefundItemExample;

public interface PaymentRefundItemMapper extends BaseMapper<PaymentRefundItem, PaymentRefundItemExample> {
    int countByExample(PaymentRefundItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentRefundItem record);

    int insertSelective(PaymentRefundItem record);

    List<PaymentRefundItem> selectByExample(PaymentRefundItemExample example);

    PaymentRefundItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentRefundItem record);

    int updateByPrimaryKey(PaymentRefundItem record);
}