package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsAccountHistory;
import net.jeeshop.biz.finance.model.PointsAccountHistoryExample;

public interface PointsAccountHistoryMapper extends BaseMapper<PointsAccountHistory, PointsAccountHistoryExample> {
    int countByExample(PointsAccountHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsAccountHistory record);

    int insertSelective(PointsAccountHistory record);

    List<PointsAccountHistory> selectByExample(PointsAccountHistoryExample example);

    PointsAccountHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsAccountHistory record);

    int updateByPrimaryKey(PointsAccountHistory record);
}