package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.client.SysPrivilegeMapper;
import net.jeeshop.biz.system.model.SysPrivilege;
import net.jeeshop.biz.system.model.SysPrivilegeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by carlosxiao on 30/12/2015.
 */

@Service
public class PrivilegeService extends BaseService<SysPrivilege, SysPrivilegeExample>
{
    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;

    @Override
    protected BaseMapper<SysPrivilege, SysPrivilegeExample> getMapper() 
    {
        return sysPrivilegeMapper;
    }
}
