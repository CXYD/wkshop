package net.jeeshop.biz.system.client;

import net.jeeshop.biz.system.model.SysPrivilege;

import java.util.List;

public interface SysPrivilegeMapperExt extends SysPrivilegeMapper 
{

    void insertPrivileges(List<SysPrivilege> privileges);

    /***
     * 删除指定角色的权限
     * @param id
     */
    void deleteByRoleId(Long id);
}