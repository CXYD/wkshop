package net.jeeshop.biz.cms.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class HotQuery extends BaseModel implements Serializable {
    /** 查询关键字 */
    private String keywork;

    /** 链接地址 */
    private String url;

    /** 是否有效,1-有效,0-无效 */
    private Boolean isValid;

    private static final long serialVersionUID = 1L;

    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(String keywork) {
        this.keywork = keywork == null ? null : keywork.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}