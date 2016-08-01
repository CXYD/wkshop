package net.jeeshop.biz.finance.paygate;

/**
 * @author dylan
 * @date 16/3/13 16:17
 * Email: dinguangx@163.com
 */
public class PaygateProperties {
    private String bgNotifyUrl;//后台通知地址
    private String fgNotifyUrl;//前台通知地址

    public String getBgNotifyUrl() {
        return bgNotifyUrl;
    }

    public void setBgNotifyUrl(String bgNotifyUrl) {
        this.bgNotifyUrl = bgNotifyUrl;
    }

    public String getFgNotifyUrl() {
        return fgNotifyUrl;
    }

    public void setFgNotifyUrl(String fgNotifyUrl) {
        this.fgNotifyUrl = fgNotifyUrl;
    }
}
