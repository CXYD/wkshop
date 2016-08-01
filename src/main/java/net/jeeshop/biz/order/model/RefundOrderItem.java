package net.jeeshop.biz.order.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class RefundOrderItem extends BaseModel implements Serializable {
    /** 订单ID */
    private Long orderId;

    /** 退单ID */
    private Long refundOrderId;

    /** 订单项ID */
    private Long orderItemId;

    /** 会员ID */
    private Long memberId;

    /** 商品ID */
    private Long productId;

    /** 数量 */
    private Integer quantity;

    /** 金额 */
    private Double amount;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(Long refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}