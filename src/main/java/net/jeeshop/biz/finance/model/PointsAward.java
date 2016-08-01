package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class PointsAward extends BaseModel implements Serializable {
    /** 会员ID */
    private Long memberId;

    /** 积分账户ID */
    private Long pointsAccountId;

    /** 获取积分额 */
    private Double awardAmount;

    /** 积分余额 */
    private Double balanceAmount;

    /** 冻结积分 */
    private Double frozenAmount;

    private static final long serialVersionUID = 1L;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPointsAccountId() {
        return pointsAccountId;
    }

    public void setPointsAccountId(Long pointsAccountId) {
        this.pointsAccountId = pointsAccountId;
    }

    public Double getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Double awardAmount) {
        this.awardAmount = awardAmount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
}