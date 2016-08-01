package net.jeeshop.biz.system.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.KeyValue;
import net.jeeshop.biz.system.model.KeyValueExample;

import java.util.List;

public interface KeyValueMapper extends BaseMapper<KeyValue, KeyValueExample> {
    int countByExample(KeyValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KeyValue record);

    int insertSelective(KeyValue record);

    List<KeyValue> selectByExample(KeyValueExample example);

    KeyValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KeyValue record);

    int updateByPrimaryKey(KeyValue record);
}