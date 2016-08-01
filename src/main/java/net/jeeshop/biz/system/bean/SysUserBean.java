package net.jeeshop.biz.system.bean;


import net.jeeshop.biz.system.model.SysUser;

/**
 * @author dinguangx@163.com
 * @date 2015-12-24 23:03
 */
public class SysUserBean extends SysUser {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
