package net.jeeshop.biz.finance.bean;

import com.google.common.collect.Maps;
import net.jeeshop.biz.finance.enums.PaymentTypeCode;

import java.util.Map;

/**
 * @author dylan
 * @date 16/3/8 22:02
 * Email: dinguangx@163.com
 */
public class PaymentItemBean {
    /**
     * 支付方式
     */
    private PaymentTypeCode paymentType;
    /**
     * 支付金额
     */
    private double amount;
    /**
     * 扩展信息
     */
    private Map<String,String> extraInfos = Maps.newHashMap();

    public PaymentTypeCode getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeCode paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Map<String, String> getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(Map<String, String> extraInfos) {
        this.extraInfos = extraInfos;
    }
}
