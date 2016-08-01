package net.jeeshop.biz.finance.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class PointsAccountHistory extends BaseModel implements Serializable {
    /** 积分账户ID */
    private Long pointsAccountId;

    /** 会员ID */
    private Long memberId;

    /** 操作类型 */
    private String operType;

    /** 数量 */
    private Double amount;

    /** 变动后账户积分余额 */
    private Double postBalanceAmount;

    /** 变动后冻结金额 */
    private Double postFrozenAmount;

    /** 备注 */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getPointsAccountId() {
        return pointsAccountId;
    }

    public void setPointsAccountId(Long pointsAccountId) {
        this.pointsAccountId = pointsAccountId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPostBalanceAmount() {
        return postBalanceAmount;
    }

    public void setPostBalanceAmount(Double postBalanceAmount) {
        this.postBalanceAmount = postBalanceAmount;
    }

    public Double getPostFrozenAmount() {
        return postFrozenAmount;
    }

    public void setPostFrozenAmount(Double postFrozenAmount) {
        this.postFrozenAmount = postFrozenAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}