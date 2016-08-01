package net.jeeshop.biz.order.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class Balance extends BaseModel implements Serializable {
    /** ba_balance.id */
    private Long id;

    /** 订单号 */
    private String orderno;

    /** 时间 */
    private Date baldate;

    /** 收入金额 */
    private Double incomeamount;

    /** 支出金额 */
    private Double payamount;

    /** 结算金额 */
    private Double balamount;

    /** 0未结算,1已结算 */
    private String balstatus;

    /** 结算人 */
    private String balman;

    /** 备注 */
    private String remark;

    private Date endTime;

    private String khid;

    public String getKhid() {
        return khid;
    }
    public void setKhid(String khid) {
        this.khid = khid;
    }
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Date getBaldate() {
        return baldate;
    }

    public void setBaldate(Date baldate) {
        this.baldate = baldate;
    }

    public Double getIncomeamount() {
        return incomeamount;
    }

    public void setIncomeamount(Double incomeamount) {
        this.incomeamount = incomeamount;
    }

    public Double getPayamount() {
        return payamount;
    }

    public void setPayamount(Double payamount) {
        this.payamount = payamount;
    }

    public Double getBalamount() {
        return balamount;
    }

    public void setBalamount(Double balamount) {
        this.balamount = balamount;
    }

    public String getBalstatus() {
        return balstatus;
    }

    public void setBalstatus(String balstatus) {
        this.balstatus = balstatus;
    }

    public String getBalman() {
        return balman;
    }

    public void setBalman(String balman) {
        this.balman = balman == null ? null : balman.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
