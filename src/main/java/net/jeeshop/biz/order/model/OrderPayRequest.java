package net.jeeshop.biz.order.model;

import java.io.Serializable;
import java.util.Date;
import net.jeeshop.biz.base.model.BaseModel;

public class OrderPayRequest extends BaseModel implements Serializable {
    /** 会员ID */
    private Long memberId;

    /** 订单编号 */
    private String orderNum;

    /** 订单ID */
    private Long orderId;

    /** 支付请求号 */
    private String requestNum;

    /** 订单总金额 */
    private Double amount;

    /** 支付状态 */
    private String payStatus;

    /** 支付方式(支付宝，积分等) */
    private String paymentType;

    /** 支付流水号 */
    private String paymentNum;

    /** 支付时间 */
    private Date paymentTime;

    /** 标题 */
    private String title;

    /** 支付记录ID */
    private Long orderPayId;

    private static final long serialVersionUID = 1L;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
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
        this.requestNum = requestNum == null ? null : requestNum.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum == null ? null : paymentNum.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(Long orderPayId) {
        this.orderPayId = orderPayId;
    }
}