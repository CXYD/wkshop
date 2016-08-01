package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import java.util.Date;
import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.finance.enums.PaymentStatus;
import net.jeeshop.biz.finance.enums.PaymentTypeCode;

public class PaymentItem extends BaseModel implements Serializable {
    /** 支付方式 */
    private Long paymentTypeId;

    /** 支付信息ID */
    private Long paymentId;

    /** 支付方式代码 */
    private PaymentTypeCode paymentType;

    /** 支付编码 */
    private String paymentNum;

    /** 支付状态 */
    private PaymentStatus paymentStatus;

    /** 金额 */
    private Double amount;

    /** 会员ID */
    private Long memberId;

    /** 支付响应号 */
    private String responseNum;

    /** 响应时间 */
    private Date responseTime;

    /** 支付完成时间 */
    private Date paymentCompleteDate;

    /** 已退金额 */
    private Double refundedAmount;

    private static final long serialVersionUID = 1L;

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentTypeCode getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeCode paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum == null ? null : paymentNum.trim();
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getResponseNum() {
        return responseNum;
    }

    public void setResponseNum(String responseNum) {
        this.responseNum = responseNum == null ? null : responseNum.trim();
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Date getPaymentCompleteDate() {
        return paymentCompleteDate;
    }

    public void setPaymentCompleteDate(Date paymentCompleteDate) {
        this.paymentCompleteDate = paymentCompleteDate;
    }

    public Double getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Double refundedAmount) {
        this.refundedAmount = refundedAmount;
    }
}