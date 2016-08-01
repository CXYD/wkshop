package net.jeeshop.biz.finance.paygate.bean;

import net.jeeshop.biz.finance.paygate.PaygateType;

/**
 * @author dylan
 * @date 16/3/13 12:21
 * Email: dinguangx@163.com
 */
public class PayRequestBean {
    private PaygateType paygateType;//网关类型
    private String orderNum;//订单号
    private String comment;// 商品描述
    private double amount;//支付金额
    private String fgNotifyUrl;//前台跳转地址
    private String bgNotifyUrl;//后台通知地址
//    private boolean mobilePay;//扩展字段，是否手机支付
    private String bankCode;//扩展字段，银行直联使用

    public PaygateType getPaygateType() {
        return paygateType;
    }

    public void setPaygateType(PaygateType paygateType) {
        this.paygateType = paygateType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFgNotifyUrl() {
        return fgNotifyUrl;
    }

    public void setFgNotifyUrl(String fgNotifyUrl) {
        this.fgNotifyUrl = fgNotifyUrl;
    }

    public String getBgNotifyUrl() {
        return bgNotifyUrl;
    }

    public void setBgNotifyUrl(String bgNotifyUrl) {
        this.bgNotifyUrl = bgNotifyUrl;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
