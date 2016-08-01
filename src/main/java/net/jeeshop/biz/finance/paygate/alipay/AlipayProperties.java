package net.jeeshop.biz.finance.paygate.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author dylan
 * @date 16/3/13 15:17
 * Email: dinguangx@163.com
 */
@Component
public class AlipayProperties {
    @Value("${alipay.partner}")
    private String partner;
    private String signType = AlipayCore.SIGN_TYPE_MD5;
    @Value("${alipay.requestUrl}")
    private String requestUrl;
    @Value("${alipay.sellerEmail}")
    private String sellerEmail;
    @Value("${alipay.signKey}")
    private String signKey;//私钥

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSignType() {
        return signType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
