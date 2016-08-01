package net.jeeshop.biz.product.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;

public class PackageInfo extends BaseModel implements Serializable {
    /** package_info.name */
    private String name;

    /** package_info.status */
    private String status;

    /** package_info.note */
    private String note;

    /** package_info.package_spec_id */
    private String packageSpecId;

    /** package_info.khid */
    private String khid;

    /** package_info.spec_array */
    private String specArray;

    /** 已选规格 */
    private String selectArray;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPackageSpecId() {
        return packageSpecId;
    }

    public void setPackageSpecId(String packageSpecId) {
        this.packageSpecId = packageSpecId == null ? null : packageSpecId.trim();
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public String getSpecArray() {
        return specArray;
    }

    public void setSpecArray(String specArray) {
        this.specArray = specArray == null ? null : specArray.trim();
    }

    public String getSelectArray() {
        return selectArray;
    }

    public void setSelectArray(String selectArray) {
        this.selectArray = selectArray == null ? null : selectArray.trim();
    }
}