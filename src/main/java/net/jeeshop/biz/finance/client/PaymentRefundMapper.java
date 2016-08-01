package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PaymentRefund;
import net.jeeshop.biz.finance.model.PaymentRefundExample;

public interface PaymentRefundMapper extends BaseMapper<PaymentRefund, PaymentRefundExample> {
    int countByExample(PaymentRefundExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentRefund record);

    int insertSelective(PaymentRefund record);

    List<PaymentRefund> selectByExample(PaymentRefundExample example);

    PaymentRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentRefund record);

    int updateByPrimaryKey(PaymentRefund record);
}