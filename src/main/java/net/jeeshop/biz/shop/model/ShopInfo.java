package net.jeeshop.biz.shop.model;

import java.io.Serializable;
import java.util.Date;
import net.jeeshop.biz.base.model.BaseModel;

public class ShopInfo extends BaseModel implements Serializable {
    /** shop_info.shopname */
    private String shopname;

    /** shop_info.createtime */
    private Date createtime;

    /** shop_info.khid */
    private String khid;

    /** shop_info.updatetime */
    private Date updatetime;

    /** shop_info.updateAccount */
    private String updateaccount;

    /** shop_info.status */
    private String status;

    /** shop_info.servicecity */
    private String servicecity;

    /** shop_info.shopurl */
    private String shopurl;

    /** shop_info.shopdesc */
    private String shopdesc;

    /** shop_info.logurl */
    private String logurl;

    /** shop_info.linkmantel */
    private String linkmantel;

    /** shop_info.servicetel */
    private String servicetel;

    /** shop_info.address */
    private String address;

    /** shop_info.linkAccount */
    private String linkaccount;

    /** shop_info.servicesheng */
    private String servicesheng;

    /** shop_info.quhao */
    private String quhao;

    private static final long serialVersionUID = 1L;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateaccount() {
        return updateaccount;
    }

    public void setUpdateaccount(String updateaccount) {
        this.updateaccount = updateaccount == null ? null : updateaccount.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getServicecity() {
        return servicecity;
    }

    public void setServicecity(String servicecity) {
        this.servicecity = servicecity == null ? null : servicecity.trim();
    }

    public String getShopurl() {
        return shopurl;
    }

    public void setShopurl(String shopurl) {
        this.shopurl = shopurl == null ? null : shopurl.trim();
    }

    public String getShopdesc() {
        return shopdesc;
    }

    public void setShopdesc(String shopdesc) {
        this.shopdesc = shopdesc == null ? null : shopdesc.trim();
    }

    public String getLogurl() {
        return logurl;
    }

    public void setLogurl(String logurl) {
        this.logurl = logurl == null ? null : logurl.trim();
    }

    public String getLinkmantel() {
        return linkmantel;
    }

    public void setLinkmantel(String linkmantel) {
        this.linkmantel = linkmantel == null ? null : linkmantel.trim();
    }

    public String getServicetel() {
        return servicetel;
    }

    public void setServicetel(String servicetel) {
        this.servicetel = servicetel == null ? null : servicetel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLinkaccount() {
        return linkaccount;
    }

    public void setLinkaccount(String linkaccount) {
        this.linkaccount = linkaccount== null ? null : linkaccount.trim();
    }

    public String getServicesheng() {
        return servicesheng;
    }

    public void setServicesheng(String servicesheng) {
        this.servicesheng = servicesheng == null ? null : servicesheng.trim();
    }

    public String getQuhao() {
        return quhao;
    }

    public void setQuhao(String quhao) {
        this.quhao = quhao == null ? null : quhao.trim();
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "shopname='" + shopname + '\'' +
                ", createtime=" + createtime +
                ", khid='" + khid + '\'' +
                ", updatetime=" + updatetime +
                ", updateaccount='" + updateaccount + '\'' +
                ", status='" + status + '\'' +
                ", servicecity='" + servicecity + '\'' +
                ", shopurl='" + shopurl + '\'' +
                ", shopdesc='" + shopdesc + '\'' +
                ", logurl='" + logurl + '\'' +
                ", linkmantel='" + linkmantel + '\'' +
                ", servicetel='" + servicetel + '\'' +
                ", address='" + address + '\'' +
                ", linkaccount='" + linkaccount + '\'' +
                ", servicesheng='" + servicesheng + '\'' +
                ", quhao='" + quhao + '\'' +
                '}';
    }
}