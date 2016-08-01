package net.jeeshop.biz.shop.model;


/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class BuySet {
    private Integer id;
    private String record;
    private String feedback;
    private String moreproduct;
    private String detail;
    private String loginbuy;
    private String khid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getMoreproduct() {
        return moreproduct;
    }

    public void setMoreproduct(String moreproduct) {
        this.moreproduct = moreproduct;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLoginbuy() {
        return loginbuy;
    }

    public void setLoginbuy(String loginbuy) {
        this.loginbuy = loginbuy;
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid;
    }

    public BuySet() {
    }

    public BuySet(String record, String feedback, String moreproduct, String detail, String loginbuy) {
        this.record = record;
        this.feedback = feedback;
        this.moreproduct = moreproduct;
        this.detail = detail;
        this.loginbuy = loginbuy;
    }

    @Override
    public String toString() {
        return "BuySet{" +
                "id=" + id +
                ", record='" + record + '\'' +
                ", feedback='" + feedback + '\'' +
                ", moreproduct='" + moreproduct + '\'' +
                ", detail='" + detail + '\'' +
                ", loginbuy='" + loginbuy + '\'' +
                ", khid='" + khid + '\'' +
                '}';
    }
}
