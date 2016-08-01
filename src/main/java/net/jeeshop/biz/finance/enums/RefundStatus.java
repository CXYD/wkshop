package net.jeeshop.biz.finance.enums;

/**
 * @author dylan
 * @date 16/3/8 21:43
 * Email: dinguangx@163.com
 */
public enum RefundStatus {
    NONE("未退款"), INIT("新建"), PROCESSING("处理中"), PARTLY_REFUND("部分退款"), REFUNDED("已退款");
    private String desc;
    RefundStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
