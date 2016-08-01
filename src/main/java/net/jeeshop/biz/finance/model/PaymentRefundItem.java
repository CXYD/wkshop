package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.finance.enums.PaymentTypeCode;
import net.jeeshop.biz.finance.enums.RefundStatus;

public class PaymentRefundItem extends BaseModel implements Serializable {
    /** 退款ID */
    private Long paymentRefundId;

    /** 原支付信息ID */
    private Long paymentId;

    /** 原支付明细ID */
    private Long paymentItemId;

    /** 支付方式代码 */
    private PaymentTypeCode paymentType;

    /** 金额 */
    private Double amount;

    /** 退款状态 */
    private RefundStatus refundStatus;

    /** 会员ID */
    private Long memberId;

    /** 备注 */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getPaymentRefundId() {
        return paymentRefundId;
    }

    public void setPaymentRefundId(Long paymentRefundId) {
        this.paymentRefundId = paymentRefundId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentItemId() {
        return paymentItemId;
    }

    public void setPaymentItemId(Long paymentItemId) {
        this.paymentItemId = paymentItemId;
    }

    public PaymentTypeCode getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeCode paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}