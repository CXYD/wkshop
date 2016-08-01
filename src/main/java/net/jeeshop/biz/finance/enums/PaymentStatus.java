package net.jeeshop.biz.finance.enums;

/**
 * @author dylan
 * @date 16/3/8 21:43
 * Email: dinguangx@163.com
 */
public enum PaymentStatus {
    INIT("新建"), PROCESSING("处理中"), PARTLY_FROZEN("部分冻结"), FROZEN("已冻结"), PAID("已支付"), CLOSED("已关闭");
    private String desc;
    PaymentStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
