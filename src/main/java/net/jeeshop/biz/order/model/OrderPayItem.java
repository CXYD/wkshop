package net.jeeshop.biz.order.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class OrderPayItem extends BaseModel implements Serializable {
    /** 支付方式(支付宝，积分等) */
    private String paymentType;

    /** 金额 */
    private Double amount;

    /** 支付明细信息 */
    private String detailMsg;

    /** 支付ID */
    private Long orderPayId;

    private static final long serialVersionUID = 1L;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg == null ? null : detailMsg.trim();
    }

    public Long getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(Long orderPayId) {
        this.orderPayId = orderPayId;
    }
}