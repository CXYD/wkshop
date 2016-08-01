package net.jeeshop.biz.system.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.SystemSetting;
import net.jeeshop.biz.system.model.SystemSettingExample;

public interface SystemSettingMapper extends BaseMapper<SystemSetting, SystemSettingExample> {
    int countByExample(SystemSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemSetting record);

    int insertSelective(SystemSetting record);

    List<SystemSetting> selectByExample(SystemSettingExample example);

    SystemSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemSetting record);

    int updateByPrimaryKey(SystemSetting record);
}