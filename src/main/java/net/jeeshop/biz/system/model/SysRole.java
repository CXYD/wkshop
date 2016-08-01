package net.jeeshop.biz.system.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class SysRole extends BaseModel implements Serializable {
    /** è§’è‰²å��ç§° */
    private String roleName;

    /** è§’è‰²æ��è¿° */
    private String roleDesc;

    /** æ˜¯å�¦æœ‰æ•ˆ,1-æ˜¯0-å�¦ */
    private Boolean isValid;

    private static final long serialVersionUID = 1L;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}