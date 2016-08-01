package net.jeeshop.biz.product.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class ProductLabel extends BaseModel implements Serializable {
    /** pro_label.label_id */
    private Long labelId;

    /** pro_label.name */
    private String name;

    /** pro_label.createtime */
    private Date createtime;

    /** pro_label.order1 */
    private Integer order1;

    /** pro_label.khdid */
    private String khdid;

    /** pro_label.status */
    private String status;

    private static final long serialVersionUID = 1L;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOrder1() {
        return order1;
    }

    public void setOrder1(Integer order1) {
        this.order1 = order1;
    }

    public String getKhdid() {
        return khdid;
    }

    public void setKhdid(String khdid) {
        this.khdid = khdid == null ? null : khdid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}