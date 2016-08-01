package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsPaymentAwardLink;
import net.jeeshop.biz.finance.model.PointsPaymentAwardLinkExample;

public interface PointsPaymentAwardLinkMapper extends BaseMapper<PointsPaymentAwardLink, PointsPaymentAwardLinkExample> {
    int countByExample(PointsPaymentAwardLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsPaymentAwardLink record);

    int insertSelective(PointsPaymentAwardLink record);

    List<PointsPaymentAwardLink> selectByExample(PointsPaymentAwardLinkExample example);

    PointsPaymentAwardLink selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsPaymentAwardLink record);

    int updateByPrimaryKey(PointsPaymentAwardLink record);
}