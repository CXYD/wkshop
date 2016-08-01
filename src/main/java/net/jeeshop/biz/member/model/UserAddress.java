package net.jeeshop.biz.member.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;

public class UserAddress extends BaseModel implements Serializable {
    /** user_address.id */
    private Long id;
    /** 手机号码 */
    private String phone;

    /** 收货人姓名 */
    private String consigneename;
    private String username;

    /** 地址 */
    private String address;

    /** 默认标记 0:不默认1:默认 */
    private String defaulttype;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDefaulttype() {
        return defaulttype;
    }

    public void setDefaulttype(String defaulttype) {
        this.defaulttype = defaulttype == null ? null : defaulttype.trim();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}