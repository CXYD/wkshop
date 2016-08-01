package net.jeeshop.biz.finance.paygate.alipay;

/**
 * 支付宝服务接口名称
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public enum AlipayServiceName {
    /**
     * 交易类型,单笔支付
     */
    TT_SINGLE_PAYMENT("create_direct_pay_by_user"),
    /**
     * 结果通过校验
     */
    TT_NOTIFY_VERIFY("notify_verify"),
    /**
     * 交易类型,查询订单详情
     */
    TT_QUERY("single_trade_query"),
    /**
     * 交易类型,退款
     */
    TT_REFUND("refund_fastpay_by_platform_nopwd"),
    /**
     * 交易类型,退款查询
     */
    TT_REFUND_QUERY("refund_fastpay_query"),
    /**
     * 手机支付
     */
    TT_MOBILE_PAY("mobile.securitypay.pay"),

    /**
     * 合作商户签约
     */
    TT_PARTNER_SIGN("sign_protocol_with_partner"),
    /**
     * 合作商户签约查询
     */
    TT_PARTNER_SIGN_QUERY("query_customer_protocol"),
    /**
     * 合作商户解约
     */
    TT_PARTNER_UNSIGN("customer_unsign"),


    /**
     * CAE代扣
     */
    TT_AGENT_PAY("cae_charge_agent"),

    /**
     * 直接转账到支付宝账户
     */
    TT_TRANSFER_ACCOUNT("batch_trans_notify_no_pwd");

    private String name;

    private AlipayServiceName(String name) {
        this.name = name;
    }

    public String serviceName() {
        return this.name;
    }
}