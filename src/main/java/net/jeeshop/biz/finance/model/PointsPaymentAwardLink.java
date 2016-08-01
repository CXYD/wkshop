package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class PointsPaymentAwardLink extends BaseModel implements Serializable {
    /** 获取记录ID */
    private Long pointsAwardId;

    /** 支付记录ID */
    private Long pointsPaymentId;

    /** 数量 */
    private Double amount;

    private static final long serialVersionUID = 1L;

    public Long getPointsAwardId() {
        return pointsAwardId;
    }

    public void setPointsAwardId(Long pointsAwardId) {
        this.pointsAwardId = pointsAwardId;
    }

    public Long getPointsPaymentId() {
        return pointsPaymentId;
    }

    public void setPointsPaymentId(Long pointsPaymentId) {
        this.pointsPaymentId = pointsPaymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}