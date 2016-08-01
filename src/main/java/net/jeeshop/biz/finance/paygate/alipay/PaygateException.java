package net.jeeshop.biz.finance.paygate.alipay;

/**
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class PaygateException extends RuntimeException{
    public PaygateException(){

    }
    public PaygateException(String msg) {
        super(msg);
    }
    public PaygateException(String msg, Throwable ex) {
        super(msg , ex);
    }
}
