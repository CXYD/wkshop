package net.jeeshop.biz.finance.bean;

import net.jeeshop.biz.finance.enums.PaymentResult;

/**
 * @author dylan
 * @date 16/3/8 22:07
 * Email: dinguangx@163.com
 */
public class PaymentResultBean {
    private String resCode;//支付响应码
    private String resMsg;//响应信息
    private String requestNum;//请求支付号
    private Long memberId;//会员ID
    private double amount;//支付金额
    private double waitPayAmount;//等待支付金额
    private String paymentNum;//支付流水号
    private PaymentResult paymentResult;//是否支付成功
    private String payUrl;//支付URL

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(String requestNum) {
        this.requestNum = requestNum;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getWaitPayAmount() {
        return waitPayAmount;
    }

    public void setWaitPayAmount(double waitPayAmount) {
        this.waitPayAmount = waitPayAmount;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public PaymentResult getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
