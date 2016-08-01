package net.jeeshop.biz.order.bean;

import net.jeeshop.biz.order.model.Order;

import java.util.Date;

/**
 * Created by pengdong on 2016/6/6.
 */
public class OrderBean extends Order {
    private String productName;
    /** 单价 */
    private Double price;
    /** 数量 */
    private Integer quantity;

    private String settlestatus;

    private double balamount;

    private Date endTime;

    private double cardprice;

    private double firstfee;

    private String issales;

    private String newmob;

    private String oldmob;

    public String getNewmob() {
        return newmob;
    }

    public void setNewmob(String newmob) {
        this.newmob = newmob;
    }

    public String getOldmob() {
        return oldmob;
    }

    public void setOldmob(String oldmob) {
        this.oldmob = oldmob;
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
        this.issales = issales;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getBalamount() {
        return balamount;
    }

    public void setBalamount(double balamount) {
        this.balamount = balamount;
    }

    public String getSettlestatus() {
        return settlestatus;
    }

    public void setSettlestatus(String settlestatus) {
        this.settlestatus = settlestatus;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

//    @Override
//    public String toString() {
//        return "OrderBean{" +
//                "productName='" + productName + '\'' +
//                ", price=" + price +
//                ", quantity=" + quantity +
//                ", settlestatus='" + settlestatus + '\'' +
//                ", balamount=" + balamount +
//                ", endTime=" + endTime +
//                ", cardprice=" + cardprice +
//                ", firstfee=" + firstfee +
//                ", issales='" + issales + '\'' +
//                '}';
//    }
}
