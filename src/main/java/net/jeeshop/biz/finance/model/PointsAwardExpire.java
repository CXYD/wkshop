package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import java.util.Date;
import net.jeeshop.biz.base.model.BaseModel;

public class PointsAwardExpire extends BaseModel implements Serializable {
    /** 获取记录ID */
    private Long pointsAwardId;

    /** 会员ID */
    private Long memberId;

    /** 过期数量 */
    private Double expireAmount;

    /** 过期时间 */
    private Date expireTime;

    /** 备注 */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getPointsAwardId() {
        return pointsAwardId;
    }

    public void setPointsAwardId(Long pointsAwardId) {
        this.pointsAwardId = pointsAwardId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Double getExpireAmount() {
        return expireAmount;
    }

    public void setExpireAmount(Double expireAmount) {
        this.expireAmount = expireAmount;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}