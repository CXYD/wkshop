package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PaymentType;
import net.jeeshop.biz.finance.model.PaymentTypeExample;

public interface PaymentTypeMapper extends BaseMapper<PaymentType, PaymentTypeExample> {
    int countByExample(PaymentTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentType record);

    int insertSelective(PaymentType record);

    List<PaymentType> selectByExample(PaymentTypeExample example);

    PaymentType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentType record);

    int updateByPrimaryKey(PaymentType record);
}