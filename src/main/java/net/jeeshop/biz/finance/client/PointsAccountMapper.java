package net.jeeshop.biz.finance.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.finance.model.PointsAccount;
import net.jeeshop.biz.finance.model.PointsAccountExample;

public interface PointsAccountMapper extends BaseMapper<PointsAccount, PointsAccountExample> {
    int countByExample(PointsAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PointsAccount record);

    int insertSelective(PointsAccount record);

    List<PointsAccount> selectByExample(PointsAccountExample example);

    PointsAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsAccount record);

    int updateByPrimaryKey(PointsAccount record);
}