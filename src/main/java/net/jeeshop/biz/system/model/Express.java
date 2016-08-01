package net.jeeshop.biz.system.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class Express extends BaseModel implements Serializable {
    /** é…�é€�æ–¹å¼�ä»£ç � */
    private String expressCode;

    /** é…�é€�æ–¹å¼�å��ç§° */
    private String expressName;

    /** é…�é€�è´¹ç”¨ */
    private Double fee;

    /** é¡ºåº� */
    private Integer ordinal;

    private static final long serialVersionUID = 1L;

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}