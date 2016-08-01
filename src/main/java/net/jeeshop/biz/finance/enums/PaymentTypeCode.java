package net.jeeshop.biz.finance.enums;

import net.jeeshop.biz.finance.paygate.PaygateType;

/**
 * @author dylan
 * @date 16/3/8 23:03
 * Email: dinguangx@163.com
 */
public enum PaymentTypeCode {
    POINTS("积分", null),
    DUMMY("模拟网关", PaygateType.DUMMY),
    ALIPAY("支付宝", PaygateType.ALIPAY),
    TENPAY("财付通", PaygateType.TENPAY),
    OFFLINE("线下付款", null);
    private String desc;
    private PaygateType paygateType;
    PaymentTypeCode(String desc, PaygateType paygateType) {
        this.desc = desc;
        this.paygateType = paygateType;
    }

    public String getDesc() {
        return desc;
    }

    public PaygateType getPaygateType() {
        return paygateType;
    }
}
