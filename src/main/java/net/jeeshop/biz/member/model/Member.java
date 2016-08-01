package net.jeeshop.biz.member.model;

import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.member.enums.IdType;

import java.io.Serializable;
import java.util.Date;

public class Member extends BaseModel implements Serializable {
    /** 登录名 */
    private String username;

    /** 昵称 */
    private String nickName;

    /** 密码 */
    private String password;

    /** 联系地址 */
    private String address;

    /** 真实姓名 */
    private String realName;

    /** 性别-男-女-保密 */
    private String gender;

    /** 电子邮箱 */
    private String email;

    /** 出生日期 */
    private Date birthDate;

    /** 联系电话 */
    private String mobile;

    /** 证件类型 */
    private IdType idType;

    /** 证件号码 */
    private String idNum;

    /** 省份代码 */
    private String province;

    /** 地市代码 */
    private String city;

    /** 区县代码 */
    private String area;

    /** 邮箱是否已经激活,1-是0-否 */
    private Boolean isEmailActive;

    /** 是否冻结，1-是0-否 */
    private Boolean isFreeze;

    /** 最后登录时间 */
    private Date lastLoginTime;

    /** 最后登录IP */
    private String lastLoginIp;

    /** 最后登录区域 */
    private String lastLoginArea;

    /** 冻结起始时间 */
    private Date freezeStartTime;

    /** 冻结结束时间 */
    private Date freezeEndTime;

    /** 会员等级ID */
    private Long memberRankId;

    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
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

    public Boolean getIsEmailActive() {
        return isEmailActive;
    }

    public void setIsEmailActive(Boolean isEmailActive) {
        this.isEmailActive = isEmailActive;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean isFreeze) {
        this.isFreeze = isFreeze;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastLoginArea() {
        return lastLoginArea;
    }

    public void setLastLoginArea(String lastLoginArea) {
        this.lastLoginArea = lastLoginArea == null ? null : lastLoginArea.trim();
    }
    public Date getFreezeStartTime() {
        return freezeStartTime;
    }

    public void setFreezeStartTime(Date freezeStartTime) {
        this.freezeStartTime = freezeStartTime;
    }

    public Date getFreezeEndTime() {
        return freezeEndTime;
    }

    public void setFreezeEndTime(Date freezeEndTime) {
        this.freezeEndTime = freezeEndTime;
    }

    public Long getMemberRankId() {
        return memberRankId;
    }

    public void setMemberRankId(Long memberRankId) {
        this.memberRankId = memberRankId;
    }
}