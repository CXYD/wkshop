package net.jeeshop.biz.order.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class MemberFavorite extends BaseModel implements Serializable {
    /** 会员ID */
    private Long memberId;

    /** 商品ID */
    private Long productId;

    /** 是否有效 ，1-是0-否 */
    private String isValid;

    private static final long serialVersionUID = 1L;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}