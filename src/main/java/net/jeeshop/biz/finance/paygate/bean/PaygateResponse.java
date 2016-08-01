package net.jeeshop.biz.finance.paygate.bean;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author dylan
 * @date 16/3/13 15:20
 * Email: dinguangx@163.com
 */
public class PaygateResponse {
    private String payUrl;
    private Map<String, String> extraInfos = Maps.newHashMap();

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public Map<String, String> getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(Map<String, String> extraInfos) {
        this.extraInfos = extraInfos;
    }
}
