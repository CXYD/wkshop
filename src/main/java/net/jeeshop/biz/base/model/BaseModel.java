package net.jeeshop.biz.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dylan
 * @date 15/8/18 21:54
 * Email: dinguangx@163.com
 */
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
    private Date createTime;
    private String createAccount;
    private Date updateTime;
    private String updateAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }


}
