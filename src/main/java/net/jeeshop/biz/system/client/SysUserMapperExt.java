package net.jeeshop.biz.system.client;

import net.jeeshop.biz.system.bean.SysUserBean;

import java.util.List;

/**
 * @author dinguangx@163.com
 * @date 2015-12-22 22:57
 */
public interface SysUserMapperExt {
    public List<SysUserBean> selectByParams(SysUserBean params);

    SysUserBean selectUserBeanById(long uid);
}
