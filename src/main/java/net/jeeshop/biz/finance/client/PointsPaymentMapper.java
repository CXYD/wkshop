package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsPayment;
import net.jeeshop.biz.finance.model.PointsPaymentExample;

public interface PointsPaymentMapper extends BaseMapper<PointsPayment, PointsPaymentExample> {
    int countByExample(PointsPaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsPayment record);

    int insertSelective(PointsPayment record);

    List<PointsPayment> selectByExample(PointsPaymentExample example);

    PointsPayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsPayment record);

    int updateByPrimaryKey(PointsPayment record);
}