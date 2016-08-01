package net.jeeshop.biz.order.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;

public class OrderItem extends BaseModel implements Serializable {
    /** 会员ID */
    private Long memberId;

    /** 商品ID */
    private Long productId;

    /** 货物编号 */
    private Long goodsid;

    /** 单价 */
    private Double price;

    /** 数量 */
    private Integer quantity;

    /** 金额 */
    private Double amount;

    /** 订单ID */
    private Long orderId;

    /** 老号码 */
    private String oldmob;

    /** 号卡金额 */
    private Double cardprice;

    /** 首月资费 */
    private Double firstfee;

    /** 是否促销 */
    private String issales;

    /** ord_order_item.product_name */
    private String productName;

    /** 新号码 */
    private String newmob;

    private static final long serialVersionUID = 1L;

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

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOldmob() {
        return oldmob;
    }

    public void setOldmob(String oldmob) {
        this.oldmob = oldmob == null ? null : oldmob.trim();
    }

    public Double getCardprice() {
        return cardprice;
    }

    public void setCardprice(Double cardprice) {
        this.cardprice = cardprice;
    }

    public Double getFirstfee() {
        return firstfee;
    }

    public void setFirstfee(Double firstfee) {
        this.firstfee = firstfee;
    }

    public String getIssales() {
        return issales;
    }

    public void setIssales(String issales) {
        this.issales = issales == null ? null : issales.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getNewmob() {
        return newmob;
    }

    public void setNewmob(String newmob) {
        this.newmob = newmob == null ? null : newmob.trim();
    }
}