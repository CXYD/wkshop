package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsAwardExpire;
import net.jeeshop.biz.finance.model.PointsAwardExpireExample;

public interface PointsAwardExpireMapper extends BaseMapper<PointsAwardExpire, PointsAwardExpireExample> {
    int countByExample(PointsAwardExpireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsAwardExpire record);

    int insertSelective(PointsAwardExpire record);

    List<PointsAwardExpire> selectByExample(PointsAwardExpireExample example);

    PointsAwardExpire selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsAwardExpire record);

    int updateByPrimaryKey(PointsAwardExpire record);
}