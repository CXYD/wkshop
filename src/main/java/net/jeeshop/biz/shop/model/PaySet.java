package net.jeeshop.biz.shop.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class PaySet extends BaseModel implements Serializable {
    /** pay_set.llpay */
    private Integer llpay;

    /** pay_set.alipay */
    private Integer alipay;

    /** pay_set.delaypay */
    private Integer delaypay;

    /** pay_set.khid */
    private String khid;

    private static final long serialVersionUID = 1L;

    public Integer getLlpay() {
        return llpay;
    }

    public void setLlpay(Integer llpay) {
        this.llpay = llpay;
    }

    public Integer getAlipay() {
        return alipay;
    }

    public void setAlipay(Integer alipay) {
        this.alipay = alipay;
    }

    public Integer getDelaypay() {
        return delaypay;
    }

    public void setDelaypay(Integer delaypay) {
        this.delaypay = delaypay;
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public PaySet() {
    }

    public PaySet(String khid) {
        this.khid = khid;
    }

    public PaySet(Integer llpay, Integer alipay, Integer delaypay) {
        this.llpay = llpay;
        this.alipay = alipay;
        this.delaypay = delaypay;
    }
}