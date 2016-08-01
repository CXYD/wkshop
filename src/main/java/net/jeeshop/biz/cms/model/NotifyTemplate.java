package net.jeeshop.biz.cms.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class NotifyTemplate extends BaseModel implements Serializable {
    /** 模板类型，email-邮件消息，message-站内信, sms-短信 */
    private String tplType;

    /** 模板名称 */
    private String tplName;

    /** 模板标识 */
    private String tplKey;

    /** 模板HTML */
    private String content;

    /** 有效状态,1-是0-否 */
    private String validStatus;

    /** 模板内容 */
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getTplType() {
        return tplType;
    }

    public void setTplType(String tplType) {
        this.tplType = tplType == null ? null : tplType.trim();
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName == null ? null : tplName.trim();
    }

    public String getTplKey() {
        return tplKey;
    }

    public void setTplKey(String tplKey) {
        this.tplKey = tplKey == null ? null : tplKey.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}