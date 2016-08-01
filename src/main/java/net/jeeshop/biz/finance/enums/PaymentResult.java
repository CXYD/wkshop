package net.jeeshop.biz.finance.enums;

/**
 * 支付结果
 * @author dylan
 * @date 16/3/8 22:29
 * Email: dinguangx@163.com
 */
public enum PaymentResult {
    SUCCESS("成功"), FAIL("失败"), WAIT_PAY("等待用户支付");
    private String desc;
    PaymentResult(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

