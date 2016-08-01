package net.jeeshop.biz.member.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class MemberRank extends BaseModel implements Serializable {
    /** 等级名称 */
    private String rankName;

    /** 等级代码 */
    private String rankCode;

    /** 积分下限(含） */
    private Integer minScore;

    /** 积分上限(不含) */
    private Integer maxScore;

    /** 备注 */
    private String remark;

    /** 是否有效,1-是0-否 */
    private String isValid;

    private static final long serialVersionUID = 1L;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode == null ? null : rankCode.trim();
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}