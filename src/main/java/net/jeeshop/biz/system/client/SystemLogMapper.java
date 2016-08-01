package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.SystemLog;
import net.jeeshop.biz.system.model.SystemLogExample;

public interface SystemLogMapper extends BaseMapper<SystemLog, SystemLogExample> {
    int countByExample(SystemLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    List<SystemLog> selectByExample(SystemLogExample example);

    SystemLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}