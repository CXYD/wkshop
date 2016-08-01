package net.jeeshop.biz.shop.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.shop.model.PaySet;
import net.jeeshop.biz.shop.model.PaySetExample;

public interface PaySetMapper extends BaseMapper<PaySet, PaySetExample> {
    int countByExample(PaySetExample example);

    int insert(PaySet record);

    int insertSelective(PaySet record);

    List<PaySet> selectByExample(PaySetExample example);

    void upPaySet(PaySet paySet);

    PaySet getPaySet(PaySet paySet);
}