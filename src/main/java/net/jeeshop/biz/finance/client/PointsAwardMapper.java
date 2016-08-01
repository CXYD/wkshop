package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsAward;
import net.jeeshop.biz.finance.model.PointsAwardExample;

public interface PointsAwardMapper extends BaseMapper<PointsAward, PointsAwardExample> {
    int countByExample(PointsAwardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsAward record);

    int insertSelective(PointsAward record);

    List<PointsAward> selectByExample(PointsAwardExample example);

    PointsAward selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsAward record);

    int updateByPrimaryKey(PointsAward record);
}