package net.jeeshop.biz.shop.client;

import net.jeeshop.biz.shop.model.PayAccount;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public interface PayAccountMapper {
    void addAccount(PayAccount payAccount);

    PayAccount getAccount(String khid);

    void upAccount(PayAccount payAccount);
}
