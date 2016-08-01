package net.jeeshop.biz.product.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;

public class ProcessInfo extends BaseModel implements Serializable {

    /** 新号库id */
    private Integer newnumId;

    /** 新号个数 */
    private Integer newnums;

    /** 老号库id */
    private Integer oldnumId;

    /** 套餐id */
    private Integer packageid;

    /** 是否上传身份证 0否 1是 */
    private String upidcard;

    /** 流程名称 */
    private String name;

    /** pro_process.username */
    private String username;

    /** pro_process.usertel */
    private String usertel;

    /** pro_process.userid */
    private String userid;

    /** pro_process.useraddr */
    private String useraddr;

    /** pro_process.khid */
    private String khid;

    /** 填写信息 */
    private String info;

    private static final long serialVersionUID = 1L;


    public Integer getNewnumId() {
        return newnumId;
    }

    public void setNewnumId(Integer newnumId) {
        this.newnumId = newnumId;
    }

    public Integer getNewnums() {
        return newnums;
    }

    public void setNewnums(Integer newnums) {
        this.newnums = newnums;
    }

    public Integer getOldnumId() {
        return oldnumId;
    }

    public void setOldnumId(Integer oldnumId) {
        this.oldnumId = oldnumId;
    }

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public String getUpidcard() {
        return upidcard;
    }

    public void setUpidcard(String upidcard) {
        this.upidcard = upidcard == null ? null : upidcard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr == null ? null : useraddr.trim();
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    @Override
    public String toString() {
        return "{" +
                "newnumId=" + newnumId +
                ", newnums=" + newnums +
                ", oldnumId=" + oldnumId +
                ", packageid=" + packageid +
                ", upidcard='" + upidcard + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", usertel='" + usertel + '\'' +
                ", userid='" + userid + '\'' +
                ", useraddr='" + useraddr + '\'' +
                ", khid=" + khid +
                ", info='" + info + '\'' +
                '}';
    }
}