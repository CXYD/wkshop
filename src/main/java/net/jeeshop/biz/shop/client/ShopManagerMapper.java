package net.jeeshop.biz.shop.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;
import net.jeeshop.web.util.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public interface ShopManagerMapper extends BaseMapper<SysUser,SysUserExample>{

    void addManager(SysUser sysUser);

    SysUser getManagerByName(SysUser sysUser);

    Integer getUserCount(String khid);

    List<SysUser> getUserList(Page<SysUser> page);

    SysUser getUser(String username);

    void upManager(SysUser sysUser);

    SysUser getManagerNoID(SysUser sysUser);

    void delManager(SysUser sysUser);
}
