package net.jeeshop.biz.finance.paygate.alipay.model;

/**
 * 退款返回结果信息
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class RefundResultModel {
    private String traceNum;
    /**
     * 请求退款金额
     **/
    private long amount;
    private String transRetMsg;

    private String sellerEmail;
    private String sellerId;
    /**
     * 实际退款金额
     **/
    private long refundAmount;
    private String refundRetMsg;


    public String getTraceNum() {
        return traceNum;
    }

    public void setTraceNum(String traceNum) {
        this.traceNum = traceNum;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTransRetMsg() {
        return transRetMsg;
    }

    public void setTransRetMsg(String transRetMsg) {
        this.transRetMsg = transRetMsg;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundRetMsg() {
        return refundRetMsg;
    }

    public void setRefundRetMsg(String refundRetMsg) {
        this.refundRetMsg = refundRetMsg;
    }


}
