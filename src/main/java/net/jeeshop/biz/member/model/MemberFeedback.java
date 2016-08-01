package net.jeeshop.biz.member.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class MemberFeedback extends BaseModel implements Serializable {
    /** 会员ID */
    private Long memberId;

    /** 反馈类型(数据字典) */
    private String feedbackType;

    /** 反馈内容 */
    private String content;

    /** 是否已经回复,1-是0-否 */
    private Boolean isReply;

    private static final long serialVersionUID = 1L;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType == null ? null : feedbackType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getIsReply() {
        return isReply;
    }

    public void setIsReply(Boolean isReply) {
        this.isReply = isReply;
    }
}