package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.client.SystemSettingMapper;
import net.jeeshop.biz.system.model.SystemSetting;
import net.jeeshop.biz.system.model.SystemSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dinguangx@163.com
 * @date 2015-12-22 23:50
 */
@Service
public class SystemSettingService extends BaseService<SystemSetting, SystemSettingExample> {
    @Autowired
    SystemSettingMapper systemSettingMapper;
    @Override
    protected BaseMapper<SystemSetting, SystemSettingExample> getMapper() {
        return systemSettingMapper;
    }
}
