package net.jeeshop.biz.member.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class MemberAddress extends BaseModel implements Serializable {
    /** 收货人 */
    private String receiver;

    /** 省份代码 */
    private String province;

    /** 地市代码 */
    private String city;

    /** 区县代码 */
    private String area;

    /** 联系地址 */
    private String address;

    /** 邮政编码 */
    private String postcode;

    /** 联系电话 */
    private String mobile;

    /** 电话号码 */
    private String phone;

    /** 是否默认地址，1-是0-否 */
    private Boolean isDefault;

    /** 会员ID */
    private Long memberId;

    private static final long serialVersionUID = 1L;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}