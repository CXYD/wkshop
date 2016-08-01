package net.jeeshop.biz.shop.model;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class PayAccount {
    private Integer id;
    private String payname;
    private String paybank;
    private String payaccount;
    private String createdate;
    private String khid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getPaybank() {
        return paybank;
    }

    public void setPaybank(String paybank) {
        this.paybank = paybank;
    }

    public String getPayaccount() {
        return payaccount;
    }

    public void setPayaccount(String payaccount) {
        this.payaccount = payaccount;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid;
    }

    public PayAccount() {
    }

    public PayAccount(String payname, String paybank, String payaccount) {
        this.payname = payname;
        this.paybank = paybank;
        this.payaccount = payaccount;
    }
}
