package net.jeeshop.biz.finance.paygate.bean;

/**
 * @author dylan
 * @date 16/3/13 12:26
 * Email: dinguangx@163.com
 */
public class PayResponseBean {
    private String orderNum;
    private String payUrl;
    private boolean payCompleted = false;
    private double payAmount;//支付成功金额
    private String payNum;
    private boolean waitPay = true;//是否等待支付，CAE代扣直接支付完成

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public boolean isPayCompleted() {
        return payCompleted;
    }

    public void setPayCompleted(boolean payCompleted) {
        this.payCompleted = payCompleted;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public boolean isWaitPay() {
        return waitPay;
    }

    public void setWaitPay(boolean waitPay) {
        this.waitPay = waitPay;
    }
}
