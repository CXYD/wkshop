package net.jeeshop.biz.finance.bean;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author dylan
 * @date 16/3/8 21:42
 * Email: dinguangx@163.com
 */
public class PaymentBean {
    /** 会员ID */
    private Long memberId;

    /** 相关的订单号 */
    private Long orderId;

    /** 支付请求号 */
    private String requestNum;

    /** 金额 */
    private double amount;

    /** 备注 */
    private String remark;
    /** 支付明细 */
    private List<PaymentItemBean> paymentItems = Lists.newArrayList();

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(String requestNum) {
        this.requestNum = requestNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PaymentItemBean> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<PaymentItemBean> paymentItems) {
        this.paymentItems = paymentItems;
    }
}
