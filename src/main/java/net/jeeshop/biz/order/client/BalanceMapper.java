package net.jeeshop.biz.order.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.Balance;
import net.jeeshop.biz.order.model.BalanceExample;

import java.util.List;

public interface BalanceMapper extends BaseMapper<Balance, BalanceExample> {
    int countByExample(BalanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Balance record);

    int insertSelective(Balance record);

    List<Balance> selectByExample(BalanceExample example);

    Balance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Balance record);

    int updateByPrimaryKey(Balance record);

    int addsettleRecordsBatch(List<Balance> list);
}
