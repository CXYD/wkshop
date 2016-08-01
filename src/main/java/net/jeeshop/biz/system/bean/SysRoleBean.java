package net.jeeshop.biz.system.bean;

import net.jeeshop.biz.system.model.SysRole;

/**
 * @author dylan
 * @date 16/1/14 22:50
 * Email: dinguangx@163.com
 */
public class SysRoleBean extends SysRole {

    private String privileges;

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
}
