package net.jeeshop.biz.data.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;

public class NuberInfo extends BaseModel implements Serializable {

    /** nuberinfo.phonenum */
    private String phonenum;

    /** nuberinfo.busstype */
    private String busstype;

    /** 批次编码 */
    private int numberid;

    /** 0正常 1失效 2占用 3购买 */
    private String state;

    /** 占用token */
    private String usetoken;

    /** 0老号 1新号 */
    private String numtype;

    /** 卡类型 */
    private String cardtype;

    /** 批次名 */
    private String batchname;

    /** nuberinfo.productid */
    private String productid;

    /** nuberinfo.khid */
    private String khid;

    private static final long serialVersionUID = 1L;



    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getBusstype() {
        return busstype;
    }

    public void setBusstype(String busstype) {
        this.busstype = busstype == null ? null : busstype.trim();
    }

    public int getNumberid() {
        return numberid;
    }

    public void setNumberid(int numberid) {
        this.numberid = numberid ;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getUsetoken() {
        return usetoken;
    }

    public void setUsetoken(String usetoken) {
        this.usetoken = usetoken == null ? null : usetoken.trim();
    }

    public String getNumtype() {
        return numtype;
    }

    public void setNumtype(String numtype) {
        this.numtype = numtype == null ? null : numtype.trim();
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname == null ? null : batchname.trim();
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid;
    }
}