package net.jeeshop.biz.product.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class PackageSpecInfo extends BaseModel implements Serializable {
    /** package_spec_info.package_id */
    private String packageId;

    /** package_spec_info.spec_no */
    private String specNo;

    /** 合约期 */
    private String month;

    /** 赠送话费 */
    private String zeng;

    /** 返还话费 */
    private String returns;

    /** 通话时长 */
    private String times;

    /** 短信条数 */
    private String sms;

    /** 网络流量 */
    private String folw;

    /** package_spec_info.spec_array */
    private String specArray;

    private static final long serialVersionUID = 1L;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId == null ? null : packageId.trim();
    }

    public String getSpecNo() {
        return specNo;
    }

    public void setSpecNo(String specNo) {
        this.specNo = specNo == null ? null : specNo.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getZeng() {
        return zeng;
    }

    public void setZeng(String zeng) {
        this.zeng = zeng == null ? null : zeng.trim();
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns == null ? null : returns.trim();
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms == null ? null : sms.trim();
    }

    public String getFolw() {
        return folw;
    }

    public void setFolw(String folw) {
        this.folw = folw == null ? null : folw.trim();
    }

    public String getSpecArray() {
        return specArray;
    }

    public void setSpecArray(String specArray) {
        this.specArray = specArray == null ? null : specArray.trim();
    }
}