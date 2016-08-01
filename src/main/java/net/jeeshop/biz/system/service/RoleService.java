package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.SysRoleBean;
import net.jeeshop.biz.system.client.SysPrivilegeMapperExt;
import net.jeeshop.biz.system.client.SysRoleMapper;
import net.jeeshop.biz.system.model.SysPrivilege;
import net.jeeshop.biz.system.model.SysRole;
import net.jeeshop.biz.system.model.SysRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dinguangx@163.com
 * @date 2015-12-19 00:14
 */
@Service
public class RoleService extends BaseService<SysRole, SysRoleExample> {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysPrivilegeMapperExt sysPrivilegeMapperExt;
    @Override
    protected BaseMapper<SysRole, SysRoleExample> getMapper() {
        return sysRoleMapper;
    }

    @Transactional
    public void addRole(SysRoleBean role) {
        sysRoleMapper.insert(role);
        String rolePrivilege = role.getPrivileges();
        String[] rolePrivileges = rolePrivilege.split(",");
        List<SysPrivilege> privilegeList = fillPrivilege(rolePrivileges , role.getId());
        if (privilegeList.size() > 0) {
//            sysPrivilegeMapperExt.insertPrivileges(privilegeList);
            for (SysPrivilege sysPrivilege:privilegeList){
                sysPrivilegeMapperExt.insertSelective(sysPrivilege);
            }
        }
    }

    @Transactional
    public void updateRole(SysRoleBean role)
    {
        sysRoleMapper.updateByPrimaryKey(role);
        //删除现有角色权限
        sysPrivilegeMapperExt.deleteByRoleId(role.getId());
        //添加新的权限
        String rolePrivilege = role.getPrivileges();
        String[] rolePrivileges = rolePrivilege.split(",");
        List<SysPrivilege> privilegeList = fillPrivilege(rolePrivileges , role.getId());
//        if (privilegeList.size() > 0) {
//            logger.debug("权限列表{}",privilegeList);
//            sysPrivilegeMapperExt.insertPrivileges(privilegeList);
//        }
        for (SysPrivilege sysPrivilege:privilegeList){
            sysPrivilegeMapperExt.insertSelective(sysPrivilege);
        }
    }

    private List<SysPrivilege> fillPrivilege(String [] rolePrivileges , Long rid)
    {
        List<SysPrivilege> privilegeList = new ArrayList<SysPrivilege>();
        for (String privilege : rolePrivileges)
        {
            SysPrivilege item = new SysPrivilege();
            item.setRoleId(rid);
            item.setResourceId(Long.parseLong(privilege));
            item.setCreateTime(new Date());
            privilegeList.add(item);
        }
        return privilegeList;
    }
}
